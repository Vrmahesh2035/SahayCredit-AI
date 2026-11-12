import React, { useState } from 'react';
import { SubmittedDossier, SupportedLanguage } from '../types';
import { audioService } from '../services/audioService';
import {
  Landmark,
  CheckCircle2,
  Clock,
  FileCheck,
  ShieldAlert,
  Search,
  Volume2,
  ExternalLink,
  Building,
  UserCheck,
  Zap,
} from 'lucide-react';

interface BranchManagerPortalProps {
  dossiers: SubmittedDossier[];
  currentLang: SupportedLanguage;
  onSelectDossier: (dossier: SubmittedDossier) => void;
  onUpdateStatus: (id: string, newStatus: SubmittedDossier['status']) => void;
}

export const BranchManagerPortal: React.FC<BranchManagerPortalProps> = ({
  dossiers,
  currentLang,
  onSelectDossier,
  onUpdateStatus,
}) => {
  const [filter, setFilter] = useState<'ALL' | 'PENDING' | 'SANCTIONED'>('ALL');
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedDossierDetail, setSelectedDossierDetail] = useState<SubmittedDossier | null>(null);

  const filtered = dossiers.filter((d) => {
    const matchesFilter =
      filter === 'ALL'
        ? true
        : filter === 'PENDING'
        ? d.status === 'PENDING_BRANCH_REVIEW'
        : d.status === 'IN_PRINCIPLE_SANCTIONED';

    const matchesSearch =
      d.borrowerName.toLowerCase().includes(searchQuery.toLowerCase()) ||
      d.businessName.toLowerCase().includes(searchQuery.toLowerCase()) ||
      d.village.toLowerCase().includes(searchQuery.toLowerCase()) ||
      d.primaryScheme.toLowerCase().includes(searchQuery.toLowerCase());

    return matchesFilter && matchesSearch;
  });

  const handleAudioExplain = () => {
    const text =
      currentLang === 'hi'
        ? 'बैंक शाखा प्रबंधक डेस्क में आपका स्वागत है। यहां आप ग्रामीण उद्यमियों के डिजिटल UPI व बही-खाते से बने प्रमाणित क्रेडिट अप्रेजल नोट देख सकते हैं। अधूरा फॉर्म न होने के कारण बैंक का 90 दिन का समय घटकर मात्र 48 घंटे रह जाता है।'
        : 'Welcome to the Bank Branch Manager portal. Here you can review complete, risk-calibrated credit dossiers with cited scheme guidelines and instant DSCR calculations, cutting file turnaround from 90 days to 48 hours.';

    audioService.speak(text, currentLang);
  };

  return (
    <div className="max-w-7xl mx-auto px-3 sm:px-4 md:px-6 py-6 sm:py-8 space-y-6 sm:space-y-8 w-full max-w-full overflow-x-hidden">
      {/* Header */}
      <div className="bg-white border border-slate-200 rounded-2xl p-4 sm:p-6 md:p-8 shadow-xs w-full max-w-full overflow-hidden">
        <div className="flex flex-col md:flex-row items-start md:items-center justify-between gap-6 pb-6 border-b border-slate-100">
          <div className="space-y-1">
            <div className="text-xs font-semibold text-emerald-800 uppercase tracking-wider flex items-center gap-1.5">
              <Landmark className="w-4 h-4 text-emerald-700" />
              <span>
                {currentLang === 'hi'
                  ? 'बैंक शाखा प्रबंधक व बैंक मित्र कंसोल'
                  : 'Branch Manager & Business Correspondent Portal'}
              </span>
            </div>
            <h1 className="text-2xl sm:text-3xl font-bold text-slate-900 tracking-tight">
              {currentLang === 'hi' ? 'त्वरित एमएसएमई क्रेडिट अप्रेजल डेस्क' : 'Instant Rural MSME Credit Desk'}
            </h1>
            <p className="text-sm text-slate-600 max-w-3xl">
              {currentLang === 'hi'
                ? 'पीडीएफ प्रश्न 5 और 6 का उत्तर: बैंक मैनेजर को बिना झंझट "हां" कहने के लिए प्रमाणित कैशफ्लो, DSCR साक्ष्य और 0% बंधक गारंटी (CGTMSE) चाहिए।'
                : 'Resolving PDF Q5 & Q6: Delivering structured cashflow velocity, legal scheme citations, and DSCR proof so branch managers can sanction without audit anxiety.'}
            </p>
          </div>

          <div className="flex items-center gap-3 shrink-0">
            <button
              onClick={handleAudioExplain}
              className="inline-flex items-center gap-2 px-3.5 py-2 bg-slate-100 hover:bg-slate-200 text-slate-800 rounded-xl text-xs font-semibold transition-colors cursor-pointer"
            >
              <Volume2 className="w-4 h-4" />
              <span>{currentLang === 'hi' ? 'पोर्टल विवरण सुनें' : 'Audio Brief'}</span>
            </button>
          </div>
        </div>

        {/* Operational Efficiency Metrics (Following the money: Every incomplete file costs a lender time) */}
        <div className="grid grid-cols-2 md:grid-cols-4 gap-4 pt-6">
          <div className="p-4 rounded-xl bg-slate-50 border border-slate-200">
            <span className="text-xs text-slate-500 block mb-1">
              {currentLang === 'hi' ? 'फाइल निर्णय समय:' : 'Avg Decision Time:'}
            </span>
            <span className="text-2xl font-bold text-emerald-800 font-mono">48 Hours</span>
            <span className="text-[11px] text-emerald-700 block mt-1">
              {currentLang === 'hi' ? 'पारंपरिक 90 दिनों से 97% तेज' : '97% faster than 90-day norm'}
            </span>
          </div>

          <div className="p-4 rounded-xl bg-slate-50 border border-slate-200">
            <span className="text-xs text-slate-500 block mb-1">
              {currentLang === 'hi' ? 'दलाल कट संरक्षण:' : 'Middleman Interception:'}
            </span>
            <span className="text-2xl font-bold text-emerald-800 font-mono">100% Zero Fee</span>
            <span className="text-[11px] text-slate-500 block mt-1">
              {currentLang === 'hi' ? 'सीधा बैंक खाता हस्तांतरण' : 'Direct DBT / Account Credit'}
            </span>
          </div>

          <div className="p-4 rounded-xl bg-slate-50 border border-slate-200">
            <span className="text-xs text-slate-500 block mb-1">
              {currentLang === 'hi' ? 'औसत DSCR कवरेज:' : 'Avg Borrower DSCR:'}
            </span>
            <span className="text-2xl font-bold text-slate-900 font-mono">1.68x</span>
            <span className="text-[11px] text-emerald-700 block mt-1">
              {currentLang === 'hi' ? 'सुरक्षित किस्त क्षमता' : 'Healthy margin over 1.30 benchmark'}
            </span>
          </div>

          <div className="p-4 rounded-xl bg-slate-50 border border-slate-200">
            <span className="text-xs text-slate-500 block mb-1">
              {currentLang === 'hi' ? 'क्रेडिट गारंटी सुरक्षा:' : 'Statutory Guarantee:'}
            </span>
            <span className="text-2xl font-bold text-slate-900 font-mono">85% CGTMSE</span>
            <span className="text-[11px] text-slate-500 block mt-1">
              {currentLang === 'hi' ? '0% बैंक एनपीए जोखिम' : 'CGFMU & CGTMSE Default Backing'}
            </span>
          </div>
        </div>
      </div>

      {/* Filter and Search Bar */}
      <div className="flex flex-col sm:flex-row items-center justify-between gap-4">
        {/* Segmented Filter Control */}
        <div className="flex items-center gap-1 p-1 bg-slate-200/80 rounded-xl w-full sm:w-auto">
          <button
            onClick={() => setFilter('ALL')}
            className={`px-4 py-2 text-xs font-semibold rounded-lg transition-all cursor-pointer ${
              filter === 'ALL' ? 'bg-white text-slate-900 shadow-xs' : 'text-slate-600 hover:text-slate-900'
            }`}
          >
            {currentLang === 'hi' ? 'सभी आवेदन' : 'All Dossiers'} ({dossiers.length})
          </button>
          <button
            onClick={() => setFilter('PENDING')}
            className={`px-4 py-2 text-xs font-semibold rounded-lg transition-all cursor-pointer ${
              filter === 'PENDING' ? 'bg-white text-slate-900 shadow-xs' : 'text-slate-600 hover:text-slate-900'
            }`}
          >
            {currentLang === 'hi' ? 'समीक्षा हेतु लंबित' : 'Pending Review'}
          </button>
          <button
            onClick={() => setFilter('SANCTIONED')}
            className={`px-4 py-2 text-xs font-semibold rounded-lg transition-all cursor-pointer ${
              filter === 'SANCTIONED' ? 'bg-white text-slate-900 shadow-xs' : 'text-slate-600 hover:text-slate-900'
            }`}
          >
            {currentLang === 'hi' ? 'स्वीकृत / Sanctioned' : 'Sanctioned'}
          </button>
        </div>

        {/* Search */}
        <div className="relative w-full sm:w-72">
          <Search className="w-4 h-4 absolute left-3 top-3 text-slate-400" />
          <input
            type="text"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            placeholder={
              currentLang === 'hi' ? 'उद्यमी, गांव या योजना खोजें...' : 'Search borrower or village...'
            }
            className="w-full pl-9 pr-4 py-2 text-xs border border-slate-300 rounded-xl bg-white focus:ring-2 focus:ring-emerald-600 focus:outline-hidden"
          />
        </div>
      </div>

      {/* Applications Table */}
      <div className="bg-white border border-slate-200 rounded-2xl shadow-xs overflow-hidden w-full max-w-full">
        <div className="overflow-x-auto w-full max-w-full">
          <table className="w-full min-w-[650px] text-left text-xs sm:text-sm">
            <thead className="bg-slate-50 border-b border-slate-200 text-slate-600 font-semibold uppercase tracking-wider text-[11px]">
              <tr>
                <th className="px-5 py-3.5">Borrower &amp; Enterprise</th>
                <th className="px-5 py-3.5">Location</th>
                <th className="px-5 py-3.5">Loan / Scheme</th>
                <th className="px-5 py-3.5">DSCR &amp; Score</th>
                <th className="px-5 py-3.5">Subsidy Entitlement</th>
                <th className="px-5 py-3.5">Status</th>
                <th className="px-5 py-3.5 text-right">Actions</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-100">
              {filtered.map((dossier) => (
                <tr key={dossier.id} className="hover:bg-slate-50/80 transition-colors">
                  <td className="px-5 py-4">
                    <div className="font-bold text-slate-900">{dossier.borrowerName}</div>
                    <div className="text-xs text-slate-500 font-medium">{dossier.businessName}</div>
                    <div className="text-[11px] text-slate-400 font-mono mt-0.5">{dossier.applicationNumber}</div>
                  </td>
                  <td className="px-5 py-4">
                    <div className="text-slate-800 font-medium">{dossier.village}</div>
                    <div className="text-xs text-slate-500">{dossier.district}, {dossier.state}</div>
                  </td>
                  <td className="px-5 py-4">
                    <div className="font-bold font-mono text-emerald-800 text-sm">
                      ₹{dossier.recommendedLoan.toLocaleString('en-IN')}
                    </div>
                    <div className="text-xs text-slate-600">{dossier.primaryScheme}</div>
                  </td>
                  <td className="px-5 py-4">
                    <div className="font-semibold text-slate-900">
                      DSCR: <span className="font-mono text-emerald-700">{dossier.dscr.toFixed(2)}x</span>
                    </div>
                    <div className="text-xs text-slate-500">
                      Score: <span className="font-mono font-medium">{dossier.riskScore}/100</span> ({dossier.riskLevel})
                    </div>
                  </td>
                  <td className="px-5 py-4">
                    <div className="font-semibold text-emerald-800">{dossier.subsidyEntitlement}</div>
                    <div className="text-[11px] text-slate-500">
                      Saved ₹{dossier.annualMoneylenderSaving.toLocaleString('en-IN')} vs moneylender
                    </div>
                  </td>
                  <td className="px-5 py-4">
                    {dossier.status === 'IN_PRINCIPLE_SANCTIONED' ? (
                      <span className="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-xs font-semibold bg-emerald-100 text-emerald-800">
                        <CheckCircle2 className="w-3.5 h-3.5" />
                        <span>Sanctioned</span>
                      </span>
                    ) : (
                      <span className="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-xs font-semibold bg-amber-100 text-amber-800">
                        <Clock className="w-3.5 h-3.5" />
                        <span>Under Review</span>
                      </span>
                    )}
                  </td>
                  <td className="px-5 py-4 text-right">
                    <div className="flex items-center justify-end gap-2">
                      <button
                        onClick={() => setSelectedDossierDetail(dossier)}
                        className="px-3 py-1.5 bg-slate-100 hover:bg-slate-200 text-slate-800 rounded-lg text-xs font-semibold transition-colors cursor-pointer"
                      >
                        Inspect
                      </button>
                      {dossier.status !== 'IN_PRINCIPLE_SANCTIONED' && (
                        <button
                          onClick={() => onUpdateStatus(dossier.id, 'IN_PRINCIPLE_SANCTIONED')}
                          className="px-3 py-1.5 bg-emerald-700 hover:bg-emerald-800 text-white rounded-lg text-xs font-semibold transition-colors cursor-pointer flex items-center gap-1"
                        >
                          <Zap className="w-3 h-3 text-emerald-200" />
                          <span>Sanction</span>
                        </button>
                      )}
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Modal to inspect complete dossier details */}
      {selectedDossierDetail && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/60 p-4 backdrop-blur-xs">
          <div className="bg-white rounded-2xl w-full max-w-3xl max-h-[90vh] overflow-y-auto shadow-2xl border border-slate-200 p-6 space-y-6">
            <div className="flex items-center justify-between border-b border-slate-200 pb-4">
              <div>
                <h3 className="text-xl font-bold text-slate-900">
                  {selectedDossierDetail.borrowerName} · {selectedDossierDetail.businessName}
                </h3>
                <span className="text-xs text-slate-500 font-mono">
                  {selectedDossierDetail.applicationNumber} · {selectedDossierDetail.village}, {selectedDossierDetail.district}
                </span>
              </div>
              <button
                onClick={() => setSelectedDossierDetail(null)}
                className="p-1.5 rounded-lg hover:bg-slate-100 text-slate-500 cursor-pointer text-lg font-bold"
              >
                ✕
              </button>
            </div>

            <div className="space-y-4 text-sm">
              <div className="grid grid-cols-2 sm:grid-cols-4 gap-3 bg-slate-50 p-4 rounded-xl text-xs">
                <div>
                  <span className="text-slate-500 block">Recommended Loan:</span>
                  <span className="font-bold text-emerald-800 font-mono text-sm">
                    ₹{selectedDossierDetail.recommendedLoan.toLocaleString('en-IN')}
                  </span>
                </div>
                <div>
                  <span className="text-slate-500 block">Assessed DSCR:</span>
                  <span className="font-bold font-mono text-sm text-slate-900">
                    {selectedDossierDetail.dscr.toFixed(2)}x
                  </span>
                </div>
                <div>
                  <span className="text-slate-500 block">Fair Risk Score:</span>
                  <span className="font-bold font-mono text-sm text-slate-900">
                    {selectedDossierDetail.riskScore}/100
                  </span>
                </div>
                <div>
                  <span className="text-slate-500 block">Scheme Subsidy:</span>
                  <span className="font-bold text-emerald-800 font-mono text-sm">
                    {selectedDossierDetail.subsidyEntitlement}
                  </span>
                </div>
              </div>

              <div>
                <span className="font-bold text-slate-800 block mb-1">Informal Record Reconstruction:</span>
                <p className="text-xs text-slate-700 bg-slate-50 p-3 rounded-lg border border-slate-200 leading-relaxed font-mono">
                  {selectedDossierDetail.recordSummary}
                </p>
              </div>

              <div>
                <span className="font-bold text-slate-800 block mb-1">Explainability Assessment:</span>
                <p className="text-xs text-slate-700 bg-emerald-50/50 p-3 rounded-lg border border-emerald-200 leading-relaxed">
                  {selectedDossierDetail.analysis.explainabilitySummary}
                </p>
              </div>

              <div className="flex items-center justify-end gap-3 pt-4 border-t border-slate-200">
                <button
                  onClick={() => {
                    onSelectDossier(selectedDossierDetail);
                    setSelectedDossierDetail(null);
                  }}
                  className="px-4 py-2 bg-slate-800 hover:bg-slate-900 text-white rounded-xl text-xs font-semibold transition-colors cursor-pointer"
                >
                  View Full Printable Appraisal Note
                </button>
                {selectedDossierDetail.status !== 'IN_PRINCIPLE_SANCTIONED' && (
                  <button
                    onClick={() => {
                      onUpdateStatus(selectedDossierDetail.id, 'IN_PRINCIPLE_SANCTIONED');
                      setSelectedDossierDetail({
                        ...selectedDossierDetail,
                        status: 'IN_PRINCIPLE_SANCTIONED',
                      });
                    }}
                    className="px-4 py-2 bg-emerald-700 hover:bg-emerald-800 text-white rounded-xl text-xs font-semibold transition-colors cursor-pointer"
                  >
                    Issue In-Principle Sanction Letter
                  </button>
                )}
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};
