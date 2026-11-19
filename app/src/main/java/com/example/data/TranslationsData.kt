package com.example.data

import com.example.data.strings.BengaliUiStrings
import com.example.data.strings.EnglishUiStrings
import com.example.data.strings.GujaratiUiStrings
import com.example.data.strings.HindiUiStrings
import com.example.data.strings.KannadaUiStrings
import com.example.data.strings.MarathiUiStrings
import com.example.data.strings.TamilUiStrings
import com.example.data.strings.TeluguUiStrings
import com.example.data.strings.UiStrings
import com.example.model.BusinessCategory
import com.example.model.SupportedLanguage

data class StepLabels(
    val businessProfile: String,
    val informalProof: String,
    val aiAnalysis: String,
    val schemeMatches: String,
    val bankDossier: String
)

data class PromptStrings(
    val heroWelcome: String,
    val chooseBusinessAudio: String,
    val recordUploadAudio: String,
    val analysisAudio: String,
    val step4Audio: String,
    val dossierAudio: String
)

data class LabelStrings(
    val monthlyIncome: String,
    val loanAmount: String,
    val category: String,
    val gender: String,
    val village: String,
    val district: String,
    val uploadRecords: String,
    val orChoosePreset: String,
    val analyzeRecords: String,
    val viewSchemes: String,
    val generateDossier: String,
    val middlemanCutWarning: String,
    val moneylenderCostWarning: String,
    val estimatedSubsidy: String,
    val dscrMetric: String,
    val riskScore: String,
    val consentStatement: String,
    val branchManagerTitle: String,
    val fieldQuestionsTitle: String,
    val approvedByEntrepreneur: String,
    val downloadDossier: String,
    val printDossier: String
)

data class TranslationStrings(
    val appName: String,
    val tagline: String,
    val badge: String,
    val listenAudio: String,
    val stopAudio: String,
    val voiceGuide: String,
    val askAssistant: String,
    val selectLanguage: String,
    val steps: StepLabels,
    val businessTypes: Map<BusinessCategory, String>,
    val prompts: PromptStrings,
    val labels: LabelStrings
)

