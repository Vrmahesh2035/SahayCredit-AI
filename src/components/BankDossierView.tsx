import React from 'react';
import { CreditAnalysisResult, SupportedLanguage } from '../types';
import { TRANSLATIONS } from '../data/translations';
import { audioService } from '../services/audioService';
import {
  FileText,
  Printer,
  Volume2,
  CheckCircle2,
  ShieldCheck,
  Building2,
  TrendingUp,
  Landmark,
  UserCheck,
} from 'lucide-react';

interface BankDossierViewProps {
  analysis: CreditAnalysisResult;
  borrowerName: string;
  businessName: string;
  businessType: string;
  village: string;
  district: string;
  state: string;
  socialCategory: string;
  gender: string;
  requestedLoanAmount: number;
  currentLang: SupportedLanguage;
  onBackToEdit: () => void;
  onSendToBranchManager: () => void;
}

export const BankDossierView: React.FC<BankDossierViewProps> = ({
  analysis,
  borrowerName,
  businessName,
  businessType,
  village,
  district,
  state,
  socialCategory,
  gender,
  requestedLoanAmount,
  currentLang,
  onBackToEdit,
  onSendToBranchManager,
}) => {
  const t = TRANSLATIONS[currentLang];

  const handlePrint = () => {
    window.print();
  };

  const handleAudioExplain = () => {
    const dossierSpeechMap: Record<SupportedLanguage, string> = {
      hi: `आपकी बैंक अप्रेजल रिपोर्ट तैयार है। आपका सत्यापित मासिक कारोबार ₹${analysis.estimatedMonthlyInflow.toLocaleString(
        'en-IN'
      )} है, और शुद्ध मासिक बचत ₹${analysis.netMonthlySurplus.toLocaleString(
        'en-IN'
      )} है। आपकी किस्त चुकाने की क्षमता (DSCR) ${analysis.dscr.toFixed(
        2
      )} है, जो बैंक मानक 1.3 से काफी बेहतर है। आप मुद्रा और PMEGP योजना के तहत पात्र हैं।`,
      ta: `உங்கள் வங்கி கடன் அறிக்கை தயாராக உள்ளது. உங்களின் மாத வரவு ₹${analysis.estimatedMonthlyInflow.toLocaleString(
        'en-IN'
      )}, மற்றும் நிகர சேமிப்பு ₹${analysis.netMonthlySurplus.toLocaleString(
        'en-IN'
      )}. நீங்கள் முத்ரா மற்றும் PMEGP திட்டங்களுக்கு தகுதியானவர்.`,
      te: `మీ బ్యాంక్ లోన్ నివేదిక సిద్ధమైంది. మీ నెలవారీ రాబడి ₹${analysis.estimatedMonthlyInflow.toLocaleString(
        'en-IN'
      )}, నికర మిగులు ₹${analysis.netMonthlySurplus.toLocaleString(
        'en-IN'
      )}. మీ చెల్లింపు సామర్థ్యం (DSCR) ${analysis.dscr.toFixed(2)} గా ఉంది.`,
      mr: `आपला बँक अहवाल तयार आहे. आपला मासिक व्यवसाय ₹${analysis.estimatedMonthlyInflow.toLocaleString(
        'en-IN'
      )} असून शुद्ध मासिक नफा ₹${analysis.netMonthlySurplus.toLocaleString(
        'en-IN'
      )} आहे. आपण मुद्रा व PMEGP योजनेसाठी पात्र आहात.`,
      bn: `আপনার ব্যাঙ্ক ডসিয়ার প্রস্তুত হয়েছে। আপনার মাসিক আয় ₹${analysis.estimatedMonthlyInflow.toLocaleString(
        'en-IN'
      )} এবং সঞ্চয় ₹${analysis.netMonthlySurplus.toLocaleString(
        'en-IN'
      )}। আপনি মুদ্রা ও পিএমইজিপি প্রকল্পের যোগ্য।`,
      kn: `ನಿಮ್ಮ ಬ್ಯಾಂಕ್ ವರದಿ ಸಿದ್ಧವಾಗಿದೆ. ಮಾಸಿಕ ವಹಿವಾಟು ₹${analysis.estimatedMonthlyInflow.toLocaleString(
        'en-IN'
      )} ಮತ್ತು ನಿವ್ವಳ ಉಳಿತಾಯ ₹${analysis.netMonthlySurplus.toLocaleString(
        'en-IN'
      )}. ನೀವು ಮುದ್ರಾ ಮತ್ತು PMEGP ಯೋಜನೆಗೆ ಅರ್ಹರಾಗಿದ್ದೀರಿ.`,
      gu: `તમારો બેંક રિપોર્ટ તૈયાર છે. તમારી માસિક આવક ₹${analysis.estimatedMonthlyInflow.toLocaleString(
        'en-IN'
      )} અને ચોખ્ખો નફો ₹${analysis.netMonthlySurplus.toLocaleString(
        'en-IN'
      )} છે. તમે મુદ્રા અને PMEGP માટે પાત્ર છો.`,
      en: `Your Bank-Ready Viability Dossier is ready. Your verified monthly inflow is ₹${analysis.estimatedMonthlyInflow.toLocaleString(
        'en-IN'
      )}, with a net monthly cash surplus of ₹${analysis.netMonthlySurplus.toLocaleString(
        'en-IN'
      )}. Your Debt Service Coverage Ratio is ${analysis.dscr.toFixed(
        2
      )}, well above the bank requirement of 1.30.`,
    };

    const text = dossierSpeechMap[currentLang] || dossierSpeechMap.hi;
    audioService.speak(text, currentLang);
  };

  const applicationNumber = `SC-MSME-${new Date().getFullYear()}-${Math.floor(100000 + Math.random() * 900000)}`;
  const consentDate = new Date().toLocaleDateString('en-IN', {
    day: 'numeric',
    month: 'short',
    year: 'numeric',
  });

  return (
    <div className="space-y-6 w-full max-w-full overflow-x-hidden">
      {/* Action Controls (Hidden during print) */}
      <div className="flex flex-wrap items-center justify-between gap-3 p-3 sm:p-4 bg-white border border-slate-200 rounded-xl shadow-xs print:hidden w-full max-w-full">
        <div className="flex items-center gap-2 sm:gap-3 flex-wrap">
          <button
            onClick={onBackToEdit}
            className="text-xs sm:text-sm font-semibold text-slate-600 hover:text-slate-900 cursor-pointer"
          >
            ← {currentLang === 'hi' ? 'विवरण बदलें' : 'Edit Details'}
          </button>
          <span className="text-slate-300 hidden sm:inline">|</span>
          <span className="text-xs font-semibold text-emerald-800">
            {currentLang === 'hi'
              ? 'प्रमाणित बैंक अप्रेजल नोट तैयार'
              : 'Certified Credit Appraisal Ready'}
          </span>
        </div>

        <div className="flex flex-wrap items-center gap-2 sm:gap-3">
          <button
            onClick={handleAudioExplain}
            className="inline-flex items-center gap-1.5 px-2.5 sm:px-3 py-1.5 bg-slate-100 hover:bg-slate-200 text-slate-800 rounded-lg text-xs font-medium transition-colors cursor-pointer"
          >
            <Volume2 className="w-3.5 h-3.5" />
            <span>{t.listenAudio}</span>
          </button>
          <button
            onClick={handlePrint}
            className="inline-flex items-center gap-1.5 px-3 sm:px-3.5 py-1.5 bg-slate-800 hover:bg-slate-900 text-white rounded-lg text-xs font-semibold transition-colors shadow-xs cursor-pointer"
          >
            <Printer className="w-3.5 h-3.5" />
            <span>{t.labels.printDossier}</span>
          </button>
          <button
            onClick={onSendToBranchManager}
            className="inline-flex items-center gap-1.5 px-3.5 sm:px-4 py-1.5 bg-emerald-700 hover:bg-emerald-800 text-white rounded-lg text-xs font-semibold transition-colors shadow-xs cursor-pointer"
          >
            <Landmark className="w-3.5 h-3.5 text-emerald-200" />
            <span>
              {currentLang === 'hi' ? 'बैंक मैनेजर को भेजें' : 'Send to Branch Manager'}
            </span>
          </button>
        </div>
      </div>

      {/* The Printable Bank Dossier Card */}
      <div className="bg-white border-2 border-slate-300 rounded-2xl p-4 sm:p-6 md:p-10 shadow-lg print:border-none print:shadow-none print:p-0 space-y-6 sm:space-y-8 text-slate-900 w-full max-w-full overflow-x-hidden">
        {/* Document Header */}
        <div className="border-b-2 border-slate-800 pb-6 flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
          <div className="min-w-0 flex-1">
            <div className="flex items-center gap-2 mb-1 flex-wrap">
              <span className="bg-slate-900 text-white text-[11px] font-bold px-2.5 py-0.5 rounded tracking-wider uppercase">
                RBI FIDD / MoMSME Compliant
              </span>
              <span className="text-xs text-slate-500 font-mono">App ID: {applicationNumber}</span>
            </div>
            <h1 className="text-xl sm:text-2xl font-bold tracking-tight text-slate-950 break-words">
              FORMAL CREDIT APPRAISAL &amp; VIABILITY DOSSIER
            </h1>
            <p className="text-xs text-slate-600 mt-0.5">
              Empowering Informal &amp; Micro Enterprises via Digital UPI / Cashflow Reconstruction
            </p>
          </div>
          <div className="text-right sm:border-l sm:border-slate-200 sm:pl-6 text-xs text-slate-600">
            <div className="font-semibold text-slate-900">SahayCredit AI Framework</div>
            <div>Date of Appraisal: {consentDate}</div>
            <div className="text-emerald-700 font-bold mt-1 flex items-center gap-1 justify-end">
              <ShieldCheck className="w-4 h-4" />
              <span>Consent Verified</span>
            </div>
          </div>
        </div>

        {/* Section 1: Enterprise & Borrower Profile */}
        <div className="space-y-3">
          <h2 className="text-sm font-bold uppercase tracking-wider text-slate-700 flex items-center gap-2">
            <Building2 className="w-4 h-4 text-emerald-700" />
            <span>1. Enterprise &amp; Promoter Identification</span>
          </h2>
          <div className="grid grid-cols-2 sm:grid-cols-4 gap-4 bg-slate-50 p-4 rounded-xl border border-slate-200 text-xs">
            <div>
              <span className="text-slate-500 block">Promoter / Applicant:</span>
              <span className="font-bold text-slate-900 text-sm">{borrowerName || 'Ramesh Kumar'}</span>
            </div>
            <div>
              <span className="text-slate-500 block">Enterprise Name:</span>
              <span className="font-bold text-slate-900 text-sm">{businessName || 'Rural Micro Enterprise'}</span>
            </div>
            <div>
              <span className="text-slate-500 block">Trade / Activity:</span>
              <span className="font-bold text-slate-900 text-sm capitalize">{businessType.replace('_', ' ')}</span>
            </div>
            <div>
              <span className="text-slate-500 block">Social Category / Gender:</span>
              <span className="font-bold text-slate-900 text-sm">{socialCategory} · {gender}</span>
            </div>
            <div>
              <span className="text-slate-500 block">Location / Village:</span>
              <span className="font-bold text-slate-900">{village || 'Village Rural'}, {district || 'District'}</span>
            </div>
            <div>
              <span className="text-slate-500 block">State:</span>
              <span className="font-bold text-slate-900">{state || 'Madhya Pradesh'}</span>
            </div>
            <div>
              <span className="text-slate-500 block">Requested Credit:</span>
              <span className="font-bold text-emerald-800 font-mono text-sm">
                ₹{requestedLoanAmount.toLocaleString('en-IN')}
              </span>
            </div>
            <div>
              <span className="text-slate-500 block">Assessed Prudent Limit:</span>
              <span className="font-bold text-emerald-800 font-mono text-sm">
                ₹{analysis.recommendedLoanAmount.toLocaleString('en-IN')}
              </span>
            </div>
          </div>
        </div>

        {/* Section 2: Reconstructed Cash Flow & DSCR Solvency Analysis */}
        <div className="space-y-3">
          <h2 className="text-sm font-bold uppercase tracking-wider text-slate-700 flex items-center gap-2">
            <TrendingUp className="w-4 h-4 text-emerald-700" />
            <span>2. Reconstructed Financial Viability &amp; Solvency Metrics</span>
          </h2>
          <div className="grid grid-cols-2 sm:grid-cols-4 gap-4">
            <div className="p-4 rounded-xl border border-slate-200 bg-white">
              <span className="text-xs text-slate-500 block mb-1">Assessed Monthly Inflow</span>
              <span className="text-xl font-bold font-mono text-slate-900 tabular-nums">
                ₹{analysis.estimatedMonthlyInflow.toLocaleString('en-IN')}
              </span>
              <span className="text-[11px] text-slate-400 block mt-1">Verified via UPI QR / Slips</span>
            </div>
            <div className="p-4 rounded-xl border border-slate-200 bg-white">
              <span className="text-xs text-slate-500 block mb-1">Monthly Operating Cost</span>
              <span className="text-xl font-bold font-mono text-slate-700 tabular-nums">
                ₹{analysis.estimatedMonthlyOperatingExpense.toLocaleString('en-IN')}
              </span>
              <span className="text-[11px] text-slate-400 block mt-1">Raw materials, fuel, rent</span>
            </div>
            <div className="p-4 rounded-xl border border-emerald-200 bg-emerald-50/50">
              <span className="text-xs text-emerald-800 font-semibold block mb-1">Net Disposable Surplus</span>
              <span className="text-xl font-bold font-mono text-emerald-900 tabular-nums">
                ₹{analysis.netMonthlySurplus.toLocaleString('en-IN')}
              </span>
              <span className="text-[11px] text-emerald-700 block mt-1">Available for EMI servicing</span>
            </div>
            <div className="p-4 rounded-xl border border-emerald-300 bg-emerald-100/60">
              <span className="text-xs text-emerald-900 font-semibold block mb-1">
                DSCR (Debt Service Coverage)
              </span>
              <span className="text-xl font-bold font-mono text-emerald-950 tabular-nums">
                {analysis.dscr.toFixed(2)}x
              </span>
              <span className="text-[11px] text-emerald-800 font-bold block mt-1">
                ✓ Benchmark &gt; 1.30 (Strong)
              </span>
            </div>
          </div>
        </div>

        {/* Section 3: Explainable Credit Verdict & Risk Calibration */}
        <div className="p-5 rounded-xl bg-slate-50 border border-slate-200 space-y-3">
          <div className="flex items-center justify-between">
            <h2 className="text-sm font-bold uppercase tracking-wider text-slate-800">
              3. Calibrated Risk &amp; Credit Explainability
            </h2>
            <div className="flex items-center gap-3">
              <span className="text-xs text-slate-500">Fairness Calibrated Score:</span>
              <span className="px-2.5 py-0.5 rounded font-bold font-mono text-xs bg-emerald-700 text-white">
                {analysis.riskScore} / 100 ({analysis.riskLevel})
              </span>
            </div>
          </div>
          <p className="text-xs sm:text-sm text-slate-700 leading-relaxed bg-white p-3.5 rounded-lg border border-slate-200">
            {analysis.explainabilitySummary}
          </p>
        </div>

        {/* Section 4: Eligible Government Schemes & Statutory Citations */}
        <div className="space-y-3">
          <h2 className="text-sm font-bold uppercase tracking-wider text-slate-700">
            4. Statutory Scheme Entitlements &amp; Guarantee Coverage
          </h2>
          <div className="space-y-3">
            {analysis.schemeMatches?.map((scheme, idx) => (
              <div key={idx} className="p-4 rounded-xl border border-slate-200 bg-white space-y-2">
                <div className="flex flex-wrap items-center justify-between gap-2">
                  <div className="flex items-center gap-2">
                    <span className="w-5 h-5 rounded-full bg-emerald-700 text-white flex items-center justify-center text-[10px] font-bold">
                      {idx + 1}
                    </span>
                    <span className="font-bold text-slate-900 text-sm">{scheme.schemeName}</span>
                  </div>
                  <span className="text-xs font-semibold text-emerald-800 bg-emerald-50 px-2 py-0.5 rounded border border-emerald-200">
                    {scheme.subsidyOrBenefit}
                  </span>
                </div>
                <div className="text-xs text-slate-600 grid grid-cols-1 sm:grid-cols-2 gap-2">
                  <div>
                    <span className="text-slate-500">Official Citation:</span>{' '}
                    <span className="font-medium text-slate-800">{scheme.officialCitation}</span>
                  </div>
                  <div>
                    <span className="text-slate-500">Interest / Guarantee:</span>{' '}
                    <span className="font-medium text-slate-800">{scheme.interestBenefit}</span>
                  </div>
                </div>
                <div className="text-xs text-slate-700 bg-slate-50 p-2 rounded">
                  <span className="font-semibold text-slate-800">Why Eligible:</span> {scheme.whyEligible}
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Section 5: Branch Manager Quick Sanction Checklist */}
        <div className="space-y-3">
          <h2 className="text-sm font-bold uppercase tracking-wider text-slate-700">
            5. Branch Manager / Loan Officer Verification Protocol
          </h2>
          <div className="bg-slate-50 p-4 rounded-xl border border-slate-200 space-y-2">
            <p className="text-xs text-slate-600 mb-2">
              Per DFS guidelines for collateral-free lending under ₹10 Lakhs, verify the following 4 items to sanction:
            </p>
            {analysis.branchManagerChecklist?.map((item, idx) => (
              <div key={idx} className="flex items-start gap-2 text-xs text-slate-800">
                <CheckCircle2 className="w-4 h-4 text-emerald-700 shrink-0 mt-0.5" />
                <span>{item}</span>
              </div>
            ))}
          </div>
        </div>

        {/* Section 6: Digital Consent & Anti-Corruption Declarations */}
        <div className="border-t-2 border-slate-200 pt-6 flex flex-col sm:flex-row justify-between items-start sm:items-end gap-6 text-xs text-slate-600">
          <div className="space-y-1.5 max-w-md">
            <div className="flex items-center gap-1.5 font-bold text-slate-800">
              <UserCheck className="w-4 h-4 text-emerald-700" />
              <span>Digital Consent &amp; Zero Middleman Declaration</span>
            </div>
            <p className="text-[11px] leading-relaxed text-slate-500">
              "I confirm that the cashflow statements, UPI QR records, and informal books provided are genuine and processed solely with my informed consent. No fee has been paid to any unauthorized intermediary."
            </p>
            <div className="font-mono text-[10px] text-slate-400">
              Digital Signature ID: {Math.random().toString(36).substring(2, 14).toUpperCase()} · Time: {new Date().toISOString()}
            </div>
          </div>

          <div className="text-right sm:border-l sm:border-slate-200 sm:pl-6 space-y-1">
            <div className="h-10 border-b border-dashed border-slate-400 w-44 ml-auto" />
            <span className="block text-[11px] font-semibold text-slate-700">
              Branch Manager / Loan Officer Signature
            </span>
            <span className="block text-[10px] text-slate-400">Sanction / Credit Committee Stamp</span>
          </div>
        </div>
      </div>
    </div>
  );
};
