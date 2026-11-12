import { SupportedLanguage } from '../types';

export type VoiceEngine = 'ai' | 'browser';

// Native greeting phrases for instant local playback across all 8 supported Indian languages
export const NATIVE_GREETING_PHRASES: Record<SupportedLanguage, string> = {
  hi: 'नमस्ते! सहाय-क्रेडिट में आपका स्वागत है। बोलकर सुनने की सुविधा चालू है।',
  ta: 'வணக்கம்! சஹாய் கிரெடிட்டிற்கு உங்களை அன்புடன் வரவேற்கிறோம்.',
  te: 'నమస్కారం! సహాయ్ క్రెడిట్ కి స్వాగతం. వాయిస్ గైడ్ సిద్ధంగా ఉంది.',
  mr: 'नमस्कार! सहाय क्रेडिट मध्ये आपले स्वागत आहे. आवाज मार्गदर्शन सुरू आहे.',
  bn: 'নমস্কার! সহায় ক্রেডিটে আপনাকে স্বাগত। অডিও নির্দেশিকা প্রস্তুত।',
  kn: 'ನಮಸ್ಕಾರ! ಸಹಾಯ್ ಕ್ರೆಡಿಟ್‌ಗೆ ಸುಸ್ವಾಗತ. ಧ್ವನಿ ಮಾರ್ಗದರ್ಶಿ ಸಿದ್ಧವಾಗಿದೆ.',
  gu: 'નમસ્તે! સહાય ક્રેડિટમાં આપનું સ્વાગત છે. અવાજ સહાય તૈયાર છે.',
  en: 'Welcome to SahayCredit AI. Voice assistance is active in English.',
};

// Fallback phonetic pronunciations for offline browsers lacking Indian language packs
const PHONETIC_GREETINGS: Record<SupportedLanguage, string> = {
  hi: 'Namaste! Sahay-Credit audio pranaali mein aapka swagat hai. Yeh aawaz bilkul saaf aur spasht sunai de rahi hai.',
  ta: 'Vanakkam! Sahay Credit oli vazhikaatti. Ungalukku nandraaga ketkiradhu.',
  te: 'Namaskaram! Sahay Credit audio guide. Mee spikarla dwara spashtamga vinapadutondi.',
  mr: 'Namaskar! Sahay Credit aawaz margadarshan suru aahe. Aawaz spasht aikoo yet aahe.',
  bn: 'Nomoshkar! Sahay Credit audio nirdeshika. Aawaz shundor o spashto shunthe pachhen.',
  kn: 'Namaskara! Sahay Credit dhwani margadarshi. Spashtavagi kelisuttide.',
  gu: 'Namaste! Sahay Credit aavaj sahay. Aa aavaj bilkul saaf sambhalay chhe.',
  en: 'Welcome! SahayCredit audio system is loud, clear, and working properly.',
};

class AudioService {
  private currentAudio: HTMLAudioElement | null = null;
  private isSpeaking = false;
  private currentUtterance: SpeechSynthesisUtterance | null = null;
  private listeners: Set<(speaking: boolean, text: string) => void> = new Set();
  private currentText = '';
  // Default to AI High-Definition Multilingual Voice for authentic regional Indian accents
  private voiceEngine: VoiceEngine = 'ai';
  private cachedVoices: SpeechSynthesisVoice[] = [];
  private activeAbortController: AbortController | null = null;
  private ttsCache: Map<string, string> = new Map();
  private audioCtx: AudioContext | null = null;
  private isAudioUnlocked = false;

  constructor() {
    if (typeof window !== 'undefined') {
      // Auto-unlock audio context on first user interaction
      const unlock = () => {
        this.unlockAudioContext();
        window.removeEventListener('click', unlock);
        window.removeEventListener('touchstart', unlock);
        window.removeEventListener('keydown', unlock);
      };
      window.addEventListener('click', unlock, { passive: true });
      window.addEventListener('touchstart', unlock, { passive: true });
      window.addEventListener('keydown', unlock, { passive: true });

      if ('speechSynthesis' in window) {
        this.refreshVoices();
        window.speechSynthesis.onvoiceschanged = () => {
          this.refreshVoices();
        };
      }

      // Preload native audio greetings for all 8 languages in the background
      setTimeout(() => {
        this.preloadCommonPhrases();
      }, 800);
    }
  }

  /**
   * Lazily initializes and unlocks a singleton Web Audio AudioContext
   */
  private getAudioContext(): AudioContext | null {
    if (typeof window === 'undefined') return null;
    try {
      const AudioContextClass = window.AudioContext || (window as any).webkitAudioContext;
      if (!AudioContextClass) return null;
      if (!this.audioCtx || this.audioCtx.state === 'closed') {
        this.audioCtx = new AudioContextClass();
      }
      if (this.audioCtx.state === 'suspended') {
        this.audioCtx.resume();
      }
      return this.audioCtx;
    } catch (e) {
      console.warn('AudioContext init error:', e);
      return null;
    }
  }