object TranslationsData {
    private val translations: Map<SupportedLanguage, TranslationStrings> = mapOf(
        SupportedLanguage.HI to TranslationStrings(
            appName = "सहाय क्रेडिट AI",
            tagline = "ग्रामीण उद्यमियों के लिए सीधी सरकारी योजना व बैंक लोन साथी",
            badge = "दलाल-मुक्त · ITR की आवश्यकता नहीं · 100% नि:शुल्क",
            listenAudio = "बोलकर सुनें",
            stopAudio = "आवाज रोकें",
            voiceGuide = "आवाज से समझें",
            askAssistant = "सहाय मित्र से पूछें",
            selectLanguage = "भाषा चुनें",
            steps = StepLabels(
                businessProfile = "1. व्यवसाय व गांव",
                informalProof = "2. बही खाता / UPI",
                aiAnalysis = "3. लोन क्षमता जांच",
                schemeMatches = "4. सरकारी योजनाएं",
                bankDossier = "5. बैंक अर्जी व रिपोर्ट"
            ),
            businessTypes = mapOf(
                BusinessCategory.TAILOR to "दर्जी व सिलाई केंद्र",
                BusinessCategory.DAIRY to "डेयरी व दुग्ध पालन",
                BusinessCategory.FABRICATION to "वेल्डिंग व लोहा कार्य",
                BusinessCategory.SHG_ENTERPRISE to "महिला स्वयं सहायता समूह",
                BusinessCategory.KIRANA to "किराना व ग्रामीण दुकान",
                BusinessCategory.VISHWAKARMA_ARTISAN to "बढ़ई / लोहार / पारंपरिक कारीगर"
            ),
            prompts = PromptStrings(
                heroWelcome = "नमस्ते! सहाय-क्रेडिट में आपका स्वागत है। अगर आपके पास ITR या बड़े कागजात नहीं हैं, फिर भी आपका UPI लेन-देन या कच्चा बही-खाता बैंक लोन के लिए पूरा मान्य है। किसी दलाल को 10-15% कमीशन न दें। आइए अपनी योजना देखें।",
                chooseBusinessAudio = "कृपया अपना व्यवसाय चुनें जैसे दर्जी, डेयरी, वेल्डिंग या महिला समूह। इसके बाद बताएं कि आपको अपने काम को बढ़ाने के लिए कितने लोन की जरूरत है।",
                recordUploadAudio = "आप अपना फोनपे/गूगल-पे QR स्क्रीनशॉट, डायरी का पन्ना या डेयरी की दूध पर्ची अपलोड कर सकते हैं, या हमारे तैयार उदाहरण में से चुन सकते हैं।",
                analysisAudio = "सहाय-क्रेडिट AI आपके लेन-देन की जांच कर रहा है ताकि बैंक मैनेजर को स्पष्ट दिखे कि आप हर महीने समय पर किस्त चुका सकते हैं।",
                step4Audio = "आपके लिए सरकारी योजनाएं मिल गई हैं! मुद्रा और PMEGP योजना में आपको सरकारी सब्सिडी मिलेगी और कोई जमीन या घर गिरवी नहीं रखना पड़ेगा।",
                dossierAudio = "बधाई हो! आपकी बैंक-रेडी रिपोर्ट तैयार है। इसमें सरकारी सब्सिडी और मुद्रा योजना का पूरा विवरण है। इसे सीधे बैंक मैनेजर को दिखाएं।"
            ),
            labels = LabelStrings(
                monthlyIncome = "औसत मासिक बिक्री / कमाई (₹)",
                loanAmount = "जरूरी लोन राशि (₹)",
                category = "सामाजिक वर्ग (आरक्षण व सब्सिडी लाभ हेतु)",
                gender = "लिंग",
                village = "गांव का नाम",
                district = "जिला",
                uploadRecords = "कच्चा बही-खाता / UPI पर्ची दर्ज करें",
                orChoosePreset = "या हमारे तैयार गांव के उदाहरण चुनें:",
                analyzeRecords = "लोन पात्रता व सब्सिडी जांचें",
                viewSchemes = "पात्र सरकारी योजनाएं देखें",
                generateDossier = "बैंक मैनेजर हेतु प्रमाणित रिपोर्ट बनाएं",
                middlemanCutWarning = "दलाल 10-15% (₹30,000+) खा जाते हैं - यहाँ शून्य शुल्क!",
                moneylenderCostWarning = "साहूकार 24-36% ब्याज लेते हैं - सरकारी योजना में सिर्फ 5-9%!",
                estimatedSubsidy = "अनुमानित सरकारी अनुदान (सब्सिडी):",
                dscrMetric = "किस्त चुकाने की क्षमता (DSCR):",
                riskScore = "विश्वसनीयता अंक (Risk Score):",
                consentStatement = "मेरी सहमति से मेरी वित्तीय जानकारी केवल बैंक अर्जी हेतु उपयोग की जाएगी।",
                branchManagerTitle = "बैंक शाखा प्रबंधक / बैंक मित्र डेस्क",
                fieldQuestionsTitle = "जमीनी हकीकत व 6 प्रमुख सवाल (PDF रिपोर्ट)",
                approvedByEntrepreneur = "उद्यमी द्वारा सत्यापित व स्वीकृत",
                downloadDossier = "बैंक रिपोर्ट डाउनलोड करें",
                printDossier = "प्रिंट / पीडीएफ निकालें"
            )
        ),
        SupportedLanguage.EN to TranslationStrings(
            appName = "SahayCredit AI",
            tagline = "Formal Credit & Government Schemes Bridge for Village Enterprises",
            badge = "Zero Middlemen · No ITR Required · 100% Free",
            listenAudio = "Listen Audio",
            stopAudio = "Stop Audio",
            voiceGuide = "Audio Guide",
            askAssistant = "Ask Sahay Mitra",
            selectLanguage = "Language",
            steps = StepLabels(
                businessProfile = "1. Business & Village",
                informalProof = "2. Ledger / UPI Records",
                aiAnalysis = "3. Capacity Analysis",
                schemeMatches = "4. Eligible Schemes",
                bankDossier = "5. Bank-Ready Dossier"
            ),
            businessTypes = mapOf(
                BusinessCategory.TAILOR to "Tailoring & Apparel",
                BusinessCategory.DAIRY to "Dairy & Cattle Unit",
                BusinessCategory.FABRICATION to "Welding & Fabrication",
                BusinessCategory.SHG_ENTERPRISE to "Women SHG Enterprise",
                BusinessCategory.KIRANA to "Village Kirana & Retail",
                BusinessCategory.VISHWAKARMA_ARTISAN to "Carpenter / Blacksmith / Artisan"
            ),
            prompts = PromptStrings(
                heroWelcome = "Welcome to SahayCredit AI. If you are a village micro-entrepreneur with steady UPI flows or informal ledger records but no ITR, you qualify for official schemes like MUDRA, PMEGP, and PM Vishwakarma. Never pay 10-15% cuts to corrupt middlemen.",
                chooseBusinessAudio = "Please select your business type and enter the loan amount you need to expand your machines, inventory, or livestock.",
                recordUploadAudio = "Enter your daily UPI QR statement, handwritten khata diary, or cooperative milk pour slip, or test with one of our realistic village presets.",
                analysisAudio = "SahayCredit AI is validating your cash velocity and debt service coverage ratio to create an explainable, risk-calibrated credit appraisal note for the branch manager.",
                step4Audio = "Matched government schemes ready! You qualify for MUDRA and PMEGP capital subsidy with zero collateral required.",
                dossierAudio = "Your certified Bank-Ready Viability Dossier is ready, complete with official scheme citations, margin money subsidy calculations, and DSCR metrics."
            ),
            labels = LabelStrings(
                monthlyIncome = "Monthly Revenue / Sales (₹)",
                loanAmount = "Required Loan Amount (₹)",
                category = "Social Category (for subsidy eligibility)",
                gender = "Gender",
                village = "Village Name",
                district = "District",
                uploadRecords = "Enter / Paste Informal Records or Ledger",
                orChoosePreset = "Or Choose a Realistic Village Preset:",
                analyzeRecords = "Analyze Credit & Check Schemes",
                viewSchemes = "View Matched Schemes",
                generateDossier = "Generate Bank-Ready Dossier",
                middlemanCutWarning = "Middlemen take 10-15% (₹30,000+) - Save 100% here!",
                moneylenderCostWarning = "Moneylenders charge 24-36% - Government schemes offer 5-9.5%!",
                estimatedSubsidy = "Estimated Govt Subsidy / Grant:",
                dscrMetric = "Debt Service Coverage (DSCR):",
                riskScore = "Credit Viability Score:",
                consentStatement = "I give consent to use my business records solely for formal loan processing.",
                branchManagerTitle = "Bank Branch Manager / Bank Mitra Portal",
                fieldQuestionsTitle = "Field Questions & Ground Reality (from PDF)",
                approvedByEntrepreneur = "Verified & Approved by Entrepreneur",
                downloadDossier = "Download Bank Dossier",
                printDossier = "Print Dossier"
            )
        ),
        SupportedLanguage.TA to TranslationStrings(
            appName = "சஹாய் கிரெடிட் AI",
            tagline = "கிராமப்புற சிறு வணிகங்களுக்கான நேரடி வங்கி கடன் & திட்ட வழிகாட்டி",
            badge = "இடைத்தரகர் இல்லாதது · ITR தேவையில்லை · இலவசம்",
            listenAudio = "குரல் வழிகாட்டல்",
            stopAudio = "நிறுத்து",
            voiceGuide = "ஒலி விளக்கம்",
            askAssistant = "உதவி கேட்க",
            selectLanguage = "மொழி",
            steps = StepLabels("1. வணிக விவரம்", "2. கணக்கு ஏடு / UPI", "3. கடன் தகுதி ஆய்வு", "4. அரசு திட்டங்கள்", "5. வங்கி அறிக்கை"),
            businessTypes = mapOf(
                BusinessCategory.TAILOR to "தையல் கடை & ஆடை உற்பத்தி",
                BusinessCategory.DAIRY to "பால் பண்ணை & மாடு வளர்ப்பு",
                BusinessCategory.FABRICATION to "வெல்டிங் & பட்டறை",
                BusinessCategory.SHG_ENTERPRISE to "சுயஉதவி குழு தொழில்",
                BusinessCategory.KIRANA to "மளிகை & கிராம கடை",
                BusinessCategory.VISHWAKARMA_ARTISAN to "ஆசாரி / தட்டார் / பாரம்பரிய கலைஞர்"
            ),
            prompts = PromptStrings(
                heroWelcome = "வணக்கம்! சஹாய் கிரெடிட் உங்களை வரவேற்கிறது. உங்களிடம் ITR இல்லாவிட்டாலும், UPI மற்றும் கணக்கு ஏடு பதிவுகள் கொண்டு முத்ரா மற்றும் PMEGP கடன் பெறலாம்.",
                chooseBusinessAudio = "உங்கள் தொழில் வகையைத் தேர்ந்தெடுத்து, தேவைப்படும் கடன் தொகையை உள்ளிடவும்.",
                recordUploadAudio = "உங்கள் UPI பரிவர்த்தனை அல்லது பால் விநியோக சீட்டை உள்ளிடவும் அல்லது மாதிரி உதாரணத்தைத் தேர்வு செய்யவும்.",
                analysisAudio = "உங்கள் வருமானம் மற்றும் கடன் திருப்பிச் செலுத்தும் திறன் பகுப்பாய்வு செய்யப்படுகிறது.",
                step4Audio = "உங்களுக்கான அரசு திட்டங்கள் கண்டறியப்பட்டுள்ளன! முத்ரா மற்றும் PMEGP திட்டத்தில் மானியம் கிடைக்கும், சொத்து அடமானம் தேவையில்லை.",
                dossierAudio = "உங்கள் வங்கி கடன் அறிக்கை தயாராக உள்ளது. அரசு மானிய விவரங்கள் இதில் இணைக்கப்பட்டுள்ளன."
            ),
            labels = LabelStrings(
                monthlyIncome = "மாத வருமானம் (₹)",
                loanAmount = "தேவைப்படும் கடன் (₹)",
                category = "பிரிவு (மானிய தகுதிக்கு)",
                gender = "பாலினம்",
                village = "கிராமம்",
                district = "மாவட்டம்",
                uploadRecords = "கணக்கு ஏடு / UPI விவரங்களை உள்ளிடவும்",
                orChoosePreset = "மாதிரி உதாரணத்தைத் தேர்வு செய்க:",
                analyzeRecords = "தகுதியை சரிபார்க்கவும்",
                viewSchemes = "திட்டங்களை பார்க்க",
                generateDossier = "வங்கி அறிக்கையை உருவாக்கவும்",
                middlemanCutWarning = "இடைத்தரகர்கள் 10-15% அபகரிக்கின்றனர் - இங்கே 0% கட்டணம்!",
                moneylenderCostWarning = "கந்துவட்டிக்காரர்கள் 24-36% வசூலிக்கின்றனர் - வங்கியில் 5-9% மட்டுமே!",
                estimatedSubsidy = "அரசு மானிய தொகை:",
                dscrMetric = "திருப்பிச் செலுத்தும் திறன் (DSCR):",
                riskScore = "நம்பகத்தன்மை புள்ளி:",
                consentStatement = "எனது ஒப்புதலுடன் இந்த தரவு வங்கி பயன்பாட்டிற்கு மட்டுமே பயன்படுத்தப்படும்.",
                branchManagerTitle = "வங்கி மேலாளர் போர்டல்",
                fieldQuestionsTitle = "கள கேள்விகள் & நடைமுறை நிலவரம்",
                approvedByEntrepreneur = "தொழில்முனைவோரால் சரிபார்க்கப்பட்டது",
                downloadDossier = "அறிக்கையை பதிவிறக்கு",
                printDossier = "அச்சு எடுக்க"
            )
        ),
        SupportedLanguage.TE to TranslationStrings(
            appName = "సహాయ్ క్రెడిట్ AI",
            tagline = "గ్రామీణ సూక్ష్మ వ్యాపారాలకు ప్రభుత్వ పథకాలు & బ్యాంక్ లోన్ తోడ్పాటు",
            badge = "దళారులు లేని సేవ · ITR అవసరం లేదు · 100% ఉచితం",
            listenAudio = "వినండి",
            stopAudio = "ఆపండి",
            voiceGuide = "వాయిస్ గైడ్",
            askAssistant = "సహాయ్ మిత్రను అడగండి",
            selectLanguage = "భాషను ఎంచుకోండి",
            steps = StepLabels("1. వ్యాపారం & గ్రామం", "2. ఖాతా బుక్ / UPI", "3. లోన్ అర్హత పరిశీలన", "4. ప్రభుత్వ పథకాలు", "5. బ్యాంక్ నివేదిక"),
            businessTypes = mapOf(
                BusinessCategory.TAILOR to "టైలరింగ్ & వస్త్ర దుకాణం",
                BusinessCategory.DAIRY to "డైరీ & పాడి పరిశ్రమ",
                BusinessCategory.FABRICATION to "వెల్డింగ్ & ఇనుప పనులు",
                BusinessCategory.SHG_ENTERPRISE to "మహిళా స్వయం సహాయక సంఘం",
                BusinessCategory.KIRANA to "కిరాణా & నిత్యావసర సరుకుల దుకాణం",
                BusinessCategory.VISHWAKARMA_ARTISAN to "వడ్రంగి / కమ్మరి / చేతివృత్తులు"
            ),
            prompts = PromptStrings(
                heroWelcome = "నమస్కారం! సహాయ్ క్రెడిట్ కి స్వాగతం. ITR లేదా ఆడిట్ పత్రాలు లేకపోయినా, మీ UPI చెల్లింపులు మరియు ఖాతా పుస్తకం ద్వారా ముద్రా, PMEGP లోన్ పొందవచ్చు.",
                chooseBusinessAudio = "మీ వ్యాపారాన్ని ఎంచుకోండి మరియు ఎంత లోన్ కావాలో నమోదు చేయండి.",
                recordUploadAudio = "మీ UPI రికార్డులు లేదా డైరీ పేజీని నమోదు చేయండి లేదా మా గ్రామీణ ఉదాహరణలను ఎంచుకోండి.",
                analysisAudio = "మీ వ్యాపార ఆదాయం మరియు బ్యాంక్ కిస్తీ చెల్లించే సామర్థ్యాన్ని పరిశీలిస్తున్నాము.",
                step4Audio = "మీకు తగిన ప్రభుత్వ పథకాలు లభించాయి! ముద్రా మరియు PMEGP పథకాల్లో సబ్సిడీ లభిస్తుంది, హామీ ఆస్తి అవసరం లేదు.",
                dossierAudio = "మీ బ్యాంక్ లోన్ నివేదిక సిద్ధమైంది. ఇందులో ప్రభుత్వ సబ్సిడీ వివరాలు ఉన్నాయి."
            ),
            labels = LabelStrings(
                monthlyIncome = "నెలవారీ అమ్మకాలు / రాబడి (₹)",
                loanAmount = "కావలసిన లోన్ మొత్తం (₹)",
                category = "సామాజిక వర్గం",
                gender = "లింగం",
                village = "గ్రామం",
                district = "జిల్లా",
                uploadRecords = "ఖాతా పుస్తకం / UPI వివరాలు నమోదు చేయండి",
                orChoosePreset = "లేదా సిద్ధంగా ఉన్న ఉదాహరణను ఎంచుకోండి:",
                analyzeRecords = "అర్హత & సబ్సిడీని తనిఖీ చేయండి",
                viewSchemes = "పథకాలను చూడండి",
                generateDossier = "బ్యాంక్ నివేదికను రూపొందించండి",
                middlemanCutWarning = "దళారులు 10-15% కమీషన్ తింటారు - ఇక్కడ ఉచితం!",
                moneylenderCostWarning = "వడ్డీ వ్యాపారులు 24-36% వసూలు చేస్తారు - ప్రభుత్వ పథకంలో కేవలం 5-9%!",
                estimatedSubsidy = "అంచనా వేసిన ప్రభుత్వ సబ్సిడీ:",
                dscrMetric = "చెల్లింపు సామర్థ్యం (DSCR):",
                riskScore = "విశ్వసనీయత స్కోర్:",
                consentStatement = "నా పూర్తి సమ్మతితోనే ఈ వివరాలు బ్యాంక్ దరఖాస్తు కోసం ఉపయోగించబడుతున్నాయి.",
                branchManagerTitle = "బ్యాంక్ బ్రాంచ్ మేనేజర్ పోర్టల్",
                fieldQuestionsTitle = "ఫీల్డ్ ప్రశ్నలు & క్షేత్రస్థాయి విశ్లేషణ",
                approvedByEntrepreneur = "వ్యాపారి చేత ధృవీకరించబడింది",
                downloadDossier = "నివేదికను డౌన్‌లోడ్ చేయండి",
                printDossier = "ప్రింట్ తీయండి"
            )
        ),
        SupportedLanguage.MR to TranslationStrings(
            appName = "सहाय क्रेडिट AI",
            tagline = "ग्रामीण उद्योजकांसाठी थेट सरकारी योजना व बँक कर्ज मार्गदर्शक",
            badge = "दलालमुक्त · ITR ची गरज नाही · 100% मोफत",
            listenAudio = "ऐका",
            stopAudio = "थांबवा",
            voiceGuide = "आवाज मार्गदर्शक",
            askAssistant = "सहाय मित्राला विचारा",
            selectLanguage = "भाषा निवडा",
            steps = StepLabels("१. व्यवसाय व गाव", "२. वही खाते / UPI", "३. कर्ज क्षमता तपासणी", "४. सरकारी योजना", "५. बँक अहवाल"),
            businessTypes = mapOf(
                BusinessCategory.TAILOR to "शिंपी व टेलरिंग केंद्र",
                BusinessCategory.DAIRY to "दुग्ध व्यवसाय व गोपालन",
                BusinessCategory.FABRICATION to "वेल्डिंग व फॅब्रिकेशन वर्कशॉप",
                BusinessCategory.SHG_ENTERPRISE to "महिला बचत गट उद्योग",
                BusinessCategory.KIRANA to "किराणा व जनरल स्टोअर",
                BusinessCategory.VISHWAKARMA_ARTISAN to "सुतार / लोहार / विश्वकर्मा कारागीर"
            ),
            prompts = PromptStrings(
                heroWelcome = "नमस्कार! सहाय क्रेडिट मध्ये आपले स्वागत आहे. ITR नसली तरीही तुमचे UPI व्यवहार आणि कच्ची वही बँक कर्जासाठी पुरेशी आहे. दलालाला कमिशन देऊ नका.",
                chooseBusinessAudio = "कृपया आपला व्यवसाय निवडा आणि व्यवसाय वाढवण्यासाठी किती कर्ज हवे आहे ते सांगा.",
                recordUploadAudio = "तुमचा PhonePe/Google Pay क्यूआर कोड व्यवहार किंवा दुधाची पावती जोडा किंवा आमचे उदाहरण निवडा.",
                analysisAudio = "आपल्या मासिक नफ्याची आणि हप्ता फेडण्याच्या क्षमतेची AI द्वारे पडताळणी होत आहे.",
                step4Audio = "आपल्यासाठी शासकीय योजना मिळाल्या आहेत! मुद्रा व PMEGP योजनेत सबसिडी मिळेल आणि मालमत्ता गहाण ठेवण्याची गरज नाही.",
                dossierAudio = "तुमचा बँक-रेडी अहवाल तयार आहे. यात शासकीय अनुदानाची (सब्सिडी) पूर्ण माहिती आहे."
            ),
            labels = LabelStrings(
                monthlyIncome = "मासिक सरासरी विक्री / कमाई (₹)",
                loanAmount = "आवश्यक कर्ज रक्कम (₹)",
                category = "सामाजिक प्रवर्ग",
                gender = "लिंग",
                village = "गाव",
                district = "जिल्हा",
                uploadRecords = "वही खाते किंवा UPI तपशील टाका",
                orChoosePreset = "किंवा तयार ग्रामीण उदाहरण निवडा:",
                analyzeRecords = "कर्ज पात्रता व सबसिडी तपासा",
                viewSchemes = "पात्र योजना पहा",
                generateDossier = "बँक अहवाल तयार करा",
                middlemanCutWarning = "दलाल १०-१५% खातात - इथे शून्य शुल्क!",
                moneylenderCostWarning = "सावकार २४-३६% व्याज घेतात - सरकारी योजनेत फक्त ५-९%!",
                estimatedSubsidy = "अंदाजे सरकारी अनुदान (सब्सिडी):",
                dscrMetric = "हप्ता भरण्याची क्षमता (DSCR):",
                riskScore = "विश्वासार्हता गुण:",
                consentStatement = "माझ्या संमतीने ही माहिती फक्त बँक कर्ज प्रक्रियेसाठी वापरली जाईल.",
                branchManagerTitle = "बँक शाखा व्यवस्थापक / बँक मित्र डेस्क",
                fieldQuestionsTitle = "क्षेत्रीय प्रश्न व वस्तुस्थिती",
                approvedByEntrepreneur = "उद्योजकाद्वारे सत्यापित व संमत",
                downloadDossier = "अहवाल डाउनलोड करा",
                printDossier = "प्रिंट काढा"
            )
        ),
        SupportedLanguage.BN to TranslationStrings(
            appName = "সহায় ক্রেডিট AI",
            tagline = "গ্রামীণ ক্ষুদ্র উদ্যোক্তাদের জন্য সরাসরি সরকারি প্রকল্প ও ব্যাঙ্ক ঋণ সহায়ক",
            badge = "দালালমুক্ত · আইটিআর প্রয়োজন নেই · সম্পূর্ণ বিনামূল্যে",
            listenAudio = "ভয়েসে শুনুন",
            stopAudio = "বন্ধ করুন",
            voiceGuide = "অডিও নির্দেশিকা",
            askAssistant = "সহায় মিত্রকে জিজ্ঞাসা করুন",
            selectLanguage = "ভাষা নির্বাচন করুন",
            steps = StepLabels("১. ব্যবসা ও গ্রাম", "২. খাতা বহি / ইউপিআই", "৩. ঋণ সক্ষমতা যাচাই", "৪. সরকারি প্রকল্প", "৫. ব্যাঙ্ক ডসিয়ার"),
            businessTypes = mapOf(
                BusinessCategory.TAILOR to "দর্জি ও সেলাই কেন্দ্র",
                BusinessCategory.DAIRY to "দুগ্ধ খামার ও পশুপালন",
                BusinessCategory.FABRICATION to "ওয়েল্ডিং ও ওয়ার্কশপ",
                BusinessCategory.SHG_ENTERPRISE to "মহিলা স্বনির্ভর দল (SHG)",
                BusinessCategory.KIRANA to "মুদিখানা ও গ্রামীণ দোকান",
                BusinessCategory.VISHWAKARMA_ARTISAN to "ছুতোর / কামার / কারিগর"
            ),
            prompts = PromptStrings(
                heroWelcome = "নমস্কার! সহায় ক্রেডিটে আপনাকে স্বাগত। আইটিআর না থাকলেও আপনার ইউপিআই ও খাতার হিসাব দিয়ে মুদ্রা ও পিএমইজিপি প্রকল্পে ঋণ পাওয়া সম্ভব।",
                chooseBusinessAudio = "আপনার ব্যবসার ধরন বেছে নিন এবং কত ঋণ প্রয়োজন তা লিখুন।",
                recordUploadAudio = "আপনার ইউপিআই রেকর্ড বা দুধ বিক্রির রশিদ যোগ করুন অথবা নমুনা বেছে নিন।",
                analysisAudio = "আপনার মাসিক সঞ্চয় এবং কিস্তি পরিশোধের ক্ষমতা যাচাই করা হচ্ছে।",
                step4Audio = "আপনার জন্য সরকারি প্রকল্প পাওয়া গেছে! মুদ্রা এবং পিএমইজিপি প্রকল্পে সরকারি অনুদান পাবেন, কোনো জমি বন্ধক রাখতে হবে না।",
                dossierAudio = "আপনার ব্যাঙ্ক ডসিয়ার প্রস্তুত হয়েছে। সরকারি ভর্তুকির বিবরণ এতে সংযুক্ত রয়েছে।"
            ),
            labels = LabelStrings(
                monthlyIncome = "মাসিক বিক্রি / আয় (₹)",
                loanAmount = "প্রয়োজনীয় ঋণ (₹)",
                category = "শ্রেণি (ভর্তুকির জন্য)",
                gender = "লিঙ্গ",
                village = "গ্রাম",
                district = "জেলা",
                uploadRecords = "খাতা বা ইউপিআই রেকর্ড লিখুন",
                orChoosePreset = "অথবা নমুনা উদাহরণ বেছে নিন:",
                analyzeRecords = "ঋণ যোগ্যতা যাচাই করুন",
                viewSchemes = "প্রকল্পগুলি দেখুন",
                generateDossier = "ব্যাঙ্ক ডসিয়ার তৈরি করুন",
                middlemanCutWarning = "দালালরা ১০-১৫% কেটে নেয় - এখানে শূন্য খরচ!",
                moneylenderCostWarning = "মহাজনরা ২৪-৩৬% সুদ নেয় - সরকারি প্রকল্পে মাত্র ৫-৯%!",
                estimatedSubsidy = "আনুমানিক সরকারি অনুদান:",
                dscrMetric = "পরিশোধের সক্ষমতা (DSCR):",
                riskScore = "বিশ্বাসযোগ্যতা স্কোর:",
                consentStatement = "আমার সম্মতিক্রমে এই তথ্য কেবল ব্যাঙ্ক ঋণ প্রসেসিংয়ে ব্যবহৃত হবে।",
                branchManagerTitle = "ব্যাঙ্ক শাখা ব্যবস্থাপক পোর্টাল",
                fieldQuestionsTitle = "মাঠ পর্যায়ের প্রশ্ন ও বাস্তবতা",
                approvedByEntrepreneur = "উদ্যোক্তা দ্বারা যাচাইকৃত",
                downloadDossier = "ডসিয়ার ডাউনলোড করুন",
                printDossier = "প্রিন্ট নিন"
            )
        ),
        SupportedLanguage.KN to TranslationStrings(
            appName = "ಸಹಾಯ್ ಕ್ರೆಡಿಟ್ AI",
            tagline = "ಗ್ರಾಮೀಣ ಸಣ್ಣ ಉದ್ಯಮಿಗಳಿಗೆ ನೇರ ಸರ್ಕಾರಿ ಯೋಜನೆ ಮತ್ತು ಬ್ಯಾಂಕ್ ಸಾಲ ಮಾರ್ಗದರ್ಶಿ",
            badge = "ಮಧ್ಯವರ್ತಿಗಳಿಲ್ಲ · ಐಟಿಆರ್ ಅಗತ್ಯವಿಲ್ಲ · ಉಚಿತ",
            listenAudio = "ಆಲಿಸಿ",
            stopAudio = "ನಿಲ್ಲಿಸಿ",
            voiceGuide = "ಧ್ವನಿ ಮಾರ್ಗದರ್ಶಿ",
            askAssistant = "ಸಹಾಯ್ ಮಿತ್ರನನ್ನು ಕೇಳಿ",
            selectLanguage = "ಭಾಷೆ",
            steps = StepLabels("೧. ವ್ಯಾಪಾರ ಮತ್ತು ಗ್ರಾಮ", "೨. ಖಾತಾ ಪುಸ್ತಕ / UPI", "೩. ಸಾಲ ಅರ್ಹತೆ ವಿಶ್ಲೇಷಣೆ", "೪. ಸರ್ಕಾರಿ ಯೋಜನೆಗಳು", "೫. ಬ್ಯಾಂಕ್ ವರದಿ"),
            businessTypes = mapOf(
                BusinessCategory.TAILOR to "ಟೈಲರಿಂಗ್ ಮತ್ತು ಬಟ್ಟೆ ಹೊಲಿಯುವ ಕೇಂದ್ರ",
                BusinessCategory.DAIRY to "ಡೈರಿ ಮತ್ತು ಹೈನುಗಾರಿಕೆ",
                BusinessCategory.FABRICATION to "ವೆಲ್ಡಿಂಗ್ ಮತ್ತು ಕಮ್ಮಾರಿಕೆ",
                BusinessCategory.SHG_ENTERPRISE to "ಮಹಿಳಾ ಸ್ವಸಹಾಯ ಸಂಘ",
                BusinessCategory.KIRANA to "ಕಿರಾಣಿ ಮತ್ತು ಗ್ರಾಮದ ಅಂಗಡಿ",
                BusinessCategory.VISHWAKARMA_ARTISAN to "ಬಡಿಗ / ಕಮ್ಮಾರ / ಕುಶಲಕರ್ಮಿ"
            ),
            prompts = PromptStrings(
                heroWelcome = "ನಮಸ್ಕಾರ! ಸಹಾಯ್ ಕ್ರೆಡಿಟ್‌ಗೆ ಸ್ವಾಗತ. ನಿಮ್ಮ ಬಳಿ ಐಟಿಆರ್ ಇಲ್ಲದಿದ್ದರೂ ನಿಮ್ಮ ಯುಪಿಐ ಮತ್ತು ಖಾತಾ ಪುಸ್ತಕದ ಮೂಲಕ ಮುದ್ರಾ ಮತ್ತು ಪಿಎಂಇಜಿಪಿ ಸಾಲ ಪಡೆಯಬಹುದು.",
                chooseBusinessAudio = "ನಿಮ್ಮ ವ್ಯಾಪಾರವನ್ನು ಆಯ್ಕೆಮಾಡಿ ಮತ್ತು ಅಗತ್ಯವಿರುವ ಸಾಲದ ಮೊತ್ತವನ್ನು ನಮೂದಿಸಿ.",
                recordUploadAudio = "ನಿಮ್ಮ ಯುಪಿಐ ಅಥವಾ ಹಾಲಿನ ರಸೀದಿಯನ್ನು ನಮೂದಿಸಿ ಅಥವಾ ನಮ್ಮ ಉದಾಹರಣೆಯನ್ನು ಆರಿಸಿ.",
                analysisAudio = "ನಿಮ್ಮ ಮಾಸಿಕ ಉಳಿತಾಯ ಮತ್ತು ಸಾಲ ಮರುಪಾವತಿ ಸಾಮರ್ಥ್ಯವನ್ನು ವಿಶ್ಲೇಷಿಸಲಾಗುತ್ತಿದೆ.",
                step4Audio = "ನಿಮಗಾಗಿ ಸರ್ಕಾರಿ ಯೋಜನೆಗಳು ದೊರೆತಿವೆ! ಮುದ್ರಾ ಮತ್ತು PMEGP ಯೋಜನೆಯಲ್ಲಿ ಸಬ್ಸಿಡಿ ದೊರೆಯುತ್ತದೆ, ಆಸ್ತಿ ಅಡಮಾನ ಅಗತ್ಯವಿಲ್ಲ.",
                dossierAudio = "ನಿಮ್ಮ ಬ್ಯಾಂಕ್ ವರದಿ ಸಿದ್ಧವಾಗಿದೆ. ಇದರಲ್ಲಿ ಸರ್ಕಾರದ ಸಬ್ಸಿಡಿ ವಿವರಗಳಿವೆ."
            ),
            labels = LabelStrings(
                monthlyIncome = "ಮಾಸಿಕ ಆದಾಯ (₹)",
                loanAmount = "ಅಗತ್ಯ ಸಾಲದ ಮೊತ್ತ (₹)",
                category = "ಸಾಮಾಜಿಕ ವರ್ಗ",
                gender = "ಲಿಂಗ",
                village = "ಗ್ರಾಮ",
                district = "ಜಿಲ್ಲೆ",
                uploadRecords = "ಖಾತಾ ಅಥವಾ ಯುಪಿಐ ವಿವರ ನಮೂದಿಸಿ",
                orChoosePreset = "ಅಥವಾ ಉದಾಹರಣೆಯನ್ನು ಆರಿಸಿ:",
                analyzeRecords = "ಅರ್ಹತೆ ಪರಿಶೀಲಿಸಿ",
                viewSchemes = "ಯೋಜನೆಗಳನ್ನು ವೀಕ್ಷಿಸಿ",
                generateDossier = "ಬ್ಯಾಂಕ್ ವರದಿ ರಚಿಸಿ",
                middlemanCutWarning = "ಮಧ್ಯವರ್ತಿಗಳು 10-15% ಕಮಿಷನ್ ಪಡೆಯುತ್ತಾರೆ - ಇಲ್ಲಿ ಶೂನ್ಯ ಶುಲ್ಕ!",
                moneylenderCostWarning = "ಲೇವಿದೇವಿಗಾರರು 24-36% ಬಡ್ಡಿ ವಿಧಿಸುತ್ತಾರೆ - ಬ್ಯಾಂಕಿನಲ್ಲಿ ಕೇವಲ 5-9%!",
                estimatedSubsidy = "ಅಂದಾಜು ಸರ್ಕಾರಿ ಸಬ್ಸಿಡಿ:",
                dscrMetric = "ಮರುಪಾವತಿ ಸಾಮರ್ಥ್ಯ (DSCR):",
                riskScore = "ವಿಶ್ವಾಸಾರ್ಹತೆ ಸ್ಕೋರ್:",
                consentStatement = "ನನ್ನ ಸಮ್ಮತಿಯೊಂದಿಗೆ ಈ ಡೇಟಾವನ್ನು ಬ್ಯಾಂಕ್ ಸಾಲಕ್ಕಾಗಿ ಮಾತ್ರ ಬಳಸಲಾಗುತ್ತದೆ.",
                branchManagerTitle = "ಬ್ಯಾಂಕ್ ಶಾಖಾ ವ್ಯವಸ್ಥಾಪಕರ ಪೋರ್ಟಲ್",
                fieldQuestionsTitle = "ಕ್ಷೇತ್ರ ಪ್ರಶ್ನೆಗಳು ಮತ್ತು ನೈಜತೆ",
                approvedByEntrepreneur = "ಉದ್ಯಮಿಯಿಂದ ದೃಢೀಕರಿಸಲ್ಪಟ್ಟಿದೆ",
                downloadDossier = "ವರದಿ ಡೌನ್‌ಲೋಡ್ ಮಾಡಿ",
                printDossier = "ಪ್ರಿಂಟ್ ಮಾಡಿ"
            )
        ),
        SupportedLanguage.GU to TranslationStrings(
            appName = "સહાય ક્રેડિટ AI",
            tagline = "ગ્રામીણ સૂક્ષ્મ ઉદ્યોગસાહસિકો માટે સરકારી યોજના અને બેંક લોન સહાયક",
            badge = "દલાલમુક્ત · ITR જરૂરી નથી · 100% મફત",
            listenAudio = "સાંભળો",
            stopAudio = "અવાજ બંધ કરો",
            voiceGuide = "ઓડિયો માર્ગદર્શિકા",
            askAssistant = "સહાય મિત્રને પૂછો",
            selectLanguage = "ભાષા",
            steps = StepLabels("૧. વ્યવસાય અને ગામ", "૨. ખાતાવહી / UPI", "૩. લોન ક્ષમતા ચકાસણી", "૪. સરકારી યોજનાઓ", "૫. બેંક રિપોર્ટ"),
            businessTypes = mapOf(
                BusinessCategory.TAILOR to "દરજી અને સિલાઈ કેન્દ્ર",
                BusinessCategory.DAIRY to "ડેરી અને પશુપાલન",
                BusinessCategory.FABRICATION to "વેલ્ડિંગ અને વર્કશોપ",
                BusinessCategory.SHG_ENTERPRISE to "મહિલા સ્વસહાય જૂથ (SHG)",
                BusinessCategory.KIRANA to "કરિયાણા અને ગામની દુકાન",
                BusinessCategory.VISHWAKARMA_ARTISAN to "સુથાર / લુહાર / પરંપરાગત કારીગર"
            ),
            prompts = PromptStrings(
                heroWelcome = "નમસ્તે! સહાય ક્રેડિટમાં આપનું સ્વાગત છે. જો તમારી પાસે ITR નથી, તો પણ UPI અને ખાતાવહીના વ્યવહારોથી મુદ્રા અને PMEGP લોન સરળતાથી મળી શકે છે.",
                chooseBusinessAudio = "કૃપા કરીને તમારો વ્યવસાય પસંદ કરો અને જરૂરી લોનની રકમ દાખલ કરો.",
                recordUploadAudio = "તમારો UPI રેકોર્ડ અથવા દૂધની સ્લિપ દાખલ કરો અથવા નમૂના પસંદ કરો.",
                analysisAudio = "તમારી માસિક કમાણી અને હપ્તો ભરવાની ક્ષમતા તપાસવામાં આવી રહી છે.",
                step4Audio = "તમારા માટે સરકારી યોજનાઓ મળી ગઈ છે! મુદ્રા અને PMEGP યોજનામાં સબસિડી મળશે અને કોઈ મિલકત ગીરો મૂકવાની જરૂર નથી.",
                dossierAudio = "તમારો બેંક રિપોર્ટ તૈયાર છે. તેમાં સરકારી સબસિડીની વિગતો પણ સામેલ છે."
            ),
            labels = LabelStrings(
                monthlyIncome = "માસિક આવક (₹)",
                loanAmount = "જરૂરી લોન રકમ (₹)",
                category = "સામાજિક વર્ગ",
                gender = "જાતિ",
                village = "ગામ",
                district = "જિલ્લો",
                uploadRecords = "ખાતાવહી અથવા UPI રેકોર્ડ લખો",
                orChoosePreset = "અથવા નમૂનો પસંદ કરો:",
                analyzeRecords = "પાત્રતા અને સબસિડી તપાસો",
                viewSchemes = "યોજનાઓ જુઓ",
                generateDossier = "બેંક રિપોર્ટ બનાવો",
                middlemanCutWarning = "દલાલો ૧૦-૧૫% કમિશન લે છે - અહીં શૂન્ય ફી!",
                moneylenderCostWarning = "શાહુકારો ૨૪-૩૬% વ્યાજ વસૂલે છે - બેંકમાં માત્ર ૫-૯%!",
                estimatedSubsidy = "અંદાજિત સરકારી સબસિડી:",
                dscrMetric = "હપ્તો ભરવાની ક્ષમતા (DSCR):",
                riskScore = "વિશ્વસનીયતા સ્કોર:",
                consentStatement = "મારી સંમતિથી જ આ ડેટા બેંક લોન પ્રક્રિયા માટે વપરાશે.",
                branchManagerTitle = "બેંક શાખા પ્રબંધક પોર્ટલ",
                fieldQuestionsTitle = "ક્ષેત્રીય પ્રશ્નો અને વાસ્તવિકતા",
                approvedByEntrepreneur = "ઉદ્યોગસાહસિક દ્વારા ચકાસાયેલ",
                downloadDossier = "રિપોર્ટ ડાઉનલોડ કરો",
                printDossier = "પ્રિન્ટ કરો"
            )
        )
    )

    private val uiStringsMap: Map<SupportedLanguage, UiStrings> by lazy {
        mapOf(
            SupportedLanguage.HI to HindiUiStrings,
            SupportedLanguage.EN to EnglishUiStrings,
            SupportedLanguage.KN to KannadaUiStrings,
            SupportedLanguage.TA to TamilUiStrings,
            SupportedLanguage.TE to TeluguUiStrings,
            SupportedLanguage.MR to MarathiUiStrings,
            SupportedLanguage.BN to BengaliUiStrings,
            SupportedLanguage.GU to GujaratiUiStrings
        )
    }

    fun get(lang: SupportedLanguage): TranslationStrings =
        translations[lang] ?: translations.getValue(SupportedLanguage.HI)

    fun ui(lang: SupportedLanguage): UiStrings =
        uiStringsMap[lang] ?: EnglishUiStrings
}
