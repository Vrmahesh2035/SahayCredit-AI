export type SupportedLanguage = 'en' | 'hi' | 'ta' | 'te' | 'mr' | 'bn' | 'kn' | 'gu';

export type BusinessCategory =
  | 'tailor'
  | 'dairy'
  | 'fabrication'
  | 'shg_enterprise'
  | 'kirana'
  | 'vishwakarma_artisan';

export type SocialCategory = 'General' | 'OBC' | 'SC' | 'ST' | 'Minority';
export type Gender = 'Male' | 'Female' | 'Transgender';
export type RegionType = 'Rural' | 'Semi-Urban' | 'Urban';

export interface SchemeInfo {
  id: string;
  name: string;
  shortName: string;
  agency: string;
  maxAmount: number;
  subsidyPercentage: string;
  interestRate: string;
  collateralRequirement: string;
  officialCitation: string;
  eligibilityHighlight: string;
  eligibleCategories: string[];
  keyBenefits: string[];
  documentsNeeded: string[];
}

export interface InformalRecordPreset {
  id: string;
  title: string;
  businessType: BusinessCategory;
  recordType: 'UPI_QR' | 'KHATA_BAHI' | 'MILK_SLIP' | 'MANDI_INVOICE';
  ownerName: string;
  village: string;
  district: string;
  state: string;
  socialCategory: SocialCategory;
  gender: Gender;
  requestedLoanAmount: number;
  statedMonthlySales: number;
  sampleContent: string;
  summary: string;
}

export interface CreditAnalysisResult {
  estimatedMonthlyInflow: number;
  estimatedMonthlyOperatingExpense: number;
  netMonthlySurplus: number;
  recommendedLoanAmount: number;
  estimatedMonthlyEMI: number;
  dscr: number;
  riskLevel: 'Low Risk' | 'Moderate Risk' | 'Caution';
  riskScore: number;
  viabilityVerdict: string;
  explainabilitySummary: string;
  schemeMatches: Array<{
    schemeName: string;
    categoryTag: string;
    subsidyOrBenefit: string;
    maxLimit: string;
    officialCitation: string;
    whyEligible: string;
    interestBenefit: string;
  }>;
  branchManagerChecklist: string[];
  villageAudioGuideText: {
    hi: string;
    en: string;
  };
}

export interface SubmittedDossier {
  id: string;
  applicationNumber: string;
  borrowerName: string;
  businessName: string;
  businessType: BusinessCategory;
  village: string;
  district: string;
  state: string;
  loanRequested: number;
  recommendedLoan: number;
  dscr: number;
  riskScore: number;
  riskLevel: string;
  primaryScheme: string;
  subsidyEntitlement: string;
  annualMoneylenderSaving: number;
  status: 'PENDING_BRANCH_REVIEW' | 'IN_PRINCIPLE_SANCTIONED' | 'DOCUMENT_VERIFIED';
  consentTimestamp: string;
  analysis: CreditAnalysisResult;
  recordSummary: string;
}
