import React, { useState, useEffect } from 'react';
import { SupportedLanguage, SubmittedDossier } from './types';
import { TRANSLATIONS } from './data/translations';
import { SAMPLE_PROFILES } from './data/sampleProfiles';
import { audioService } from './services/audioService';
import { Header } from './components/Header';
import { AudioBanner } from './components/AudioBanner';
import { VoiceAssistantModal } from './components/VoiceAssistantModal';
import { EntrepreneurWizard } from './components/EntrepreneurWizard';
import { MiddlemanCalculator } from './components/MiddlemanCalculator';
import { BranchManagerPortal } from './components/BranchManagerPortal';
import { FieldQuestionsExplorer } from './components/FieldQuestionsExplorer';
import {
  Volume2,
  VolumeX,
  Sparkles,
  ShieldCheck,
  CheckCircle2,
  ArrowRight,
  TrendingDown,
  Landmark,
  FileCheck,
  PhoneCall,
  ExternalLink,
} from 'lucide-react';

export default function App() {
  const [currentLang, setCurrentLang] = useState<SupportedLanguage>('hi');
  const [activeTab, setActiveTab] = useState<'wizard' | 'calculator' | 'portal' | 'questions'>('wizard');
  const [isVoiceModalOpen, setIsVoiceModalOpen] = useState<boolean>(false);
  const [isSpeaking, setIsSpeaking] = useState<boolean>(false);
  const [currentSpeakingText, setCurrentSpeakingText] = useState<string>('');
  const [lastPlayedText, setLastPlayedText] = useState<string>('');

  // Initial dossiers seeded from realistic sample profiles
  const [dossiers, setDossiers] = useState<SubmittedDossier[]>([
    {
      id: 'dossier-ramesh-1',
      applicationNumber: 'SC-MSME-2026-849102',
      borrowerName: 'Ramesh Kumar Solanki',
      businessName: 'Ramesh Tailoring Works',
      businessType: 'tailor',
      village: 'Pipariya Kalan',
      district: 'Narmadapuram',
      state: 'Madhya Pradesh',
      loanRequested: 180000,
      recommendedLoan: 180000,
      dscr: 1.84,
      riskScore: 88,
      riskLevel: 'Low Risk',
      primaryScheme: 'MUDRA Kishore (PMMY)',
      subsidyEntitlement: '0% Mortgage + CGFMU Guarantee',
      annualMoneylenderSaving: 48600,
      status: 'PENDING_BRANCH_REVIEW',
      consentTimestamp: '2026-11-06T10:14:00.000Z',
      recordSummary: 'Daily PhonePe/GPay QR averaging ₹1,800 to ₹2,450/day. Monthly revenue ₹62,000, verified 3 singer machines.',
      analysis: {
        estimatedMonthlyInflow: 62000,
        estimatedMonthlyOperatingExpense: 22500,
        netMonthlySurplus: 39500,
        recommendedLoanAmount: 180000,
        estimatedMonthlyEMI: 3960,
        dscr: 1.84,
        riskLevel: 'Low Risk',
        riskScore: 88,
        viabilityVerdict: 'Viable Bank Candidate',
        explainabilitySummary: 'Steady UPI transaction frequency (42 trans/month) and high repeat festival tailoring revenue. DSCR of 1.84x provides ample safety margin over the 1.30 benchmark.',
        schemeMatches: [
          {
            schemeName: 'Pradhan Mantri MUDRA Yojana (PMMY) - Kishore',
            categoryTag: 'Collateral-Free',
            subsidyOrBenefit: 'Nil Collateral (CGFMU Guarantee)',
            maxLimit: 'Up to ₹5,00,000',
            officialCitation: 'PMMY Circular DFS/RBI FIDD.MSME.BC.No.4/2023-24',
            whyEligible: 'Existing tailoring unit with proven UPI inflow for machinery upgrade.',
            interestBenefit: 'MCLR-linked rate 9.1% p.a.',
          },
        ],
        branchManagerChecklist: [
          'Verify applicant Aadhaar and rural enterprise location',
          'Review bank passbook matching PhonePe QR settlements',
          'Verify invoice for Jack zigzag sewing machines',
        ],
        villageAudioGuideText: {
          hi: 'रमेश जी, आपका UPI रिकॉर्ड मजबूत है और आपकी किस्त चुकाने की क्षमता उत्कृष्ट है।',
          en: 'Ramesh, your UPI cashflow is solid and your repayment capacity is strong.',
        },
      },
    },
    {
      id: 'dossier-kamla-2',
      applicationNumber: 'SC-MSME-2026-619284',
      borrowerName: 'Kamla Devi Ahirwar',
      businessName: 'Kamla Devi Dairy Unit',
      businessType: 'dairy',
      village: 'Chandanpur',
      district: 'Bareilly',
      state: 'Uttar Pradesh',
      loanRequested: 250000,
      recommendedLoan: 250000,
      dscr: 1.95,
      riskScore: 92,
      riskLevel: 'Low Risk',
      primaryScheme: 'PMEGP Special Category Subsidy',
      subsidyEntitlement: '35% Rural SC/Woman Grant (₹87,500)',
      annualMoneylenderSaving: 67500,
      status: 'IN_PRINCIPLE_SANCTIONED',
      consentTimestamp: '2026-11-05T14:30:00.000Z',
      recordSummary: '4 milch cows, Parag milk cooperative weekly slips averaging ₹11,200/week directly into bank.',
      analysis: {
        estimatedMonthlyInflow: 64000,
        estimatedMonthlyOperatingExpense: 23500,
        netMonthlySurplus: 40500,
        recommendedLoanAmount: 250000,
        estimatedMonthlyEMI: 5500,
        dscr: 1.95,
        riskLevel: 'Low Risk',
        riskScore: 92,
        viabilityVerdict: 'Viable Bank Candidate',
        explainabilitySummary: 'Cooperative dairy slips provide statutory proof of primary income. 35% PMEGP margin money grant reduces bank risk to under 65%.',
        schemeMatches: [
          {
            schemeName: "Prime Minister's Employment Generation Programme (PMEGP)",
            categoryTag: 'Capital Grant',
            subsidyOrBenefit: '35% Margin Money Subsidy',
            maxLimit: 'Up to ₹20,00,000',
            officialCitation: 'KVIC PMEGP Special Category Guidelines',
            whyEligible: 'Rural SC Woman entrepreneur establishing expanded animal husbandry unit.',
            interestBenefit: 'Government grant adjusted against principal after 3 years',
          },
        ],
        branchManagerChecklist: [
          'Verify Parag dairy cooperative passbook entries',
          'Inspect cattle shed and water arrangement',
          'Submit margin money claim to KVIC portal',
        ],
        villageAudioGuideText: {
          hi: 'कमला देवी जी, आपको 35% सरकारी अनुदान (सब्सिडी) का लाभ मिलेगा।',
          en: 'Kamla Devi, you are entitled to a 35% non-repayable government capital grant.',
        },
      },
    },
    {
      id: 'dossier-shankar-3',
      applicationNumber: 'SC-MSME-2026-492019',
      borrowerName: 'Shankar Lal Panchal',
      businessName: 'Shankar Welding & Agri Implements',
      businessType: 'fabrication',
      village: 'Sikandrabad Dehat',
      district: 'Bulandshahr',
      state: 'Uttar Pradesh',
      loanRequested: 400000,
      recommendedLoan: 400000,
      dscr: 1.62,
      riskScore: 84,
      riskLevel: 'Low Risk',
      primaryScheme: 'PM Vishwakarma & CGTMSE',
      subsidyEntitlement: '5% Concessional Interest + 85% CGTMSE Guarantee',
      annualMoneylenderSaving: 108000,
      status: 'PENDING_BRANCH_REVIEW',
      consentTimestamp: '2026-11-04T09:20:00.000Z',
      recordSummary: 'Paytm Soundbox UPI receipts ₹72,000/mo, steel raw material dealer invoices ₹58,000/mo.',
      analysis: {
        estimatedMonthlyInflow: 125000,
        estimatedMonthlyOperatingExpense: 80700,
        netMonthlySurplus: 44300,
        recommendedLoanAmount: 400000,
        estimatedMonthlyEMI: 8800,
        dscr: 1.62,
        riskLevel: 'Low Risk',
        riskScore: 84,
        viabilityVerdict: 'Viable Bank Candidate',
        explainabilitySummary: 'Strong cash velocity and agricultural equipment fabrication orders. Covered under PM Vishwakarma and 85% CGTMSE default guarantee.',
        schemeMatches: [
          {
            schemeName: 'PM Vishwakarma Scheme',
            categoryTag: 'Concessional Artisan Credit',
            subsidyOrBenefit: '5% Interest Rate Subvention',
            maxLimit: 'Up to ₹3,00,000 in two tranches',
            officialCitation: 'PM Vishwakarma Scheme Guidelines 2023-24',
            whyEligible: 'Traditional artisan (welder/blacksmith) trade recognized under scheme.',
            interestBenefit: 'Government pays 8% interest subsidy directly to bank',
          },
        ],
        branchManagerChecklist: [
          'Verify workshop machinery and 3-phase electricity connection',
          'Review steel supplier purchase invoices',
          'Check Udyam registration certificate',
        ],
        villageAudioGuideText: {
          hi: 'शंकर जी, आपकी वेल्डिंग दुकान के लिए 5% ब्याज दर पर विश्वकर्मा योजना उपलब्ध है।',
          en: 'Shankar, you qualify for PM Vishwakarma with only 5% interest.',
        },
      },
    },
  ]);

  // Audio subscription hook
  useEffect(() => {
    const unsubscribe = audioService.subscribe((speaking, text) => {
      setIsSpeaking(speaking);
      if (speaking) {
        setCurrentSpeakingText(text);
        setLastPlayedText(text);
      }
    });
    return () => {
      unsubscribe();
    };
  }, []);

  const t = TRANSLATIONS[currentLang];

  const handleToggleHeroAudio = () => {
    if (isSpeaking) {
      audioService.stop();
    } else {
      audioService.speak(t.prompts.heroWelcome, currentLang, 'ai');
    }
  };

  const handleDossierCreated = (newDossier: SubmittedDossier) => {
    setDossiers((prev) => [newDossier, ...prev]);
  };

  const handleUpdateDossierStatus = (id: string, newStatus: SubmittedDossier['status']) => {
    setDossiers((prev) =>
      prev.map((d) => (d.id === id ? { ...d, status: newStatus } : d))
    );
  };

  return (
    <div className="min-h-screen flex flex-col bg-slate-50 text-slate-900 selection:bg-emerald-100 selection:text-emerald-900 w-full max-w-full overflow-x-hidden">
      {/* Header */}
      <Header
        currentLang={currentLang}
        onLanguageChange={(l) => {
          setCurrentLang(l);
          audioService.stop();
          const greetings: Record<SupportedLanguage, string> = {
            hi: 'नमस्ते! सहाय-क्रेडिट में आपका स्वागत है। बोलकर सुनने की सुविधा चालू है।',
            ta: 'வணக்கம்! சஹாய் கிரெடிட்டிற்கு உங்களை அன்புடன் வரவேற்கிறோம்.',
            te: 'నమస్కారం! సహాయ్ క్రెడిట్ కి స్వాగతం. వాయిస్ గైడ్ సిద్ధంగా ఉంది.',
            mr: 'नमस्कार! सहाय क्रेडिट मध्ये आपले स्वागत आहे. आवाज मार्गदर्शन सुरू आहे.',
            bn: 'নমস্কার! সহায় ক্রেডিটে আপনাকে স্বাগত। অডিও নির্দেশিকা প্রস্তুত।',
            kn: 'ನಮಸ್ಕಾರ! ಸಹಾಯ್ ಕ್ರೆಡಿಟ್‌ಗೆ ಸುಸ್ವಾಗತ. ಧ್ವನಿ ಮಾರ್ಗದರ್ಶಿ ಸಿದ್ಧವಾಗಿದೆ.',
            gu: 'નમસ્તે! સહાય ક્રેડિટમાં આપનું સ્વાગત છે. અવાજ સહાય તૈયાર છે.',
            en: 'Welcome to SahayCredit AI. Voice assistance is active in English.',
          };
          audioService.speak(greetings[l] || greetings.hi, l, 'ai');
        }}
        activeTab={activeTab}
        onTabChange={(tab) => {
          setActiveTab(tab);
          audioService.stop();
        }}
        isSpeaking={isSpeaking}
        onToggleHeroAudio={handleToggleHeroAudio}
        onOpenVoiceAssistant={() => setIsVoiceModalOpen(true)}
      />

      {/* Audio Status Banner when playing */}
      <AudioBanner
        isSpeaking={isSpeaking}
        currentSpeakingText={currentSpeakingText}
        currentLang={currentLang}
        lastPlayedText={lastPlayedText}
      />

      {/* Multilingual Voice Quick Bar */}
      <div className="bg-emerald-950 text-white px-3 sm:px-4 py-2 border-b border-emerald-800 text-xs w-full max-w-full overflow-x-hidden">
        <div className="max-w-7xl mx-auto flex flex-col sm:flex-row items-start sm:items-center justify-between gap-2 sm:gap-3 min-w-0">
          <div className="flex items-center gap-2 shrink-0">
            <Volume2 className="w-4 h-4 text-emerald-400 shrink-0" />
            <span className="font-semibold text-emerald-200 text-xs">
              {currentLang === 'hi' ? 'अपनी भाषा में आवाज सुनें:' : 'Listen in your native language:'}
            </span>
          </div>
          <div className="flex flex-wrap items-center gap-1 sm:gap-1.5 w-full sm:w-auto">
            {[
              { code: 'hi' as SupportedLanguage, label: 'हिंदी', phrase: 'नमस्ते! सहाय-क्रेडिट में आपका स्वागत है।' },
              { code: 'ta' as SupportedLanguage, label: 'தமிழ்', phrase: 'வணக்கம்! சஹாய் கிரெடிட்டிற்கு உங்களை அன்புடன் வரவேற்கிறோம்.' },
              { code: 'te' as SupportedLanguage, label: 'తెలుగు', phrase: 'నమస్కారం! సహాయ్ క్రెడిట్ కి స్వాగతం.' },
              { code: 'mr' as SupportedLanguage, label: 'मराठी', phrase: 'नमस्कार! सहाय क्रेडिट मध्ये आपले स्वागत आहे.' },
              { code: 'bn' as SupportedLanguage, label: 'বাংলা', phrase: 'নমস্কার! সহায় ক্রেডিটে আপনাকে স্বাগত।' },
              { code: 'kn' as SupportedLanguage, label: 'ಕನ್ನಡ', phrase: 'ನಮಸ್ಕಾರ! ಸಹಾಯ್ ಕ್ರೆಡಿಟ್‌ಗೆ ಸುಸ್ವಾಗತ.' },
              { code: 'gu' as SupportedLanguage, label: 'ગુજરાતી', phrase: 'નમસ્તે! સહાય ક્રેડિટમાં આપનું સ્વાગત છે.' },
              { code: 'en' as SupportedLanguage, label: 'English', phrase: 'Welcome to SahayCredit AI.' },
            ].map((item) => (
              <button
                key={item.code}
                onClick={() => {
                  setCurrentLang(item.code);
                  audioService.stop();
                  audioService.speak(item.phrase, item.code, 'ai');
                }}
                className={`px-2 sm:px-2.5 py-0.5 sm:py-1 rounded-md text-[11px] sm:text-xs font-medium transition-all cursor-pointer flex items-center gap-1 shrink-0 ${
                  currentLang === item.code
                    ? 'bg-emerald-600 text-white font-bold ring-2 ring-emerald-300'
                    : 'bg-emerald-900/80 hover:bg-emerald-800 text-emerald-200'
                }`}
                title={`Click to listen in ${item.label}`}
              >
                <span>{item.label}</span>
                {currentLang === item.code && <span className="text-[10px]">🔊</span>}
              </button>
            ))}
          </div>
        </div>
      </div>

      {/* Hero Welcome Banner (Visible on wizard tab) */}
      {activeTab === 'wizard' && (
        <section className="bg-emerald-900 text-white py-8 sm:py-10 px-4 sm:px-6 relative overflow-hidden w-full max-w-full">
          <div className="max-w-6xl mx-auto flex flex-col md:flex-row items-center justify-between gap-6 md:gap-8 min-w-0">
            <div className="space-y-4 max-w-2xl min-w-0 w-full">
              <div className="flex items-center gap-2 text-xs font-semibold text-emerald-300 uppercase tracking-wider">
                <ShieldCheck className="w-4 h-4 text-emerald-400 shrink-0" />
                <span className="truncate">{t.badge}</span>
              </div>
              <h1 className="text-2xl sm:text-4xl lg:text-5xl font-extrabold tracking-tight text-white leading-tight break-words">
                {currentLang === 'hi' ? (
                  <>
                    कच्ची बही व UPI से पाएं <span className="text-emerald-300">सरकारी लोन</span>
                  </>
                ) : (
                  <>
                    Turn UPI &amp; Informal Records into <span className="text-emerald-300">Bank Loans</span>
                  </>
                )}
              </h1>
              <p className="text-xs sm:text-base text-emerald-100 leading-relaxed">
                {currentLang === 'hi'
                  ? 'दर्जी, डेयरी, वेल्डर, किराना या महिला समूह - बिना किसी दलाल को 10-15% दिए, सीधे MUDRA (मुद्रा) और PMEGP 35% सरकारी सब्सिडी योजना से जुड़ें।'
                  : 'Bypassing 10-15% middleman extortions and 24-36% moneylenders. Translating daily UPI cashflows and milk slips into certified bank-ready dossiers.'}
              </p>

              {/* Action buttons */}
              <div className="flex flex-wrap items-center gap-2.5 sm:gap-3 pt-2">
                <button
                  onClick={handleToggleHeroAudio}
                  className="inline-flex items-center gap-2 px-4 sm:px-5 py-2.5 sm:py-3 rounded-xl bg-white text-emerald-950 font-bold text-xs sm:text-sm hover:bg-emerald-50 transition-colors shadow-xs cursor-pointer shrink-0"
                >
                  {isSpeaking ? (
                    <>
                      <VolumeX className="w-4 h-4 text-amber-700 animate-pulse" />
                      <span>{t.stopAudio}</span>
                    </>
                  ) : (
                    <>
                      <Volume2 className="w-4 h-4 text-emerald-700" />
                      <span>{t.listenAudio}</span>
                    </>
                  )}
                </button>
                <button
                  onClick={() => setIsVoiceModalOpen(true)}
                  className="inline-flex items-center gap-2 px-4 sm:px-5 py-2.5 sm:py-3 rounded-xl bg-emerald-800 hover:bg-emerald-700 text-white font-bold text-xs sm:text-sm transition-colors border border-emerald-600 shadow-xs cursor-pointer shrink-0"
                >
                  <Sparkles className="w-4 h-4 text-emerald-300" />
                  <span>{t.askAssistant}</span>
                </button>
              </div>
            </div>

            {/* Right Side Trust Strip */}
            <div className="w-full md:w-80 max-w-full bg-emerald-800/80 backdrop-blur-xs border border-emerald-700 rounded-2xl p-4 sm:p-5 space-y-3 text-xs text-emerald-100 shrink-0">
              <span className="font-bold text-white uppercase tracking-wider block text-[11px]">
                {currentLang === 'hi' ? 'ग्रामीण उद्यमी सुरक्षा स्तंभ:' : 'Rural Credit Pillars:'}
              </span>
              <div className="flex items-start gap-2.5">
                <CheckCircle2 className="w-4 h-4 text-emerald-300 shrink-0 mt-0.5" />
                <span>
                  <strong>{currentLang === 'hi' ? 'बिना ITR या बैलेंस शीट:' : 'No ITR Required:'}</strong>{' '}
                  {currentLang === 'hi' ? 'फोनपे/गूगल-पे व कच्ची बही मान्य' : 'UPI QR & informal records accepted'}
                </span>
              </div>
              <div className="flex items-start gap-2.5">
                <CheckCircle2 className="w-4 h-4 text-emerald-300 shrink-0 mt-0.5" />
                <span>
                  <strong>{currentLang === 'hi' ? 'शून्य दलाली (0% Fee):' : 'Zero Middleman Fee:'}</strong>{' '}
                  {currentLang === 'hi' ? 'दलाल द्वारा ली जाने वाली 10-15% की बचत' : 'Save 10-15% agent commission'}
                </span>
              </div>
              <div className="flex items-start gap-2.5">
                <CheckCircle2 className="w-4 h-4 text-emerald-300 shrink-0 mt-0.5" />
                <span>
                  <strong>{currentLang === 'hi' ? '25% से 35% सरकारी सब्सिडी:' : 'Up to 35% Subsidy:'}</strong>{' '}
                  {currentLang === 'hi' ? 'PMEGP योजना में गैर-वापसी योग्य अनुदान' : 'Non-repayable KVIC margin money'}
                </span>
              </div>
              <div className="flex items-start gap-2.5">
                <CheckCircle2 className="w-4 h-4 text-emerald-300 shrink-0 mt-0.5" />
                <span>
                  <strong>{currentLang === 'hi' ? 'बिना जमीन या मकान गारंटी:' : 'Zero Collateral:'}</strong>{' '}
                  {currentLang === 'hi' ? 'CGFMU और CGTMSE द्वारा 85% गारंटी' : 'Covered under CGFMU / CGTMSE'}
                </span>
              </div>
            </div>
          </div>
        </section>
      )}

      {/* Main Content Body */}
      <main className="flex-1 w-full max-w-full overflow-x-hidden">
        {activeTab === 'wizard' && (
          <EntrepreneurWizard
            currentLang={currentLang}
            onDossierCreated={handleDossierCreated}
            onNavigateToPortal={() => setActiveTab('portal')}
          />
        )}

        {activeTab === 'calculator' && <MiddlemanCalculator currentLang={currentLang} />}

        {activeTab === 'portal' && (
          <BranchManagerPortal
            dossiers={dossiers}
            currentLang={currentLang}
            onSelectDossier={(d) => {
              // Could inspect details
            }}
            onUpdateStatus={handleUpdateDossierStatus}
          />
        )}

        {activeTab === 'questions' && (
          <FieldQuestionsExplorer
            currentLang={currentLang}
            onOpenWizard={() => setActiveTab('wizard')}
          />
        )}
      </main>

      {/* Voice Assistant Modal */}
      <VoiceAssistantModal
        isOpen={isVoiceModalOpen}
        onClose={() => setIsVoiceModalOpen(false)}
        currentLang={currentLang}
      />

      {/* Footer conforming to anti-slop rules */}
      <footer className="bg-white border-t border-slate-200 py-6 sm:py-8 px-4 sm:px-6 text-xs text-slate-600 mt-12 w-full max-w-full overflow-x-hidden">
        <div className="max-w-7xl mx-auto flex flex-col md:flex-row items-center justify-between gap-6 min-w-0">
          <div className="space-y-1 text-center md:text-left min-w-0">
            <div className="flex items-center justify-center md:justify-start gap-2 font-bold text-slate-900 text-sm">
              <span className="w-5 h-5 rounded bg-emerald-700 text-white flex items-center justify-center text-xs shrink-0">
                स
              </span>
              <span className="truncate">SahayCredit AI · ग्रामीण एमएसएमई ऋण सशक्तिकरण पहल</span>
            </div>
            <p className="text-slate-500 text-[11px]">
              Direct credit appraisal framework bridging viable rural micro-enterprises with formal banking institutions.
            </p>
          </div>

          <div className="flex flex-wrap items-center justify-center gap-4 sm:gap-6 text-[11px] font-medium text-slate-600">
            <span className="text-slate-400">Official Links:</span>
            <a
              href="https://www.jansamarth.in"
              target="_blank"
              rel="noreferrer"
              className="hover:text-emerald-700 flex items-center gap-1 transition-colors"
            >
              <span>JanSamarth Portal</span>
              <ExternalLink className="w-3 h-3 text-slate-400" />
            </a>
            <a
              href="https://www.mudra.org.in"
              target="_blank"
              rel="noreferrer"
              className="hover:text-emerald-700 flex items-center gap-1 transition-colors"
            >
              <span>MUDRA (PMMY)</span>
              <ExternalLink className="w-3 h-3 text-slate-400" />
            </a>
            <a
              href="https://www.kviconline.gov.in/pmegpeportal/"
              target="_blank"
              rel="noreferrer"
              className="hover:text-emerald-700 flex items-center gap-1 transition-colors"
            >
              <span>PMEGP e-Portal</span>
              <ExternalLink className="w-3 h-3 text-slate-400" />
            </a>
            <a
              href="https://pmvishwakarma.gov.in"
              target="_blank"
              rel="noreferrer"
              className="hover:text-emerald-700 flex items-center gap-1 transition-colors"
            >
              <span>PM Vishwakarma</span>
              <ExternalLink className="w-3 h-3 text-slate-400" />
            </a>
          </div>

          <div className="text-slate-400 text-[11px] text-center md:text-right shrink-0">
            <span>Anti-Bribery Grievance Toll-Free: 1800-180-6763</span>
          </div>
        </div>
      </footer>
    </div>
  );
}