  public unlockAudioContext() {
    if (this.isAudioUnlocked) return;
    const ctx = this.getAudioContext();
    if (ctx && ctx.state === 'suspended') {
      ctx.resume().then(() => {
        this.isAudioUnlocked = true;
      });
    } else if (ctx) {
      this.isAudioUnlocked = true;
    }
  }

  /**
   * Background preloader: caches the greeting audio for each language so playback is instant
   */
  public async preloadCommonPhrases() {
    const languages: SupportedLanguage[] = ['hi', 'ta', 'te', 'mr', 'bn', 'kn', 'gu', 'en'];
    for (const lang of languages) {
      const text = NATIVE_GREETING_PHRASES[lang];
      const cacheKey = `${lang}:${text.trim()}`;
      if (!this.ttsCache.has(cacheKey)) {
        try {
          const res = await fetch('/api/gemini/tts', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
              text: text.slice(0, 300),
              language: lang,
              voiceName: 'Kore',
            }),
          });
          const data = await res.json();
          if (data.success && data.audioBase64) {
            this.ttsCache.set(cacheKey, data.audioBase64);
          }
        } catch {
          // ignore background preload errors
        }
      }
    }
  }

  private refreshVoices() {
    if (typeof window !== 'undefined' && 'speechSynthesis' in window) {
      try {
        const v = window.speechSynthesis.getVoices();
        if (v && v.length > 0) {
          this.cachedVoices = v;
        }
      } catch (e) {
        console.warn('getVoices error:', e);
      }
    }
  }

  public subscribe(callback: (speaking: boolean, text: string) => void): () => void {
    this.listeners.add(callback);
    return () => {
      this.listeners.delete(callback);
    };
  }

  private notify(speaking: boolean, text: string) {
    this.isSpeaking = speaking;
    this.currentText = speaking ? text : '';
    this.listeners.forEach((cb) => {
      try {
        cb(speaking, text);
      } catch (err) {
        console.warn('Audio listener error:', err);
      }
    });
  }

  public getSpeakingState() {
    return {
      isSpeaking: this.isSpeaking,
      currentText: this.currentText,
      voiceEngine: this.voiceEngine,
    };
  }

  public setVoiceEngine(engine: VoiceEngine) {
    this.voiceEngine = engine;
  }

  public getVoiceEngine(): VoiceEngine {
    return this.voiceEngine;
  }

  /**
   * Play an audible acoustic chime using Web Audio API (Guaranteed real sound output)
   */
  public playChime(freq = 523.25, isMajorChord = true) {
    try {
      const ctx = this.getAudioContext();
      if (!ctx) return;

      const now = ctx.currentTime;

      // Note 1
      const osc1 = ctx.createOscillator();
      const gain1 = ctx.createGain();
      osc1.type = 'triangle';
      osc1.frequency.setValueAtTime(freq, now);
      gain1.gain.setValueAtTime(0.22, now);
      gain1.gain.exponentialRampToValueAtTime(0.001, now + 0.28);
      osc1.connect(gain1);
      gain1.connect(ctx.destination);
      osc1.start(now);
      osc1.stop(now + 0.28);

      // Note 2 (Ascending harmonic harmony for pleasant confirmation)
      if (isMajorChord) {
        const osc2 = ctx.createOscillator();
        const gain2 = ctx.createGain();
        osc2.type = 'sine';
        osc2.frequency.setValueAtTime(freq * 1.25, now + 0.08); // Major 3rd
        osc2.frequency.exponentialRampToValueAtTime(freq * 1.5, now + 0.2); // 5th
        gain2.gain.setValueAtTime(0.001, now);
        gain2.gain.setValueAtTime(0.18, now + 0.08);
        gain2.gain.exponentialRampToValueAtTime(0.001, now + 0.35);
        osc2.connect(gain2);
        gain2.connect(ctx.destination);
        osc2.start(now + 0.08);
        osc2.stop(now + 0.35);
      }
    } catch (e) {
      console.warn('playChime error:', e);
    }
  }

  /**
   * Sound check test: plays melodic tone and speaks native test phrase in the selected language
   */
  public testSpeaker(lang: SupportedLanguage = 'hi') {
    this.stop();
    this.unlockAudioContext();
    this.playChime(659.25, true); // E5 chord

    const testPhrases: Record<SupportedLanguage, string> = {
      hi: 'नमस्ते! सहाय-क्रेडिट ऑडियो प्रणाली बिल्कुल सही और साफ काम कर रही है। आप किसी भी भाषा में सुन सकते हैं।',
      ta: 'வணக்கம்! சஹாய் கிரெடிட் ஒலி வழிகாட்டி தெளிவாக கேட்கிறது. அரசு திட்டங்களுக்கு விண்ணப்பிக்கலாம்.',
      te: 'నమస్కారం! సహాయ్ క్రెడిట్ ఆడియో స్పష్టంగా పనిచేస్తుంది. బ్యాంక్ రుణాలకు దరఖాస్తు చేసుకోవచ్చు.',
      mr: 'नमस्कार! सहाय क्रेडिट आवाज प्रणाली अगदी स्पष्ट सुरू आहे. शासकीय योजनांची माहिती ऐकू शकता.',
      bn: 'নমস্কার! সহায় ক্রেডিট অডিও ব্যবস্থা সঠিকভাবে কাজ করছে। আপনি স্পষ্টভাবে শুনতে পাচ্ছেন।',
      kn: 'ನಮಸ್ಕಾರ! ಸಹಾಯ್ ಕ್ರೆಡಿಟ್ ಧ್ವನಿ ವ್ಯವಸ್ಥೆ ಸ್ಪಷ್ಟವಾಗಿ ಕೇಳುತ್ತಿದೆ. ಸರ್ಕಾರಿ ಯೋಜನೆಗಳಿಗೆ ಅರ್ಜಿ ಸಲ್ಲಿಸಿ.',
      gu: 'નમસ્તે! સહાય ક્રેડિટ અવાજ પ્રણાલી બરાબર કામ કરી રહી છે. આપ બધી યોજનાઓ સાંભળી શકો છો.',
      en: 'Welcome! SahayCredit audio system is loud, clear, and working properly on your device.',
    };

    setTimeout(() => {
      this.speak(testPhrases[lang] || testPhrases.hi, lang, 'ai');
    }, 180);
  }

  /**
   * Instantly stops any active audio, speech, or in-flight requests
   */
  public stop() {
    if (this.activeAbortController) {
      this.activeAbortController.abort();
      this.activeAbortController = null;
    }

    if (this.currentAudio) {
      try {
        this.currentAudio.pause();
        this.currentAudio.currentTime = 0;
      } catch (e) {
        // ignore
      }
      this.currentAudio = null;
    }

    if (typeof window !== 'undefined' && 'speechSynthesis' in window) {
      try {
        window.speechSynthesis.cancel();
      } catch (e) {
        // ignore
      }
      this.currentUtterance = null;
    }

    this.notify(false, '');
  }

  /**
   * Speak text clearly in the target language (Native AI Speech default, 100% audible)
   */
  public async speak(
    text: string,
    language: SupportedLanguage = 'hi',
    forceEngine?: VoiceEngine
  ): Promise<void> {
    this.stop();
    if (!text || text.trim() === '') return;

    this.unlockAudioContext();
    this.notify(true, text);

    // Play instant pleasant chime to signal audio start and unlock speaker hardware
    this.playChime(523.25, true);

    const engine = forceEngine || this.voiceEngine;

    // 1. High-Definition Native AI Multilingual Voice (Default)
    if (engine === 'ai') {
      const cacheKey = `${language}:${text.trim()}`;
      const cachedBase64 = this.ttsCache.get(cacheKey);

      if (cachedBase64) {
        this.playBase64Audio(cachedBase64);
        return;
      }

      try {
        const controller = new AbortController();
        this.activeAbortController = controller;

        const response = await fetch('/api/gemini/tts', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          signal: controller.signal,
          body: JSON.stringify({
            text: text.slice(0, 450),
            language,
            voiceName: 'Kore',
          }),
        });

        const data = await response.json();
        if (data.success && data.audioBase64) {
          this.ttsCache.set(cacheKey, data.audioBase64);
          this.playBase64Audio(data.audioBase64);
          return;
        }
      } catch (err: any) {
        if (err?.name === 'AbortError') return;
        console.warn('Native AI voice unavailable, using browser speech fallback:', err);
      }
    }

    // 2. Browser Voice Fallback (Only if AI voice was offline or disabled)
    this.speakWithBrowser(text, language);
  }

  private playBase64Audio(base64: string) {
    try {
      const binaryString = window.atob(base64);
      const len = binaryString.length;
      const bytes = new Uint8Array(len);
      for (let i = 0; i < len; i++) {
        bytes[i] = binaryString.charCodeAt(i);
      }
      const blob = new Blob([bytes.buffer], { type: 'audio/wav' });
      const blobUrl = URL.createObjectURL(blob);

      const audio = new Audio(blobUrl);
      this.currentAudio = audio;
      audio.volume = 1.0;

      audio.onplay = () => {
        this.isSpeaking = true;
        this.notify(true, this.currentText);
      };

      audio.onended = () => {
        URL.revokeObjectURL(blobUrl);
        this.currentAudio = null;
        this.notify(false, '');
      };

      audio.onerror = (e) => {
        console.warn('HTML5 Audio playback error:', e);
        URL.revokeObjectURL(blobUrl);
        this.currentAudio = null;
        this.notify(false, '');
      };

      const playPromise = audio.play();
      if (playPromise !== undefined) {
        playPromise.catch((e) => {
          console.warn('HTML5 Audio play error:', e);
          this.notify(false, '');
        });
      }
    } catch (e) {
      console.warn('playBase64Audio error:', e);
      this.notify(false, '');
    }
  }

  private speakWithBrowser(text: string, language: SupportedLanguage) {
    if (typeof window === 'undefined' || !('speechSynthesis' in window)) {
      this.notify(false, '');
      return;
    }

    this.refreshVoices();

    const langCodeMap: Record<SupportedLanguage, string> = {
      en: 'en-IN',
      hi: 'hi-IN',
      ta: 'ta-IN',
      te: 'te-IN',
      mr: 'mr-IN',
      bn: 'bn-IN',
      kn: 'kn-IN',
      gu: 'gu-IN',
    };

    const targetLang = langCodeMap[language] || 'hi-IN';
    const voices =
      this.cachedVoices.length > 0 ? this.cachedVoices : window.speechSynthesis.getVoices();

    const langKeywords: Record<SupportedLanguage, string[]> = {
      hi: ['hi', 'hindi', 'हिन्दी', 'devanagari'],
      ta: ['ta', 'tamil', 'தமிழ்'],
      te: ['te', 'telugu', 'తెలుగు'],
      mr: ['mr', 'marathi', 'मराठी'],
      bn: ['bn', 'bengali', 'বাংলা'],
      kn: ['kn', 'kannada', 'ಕನ್ನಡ'],
      gu: ['gu', 'gujarati', 'ગુજરાતી'],
      en: ['en-in', 'india', 'indian', 'en-gb', 'en-us', 'en'],
    };

    const keywords = langKeywords[language] || ['hi'];

    let matchingVoice = voices.find((v) =>
      v.lang.toLowerCase() === targetLang.toLowerCase()
    );

    if (!matchingVoice) {
      matchingVoice = voices.find((v) =>
        keywords.some(
          (k) =>
            v.lang.toLowerCase().startsWith(k) ||
            v.name.toLowerCase().includes(k)
        )
      );
    }

    let textToSpeak = text;
    let spokenLangCode = targetLang;

    if (!matchingVoice && language !== 'en') {
      const indianVoice = voices.find((v) => v.lang.toLowerCase().includes('en-in') || v.name.toLowerCase().includes('india'));
      const defaultVoice = indianVoice || voices.find((v) => v.lang.toLowerCase().startsWith('en')) || voices[0];

      if (defaultVoice) {
        matchingVoice = defaultVoice;
        spokenLangCode = defaultVoice.lang || 'en-IN';
        const phoneticGreeting = PHONETIC_GREETINGS[language];
        if (phoneticGreeting && (text.length < 150 || text.includes('नमस्ते') || text.includes('வணக்கம்') || text.includes('నమస్కారం'))) {
          textToSpeak = phoneticGreeting;
        }
      }
    }

    const utterance = new SpeechSynthesisUtterance(textToSpeak);
    utterance.lang = spokenLangCode;
    utterance.volume = 1.0;
    utterance.rate = 0.92;
    utterance.pitch = 1.0;

    if (matchingVoice) {
      utterance.voice = matchingVoice;
    }

    utterance.onstart = () => {
      this.isSpeaking = true;
      this.notify(true, text);
    };

    utterance.onend = () => {
      this.notify(false, '');
      (window as any).__sahay_utterance = null;
    };

    utterance.onerror = (e) => {
      console.warn('SpeechSynthesis error:', e);
      this.notify(false, '');
      (window as any).__sahay_utterance = null;
    };

    this.currentUtterance = utterance;
    (window as any).__sahay_utterance = utterance;

    try {
      if (window.speechSynthesis.paused) {
        window.speechSynthesis.resume();
      }
      window.speechSynthesis.cancel();
    } catch {
      // ignore
    }

    setTimeout(() => {
      try {
        if (window.speechSynthesis.paused) {
          window.speechSynthesis.resume();
        }
        window.speechSynthesis.speak(utterance);
      } catch (err) {
        console.warn('Failed to call speech speak:', err);
        this.notify(false, '');
      }
    }, 35);
  }
}

export const audioService = new AudioService();
