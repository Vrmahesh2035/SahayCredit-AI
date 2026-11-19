package com.example.model

import kotlinx.serialization.Serializable

enum class SupportedLanguage(
    val code: String,
    val englishLabel: String,
    val nativeScript: String,
    val localeTag: String,
    val greetingPhrase: String,
    val quickGreeting: String,
    val phoneticFallback: String,
    val soundTestPhrase: String
) {
    HI(
        code = "hi",
        englishLabel = "Hindi",
        nativeScript = "हिन्दी",
        localeTag = "hi-IN",
        greetingPhrase = "नमस्ते! सहाय-क्रेडिट में आपका स्वागत है। बोलकर सुनने की सुविधा चालू है।",
        quickGreeting = "नमस्ते! सहाय-क्रेडिट में आपका स्वागत है।",
        phoneticFallback = "Namaste! Sahay-Credit audio pranaali mein aapka swagat hai.",
        soundTestPhrase = "नमस्ते! सहाय-क्रेडिट ऑडियो प्रणाली बिल्कुल सही और साफ काम कर रही है। आप किसी भी भाषा में सुन सकते हैं।"
    ),
    EN(
        code = "en",
        englishLabel = "English",
        nativeScript = "English",
        localeTag = "en-IN",
        greetingPhrase = "Welcome to SahayCredit AI. Voice assistance is active in English.",
        quickGreeting = "Welcome to SahayCredit AI.",
        phoneticFallback = "Welcome! SahayCredit audio system is loud, clear, and working properly.",
        soundTestPhrase = "Welcome! SahayCredit audio system is loud, clear, and working properly on your device."
    ),
    TA(
        code = "ta",
        englishLabel = "Tamil",
        nativeScript = "தமிழ்",
        localeTag = "ta-IN",
        greetingPhrase = "வணக்கம்! சஹாய் கிரெடிட்டிற்கு உங்களை அன்புடன் வரவேற்கிறோம்.",
        quickGreeting = "வணக்கம்! சஹாய் கிரெடிட்டிற்கு உங்களை அன்புடன் வரவேற்கிறோம்.",
        phoneticFallback = "Vanakkam! Sahay Credit oli vazhikaatti. Ungalukku nandraaga ketkiradhu.",
        soundTestPhrase = "வணக்கம்! சஹாய் கிரெடிட் ஒலி வழிகாட்டி தெளிவாக கேட்கிறது. அரசு திட்டங்களுக்கு விண்ணப்பிக்கலாம்."
    ),
    TE(
        code = "te",
        englishLabel = "Telugu",
        nativeScript = "తెలుగు",
        localeTag = "te-IN",
        greetingPhrase = "నమస్కారం! సహాయ్ క్రెడిట్ కి స్వాగతం. వాయిస్ గైడ్ సిద్ధంగా ఉంది.",
        quickGreeting = "నమస్కారం! సహాయ్ క్రెడిట్ కి స్వాగతం.",
        phoneticFallback = "Namaskaram! Sahay Credit audio guide. Spashtamga vinapadutondi.",
        soundTestPhrase = "నమస్కారం! సహాయ్ క్రెడిట్ ఆడియో స్పష్టంగా పనిచేస్తుంది. బ్యాంక్ రుణాలకు దరఖాస్తు చేసుకోవచ్చు."
    ),
    MR(
        code = "mr",
        englishLabel = "Marathi",
        nativeScript = "मराठी",
        localeTag = "mr-IN",
        greetingPhrase = "नमस्कार! सहाय क्रेडिट मध्ये आपले स्वागत आहे. आवाज मार्गदर्शन सुरू आहे.",
        quickGreeting = "नमस्कार! सहाय क्रेडिट मध्ये आपले स्वागत आहे.",
        phoneticFallback = "Namaskar! Sahay Credit aawaz margadarshan suru aahe.",
        soundTestPhrase = "नमस्कार! सहाय क्रेडिट आवाज प्रणाली अगदी स्पष्ट सुरू आहे. शासकीय योजनांची माहिती ऐकू शकता."
    ),
    BN(
        code = "bn",
        englishLabel = "Bengali",
        nativeScript = "বাংলা",
        localeTag = "bn-IN",
        greetingPhrase = "নমস্কার! সহায় ক্রেডিটে আপনাকে স্বাগত। অডিও নির্দেশিকা প্রস্তুত।",
        quickGreeting = "নমস্কার! সহায় ক্রেডিটে আপনাকে স্বাগত।",
        phoneticFallback = "Nomoshkar! Sahay Credit audio nirdeshika shundor shunthe pachhen.",
        soundTestPhrase = "নমস্কার! সহায় ক্রেডিট অডিও ব্যবস্থা সঠিকভাবে কাজ করছে। আপনি স্পষ্টভাবে শুনতে পাচ্ছেন।"
    ),
    KN(
        code = "kn",
        englishLabel = "Kannada",
        nativeScript = "ಕನ್ನಡ",
        localeTag = "kn-IN",
        greetingPhrase = "ನಮಸ್ಕಾರ! ಸಹಾಯ್ ಕ್ರೆಡಿಟ್‌ಗೆ ಸುಸ್ವಾಗತ. ಧ್ವನಿ ಮಾರ್ಗದರ್ಶಿ ಸಿದ್ಧವಾಗಿದೆ.",
        quickGreeting = "ನಮಸ್ಕಾರ! ಸಹಾಯ್ ಕ್ರೆಡಿಟ್‌ಗೆ ಸುಸ್ವಾಗತ.",
        phoneticFallback = "Namaskara! Sahay Credit dhwani margadarshi spashtavagi kelisuttide.",
        soundTestPhrase = "ನಮಸ್ಕಾರ! ಸಹಾಯ್ ಕ್ರೆಡಿಟ್ ಧ್ವನಿ ವ್ಯವಸ್ಥೆ ಸ್ಪಷ್ಟವಾಗಿ ಕೇಳುತ್ತಿದೆ. ಸರ್ಕಾರಿ ಯೋಜನೆಗಳಿಗೆ ಅರ್ಜಿ ಸಲ್ಲಿಸಿ."
    ),
    GU(
        code = "gu",
        englishLabel = "Gujarati",
        nativeScript = "ગુજરાતી",
        localeTag = "gu-IN",
        greetingPhrase = "નમસ્તે! સહાય ક્રેડિટમાં આપનું સ્વાગત છે. અવાજ સહાય તૈયાર છે.",
        quickGreeting = "નમસ્તે! સહાય ક્રેડિટમાં આપનું સ્વાગત છે.",
        phoneticFallback = "Namaste! Sahay Credit aavaj sahay bilkul saaf sambhalay chhe.",
        soundTestPhrase = "નમસ્તે! સહાય ક્રેડિટ અવાજ પ્રણાલી બરાબર કામ કરી રહી છે. આપ બધી યોજનાઓ સાંભળી શકો છો."
    )
}

