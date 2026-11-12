import React from 'react';
import { SupportedLanguage } from '../types';
import { TRANSLATIONS } from '../data/translations';
import { audioService } from '../services/audioService';
import { Volume2, VolumeX, Mic, ShieldCheck, Landmark } from 'lucide-react';

interface HeaderProps {
  currentLang: SupportedLanguage;
  onLanguageChange: (lang: SupportedLanguage) => void;
  activeTab: 'wizard' | 'calculator' | 'portal' | 'questions';
  onTabChange: (tab: 'wizard' | 'calculator' | 'portal' | 'questions') => void;
  isSpeaking: boolean;
  onToggleHeroAudio: () => void;
  onOpenVoiceAssistant: () => void;
}

export const Header: React.FC<HeaderProps> = ({
  currentLang,
  onLanguageChange,
  activeTab,
  onTabChange,
  isSpeaking,
  onToggleHeroAudio,
  onOpenVoiceAssistant,
}) => {
  const t = TRANSLATIONS[currentLang];

  const languages: { code: SupportedLanguage; label: string; script: string }[] = [
    { code: 'hi', label: 'Hindi', script: 'हिंदी' },
    { code: 'en', label: 'English', script: 'English' },
    { code: 'ta', label: 'Tamil', script: 'தமிழ்' },
    { code: 'te', label: 'Telugu', script: 'తెలుగు' },
    { code: 'mr', label: 'Marathi', script: 'मराठी' },
    { code: 'bn', label: 'Bengali', script: 'বাংলা' },
    { code: 'kn', label: 'Kannada', script: 'ಕನ್ನಡ' },
    { code: 'gu', label: 'Gujarati', script: 'ગુજરાતી' },
  ];

  return (
    <header className="sticky top-0 z-40 bg-white border-b border-slate-200 shadow-xs w-full max-w-full overflow-x-hidden">
      {/* Top utility alert bar for zero middleman guarantee */}
      <div className="bg-emerald-800 text-white px-3 sm:px-4 py-1.5 text-xs w-full overflow-hidden">
        <div className="max-w-7xl mx-auto flex items-center justify-between gap-2 sm:gap-4 min-w-0">
          <div className="flex items-center gap-2 font-medium min-w-0 flex-1 overflow-hidden">
            <ShieldCheck className="w-4 h-4 text-emerald-300 shrink-0" />
            <span className="truncate block min-w-0 text-[11px] sm:text-xs">
              {currentLang === 'hi'
                ? 'सरकारी योजना में 0% दलाली · किसी एजेंट को 10-15% रिश्वत न दें · सीधी बैंक अर्जी'
                : 'Zero Agent Fee Guarantee · Direct Bank File Preparation · Say NO to 10-15% Middlemen'}
            </span>
          </div>
          <div className="flex items-center gap-2 shrink-0">
            <span className="text-emerald-200 hidden md:inline text-[11px]">
              {currentLang === 'hi' ? 'बोलकर सुनें:' : 'Audio Guide:'}
            </span>
            <button
              onClick={onToggleHeroAudio}
              className="inline-flex items-center gap-1 sm:gap-1.5 px-2 sm:px-2.5 py-0.5 rounded bg-emerald-700 hover:bg-emerald-600 text-white font-medium text-[11px] sm:text-xs transition-colors cursor-pointer shrink-0"
              title="Play/Pause Voice Guide"
            >
              {isSpeaking ? (
                <>
                  <VolumeX className="w-3.5 h-3.5 text-amber-300 animate-pulse" />
                  <span>{t.stopAudio}</span>
                </>
              ) : (
                <>
                  <Volume2 className="w-3.5 h-3.5" />
                  <span>{t.listenAudio}</span>
                </>
              )}
            </button>
          </div>
        </div>
      </div>

      {/* Main Navigation conforming to Top Bar Contract */}
      <div className="max-w-7xl mx-auto px-3 sm:px-6 py-2.5 sm:py-3.5 flex items-center justify-between gap-2 sm:gap-4 md:gap-8 min-w-0 w-full">
        {/* Zone 1: Brand wordmark */}
        <div className="flex items-center gap-2 sm:gap-3 shrink-0 min-w-0">
          <div className="w-8 h-8 sm:w-10 sm:h-10 rounded-xl bg-emerald-700 flex items-center justify-center text-white shadow-xs font-bold text-base sm:text-xl shrink-0">
            स
          </div>
          <div className="flex flex-col min-w-0">
            <button
              onClick={() => onTabChange('wizard')}
              className="text-left text-base sm:text-xl font-bold tracking-tight text-slate-900 whitespace-nowrap hover:text-emerald-800 transition-colors cursor-pointer"
            >
              SahayCredit AI
            </button>
            <span className="text-[11px] text-slate-600 font-medium tracking-wide hidden lg:block truncate">
              {t.tagline}
            </span>
          </div>
        </div>

        {/* Zone 2: Single-line navigation links */}
        <nav className="hidden md:flex items-center gap-5 lg:gap-7 text-sm font-medium text-slate-600 shrink-0">
          <button
            onClick={() => onTabChange('wizard')}
            className={`whitespace-nowrap transition-colors py-1 cursor-pointer ${
              activeTab === 'wizard'
                ? 'text-emerald-700 font-semibold border-b-2 border-emerald-700'
                : 'hover:text-slate-900'
            }`}
          >
            {currentLang === 'hi' ? 'लोन व योजना साथी' : 'Loan & Schemes'}
          </button>
          <button
            onClick={() => onTabChange('calculator')}
            className={`whitespace-nowrap transition-colors py-1 cursor-pointer ${
              activeTab === 'calculator'
                ? 'text-emerald-700 font-semibold border-b-2 border-emerald-700'
                : 'hover:text-slate-900'
            }`}
          >
            {currentLang === 'hi' ? 'दलाल व ब्याज कैलकुलेटर' : 'Middleman Trap Calculator'}
          </button>
          <button
            onClick={() => onTabChange('portal')}
            className={`whitespace-nowrap transition-colors py-1 cursor-pointer ${
              activeTab === 'portal'
                ? 'text-emerald-700 font-semibold border-b-2 border-emerald-700'
                : 'hover:text-slate-900'
            }`}
          >
            <span className="inline-flex items-center gap-1.5">
              <Landmark className="w-4 h-4 text-slate-500" />
              {currentLang === 'hi' ? 'बैंक मैनेजर डेस्क' : 'Bank Manager Desk'}
            </span>
          </button>
          <button
            onClick={() => onTabChange('questions')}
            className={`whitespace-nowrap transition-colors py-1 cursor-pointer ${
              activeTab === 'questions'
                ? 'text-emerald-700 font-semibold border-b-2 border-emerald-700'
                : 'hover:text-slate-900'
            }`}
          >
            {currentLang === 'hi' ? 'जमीनी सवाल (PDF)' : 'Field Insights (PDF)'}
          </button>
        </nav>

        {/* Zone 3: Primary Action & Language Selector */}
        <div className="flex items-center gap-1.5 sm:gap-3 shrink-0">
          {/* Quick Sound Check Button */}
          <button
            onClick={() => audioService.testSpeaker(currentLang)}
            className="hidden sm:inline-flex items-center gap-1 px-2 sm:px-2.5 py-1.5 text-xs font-semibold text-slate-700 bg-slate-100 hover:bg-slate-200 border border-slate-300 rounded-lg transition-colors cursor-pointer shrink-0"
            title="Click to test audio output and speaker"
          >
            <span className="text-xs">🔊</span>
            <span className="hidden md:inline">{currentLang === 'hi' ? 'ध्वनि टेस्ट' : 'Test Sound'}</span>
          </button>

          {/* Language Dropdown */}
          <div className="relative">
            <select
              value={currentLang}
              onChange={(e) => onLanguageChange(e.target.value as SupportedLanguage)}
              aria-label="Select language"
              className="text-xs font-semibold bg-slate-100 hover:bg-slate-200 text-slate-800 border border-slate-300 rounded-lg px-2 sm:px-2.5 py-1.5 sm:py-2 cursor-pointer transition-colors focus:ring-2 focus:ring-emerald-600 focus:outline-hidden max-w-[95px] sm:max-w-none truncate"
            >
              {languages.map((l) => (
                <option key={l.code} value={l.code}>
                  {l.script} ({l.label})
                </option>
              ))}
            </select>
          </div>

          {/* Primary Action Button: Voice Assistant */}
          <button
            onClick={onOpenVoiceAssistant}
            className="inline-flex items-center gap-1 sm:gap-2 px-2.5 sm:px-3.5 py-1.5 sm:py-2 text-xs sm:text-sm font-semibold text-white bg-emerald-700 rounded-lg hover:bg-emerald-800 transition-colors shadow-xs whitespace-nowrap cursor-pointer shrink-0"
          >
            <Mic className="w-3.5 h-3.5 sm:w-4 sm:h-4 text-emerald-200" />
            <span className="hidden sm:inline">{t.askAssistant}</span>
            <span className="sm:hidden">{currentLang === 'hi' ? 'पूछें' : 'Voice'}</span>
          </button>
        </div>
      </div>

      {/* Mobile nav strip */}
      <div className="flex md:hidden border-t border-slate-200 overflow-x-auto px-3 py-1.5 gap-2 text-xs bg-slate-50 no-scrollbar w-full max-w-full">
        <button
          onClick={() => onTabChange('wizard')}
          className={`whitespace-nowrap px-2.5 py-1 rounded-md font-medium cursor-pointer shrink-0 ${
            activeTab === 'wizard' ? 'bg-emerald-700 text-white' : 'bg-white text-slate-700 border border-slate-200'
          }`}
        >
          {currentLang === 'hi' ? 'योजना साथी' : 'Loan Schemes'}
        </button>
        <button
          onClick={() => onTabChange('calculator')}
          className={`whitespace-nowrap px-2.5 py-1 rounded-md font-medium cursor-pointer shrink-0 ${
            activeTab === 'calculator' ? 'bg-emerald-700 text-white' : 'bg-white text-slate-700 border border-slate-200'
          }`}
        >
          {currentLang === 'hi' ? 'दलाल कैलकुलेटर' : 'Middleman Trap'}
        </button>
        <button
          onClick={() => onTabChange('portal')}
          className={`whitespace-nowrap px-2.5 py-1 rounded-md font-medium cursor-pointer shrink-0 ${
            activeTab === 'portal' ? 'bg-emerald-700 text-white' : 'bg-white text-slate-700 border border-slate-200'
          }`}
        >
          {currentLang === 'hi' ? 'मैनेजर डेस्क' : 'Manager Desk'}
        </button>
        <button
          onClick={() => onTabChange('questions')}
          className={`whitespace-nowrap px-2.5 py-1 rounded-md font-medium cursor-pointer shrink-0 ${
            activeTab === 'questions' ? 'bg-emerald-700 text-white' : 'bg-white text-slate-700 border border-slate-200'
          }`}
        >
          {currentLang === 'hi' ? 'जमीनी सवाल' : 'Field Insights'}
        </button>
      </div>
    </header>
  );
};
