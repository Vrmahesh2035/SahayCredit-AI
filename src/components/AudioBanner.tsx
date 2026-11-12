import React, { useState } from 'react';
import { Volume2, VolumeX, RotateCcw, Sparkles } from 'lucide-react';
import { audioService, VoiceEngine } from '../services/audioService';
import { SupportedLanguage } from '../types';

interface AudioBannerProps {
  isSpeaking: boolean;
  currentSpeakingText: string;
  currentLang: SupportedLanguage;
  lastPlayedText: string;
}

export const AudioBanner: React.FC<AudioBannerProps> = ({
  isSpeaking,
  currentSpeakingText,
  currentLang,
  lastPlayedText,
}) => {
  const [engine, setEngine] = useState<VoiceEngine>(audioService.getVoiceEngine());

  const handleToggleEngine = () => {
    const nextEngine: VoiceEngine = engine === 'ai' ? 'browser' : 'ai';
    setEngine(nextEngine);
    audioService.setVoiceEngine(nextEngine);

    if (lastPlayedText) {
      audioService.speak(lastPlayedText, currentLang, nextEngine);
    }
  };

  const handleSoundCheck = () => {
    audioService.testSpeaker(currentLang);
  };

  if (!isSpeaking && !lastPlayedText) {
    return (
      <div className="bg-slate-100 border-b border-slate-200 text-slate-600 py-1 px-4 text-xs">
        <div className="max-w-7xl mx-auto flex items-center justify-between gap-3">
          <div className="flex items-center gap-2">
            <Volume2 className="w-3.5 h-3.5 text-emerald-700" />
            <span className="font-medium">
              {currentLang === 'hi'
                ? 'आवाज मार्गदर्शिका सक्रिय है (स्पीकर चालू रखें)'
                : 'Audio Guidance Ready (Ensure volume is unmuted)'}
            </span>
          </div>
          <button
            onClick={handleSoundCheck}
            className="inline-flex items-center gap-1.5 px-2.5 py-0.5 rounded bg-emerald-50 hover:bg-emerald-100 text-emerald-900 border border-emerald-300 font-semibold text-[11px] transition-colors cursor-pointer"
            title="Click to test sound output and speaker volume"
          >
            <span>🔊</span>
            <span>{currentLang === 'hi' ? 'आवाज चेक करें' : 'Test Sound'}</span>
          </button>
        </div>
      </div>
    );
  }

  return (
    <div
      className={`transition-all duration-300 border-b w-full max-w-full overflow-hidden ${
        isSpeaking
          ? 'bg-amber-50 border-amber-200 text-amber-950 py-2 sm:py-2.5 px-3 sm:px-4'
          : 'bg-slate-50 border-slate-200 text-slate-700 py-1.5 sm:py-2 px-3 sm:px-4'
      }`}
    >
      <div className="max-w-7xl mx-auto flex flex-col sm:flex-row items-start sm:items-center justify-between gap-2 sm:gap-3 min-w-0 w-full">
        <div className="flex items-center gap-2 sm:gap-3 overflow-hidden flex-1 min-w-0 w-full">
          {/* Animated sound bars when speaking */}
          {isSpeaking ? (
            <div className="flex items-center gap-1 h-5 px-1 shrink-0" aria-label="Audio playing">
              <span className="w-1.5 h-4 bg-amber-600 rounded-full animate-bounce inline-block" />
              <span className="w-1.5 h-5 bg-amber-700 rounded-full animate-pulse inline-block" />
              <span className="w-1.5 h-3 bg-amber-600 rounded-full animate-bounce inline-block" />
              <span className="w-1.5 h-5 bg-amber-700 rounded-full animate-pulse inline-block" />
            </div>
          ) : (
            <Volume2 className="w-4 h-4 text-slate-500 shrink-0" />
          )}

          <div className="truncate text-xs sm:text-sm min-w-0 flex-1">
            <span className="font-bold text-slate-900 mr-1.5 shrink-0">
              {isSpeaking
                ? currentLang === 'hi'
                  ? 'बोलकर सुनाया जा रहा है:'
                  : 'Speaking Now:'
                : currentLang === 'hi'
                ? 'पिछला ऑडियो:'
                : 'Last Audio:'}
            </span>
            <span className="text-slate-800 italic truncate font-medium">
              "{isSpeaking ? currentSpeakingText : lastPlayedText}"
            </span>
          </div>
        </div>

        <div className="flex items-center gap-2 shrink-0 self-end sm:self-auto flex-wrap">
          {/* Active AI Language Voice Badge */}
          <span className="hidden md:inline-flex items-center gap-1 px-2 py-0.5 rounded text-[10px] font-bold bg-emerald-100 text-emerald-900 border border-emerald-300">
            <Sparkles className="w-2.5 h-2.5 text-emerald-700" />
            <span>Native Voice: {currentLang.toUpperCase()}</span>
          </span>

          {/* Instant Speaker Sound Check */}
          <button
            onClick={handleSoundCheck}
            className="inline-flex items-center gap-1 px-2 sm:px-2.5 py-1 bg-white hover:bg-slate-100 text-slate-800 rounded-md text-xs font-semibold border border-slate-300 shadow-2xs transition-colors cursor-pointer shrink-0"
            title="Play acoustic sound test to confirm audio is audible in selected language"
          >
            <span>🔊</span>
            <span>{currentLang === 'hi' ? 'ध्वनि टेस्ट' : 'Sound Test'}</span>
          </button>

          {isSpeaking ? (
            <button
              onClick={() => audioService.stop()}
              className="inline-flex items-center gap-1.5 px-2.5 sm:px-3 py-1 bg-amber-700 hover:bg-amber-800 text-white rounded-md text-xs font-semibold shadow-2xs transition-colors cursor-pointer shrink-0"
            >
              <VolumeX className="w-3.5 h-3.5" />
              <span>{currentLang === 'hi' ? 'आवाज रोकें' : 'Stop'}</span>
            </button>
          ) : (
            <button
              onClick={() => audioService.speak(lastPlayedText, currentLang, 'ai')}
              className="inline-flex items-center gap-1.5 px-2.5 sm:px-3 py-1 bg-emerald-700 hover:bg-emerald-800 text-white rounded-md text-xs font-semibold shadow-2xs transition-colors cursor-pointer shrink-0"
            >
              <RotateCcw className="w-3.5 h-3.5" />
              <span>{currentLang === 'hi' ? 'फिर से सुनें' : 'Replay'}</span>
            </button>
          )}
        </div>
      </div>
    </div>
  );
};