@Serializable
enum class BusinessCategory(val id: String, val expenseRatio: Double) {
    TAILOR("tailor", 0.36),
    DAIRY("dairy", 0.38),
    FABRICATION("fabrication", 0.64),
    SHG_ENTERPRISE("shg_enterprise", 0.48),
    KIRANA("kirana", 0.76),
    VISHWAKARMA_ARTISAN("vishwakarma_artisan", 0.34)
}

@Serializable
enum class SocialCategory(val label: String, val displayHi: String) {
    OBC("OBC", "OBC (अन्य पिछड़ा वर्ग)"),
    SC("SC", "SC (अनुसूचित जाति - 35% Subsidy)"),
    ST("ST", "ST (अनुसूचित जनजाति - 35% Subsidy)"),
    GENERAL("General", "General (सामान्य वर्ग - 25% Rural Subsidy)"),
    MINORITY("Minority", "Minority (अल्पसंख्यक वर्ग)")
}

@Serializable
enum class Gender(val label: String, val displayHi: String) {
    MALE("Male", "Male (पुरुष)"),
    FEMALE("Female", "Female (महिला - 35% Subsidy Special Category)"),
    TRANSGENDER("Transgender", "Transgender")
}

@Serializable
enum class RecordType(val id: String, val displayLabel: String) {
    UPI_QR("UPI_QR", "PhonePe / GPay QR लेन-देन"),
    MILK_SLIP("MILK_SLIP", "डेयरी दूध पर्ची / Dairy Milk Slip"),
    KHATA_BAHI("KHATA_BAHI", "दुकानदार कच्ची बही / Handwritten Khata"),
    MANDI_INVOICE("MANDI_INVOICE", "मंडी पर्ची / Raw Material Invoice")
}

