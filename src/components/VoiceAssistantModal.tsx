import React, { useState } from 'react';
import { SupportedLanguage } from '../types';
import { TRANSLATIONS } from '../data/translations';
import { audioService } from '../services/audioService';
import {
  Mic,
  MicOff,
  X,
  Volume2,
  AlertTriangle,
  Sparkles,
  Send,
  Loader2,
  CheckCircle2,
} from 'lucide-react';

interface VoiceAssistantModalProps {
  isOpen: boolean;
  onClose: () => void;
  currentLang: SupportedLanguage;
}

interface AssistantResponse {
  spokenResponse: string;
  englishSummary: string;
  keyAdviceBulletPoints: string[];
  middlemanWarning: string;
  followUpSuggestions: string[];
}

export const VoiceAssistantModal: React.FC<VoiceAssistantModalProps> = ({
  isOpen,
  onClose,
  currentLang,
}) => {
  const [queryText, setQueryText] = useState('');
  const [isRecording, setIsRecording] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [response, setResponse] = useState<AssistantResponse | null>(null);

  if (!isOpen) return null;

  const t = TRANSLATIONS[currentLang];

  const quickQuestions: Record<SupportedLanguage, string[]> = {
    hi: [
      'क्या बिना ITR और सीए रिपोर्ट के बैंक से 2 से 5 लाख का लोन मिल सकता है?',
      'एक दलाल ने लोन पास कराने के लिए ₹25,000 कमीशन मांगा है, क्या मुझे देना चाहिए?',
      'मेरे पास सिर्फ PhonePe / Google Pay और कच्ची बही है, क्या बैंक इसे मानेगा?',
      'PMEGP योजना में 35% सरकारी सब्सिडी मुझे कैसे मिलेगी?',
      'बैंक शाखा प्रबंधक (Branch Manager) से मिलने जाने पर मुझे क्या बातें कहनी चाहिए?',
    ],
    en: [
      'Can I get a ₹2-5 Lakh MUDRA loan without ITR or audited balance sheets?',
      'An agent asked for ₹25,000 commission to approve my loan. Should I pay him?',
      'I only have UPI QR slips and daily khata. Does the bank recognize this?',
      'How do I claim the 35% PMEGP government capital subsidy in rural areas?',
      'What does a bank branch manager need to see before saying yes?',
    ],
    ta: [
      'ITR இல்லாமல் முத்ரா திட்டத்தில் ₹2-5 லட்சம் கடன் பெற முடியுமா?',
      'இடைத்தரகர் ₹20,000 கமிஷன் கேட்கிறார், கொடுக்கலாமா?',
      'என்னுடைய UPI மற்றும் கணக்கு ஏடு விவரங்களை வங்கி ஏற்குமா?',
    ],
    te: [
      'ITR లేకుండా ముద్రా లోన్ ₹2-5 లక్షలు పొందవచ్చా?',
      'దళారీ ₹20,000 కమీషన్ అడుగుతున్నాడు, ఇవ్వవచ్చా?',
      'నా UPI మరియు ఖాతా పుస్తకం బ్యాంక్ అంగీకరిస్తుందా?',
    ],
    mr: [
      'ITR शिवाय मला २ ते ५ लाखांचे मुद्रा कर्ज मिळू शकेल का?',
      'एका दलालाने ₹२०,००० कमिशन मागितले आहे, मी द्यावे का?',
      'माझ्याकडे फक्त PhonePe/Google Pay आणि वही खाते आहे, बँक मानेल का?',
    ],
    bn: [
      'আইটিআর ছাড়া কি ২ থেকে ৫ লাখ টাকার মুদ্রা ঋণ পাওয়া যায়?',
      'দালাল ২০ হাজার টাকা দাবি করছে, আমি কি দেব?',
      'আমার শুধু ইউপিআই এবং খাতার হিসাব আছে, ব্যাঙ্ক কি মানবে?',
    ],
    kn: [
      'ಐಟಿಆರ್ ಇಲ್ಲದೆ ₹2-5 ಲಕ್ಷ ಮುದ್ರಾ ಸಾಲ ಪಡೆಯಬಹುದೇ?',
      'ಮಧ್ಯವರ್ತಿ ₹20,000 ಕಮಿಷನ್ ಕೇಳುತ್ತಿದ್ದಾನೆ, ಕೊಡಬೇಕೇ?',
      'ನನ್ನ ಯುಪಿಐ ಮತ್ತು ಖಾತಾ ಪುಸ್ತಕವನ್ನು ಬ್ಯಾಂಕ್ ಒಪ್ಪುತ್ತದೆಯೇ?',
    ],
    gu: [
      'ITR વગર ૨ થી ૫ લાખની મુદ્રા લોન મળી શકે?',
      'દલાલ ૨૦,૦૦૦ કમિશન માંગે છે, શું આપવું જોઈએ?',
      'મારી પાસે માત્ર UPI અને ખાતાવહી છે, શું બેંક સ્વીકારશે?',
    ],
  };

  const handleStartVoice = () => {
    if (typeof window === 'undefined') return;

    const SpeechRecognition =
      (window as any).SpeechRecognition || (window as any).webkitSpeechRecognition;

    if (!SpeechRecognition) {
      alert(
        currentLang === 'hi'
          ? 'आपके ब्राउज़र में वॉइस इनपुट उपलब्ध नहीं है। कृपया नीचे लिखकर पूछें।'
          : 'Voice speech recognition not supported in this browser. Please type your query.'
      );
      return;
    }

    const recognition = new SpeechRecognition();
    const langMap: Record<SupportedLanguage, string> = {
      hi: 'hi-IN',
      en: 'en-IN',
      ta: 'ta-IN',
      te: 'te-IN',
      mr: 'mr-IN',
      bn: 'bn-IN',
      kn: 'kn-IN',
      gu: 'gu-IN',
    };
    recognition.lang = langMap[currentLang] || 'hi-IN';
    recognition.interimResults = false;
    recognition.maxAlternatives = 1;

    setIsRecording(true);

    recognition.onresult = (event: any) => {
      const speechText = event.results[0][0].transcript;
      setQueryText(speechText);
      setIsRecording(false);
      submitQuery(speechText);
    };

    recognition.onerror = () => {
      setIsRecording(false);
    };

    recognition.onend = () => {
      setIsRecording(false);
    };

    recognition.start();
  };

  const submitQuery = async (textToSend: string) => {
    const q = textToSend.trim();
    if (!q) return;

    setIsLoading(true);
    try {
      const res = await fetch('/api/gemini/ask-voice', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          question: q,
          language: currentLang,
          context:
            'Rural village entrepreneur asking questions to bypass middlemen, avoid high moneylender interest rates (24-36%), and get official MUDRA, PMEGP, or PM Vishwakarma loans with informal UPI/khata proofs.',
        }),
      });

      const data = await res.json();
      if (data.success && data.data) {
        setResponse(data.data);
        // Automatically speak the response aloud in the selected language!
        if (data.data.spokenResponse) {
          audioService.speak(data.data.spokenResponse, currentLang);
        }
      }
    } catch (err) {
      console.error('Error fetching voice answer:', err);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/60 p-4 backdrop-blur-xs">
      <div className="bg-white rounded-2xl w-full max-w-2xl max-h-[90vh] overflow-y-auto shadow-2xl border border-slate-200">
        {/* Modal Header */}
        <div className="flex items-center justify-between p-5 border-b border-slate-200 bg-emerald-800 text-white rounded-t-2xl">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-full bg-emerald-700 flex items-center justify-center text-emerald-200">
              <Sparkles className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold">
                {currentLang === 'hi' ? 'सहाय मित्र - बोलकर पूछें' : 'Sahay Mitra - Voice Advisor'}
              </h2>
              <p className="text-xs text-emerald-200">
                {currentLang === 'hi'
                  ? 'बैंक लोन, सरकारी सब्सिडी व दलाल से बचने की सही जानकारी'
                  : 'Instant AI answers for rural schemes, bank managers, and zero middleman protection'}
              </p>
            </div>
          </div>
          <button
            onClick={() => {
              audioService.stop();
              onClose();
            }}
            className="p-2 rounded-lg hover:bg-emerald-700 text-white transition-colors cursor-pointer"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        <div className="p-6 space-y-6">
          {/* Voice Input Box */}
          <div className="space-y-3">
            <label className="block text-sm font-semibold text-slate-800">
              {currentLang === 'hi'
                ? 'अपना सवाल बोलें या लिखें:'
                : 'Speak or type your question:'}
            </label>
            <div className="flex gap-2">
              <input
                type="text"
                value={queryText}
                onChange={(e) => setQueryText(e.target.value)}
                onKeyDown={(e) => e.key === 'Enter' && submitQuery(queryText)}
                placeholder={
                  currentLang === 'hi'
                    ? 'उदा. क्या कच्ची बही से मुझे 2 लाख का लोन मिल जाएगा?'
                    : 'e.g. Can I get a loan using my PhonePe QR sales?'
                }
                className="flex-1 px-4 py-3 border border-slate-300 rounded-xl text-sm focus:ring-2 focus:ring-emerald-600 focus:outline-hidden"
              />
              <button
                type="button"
                onClick={handleStartVoice}
                className={`px-4 py-3 rounded-xl flex items-center justify-center transition-all cursor-pointer ${
                  isRecording
                    ? 'bg-red-600 text-white animate-pulse'
                    : 'bg-emerald-100 hover:bg-emerald-200 text-emerald-900 border border-emerald-300'
                }`}
                title="Click to speak into microphone"
              >
                {isRecording ? <MicOff className="w-5 h-5" /> : <Mic className="w-5 h-5" />}
              </button>
              <button
                type="button"
                onClick={() => submitQuery(queryText)}
                disabled={isLoading || !queryText.trim()}
                className="px-5 py-3 bg-emerald-700 hover:bg-emerald-800 disabled:opacity-50 text-white rounded-xl font-medium text-sm transition-colors flex items-center gap-2 cursor-pointer"
              >
                {isLoading ? <Loader2 className="w-4 h-4 animate-spin" /> : <Send className="w-4 h-4" />}
                <span className="hidden sm:inline">{currentLang === 'hi' ? 'पूछें' : 'Ask'}</span>
              </button>
            </div>
          </div>

          {/* Preset common field questions */}
          <div className="space-y-2">
            <span className="text-xs font-semibold text-slate-500 uppercase tracking-wider">
              {currentLang === 'hi' ? 'गांव के उद्यमियों द्वारा अक्सर पूछे जाने वाले सवाल:' : 'Frequently Asked Village Questions:'}
            </span>
            <div className="flex flex-col gap-2">
              {(quickQuestions[currentLang] || quickQuestions.hi).map((q, idx) => (
                <button
                  key={idx}
                  onClick={() => {
                    setQueryText(q);
                    submitQuery(q);
                  }}
                  className="text-left p-3 rounded-lg border border-slate-200 hover:border-emerald-300 hover:bg-emerald-50/50 text-xs sm:text-sm text-slate-800 transition-all cursor-pointer flex items-center justify-between group"
                >
                  <span>{q}</span>
                  <span className="text-emerald-700 opacity-0 group-hover:opacity-100 font-semibold text-xs ml-2 shrink-0">
                    {currentLang === 'hi' ? 'उत्तर सुनें →' : 'Listen →'}
                  </span>
                </button>
              ))}
            </div>
          </div>

          {/* AI Response Display */}
          {isLoading && (
            <div className="p-6 rounded-xl bg-slate-50 border border-slate-200 flex flex-col items-center justify-center gap-3">
              <Loader2 className="w-6 h-6 animate-spin text-emerald-700" />
              <p className="text-sm font-medium text-slate-700">
                {currentLang === 'hi'
                  ? 'सहाय मित्र आपके लिए सरल भाषा में जानकारी तैयार कर रहे हैं...'
                  : 'Sahay Mitra is formulating clear advice...'}
              </p>
            </div>
          )}

          {response && !isLoading && (
            <div className="p-5 rounded-xl bg-emerald-50/70 border border-emerald-200 space-y-4">
              <div className="flex items-start justify-between gap-3">
                <div className="flex items-center gap-2">
                  <div className="w-7 h-7 rounded-full bg-emerald-700 flex items-center justify-center text-white text-xs font-bold">
                    ✓
                  </div>
                  <h3 className="font-bold text-slate-900 text-sm sm:text-base">
                    {currentLang === 'hi' ? 'सलाहकार का उत्तर:' : "Advisor's Guidance:"}
                  </h3>
                </div>
                <button
                  onClick={() => audioService.speak(response.spokenResponse, currentLang)}
                  className="inline-flex items-center gap-1.5 px-3 py-1 bg-emerald-700 text-white rounded-lg text-xs font-medium hover:bg-emerald-800 transition-colors cursor-pointer"
                >
                  <Volume2 className="w-3.5 h-3.5" />
                  <span>{t.listenAudio}</span>
                </button>
              </div>

              {/* Spoken Response text */}
              <p className="text-sm sm:text-base text-slate-800 leading-relaxed font-medium bg-white p-4 rounded-lg border border-emerald-100">
                {response.spokenResponse}
              </p>

              {/* Middleman warning callout */}
              {response.middlemanWarning && (
                <div className="p-3 bg-amber-50 border border-amber-200 rounded-lg flex items-start gap-2.5 text-xs text-amber-950">
                  <AlertTriangle className="w-4 h-4 text-amber-600 shrink-0 mt-0.5" />
                  <div>
                    <span className="font-bold block mb-0.5">
                      {currentLang === 'hi' ? 'दलाल से सावधान:' : 'Anti-Middleman Alert:'}
                    </span>
                    <span>{response.middlemanWarning}</span>
                  </div>
                </div>
              )}

              {/* Key Bullet Points */}
              {response.keyAdviceBulletPoints?.length > 0 && (
                <div className="space-y-1.5">
                  <span className="text-xs font-bold text-slate-700 block">
                    {currentLang === 'hi' ? 'मुख्य कदम:' : 'Key Steps:'}
                  </span>
                  <div className="space-y-1">
                    {response.keyAdviceBulletPoints.map((pt, i) => (
                      <div key={i} className="flex items-start gap-2 text-xs sm:text-sm text-slate-800">
                        <CheckCircle2 className="w-4 h-4 text-emerald-600 shrink-0 mt-0.5" />
                        <span>{pt}</span>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
