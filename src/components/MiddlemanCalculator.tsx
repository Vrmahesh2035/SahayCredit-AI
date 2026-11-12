import React, { useState } from 'react';
import { SupportedLanguage } from '../types';
import { TRANSLATIONS } from '../data/translations';
import { audioService } from '../services/audioService';
import {
  AlertOctagon,
  Volume2,
  TrendingDown,
  CheckCircle,
  HelpCircle,
} from 'lucide-react';

interface MiddlemanCalculatorProps {
  currentLang: SupportedLanguage;
}

export const MiddlemanCalculator: React.FC<MiddlemanCalculatorProps> = ({ currentLang }) => {
  const [loanAmount, setLoanAmount] = useState<number>(300000);
  const [tenureYears, setTenureYears] = useState<number>(3);
  const [agentCommissionPct, setAgentCommissionPct] = useState<number>(12); // PDF: 10-15%
  const [moneylenderRate, setMoneylenderRate] = useState<number>(30); // PDF: 24-36%

  const t = TRANSLATIONS[currentLang];

  // Calculations
  const agentCut = Math.round((loanAmount * agentCommissionPct) / 100);

  // Moneylender total interest at simple approximation (often compound in villages)
  const moneylenderInterest = Math.round(loanAmount * (moneylenderRate / 100) * tenureYears);
  const moneylenderTotal = loanAmount + moneylenderInterest;

  // Formal bank rate under MUDRA / CGTMSE / Vishwakarma (average 9% p.a.)
  const formalRate = 9.0;
  const formalInterest = Math.round(loanAmount * (formalRate / 100) * tenureYears);
  const formalTotal = loanAmount + formalInterest;

  // PMEGP Rural Subsidy (25% for general, 35% for women/SC/ST)
  const ruralSubsidy35 = Math.round(loanAmount * 0.35);
  const effectiveLoanWithSubsidy = loanAmount - ruralSubsidy35;

  // Total money saved by going direct via SahayCredit AI
  const totalMoneySaved = agentCut + (moneylenderInterest - formalInterest) + ruralSubsidy35;

  const handleAudioExplain = () => {
    const calcSpeechMap: Record<SupportedLanguage, string> = {
      hi: `अगर आप ₹${loanAmount.toLocaleString('en-IN')} का लोन लेते हैं, तो दलाल आपसे ₹${agentCut.toLocaleString(
        'en-IN'
      )} का कमीशन काट लेता है, और साहूकार 3 साल में ₹${moneylenderInterest.toLocaleString(
        'en-IN'
      )} का भारी ब्याज वसूल लेता है। जबकि सरकारी योजना में आपको शून्य दलाली देनी है और ₹${ruralSubsidy35.toLocaleString(
        'en-IN'
      )} तक की सरकारी सब्सिडी मिल सकती है। आप कुल ₹${totalMoneySaved.toLocaleString('en-IN')} बचा रहे हैं!`,
      ta: `நீங்கள் ₹${loanAmount.toLocaleString('en-IN')} கடன் வாங்கினால், இடைத்தரகர் ₹${agentCut.toLocaleString(
        'en-IN'
      )} கமிஷன் பறித்துக்கொள்வார். கந்துவட்டிக்காரர் ₹${moneylenderInterest.toLocaleString(
        'en-IN'
      )} வட்டி வசூலிப்பார். ஆனால் அரசு திட்டத்தில் 0% கமிஷன் மற்றும் ₹${ruralSubsidy35.toLocaleString(
        'en-IN'
      )} மானியம் கிடைக்கும்!`,
      te: `మీరు ₹${loanAmount.toLocaleString('en-IN')} లోన్ తీసుకుంటే, దళారీ ₹${agentCut.toLocaleString(
        'en-IN'
      )} కమీషన్ తీసుకుంటాడు. వడ్డీ వ్యాపారి ₹${moneylenderInterest.toLocaleString(
        'en-IN'
      )} అధిక వడ్డీ వసూలు చేస్తాడు. కానీ ప్రభుత్వ పథకంలో ₹0 కమీషన్ మరియు ₹${ruralSubsidy35.toLocaleString(
        'en-IN'
      )} సబ్సిడీ లభిస్తుంది!`,
      mr: `जर तुम्ही ₹${loanAmount.toLocaleString('en-IN')} चे कर्ज घेतले, तर दलाल ₹${agentCut.toLocaleString(
        'en-IN'
      )} चे कमिशन कापून घेतो, आणि सावकार ₹${moneylenderInterest.toLocaleString(
        'en-IN'
      )} व्याज घेतो. सरकारी योजनेत शून्य दलाली आणि ₹${ruralSubsidy35.toLocaleString(
        'en-IN'
      )} अनुदान मिळेल!`,
      bn: `যদি আপনি ₹${loanAmount.toLocaleString('en-IN')} ঋণ নেন, দালাল ₹${agentCut.toLocaleString(
        'en-IN'
      )} কেটে নেয়, এবং মহাজন ₹${moneylenderInterest.toLocaleString(
        'en-IN'
      )} সুদ নেয়। সরকারি প্রকল্পে আপনি শূন্য খরচে ₹${ruralSubsidy35.toLocaleString(
        'en-IN'
      )} অনুদান পাবেন!`,
      kn: `ನೀವು ₹${loanAmount.toLocaleString('en-IN')} ಸಾಲ ಪಡೆದರೆ, ಮಧ್ಯವರ್ತಿ ₹${agentCut.toLocaleString(
        'en-IN'
      )} ಕಮಿಷನ್ ಪಡೆಯುತ್ತಾನೆ. ಆದರೆ ಸರ್ಕಾರಿ ಯೋಜನೆಯಲ್ಲಿ ಶೂನ್ಯ ಕಮಿಷನ್ ಮತ್ತು ₹${ruralSubsidy35.toLocaleString(
        'en-IN'
      )} ಸಬ್ಸಿಡಿ ಲಭಿಸುತ್ತದೆ!`,
      gu: `જો તમે ₹${loanAmount.toLocaleString('en-IN')} ની લોન લો છો, તો દલાલ ₹${agentCut.toLocaleString(
        'en-IN'
      )} નું કમિશન કાપે છે. જ્યારે સરકારી યોજનામાં શૂન્ય દલાલી અને ₹${ruralSubsidy35.toLocaleString(
        'en-IN'
      )} સુધીની સબસિડી મળશે!`,
      en: `For a loan of ₹${loanAmount.toLocaleString('en-IN')}, a middleman takes ₹${agentCut.toLocaleString(
        'en-IN'
      )} in illegal cuts, and a moneylender charges ₹${moneylenderInterest.toLocaleString(
        'en-IN'
      )} in exorbitant interest. Under official schemes, you pay zero commission, get low interest, and up to ₹${ruralSubsidy35.toLocaleString(
        'en-IN'
      )} in capital subsidy. You save over ₹${totalMoneySaved.toLocaleString('en-IN')}!`,
    };

    const text = calcSpeechMap[currentLang] || calcSpeechMap.hi;
    audioService.speak(text, currentLang);
  };

  return (
    <div className="max-w-6xl mx-auto px-3 sm:px-4 md:px-6 py-6 sm:py-8 space-y-6 sm:space-y-8 w-full max-w-full overflow-x-hidden">
      {/* Top Banner */}
      <div className="bg-white border border-slate-200 rounded-2xl p-4 sm:p-6 md:p-8 shadow-xs w-full max-w-full overflow-hidden">
        <div className="flex flex-col md:flex-row items-start md:items-center justify-between gap-6 pb-6 border-b border-slate-100">
          <div>
            <div className="text-xs font-semibold text-emerald-800 uppercase tracking-wider mb-1 flex items-center gap-1.5">
              <AlertOctagon className="w-4 h-4 text-emerald-700" />
              <span>
                {currentLang === 'hi'
                  ? 'पीडीएफ प्रश्न 3: दलाल क्या लेता है और क्या करता है?'
                  : 'PDF Question 3: What does the agent charge and actually do?'}
              </span>
            </div>
            <h1 className="text-2xl sm:text-3xl font-bold text-slate-900 tracking-tight">
              {currentLang === 'hi'
                ? 'दलाल की लूट व साहूकार के चक्रव्यूह से आजादी'
                : 'Middleman Cut & Moneylender Trap Calculator'}
            </h1>
            <p className="text-sm text-slate-600 mt-1 max-w-3xl">
              {currentLang === 'hi'
                ? 'सरकारी योजनाओं (MUDRA, PMEGP, PM Vishwakarma) में कोई दलाल या एजेंट अधिकृत नहीं है। जानिए कैसे बिचौलिए आपकी मेहनत की 10-15% कमाई खा जाते हैं।'
                : 'Government schemes are 100% direct. See how middlemen siphon off 10-15% upfront and push viable enterprises into 24-36% moneylender debts.'}
            </p>
          </div>
          <button
            onClick={handleAudioExplain}
            className="inline-flex items-center gap-2 px-4 py-2.5 rounded-xl bg-emerald-700 hover:bg-emerald-800 text-white font-semibold text-sm transition-colors shadow-xs cursor-pointer shrink-0"
          >
            <Volume2 className="w-4 h-4" />
            <span>{t.listenAudio}</span>
          </button>
        </div>

        {/* Sliders for Loan Amount and Agent Percentage */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 pt-6">
          <div className="space-y-2">
            <div className="flex justify-between items-center text-sm">
              <label className="font-semibold text-slate-800">
                {currentLang === 'hi' ? 'जरूरी लोन राशि:' : 'Required Loan Amount:'}
              </label>
              <span className="font-bold text-emerald-800 font-mono tabular-nums text-base">
                ₹{loanAmount.toLocaleString('en-IN')}
              </span>
            </div>
            <input
              type="range"
              min="50000"
              max="2000000"
              step="25000"
              value={loanAmount}
              onChange={(e) => setLoanAmount(Number(e.target.value))}
              aria-label="Loan Amount Slider"
              className="w-full accent-emerald-700 cursor-pointer"
            />
            <div className="flex justify-between text-[11px] text-slate-600">
              <span>₹50,000 (Shishu)</span>
              <span>₹5,00,000 (Kishore)</span>
              <span>₹20,00,000 (Tarun)</span>
            </div>
          </div>

          <div className="space-y-2">
            <div className="flex justify-between items-center text-sm">
              <label className="font-semibold text-slate-800">
                {currentLang === 'hi' ? 'दलाल / एजेंट का कमीशन:' : 'Agent / Middleman Cut:'}
              </label>
              <span className="font-bold text-amber-700 font-mono tabular-nums text-base">
                {agentCommissionPct}% (₹{agentCut.toLocaleString('en-IN')})
              </span>
            </div>
            <input
              type="range"
              min="5"
              max="20"
              step="1"
              value={agentCommissionPct}
              onChange={(e) => setAgentCommissionPct(Number(e.target.value))}
              aria-label="Agent Commission Percentage Slider"
              className="w-full accent-amber-600 cursor-pointer"
            />
            <div className="flex justify-between text-[11px] text-slate-600">
              <span>5%</span>
              <span className="text-amber-800 font-semibold">10-15% (PDF Standard)</span>
              <span>20%</span>
            </div>
          </div>

          <div className="space-y-2">
            <div className="flex justify-between items-center text-sm">
              <label className="font-semibold text-slate-800">
                {currentLang === 'hi' ? 'साहूकार की ब्याज दर:' : 'Local Moneylender Rate:'}
              </label>
              <span className="font-bold text-rose-700 font-mono tabular-nums text-base">
                {moneylenderRate}% p.a.
              </span>
            </div>
            <input
              type="range"
              min="18"
              max="48"
              step="2"
              value={moneylenderRate}
              onChange={(e) => setMoneylenderRate(Number(e.target.value))}
              aria-label="Moneylender Rate Slider"
              className="w-full accent-rose-600 cursor-pointer"
            />
            <div className="flex justify-between text-[11px] text-slate-600">
              <span>18%</span>
              <span className="text-rose-800 font-semibold">24-36% (Village Trap)</span>
              <span>48%</span>
            </div>
          </div>
        </div>
      </div>

      {/* 3-Column Comparison Grid */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {/* Column 1: Moneylender Trap */}
        <div className="bg-white border border-rose-200 rounded-2xl p-6 shadow-xs flex flex-col justify-between">
          <div>
            <div className="text-xs font-bold text-rose-800 uppercase tracking-wider mb-2">
              {currentLang === 'hi' ? 'विकल्प 1: गांव का साहूकार' : 'Option 1: Village Moneylender'}
            </div>
            <h2 className="text-lg font-bold text-slate-900 mb-4">
              {currentLang === 'hi' ? '24% - 36% शोषणकारी ब्याज' : '24% - 36% Predatory Debt'}
            </h2>

            <div className="space-y-3 text-xs sm:text-sm text-slate-700">
              <div className="flex justify-between py-1.5 border-b border-slate-100">
                <span>{currentLang === 'hi' ? 'मूलधन:' : 'Principal:'}</span>
                <span className="font-mono font-medium">₹{loanAmount.toLocaleString('en-IN')}</span>
              </div>
              <div className="flex justify-between py-1.5 border-b border-slate-100">
                <span>{currentLang === 'hi' ? '3 साल का कुल ब्याज:' : '3-Yr Total Interest:'}</span>
                <span className="font-mono font-bold text-rose-600">
                  +₹{moneylenderInterest.toLocaleString('en-IN')}
                </span>
              </div>
              <div className="flex justify-between py-1.5 border-b border-slate-100">
                <span>{currentLang === 'hi' ? 'सरकारी सब्सिडी:' : 'Govt Subsidy:'}</span>
                <span className="font-mono text-slate-600">₹0 (शून्य)</span>
              </div>
              <div className="flex justify-between py-2 bg-rose-50 px-3 rounded-lg text-rose-950 font-bold">
                <span>{currentLang === 'hi' ? 'कुल जेब से भुगतान:' : 'Total Out of Pocket:'}</span>
                <span className="font-mono">₹{moneylenderTotal.toLocaleString('en-IN')}</span>
              </div>
            </div>
          </div>

          <div className="mt-6 p-3 bg-slate-50 rounded-xl text-xs text-slate-600">
            <span className="font-semibold block text-slate-800 mb-1">
              {currentLang === 'hi' ? 'नुकसान:' : 'Consequence:'}
            </span>
            {currentLang === 'hi'
              ? 'ब्याज चुकाते-चुकाते व्यापार का पूरा मुनाफा खत्म हो जाता है और संपत्ति गिरवी रखनी पड़ती है।'
              : 'Working capital is eaten by interest; enterprise growth gets postponed season after season.'}
          </div>
        </div>

        {/* Column 2: Corrupt Middleman / Agent */}
        <div className="bg-white border border-amber-200 rounded-2xl p-6 shadow-xs flex flex-col justify-between">
          <div>
            <div className="text-xs font-bold text-amber-800 uppercase tracking-wider mb-2">
              {currentLang === 'hi' ? 'विकल्प 2: बैंक एजेंट / बिचौलिया' : 'Option 2: Middleman / Agent'}
            </div>
            <h2 className="text-lg font-bold text-slate-900 mb-4">
              {currentLang === 'hi' ? '10-15% अवैध कमीशन' : '10-15% Siphoned Commission'}
            </h2>

            <div className="space-y-3 text-xs sm:text-sm text-slate-700">
              <div className="flex justify-between py-1.5 border-b border-slate-100">
                <span>{currentLang === 'hi' ? 'लोन स्वीकृत:' : 'Sanctioned Loan:'}</span>
                <span className="font-mono font-medium">₹{loanAmount.toLocaleString('en-IN')}</span>
              </div>
              <div className="flex justify-between py-1.5 border-b border-slate-100">
                <span>{currentLang === 'hi' ? 'दलाल द्वारा कटी रकम:' : 'Agent Cut Taken:'}</span>
                <span className="font-mono font-bold text-amber-700">
                  -₹{agentCut.toLocaleString('en-IN')}
                </span>
              </div>
              <div className="flex justify-between py-1.5 border-b border-slate-100">
                <span>{currentLang === 'hi' ? 'हाथ में मिली वास्तविक पूंजी:' : 'Actual Capital Received:'}</span>
                <span className="font-mono font-bold text-slate-900">
                  ₹{(loanAmount - agentCut).toLocaleString('en-IN')}
                </span>
              </div>
              <div className="flex justify-between py-2 bg-amber-50 px-3 rounded-lg text-amber-950 font-bold">
                <span>{currentLang === 'hi' ? 'औसत फाइल लटकाने का समय:' : 'Average File Delay:'}</span>
                <span>{currentLang === 'hi' ? '3 से 6 महीने' : '3 - 6 Months'}</span>
              </div>
            </div>
          </div>

          <div className="mt-6 p-3 bg-slate-50 rounded-xl text-xs text-slate-600">
            <span className="font-semibold block text-slate-800 mb-1">
              {currentLang === 'hi' ? 'सच्चाई (PDF प्रश्न 3):' : 'Ground Reality (PDF Q3):'}
            </span>
            {currentLang === 'hi'
              ? 'दलाल केवल बैंक फॉर्म भरता है और झूठा आश्वासन देता है। कोई भी सरकारी अधिकारी दलाली की मांग नहीं करता।'
              : 'Agents only photocopy forms and delay files. Official rules require zero fees.'}
          </div>
        </div>

        {/* Column 3: SahayCredit AI Direct Government Scheme */}
        <div className="bg-emerald-50/70 border-2 border-emerald-600 rounded-2xl p-6 shadow-md flex flex-col justify-between relative">
          <div className="absolute -top-3 right-4 bg-emerald-700 text-white text-[11px] font-bold px-3 py-0.5 rounded-full shadow-xs">
            {currentLang === 'hi' ? 'अनुशंसित व 100% सुरक्षित' : 'Recommended & 100% Direct'}
          </div>

          <div>
            <div className="text-xs font-bold text-emerald-800 uppercase tracking-wider mb-2">
              {currentLang === 'hi' ? 'विकल्प 3: सहाय क्रेडिट सीधी बैंक अर्जी' : 'Option 3: SahayCredit AI Direct File'}
            </div>
            <h2 className="text-lg font-bold text-emerald-950 mb-4">
              {currentLang === 'hi' ? '0% दलाली + 35% तक सब्सिडी' : '0% Commission + Up to 35% Subsidy'}
            </h2>

            <div className="space-y-3 text-xs sm:text-sm text-slate-800">
              <div className="flex justify-between py-1.5 border-b border-emerald-200">
                <span>{currentLang === 'hi' ? 'दलाल को दिया शुल्क:' : 'Middleman Fee:'}</span>
                <span className="font-mono font-bold text-emerald-700">₹0 (शून्य)</span>
              </div>
              <div className="flex justify-between py-1.5 border-b border-emerald-200">
                <span>{currentLang === 'hi' ? 'बैंक ब्याज (MUDRA/Vishwakarma):' : 'Bank Interest Rate:'}</span>
                <span className="font-mono font-semibold">5% - 9.5% p.a.</span>
              </div>
              <div className="flex justify-between py-1.5 border-b border-emerald-200">
                <span>{currentLang === 'hi' ? 'PMEGP 35% ग्रामीण सब्सिडी:' : '35% Rural Subsidy:'}</span>
                <span className="font-mono font-bold text-emerald-700">
                  +₹{ruralSubsidy35.toLocaleString('en-IN')}
                </span>
              </div>
              <div className="flex justify-between py-2 bg-emerald-700 text-white px-3 rounded-lg font-bold">
                <span>{currentLang === 'hi' ? 'कुल सीधी बचत:' : 'Total Direct Savings:'}</span>
                <span className="font-mono text-emerald-100">
                  ₹{totalMoneySaved.toLocaleString('en-IN')}
                </span>
              </div>
            </div>
          </div>

          <div className="mt-6 p-3 bg-white/80 rounded-xl text-xs text-slate-700 border border-emerald-200">
            <div className="flex items-center gap-1.5 text-emerald-800 font-bold mb-1">
              <CheckCircle className="w-4 h-4" />
              <span>{currentLang === 'hi' ? 'बैंक मैनेजर को क्या चाहिए:' : 'What Bank Manager Needs:'}</span>
            </div>
            {currentLang === 'hi'
              ? 'प्रमाणित UPI लेन-देन, DSCR किस्त क्षमता और CGFMU/CGTMSE सरकारी गारंटी।'
              : 'Verified UPI cash flows, calibrated DSCR, and statutory guarantee citations.'}
          </div>
        </div>
      </div>

      {/* Field Insight Box answering PDF Question 3 */}
      <div className="bg-slate-900 text-white rounded-2xl p-6 sm:p-8 space-y-4">
        <div className="flex items-center gap-3">
          <div className="w-9 h-9 rounded-xl bg-emerald-600 flex items-center justify-center text-white font-bold">
            <HelpCircle className="w-5 h-5" />
          </div>
          <div>
            <h3 className="text-base sm:text-lg font-bold">
              {currentLang === 'hi'
                ? 'जमीनी सच्चाई: दलाल क्या करता है और बैंक फाइल क्यों अटकती है?'
                : 'Field Insight: What Does the Agent Charge, and What Does He Actually Do?'}
            </h3>
            <span className="text-xs text-slate-400">
              {currentLang === 'hi'
                ? 'स्रोत: पृष्ठ 1 - "Questions to take into the field"'
                : 'Citation: Page 1 of field study attachment'}
            </span>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 text-sm text-slate-300 pt-2">
          <div className="space-y-2">
            <span className="font-semibold text-white block">
              {currentLang === 'hi' ? 'दलाल क्या करता है:' : 'What the Middleman Actually Does:'}
            </span>
            <p className="text-xs sm:text-sm leading-relaxed">
              {currentLang === 'hi'
                ? 'दलाल केवल बैंक से फॉर्म लाकर कुछ अनौपचारिक कागजों की फोटोकॉपी करता है और बैंक मित्र या मैनेजर से जान-पहचान का झांसा देकर 10 से 15% की भारी रकम ऐंठता है। कई बार फाइल महीनों लटकी रहती है और अंततः खारिज हो जाती है।'
                : 'Agents merely collect loose photocopies, promise informal access to the branch, and siphon off 10-15% of the loan. Most stalled files originate from half-filled agent applications.'}
            </p>
          </div>
          <div className="space-y-2">
            <span className="font-semibold text-white block">
              {currentLang === 'hi' ? 'सहाय क्रेडिट AI समाधान:' : 'The SahayCredit AI Solution:'}
            </span>
            <p className="text-xs sm:text-sm leading-relaxed">
              {currentLang === 'hi'
                ? 'हम आपके दैनिक UPI क्यूआर कोड और कच्ची बही को सीधे बैंक-स्वीकृत क्रेडिट अप्रेजल रिपोर्ट (Dossier) में बदलते हैं। इसमें रिजर्व बैंक और MSME मंत्रालय के नियमों का हवाला होता है, जिससे बैंक मैनेजर 48 घंटे में फाइल स्वीकृत कर सकता है।'
                : 'We transform daily UPI transactions and handwritten khata entries into a certified credit viability appraisal with legal scheme citations and DSCR metrics, reducing lender turnaround from 90 days to 48 hours.'}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};