@Serializable
enum class DossierStatus {
    PENDING_BRANCH_REVIEW,
    IN_PRINCIPLE_SANCTIONED,
    DOCUMENT_VERIFIED
}

data class SchemeInfo(
    val id: String,
    val name: String,
    val shortName: String,
    val agency: String,
    val maxAmount: Long,
    val subsidyPercentage: String,
    val interestRate: String,
    val collateralRequirement: String,
    val officialCitation: String,
    val eligibilityHighlight: String,
    val eligibleCategories: List<String>,
    val keyBenefits: List<String>,
    val documentsNeeded: List<String>
)

data class InformalRecordPreset(
    val id: String,
    val title: String,
    val businessType: BusinessCategory,
    val recordType: RecordType,
    val ownerName: String,
    val village: String,
    val district: String,
    val state: String,
    val socialCategory: SocialCategory,
    val gender: Gender,
    val requestedLoanAmount: Int,
    val statedMonthlySales: Int,
    val sampleContent: String,
    val summary: String
)

@Serializable
data class SchemeMatch(
    val schemeName: String,
    val categoryTag: String,
    val subsidyOrBenefit: String,
    val maxLimit: String,
    val officialCitation: String,
    val whyEligible: String,
    val interestBenefit: String
)

@Serializable
data class VillageAudioGuideText(
    val hi: String,
    val en: String,
    val localized: Map<String, String> = emptyMap()
) {
    fun textFor(lang: SupportedLanguage): String =
        localized[lang.code] ?: when (lang) {
            SupportedLanguage.HI -> hi
            else -> en
        }
}

@Serializable
data class CreditAnalysisResult(
    val estimatedMonthlyInflow: Int,
    val estimatedMonthlyOperatingExpense: Int,
    val netMonthlySurplus: Int,
    val recommendedLoanAmount: Int,
    val estimatedMonthlyEMI: Int,
    val dscr: Double,
    val riskLevel: String,
    val riskScore: Int,
    val viabilityVerdict: String,
    val explainabilitySummary: String,
    val schemeMatches: List<SchemeMatch>,
    val branchManagerChecklist: List<String>,
    val villageAudioGuideText: VillageAudioGuideText,
    val isPlaceholderResponse: Boolean = false
)

@Serializable
data class SubmittedDossier(
    val id: String,
    val applicationNumber: String,
    val borrowerName: String,
    val businessName: String,
    val businessType: BusinessCategory,
    val village: String,
    val district: String,
    val state: String,
    val loanRequested: Int,
    val recommendedLoan: Int,
    val dscr: Double,
    val riskScore: Int,
    val riskLevel: String,
    val primaryScheme: String,
    val subsidyEntitlement: String,
    val annualMoneylenderSaving: Int,
    val status: DossierStatus,
    val consentTimestamp: String,
    val analysis: CreditAnalysisResult,
    val recordSummary: String
)

data class VoiceAssistantResponse(
    val spokenResponse: String,
    val englishSummary: String,
    val keyAdviceBulletPoints: List<String>,
    val middlemanWarning: String,
    val followUpSuggestions: List<String>,
    val isPlaceholderResponse: Boolean = false
)

data class FieldQuestionItem(
    val id: Int,
    val title: String,
    val titleHi: String,
    val groundProblem: String,
    val groundProblemHi: String,
    val agenticAiSolution: String,
    val agenticAiSolutionHi: String,
    val stakeholder: String,
    val metric: String
)

enum class AppTab(val route: String) {
    WIZARD("wizard"),
    CALCULATOR("calculator"),
    PORTAL("portal"),
    QUESTIONS("questions")
}
