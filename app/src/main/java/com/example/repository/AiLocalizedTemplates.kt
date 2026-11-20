package com.example.repository

import com.example.model.BusinessCategory
import com.example.model.Gender
import com.example.model.RecordType
import com.example.model.SchemeMatch
import com.example.model.SocialCategory
import com.example.model.SupportedLanguage
import com.example.model.VillageAudioGuideText
import com.example.util.formatInr

internal object AiLocalizedTemplates {

    fun localizedRiskLevel(lang: SupportedLanguage): String = when (lang) {
        SupportedLanguage.HI -> "कम जोखिम (Low Risk)"
        SupportedLanguage.KN -> "ಕಡಿಮೆ ಅಪಾಯ (Low Risk)"
        SupportedLanguage.TA -> "குறைந்த இடர் (Low Risk)"
        SupportedLanguage.TE -> "తక్కువ రిస్క్ (Low Risk)"
        SupportedLanguage.MR -> "कमी जोखीम (Low Risk)"
        SupportedLanguage.BN -> "কম ঝুঁকি (Low Risk)"
        SupportedLanguage.GU -> "ઓછું જોખમ (Low Risk)"
        SupportedLanguage.EN -> "Low Risk"
    }

    fun localizedVerdict(lang: SupportedLanguage): String = when (lang) {
        SupportedLanguage.HI -> "बैंक ऋण हेतु पूर्णतः सक्षम (नमूना आकलन)"
        SupportedLanguage.KN -> "ಬ್ಯಾಂಕ್ ಸಾಲಕ್ಕೆ ಅರ್ಹ ಅಭ್ಯರ್ಥಿ (ಮಾದರಿ ಮೌಲ್ಯಮಾಪನ)"
        SupportedLanguage.TA -> "வங்கி கடனுக்கு தகுதியானவர் (மாதிரி மதிப்பீடு)"
        SupportedLanguage.TE -> "బ్యాంక్ రుణానికి అర్హమైన వ్యాపారం (నమూనా మదింపు)"
        SupportedLanguage.MR -> "बँक कर्जासाठी पात्र उद्योजक (नमुना मूल्यांकन)"
        SupportedLanguage.BN -> "ব্যাঙ্ক ঋণের জন্য যোগ্য প্রার্থী (নমুনা মূল্যায়ন)"
        SupportedLanguage.GU -> "બેંક લોન માટે યોગ્ય અરજદાર (નમૂના મૂલ્યાંકન)"
        SupportedLanguage.EN -> "Viable Bank Candidate (Sample Calibration)"
    }

    fun localizedExplainability(
        lang: SupportedLanguage,
        dailyAvg: Int,
        marginPct: Int,
        dscrFormatted: String
    ): String {
        val daily = formatInr(dailyAvg)
        return when (lang) {
            SupportedLanguage.HI ->
                "[नमूना स्थानीय आकलन] अनौपचारिक बही-खाता व UPI रिकॉर्ड से लगभग $daily/दिन की आवक और $marginPct% शुद्ध मार्जिन प्रमाणित होता है। $dscrFormatted x का DSCR RBI FIDD के 1.30 मानक से ऊपर है और CGTMSE/CGFMU गारंटी में सुरक्षित है।"
            SupportedLanguage.KN ->
                "[ಮಾದರಿ ಸ್ಥಳೀಯ ಮೌಲ್ಯಮಾಪನ] ದೈನಂದಿನ UPI ಮತ್ತು ಖಾತಾ ದಾಖಲೆಗಳು ದಿನಕ್ಕೆ ಅಂದಾಜು $daily ಒಳಹರಿವು ಮತ್ತು $marginPct% ನಿವ್ವಳ ಲಾಭಾಂಶವನ್ನು ತೋರಿಸುತ್ತವೆ. ${dscrFormatted}x DSCR ಪ್ರಮಾಣವು RBI FIDD 1.30 ಮಾನದಂಡಕ್ಕಿಂತ ಹೆಚ್ಚಿದ್ದು CGTMSE/CGFMU ಅಡಿಯಲ್ಲಿ ಸುರಕ್ಷಿತವಾಗಿದೆ."
            SupportedLanguage.TA ->
                "[மாதிரி உள்ளூர் மதிப்பீடு] UPI மற்றும் கணக்கு ஏடு பதிவுகள் தினமும் சுமார் $daily வருமானத்தையும் $marginPct% லாப வரம்பையும் காட்டுகின்றன. ${dscrFormatted}x DSCR மதிப்பு RBI FIDD 1.30 அளவை விட சிறப்பாக உள்ளது."
            SupportedLanguage.TE ->
                "[నమూనా స్థానిక మదింపు] రోజువారీ UPI మరియు ఖాతా పుస్తక రికార్డులు సుమారు $daily/రోజు రాబడి మరియు $marginPct% లాభ మార్జిన్‌ను సూచిస్తున్నాయి. ${dscrFormatted}x DSCR విలువ RBI FIDD 1.30 ప్రమాణం కంటే ఎక్కువగా ఉంది."
            SupportedLanguage.MR ->
                "[नमुना स्थानिक मूल्यांकन] दैनिक UPI आणि वही खात्यावरून अंदाजे $daily/दिवस आवक आणि $marginPct% नफा मार्जिन सिद्ध होते. ${dscrFormatted}x चे DSCR प्रमाण RBI FIDD 1.30 मानकापेक्षा जास्त असून CGTMSE हमीअंतर्गत सुरक्षित आहे."
            SupportedLanguage.BN ->
                "[নমুনা স্থানীয় মূল্যায়ন] দৈনিক UPI ও খাতার হিসাব থেকে প্রতিদিন প্রায় $daily আয় এবং $marginPct% লাভের হার প্রমাণিত হয়। ${dscrFormatted}x DSCR মানদণ্ড RBI FIDD ১.৩০ সীমার উপরে এবং CGTMSE-এর আওতায় সুরক্ষিত।"
            SupportedLanguage.GU ->
                "[નમૂના સ્થાનિક મૂલ્યાંકન] દૈનિક UPI અને ખાતાવહીના રેકોર્ડ અંદાજે $daily/દિવસની આવક અને $marginPct% નફાનો ગાળો દર્શાવે છે. ${dscrFormatted}x નો DSCR RBI FIDD 1.30 ધોરણ કરતા વધુ છે."
            SupportedLanguage.EN ->
                "[Sample Local Appraisal] Verified cash velocity indicates ~$daily/day inflow with a $marginPct% gross margin. DSCR of ${dscrFormatted}x exceeds the RBI FIDD 1.30x benchmark under CGTMSE/CGFMU guarantee."
        }
    }

    fun localizedSchemeMatches(
        lang: SupportedLanguage,
        businessType: BusinessCategory,
        recordType: RecordType,
        socialCategory: SocialCategory,
        gender: Gender
    ): List<SchemeMatch> {
        val isSpecial = socialCategory == SocialCategory.SC ||
            socialCategory == SocialCategory.ST ||
            gender == Gender.FEMALE
        val isArtisan = businessType == BusinessCategory.TAILOR ||
            businessType == BusinessCategory.FABRICATION ||
            businessType == BusinessCategory.VISHWAKARMA_ARTISAN

        return when (lang) {
            SupportedLanguage.KN -> listOf(
                SchemeMatch(
                    schemeName = "ಪ್ರಧಾನ ಮಂತ್ರಿ ಮುದ್ರಾ ಯೋಜನೆ (PMMY) - ಕಿಶೋರ",
                    categoryTag = "ಅಡಮಾನ-ರಹಿತ MSME ಸಾಲ",
                    subsidyOrBenefit = "ಆಸ್ತಿ ಅಡಮಾನವಿಲ್ಲ (CGFMU ಖಾತರಿ)",
                    maxLimit = "₹5,00,000 ವರೆಗೆ",
                    officialCitation = "PMMY ಸುತ್ತೋಲೆ DFS/RBI FIDD.MSME.BC.No.4/2023-24",
                    whyEligible = "ನಿಯಮಿತ UPI/ಖಾತಾ ವಹಿವಾಟು ದಾಖಲೆ ಹೊಂದಿರುವ ಸಕ್ರಿಯ ಗ್ರಾಮೀಣ ಉದ್ಯಮ.",
                    interestBenefit = "MCLR ಆಧಾರಿತ ರಿಯಾಯಿತಿ ಬಡ್ಡಿ ದರ (8.5% - 9.75%)"
                ),
                SchemeMatch(
                    schemeName = "ಪ್ರಧಾನ ಮಂತ್ರಿ ಉದ್ಯೋಗ ಸೃಜನ ಕಾರ್ಯಕ್ರಮ (PMEGP)",
                    categoryTag = "ಬಂಡವಾಳ ಮಾರ್ಜಿನ್ ಸಬ್ಸಿಡಿ",
                    subsidyOrBenefit = if (isSpecial) "35% ಗ್ರಾಮೀಣ ವಿಶೇಷ ವರ್ಗದ ಅನುದಾನ" else "25% ಗ್ರಾಮೀಣ ಅನುದಾನ",
                    maxLimit = "₹20,00,000 (ಸೇವೆ) / ₹50,00,000 (ಉತ್ಪಾದನೆ)",
                    officialCitation = "KVIC / MoMSME ಅಧಿಸೂಚನೆ PMEGP/2024-25",
                    whyEligible = "${socialCategory.label} / ${gender.label} ವರ್ಗದ ಗ್ರಾಮೀಣ ಉದ್ಯಮಿಗೆ ವಿಶೇಷ ಸಹಾಯಧನ ಅರ್ಹತೆ.",
                    interestBenefit = "ಸರ್ಕಾರಿ ಸಬ್ಸಿಡಿ ಸಾಲದ ಅಸಲಿನಲ್ಲಿ ನೇರವಾಗಿ ಹೊಂದಾಣಿಕೆಯಾಗುತ್ತದೆ"
                ),
                if (isArtisan) {
                    SchemeMatch(
                        schemeName = "PM ವಿಶ್ವಕರ್ಮ ಯೋಜನೆ",
                        categoryTag = "ರಿಯಾಯಿತಿ ಕುಶಲಕರ್ಮಿ ಸಾಲ",
                        subsidyOrBenefit = "5% ಬಡ್ಡಿ ದರ + ₹15,000 ಉಪಕರಣ ಅನುದಾನ",
                        maxLimit = "ಎರಡು ಹಂತಗಳಲ್ಲಿ ₹3,00,000 ವರೆಗೆ",
                        officialCitation = "PM ವಿಶ್ವಕರ್ಮ ಮಾರ್ಗಸೂಚಿ 2023-24",
                        whyEligible = "ಸಾಂಪ್ರದಾಯಿಕ ಟೈಲರ್/ವೆಲ್ಡರ್/ಕುಶಲಕರ್ಮಿ ವೃತ್ತಿಗೆ 8% ಬಡ್ಡಿ ಸಹಾಯಧನ.",
                        interestBenefit = "ಉದ್ಯಮಿಗೆ ವಾರ್ಷಿಕ ಕೇವಲ 5% ಸ್ಥಿರ ಬಡ್ಡಿ ದರ"
                    )
                } else {
                    SchemeMatch(
                        schemeName = "ಸೂಕ್ಷ್ಮ ಮತ್ತು ಸಣ್ಣ ಉದ್ಯಮಗಳ ಕ್ರೆಡಿಟ್ ಗ್ಯಾರಂಟಿ ನಿಧಿ (CGTMSE)",
                        categoryTag = "ಸಾಲ ಖಾತರಿ ರಕ್ಷಣೆ",
                        subsidyOrBenefit = "85% ಸರ್ಕಾರಿ ಸಾಲ ಖಾತರಿ ಕವಚ",
                        maxLimit = "₹5 ಕೋಟಿಯವರೆಗೆ",
                        officialCitation = "CGTMSE ಸುತ್ತೋಲೆ 222/2023-24",
                        whyEligible = "ಆಸ್ತಿ ಅಡಮಾನವಿಲ್ಲದೆ ಯಂತ್ರೋಪಕರಣ ಮತ್ತು ವ್ಯಾಪಾರ ವಿಸ್ತರಣೆಗೆ ಅರ್ಹ.",
                        interestBenefit = "ಜಮೀನು/ಮನೆ ಪತ್ರ ಕೇಳದೆ ಬ್ಯಾಂಕ್‌ಗೆ ಪೂರ್ಣ ಸುರಕ್ಷತೆ"
                    )
                }
            )

            SupportedLanguage.HI -> listOf(
                SchemeMatch(
                    schemeName = "प्रधानमंत्री मुद्रा योजना (PMMY) - किशोर",
                    categoryTag = "बिना गारंटी MSME ऋण",
                    subsidyOrBenefit = "शून्य गिरवी (CGFMU सरकारी गारंटी)",
                    maxLimit = "₹5,00,000 तक",
                    officialCitation = "PMMY परिपत्र DFS/RBI FIDD.MSME.BC.No.4/2023-24",
                    whyEligible = "सक्रिय UPI और बही-खाता नकदी प्रवाह के आधार पर पात्र व्यवसाय।",
                    interestBenefit = "रियायती बैंक ब्याज दर (8.5% - 9.75%)"
                ),
                SchemeMatch(
                    schemeName = "प्रधानमंत्री रोजगार सृजन कार्यक्रम (PMEGP)",
                    categoryTag = "पूंजीगत सब्सिडी अनुदान",
                    subsidyOrBenefit = if (isSpecial) "35% ग्रामीण विशेष वर्ग अनुदान" else "25% ग्रामीण अनुदान",
                    maxLimit = "₹20,00,000 (सेवा) / ₹50,00,000 (विनिर्माण)",
                    officialCitation = "KVIC / MoMSME अधिसूचना PMEGP/2024-25",
                    whyEligible = "ग्रामीण क्षेत्र के पात्र उद्यमी हेतु गैर-वापसी योग्य मार्जिन मनी।",
                    interestBenefit = "सरकारी अनुदान सीधे मूलधन में समायोजित होता है"
                ),
                if (isArtisan) {
                    SchemeMatch(
                        schemeName = "PM विश्वकर्मा योजना",
                        categoryTag = "कारीगर रियायती ऋण",
                        subsidyOrBenefit = "5% ब्याज दर + ₹15,000 टूलकिट सहायता",
                        maxLimit = "₹3,00,000 तक (दो किश्तों में)",
                        officialCitation = "PM विश्वकर्मा दिशानिर्देश 2023-24",
                        whyEligible = "दर्जी, वेल्डर व पारंपरिक कारीगर ट्रेड के लिए 8% ब्याज अनुदान।",
                        interestBenefit = "उद्यमी के लिए मात्र 5% वार्षिक निश्चित ब्याज"
                    )
                } else {
                    SchemeMatch(
                        schemeName = "CGTMSE क्रेडिट गारंटी योजना",
                        categoryTag = "सरकारी ऋण सुरक्षा",
                        subsidyOrBenefit = "85% डिफ़ॉल्ट गारंटी कवरेज",
                        maxLimit = "₹5 करोड़ तक",
                        officialCitation = "CGTMSE परिपत्र 222/2023-24",
                        whyEligible = "बिना जमीन या मकान गिरवी रखे व्यापार विस्तार के लिए मान्य।",
                        interestBenefit = "बैंक को बिना किसी निजी गारंटर के सरकारी सुरक्षा"
                    )
                }
            )

            SupportedLanguage.TA -> listOf(
                SchemeMatch(
                    schemeName = "பிரதம மந்திரி முத்ரா திட்டம் (PMMY) - கிஷோர்",
                    categoryTag = "அடமானமற்ற MSME கடன்",
                    subsidyOrBenefit = "சொத்து அடமானமில்லை (CGFMU உத்தரவாதம்)",
                    maxLimit = "₹5,00,000 வரை",
                    officialCitation = "PMMY சுற்றறிக்கை DFS/RBI FIDD.MSME.BC.No.4/2023-24",
                    whyEligible = "தொடர்ச்சியான UPI மற்றும் கணக்கு ஏடு பணப்புழக்கம் கொண்ட கிராமப்புற தொழில்.",
                    interestBenefit = "சலுகை வங்கி வட்டி விகிதம் (8.5% - 9.75%)"
                ),
                SchemeMatch(
                    schemeName = "பிரதம மந்திரி வேலைவாய்ப்பு உருவாக்கத் திட்டம் (PMEGP)",
                    categoryTag = "மூலதன மானியத் திட்டம்",
                    subsidyOrBenefit = if (isSpecial) "35% கிராமப்புற சிறப்பு மானியம்" else "25% கிராமப்புற மானியம்",
                    maxLimit = "₹20,00,000 (சேவை) / ₹50,00,000 (உற்பத்தி)",
                    officialCitation = "KVIC / MoMSME அறிவிப்பு PMEGP/2024-25",
                    whyEligible = "கிராமப்புற தொழில்முனைவோருக்கான திருப்பிச் செலுத்த தேவையில்லா மானியம்.",
                    interestBenefit = "அரசு மானியம் நேரடியாக கடன் அசலில் சரிசெய்யப்படும்"
                ),
                if (isArtisan) {
                    SchemeMatch(
                        schemeName = "PM விஸ்வகர்மா திட்டம்",
                        categoryTag = "கைவினைஞர் சலுகை கடன்",
                        subsidyOrBenefit = "5% வட்டி + ₹15,000 கருவித்தொகுப்பு மானியம்",
                        maxLimit = "₹3,00,000 வரை",
                        officialCitation = "PM விஸ்வகர்மா வழிகாட்டுதல் 2023-24",
                        whyEligible = "தையல்காரர், வெல்டர் மற்றும் பாரம்பரிய கைவினைஞர்களுக்கு 8% வட்டி மானியம்.",
                        interestBenefit = "ஆண்டுக்கு நிலையான 5% வட்டி மட்டுமே"
                    )
                } else {
                    SchemeMatch(
                        schemeName = "CGTMSE கடன் உத்தரவாதத் திட்டம்",
                        categoryTag = "கடன் உத்தரவாதம்",
                        subsidyOrBenefit = "85% அரசு கடன் உத்தரவாத பாதுகாப்பு",
                        maxLimit = "₹5 கோடி வரை",
                        officialCitation = "CGTMSE சுற்றறிக்கை 222/2023-24",
                        whyEligible = "சொத்து அடமானமின்றி இயந்திரம் மற்றும் மூலதனம் பெறத் தகுதியானது.",
                        interestBenefit = "வங்கிக்கு முழு அரசு உத்தரவாதம்"
                    )
                }
            )

            SupportedLanguage.TE -> listOf(
                SchemeMatch(
                    schemeName = "ప్రధాన మంత్రి ముద్రా యోజన (PMMY) - కిషోర్",
                    categoryTag = "హామీ లేని MSME రుణం",
                    subsidyOrBenefit = "ఆస్తి తనఖా లేదు (CGFMU గ్యారంటీ)",
                    maxLimit = "₹5,00,000 వరకు",
                    officialCitation = "PMMY సర్క్యులర్ DFS/RBI FIDD.MSME.BC.No.4/2023-24",
                    whyEligible = "క్రమబద్ధమైన UPI మరియు ఖాతా పుస్తక ఆదాయం కలిగిన గ్రామీణ వ్యాపారం.",
                    interestBenefit = "రాయితీ బ్యాంక్ వడ్డీ రేటు (8.5% - 9.75%)"
                ),
                SchemeMatch(
                    schemeName = "ప్రధాన మంత్రి ఉపాధి కల్పన కార్యక్రమం (PMEGP)",
                    categoryTag = "మూలధన సబ్సిడీ గ్రాంట్",
                    subsidyOrBenefit = if (isSpecial) "35% గ్రామీణ ప్రత్యేక వర్గ సబ్సిడీ" else "25% గ్రామీణ సబ్సిడీ",
                    maxLimit = "₹20,00,000 (సేవ) / ₹50,00,000 (తయారీ)",
                    officialCitation = "KVIC / MoMSME నోటిఫికేషన్ PMEGP/2024-25",
                    whyEligible = "గ్రామీణ వ్యాపారస్తునికి తిరిగి చెల్లించనవసరం లేని ప్రభుత్వ గ్రాంట్.",
                    interestBenefit = "ప్రభుత్వ సబ్సిడీ నేరుగా అసలు రుణంలో సర్దుబాటు అవుతుంది"
                ),
                if (isArtisan) {
                    SchemeMatch(
                        schemeName = "PM విశ్వకర్మ యోజన",
                        categoryTag = "చేతివృత్తుల రాయితీ రుణం",
                        subsidyOrBenefit = "5% వడ్డీ + ₹15,000 టూల్‌కిట్ గ్రాంట్",
                        maxLimit = "₹3,00,000 వరకు",
                        officialCitation = "PM విశ్వకర్మ మార్గదర్శకాలు 2023-24",
                        whyEligible = "టైలర్, వెల్డర్ మరియు సాంప్రదాయ వృత్తులకు 8% వడ్డీ రాయితీ.",
                        interestBenefit = "సంవత్సరానికి కేవలం 5% స్థిర వడ్డీ రేటు"
                    )
                } else {
                    SchemeMatch(
                        schemeName = "CGTMSE క్రెడిట్ గ్యారంటీ పథకం",
                        categoryTag = "రుణ హామీ రక్షణ",
                        subsidyOrBenefit = "85% ప్రభుత్వ గ్యారంటీ కవరేజ్",
                        maxLimit = "₹5 కోట్ల వరకు",
                        officialCitation = "CGTMSE సర్క్యులర్ 222/2023-24",
                        whyEligible = "భూమి లేదా ఇల్లు తనఖా పెట్టకుండా వ్యాపార విస్తరణకు అర్హత.",
                        interestBenefit = "బ్యాంకుకు పూర్తి ప్రభుత్వ హామీ రక్షణ"
                    )
                }
            )

            SupportedLanguage.MR -> listOf(
                SchemeMatch(
                    schemeName = "प्रधानमंत्री मुद्रा योजना (PMMY) - किशोर",
                    categoryTag = "तारणमुक्त MSME कर्ज",
                    subsidyOrBenefit = "मालमत्ता गहाण नाही (CGFMU हमी)",
                    maxLimit = "₹5,00,000 पर्यंत",
                    officialCitation = "PMMY परिपत्रक DFS/RBI FIDD.MSME.BC.No.4/2023-24",
                    whyEligible = "नियमित UPI आणि वही खाते आवक असलेला सक्रिय ग्रामीण व्यवसाय.",
                    interestBenefit = "सवलतीचा बँक व्याज दर (8.5% - 9.75%)"
                ),
                SchemeMatch(
                    schemeName = "प्रधानमंत्री रोजगार निर्मिती कार्यक्रम (PMEGP)",
                    categoryTag = "भांडवली अनुदान (सबसिडी)",
                    subsidyOrBenefit = if (isSpecial) "35% ग्रामीण विशेष प्रवर्ग अनुदान" else "25% ग्रामीण अनुदान",
                    maxLimit = "₹20,00,000 (सेवा) / ₹50,00,000 (उत्पादन)",
                    officialCitation = "KVIC / MoMSME अधिसूचना PMEGP/2024-25",
                    whyEligible = "ग्रामीण उद्योजकासाठी बिनपरतावा सरकारी मार्जिन मनी अनुदान.",
                    interestBenefit = "सरकारी सबसिडी थेट कर्जाच्या मुद्दलात समायोजित होते"
                ),
                if (isArtisan) {
                    SchemeMatch(
                        schemeName = "PM विश्वकर्मा योजना",
                        categoryTag = "सवलतीचे कारागीर कर्ज",
                        subsidyOrBenefit = "5% व्याज दर + ₹15,000 टूलकिट अनुदान",
                        maxLimit = "₹3,00,000 पर्यंत",
                        officialCitation = "PM विश्वकर्मा मार्गदर्शक तत्त्वे 2023-24",
                        whyEligible = "शिंपी, वेल्डर व पारंपरिक कारागिरांसाठी 8% व्याज अनुदान.",
                        interestBenefit = "केवळ 5% वार्षिक निश्चित व्याज दर"
                    )
                } else {
                    SchemeMatch(
                        schemeName = "CGTMSE क्रेडिट गॅरंटी योजना",
                        categoryTag = "कर्ज हमी संरक्षण",
                        subsidyOrBenefit = "85% सरकारी कर्ज हमी संरक्षण",
                        maxLimit = "₹5 कोटींपर्यंत",
                        officialCitation = "CGTMSE परिपत्रक 222/2023-24",
                        whyEligible = "जमीन किंवा घर गहाण न ठेवता व्यवसाय विस्तारासाठी पात्र.",
                        interestBenefit = "बँकेला वैयक्तिक जामीनदाराशिवाय सरकारी सुरक्षा"
                    )
                }
            )

            SupportedLanguage.BN -> listOf(
                SchemeMatch(
                    schemeName = "প্রধানমন্ত্রী মুদ্রা যোজনা (PMMY) - কিশোর",
                    categoryTag = "জামানত-মুক্ত MSME ঋণ",
                    subsidyOrBenefit = "কোনো সম্পত্তি বন্ধক নয় (CGFMU গ্যারান্টি)",
                    maxLimit = "₹5,00,000 পর্যন্ত",
                    officialCitation = "PMMY সার্কুলার DFS/RBI FIDD.MSME.BC.No.4/2023-24",
                    whyEligible = "নিয়মিত UPI ও খাতার নগদ প্রবাহ থাকা সক্রিয় গ্রামীণ ব্যবসা।",
                    interestBenefit = "সহজ ব্যাঙ্ক সুদের হার (8.5% - 9.75%)"
                ),
                SchemeMatch(
                    schemeName = "প্রধানমন্ত্রী কর্মসংস্থান সৃষ্টি কর্মসূচি (PMEGP)",
                    categoryTag = "মূলধন ভর্তুকি অনুদান",
                    subsidyOrBenefit = if (isSpecial) "৩৫% গ্রামীণ বিশেষ শ্রেণির অনুদান" else "২৫% গ্রামীণ অনুদান",
                    maxLimit = "₹20,00,000 (পরিষেবা) / ₹50,00,000 (উৎপাদন)",
                    officialCitation = "KVIC / MoMSME বিজ্ঞপ্তি PMEGP/2024-25",
                    whyEligible = "গ্রামীণ উদ্যোক্তাদের জন্য অফেরতযোগ্য সরকারি মার্জিন মানি ভর্তুকি।",
                    interestBenefit = "সরকারি ভর্তুকি সরাসরি ঋণের আসলের সাথে সমন্বয় করা হয়"
                ),
                if (isArtisan) {
                    SchemeMatch(
                        schemeName = "PM বিশ্বকর্মা প্রকল্প",
                        categoryTag = "কারিগর ছাড়ের ঋণ",
                        subsidyOrBenefit = "৫% সুদ + ₹15,000 টুলকিট অনুদান",
                        maxLimit = "₹3,00,000 পর্যন্ত",
                        officialCitation = "PM বিশ্বকর্মা নির্দেশিকা ২০২৩-২৪",
                        whyEligible = "দর্জি, ওয়েল্ডার ও ঐতিহ্যবাহী কারিগরদের জন্য ৮% সুদে ভর্তুকি।",
                        interestBenefit = "বছরে মাত্র ৫% স্থির সুদের হার"
                    )
                } else {
                    SchemeMatch(
                        schemeName = "CGTMSE ক্রেডিট গ্যারান্টি প্রকল্প",
                        categoryTag = "ঋণ সুরক্ষা গ্যারান্টি",
                        subsidyOrBenefit = "৮৫% সরকারি ঋণ গ্যারান্টি সুরক্ষা",
                        maxLimit = "₹5 কোটি পর্যন্ত",
                        officialCitation = "CGTMSE সার্কুলার 222/2023-24",
                        whyEligible = "জমি বা বাড়ি বন্ধক না রেখেই ব্যবসা সম্প্রসারণের যোগ্য।",
                        interestBenefit = "ব্যাঙ্কের জন্য সম্পূর্ণ সরকারি নিরাপত্তা"
                    )
                }
            )

            SupportedLanguage.GU -> listOf(
                SchemeMatch(
                    schemeName = "પ્રધાનમંત્રી મુદ્રા યોજના (PMMY) - કિશોર",
                    categoryTag = "ગીરો વગરની MSME લોન",
                    subsidyOrBenefit = "કોઈ મિલકત ગીરો નહીં (CGFMU ગેરંટી)",
                    maxLimit = "₹5,00,000 સુધી",
                    officialCitation = "PMMY પરિપત્ર DFS/RBI FIDD.MSME.BC.No.4/2023-24",
                    whyEligible = "નિયમિત UPI અને ખાતાવહીનો રોકડ પ્રવાહ ધરાવતો સક્રિય ગ્રામીણ વ્યવસાય.",
                    interestBenefit = "રાહત દરે બેંક વ્યાજ (8.5% - 9.75%)"
                ),
                SchemeMatch(
                    schemeName = "પ્રધાનમંત્રી રોજગાર સર્જન કાર્યક્રમ (PMEGP)",
                    categoryTag = "મૂડી સબસિડી સહાય",
                    subsidyOrBenefit = if (isSpecial) "35% ગ્રામીણ વિશેષ વર્ગ સબસિડી" else "25% ગ્રામીણ સબસિડી",
                    maxLimit = "₹20,00,000 (સેવા) / ₹50,00,000 (ઉત્પાદન)",
                    officialCitation = "KVIC / MoMSME જાહેરનામું PMEGP/2024-25",
                    whyEligible = "ગ્રામીણ ઉદ્યોગસાહસિક માટે બિન-પરતપાત્ર સરકારી માર્જિન મની સબસિડી.",
                    interestBenefit = "સરકારી સબસિડી સીધી લોનની મુદ્દલમાં જમા થાય છે"
                ),
                if (isArtisan) {
                    SchemeMatch(
                        schemeName = "PM વિશ્વકર્મા યોજના",
                        categoryTag = "કારીગર રાહત લોન",
                        subsidyOrBenefit = "5% વ્યાજ દર + ₹15,000 ટૂલકીટ સહાય",
                        maxLimit = "₹3,00,000 સુધી",
                        officialCitation = "PM વિશ્વકર્મા માર્ગદર્શિકા 2023-24",
                        whyEligible = "દરજી, વેલ્ડર અને પરંપરાગત કારીગરો માટે 8% વ્યાજ સહાય.",
                        interestBenefit = "વાર્ષિક માત્ર 5% નિશ્ચિત વ્યાજ દર"
                    )
                } else {
                    SchemeMatch(
                        schemeName = "CGTMSE ક્રેડિટ ગેરંટી યોજના",
                        categoryTag = "ધિરાણ ગેરંટી સુરક્ષા",
                        subsidyOrBenefit = "85% સરકારી લોન ગેરંટી કવચ",
                        maxLimit = "₹5 કરોડ સુધી",
                        officialCitation = "CGTMSE પરિપત્ર 222/2023-24",
                        whyEligible = "જમીન કે મકાન ગીરો મૂક્યા વગર ધંધાના વિસ્તરણ માટે માન્ય.",
                        interestBenefit = "બેંક માટે સંપૂર્ણ સરકારી સુરક્ષા"
                    )
                }
            )

            SupportedLanguage.EN -> listOf(
                SchemeMatch(
                    schemeName = "Pradhan Mantri MUDRA Yojana (PMMY) - Kishore",
                    categoryTag = "Collateral-Free MSME Credit",
                    subsidyOrBenefit = "Nil Mortgage (CGFMU Guarantee)",
                    maxLimit = "Up to ₹5,00,000",
                    officialCitation = "PMMY Circular DFS/RBI FIDD.MSME.BC.No.4/2023-24",
                    whyEligible = "Sample appraisal: Active ${businessType.id.replace('_', ' ')} enterprise with ${recordType.id.replace('_', ' ')} track record.",
                    interestBenefit = "MCLR-linked nominal rate (8.5% - 9.75%)"
                ),
                SchemeMatch(
                    schemeName = "Prime Minister's Employment Generation Programme (PMEGP)",
                    categoryTag = "Capital Margin Subsidy",
                    subsidyOrBenefit = if (isSpecial) "35% Rural Special Category Grant" else "25% Rural Grant",
                    maxLimit = "Up to ₹20,00,000 (Service) / ₹50,00,000 (Mfg)",
                    officialCitation = "KVIC / MoMSME Notification PMEGP/2024-25",
                    whyEligible = "Rural enterprise classified under ${socialCategory.label} / ${gender.label} beneficiary status.",
                    interestBenefit = "Direct government capital subsidy adjusted against loan principal"
                ),
                if (isArtisan) {
                    SchemeMatch(
                        schemeName = "PM Vishwakarma Scheme",
                        categoryTag = "Concessional Artisan Credit",
                        subsidyOrBenefit = "5% Interest + ₹15,000 Toolkit Grant",
                        maxLimit = "Up to ₹3,00,000 in two tranches",
                        officialCitation = "PM Vishwakarma Scheme Guidelines 2023-24",
                        whyEligible = "Traditional artisan/tailor/welder trade eligible for 8% interest subvention.",
                        interestBenefit = "Fixed 5% p.a. net interest rate to entrepreneur"
                    )
                } else {
                    SchemeMatch(
                        schemeName = "Credit Guarantee Fund Trust for Micro and Small Enterprises (CGTMSE)",
                        categoryTag = "Credit Guarantee",
                        subsidyOrBenefit = "85% Default Guarantee Coverage",
                        maxLimit = "Up to ₹5 Crore",
                        officialCitation = "CGTMSE Circular 222/2023-24",
                        whyEligible = "Enterprise expanding production equipment with zero physical mortgage.",
                        interestBenefit = "Bank protected against default without demanding borrower land/house"
                    )
                }
            )
        }
    }

    fun localizedChecklist(lang: SupportedLanguage): List<String> = when (lang) {
        SupportedLanguage.HI -> listOf(
            "आवेदक का आधार कार्ड व ग्रामीण व्यवसाय स्थान सत्यापित करें",
            "UPI QR जमा प्रविष्टियों से मेल खाती बैंक पासबुक की जांच करें",
            "अधिकृत विक्रेता से मशीनरी / कच्चे माल का कोटेशन सत्यापित करें",
            "गारंटी हेतु CGFMU / CGTMSE पोर्टल पर स्वीकृति दर्ज करें"
        )
        SupportedLanguage.KN -> listOf(
            "ಅರ್ಜಿದಾರರ ಆಧಾರ್ ಮತ್ತು ಗ್ರಾಮೀಣ ಉದ್ಯಮದ ಸ್ಥಳವನ್ನು ದೃಢೀಕರಿಸಿ",
            "UPI QR ಜಮಾ ನಮೂದುಗಳಿಗೆ ಹೊಂದಿಕೆಯಾಗುವ ಬ್ಯಾಂಕ್ ಪಾಸ್‌ಬುಕ್ ಪರಿಶೀಲಿಸಿ",
            "ಅಧಿಕೃತ ಮಾರಾಟಗಾರರಿಂದ ಯಂತ್ರೋಪಕರಣ / ಸಾಮಗ್ರಿ ಕೊಟೇಶನ್ ದೃಢೀಕರಿಸಿ",
            "ಸರ್ಕಾರಿ ಖಾತರಿಗಾಗಿ CGFMU / CGTMSE ಪೋರ್ಟಲ್‌ನಲ್ಲಿ ಮಂಜೂರಾತಿ ದಾಖಲಿಸಿ"
        )
        SupportedLanguage.TA -> listOf(
            "விண்ணப்பதாரரின் ஆதார் மற்றும் கிராமிய தொழில் இடத்தை சரிபார்க்கவும்",
            "UPI QR வரவுகளுடன் பொருந்தும் வங்கி பாஸ்புக்கை ஆய்வு செய்யவும்",
            "அங்கீகரிக்கப்பட்ட விற்பனையாளரிடமிருந்து இயந்திர கொட்டேஷன் சரிபார்க்கவும்",
            "உத்தரவாதத்திற்கு CGFMU / CGTMSE போர்ட்டலில் ஒப்புதலைப் பதிவு செய்யவும்"
        )
        SupportedLanguage.TE -> listOf(
            "దరఖాస్తుదారుని ఆధార్ మరియు గ్రామీణ వ్యాపార చిరునామాను ధృవీకరించండి",
            "UPI QR జమలకు సరిపోయే బ్యాంక్ పాస్‌బుక్ ఎంట్రీలను తనిఖీ చేయండి",
            "అధీకృత డీలర్ నుండి యంత్రాలు / ముడి సరుకు కొటేషన్‌ను ధృవీకరించండి",
            "గ్యారంటీ కవరేజ్ కోసం CGFMU / CGTMSE పోర్టల్‌లో నమోదు చేయండి"
        )
        SupportedLanguage.MR -> listOf(
            "अर्जदाराचे आधार कार्ड व ग्रामीण व्यवसायाचे ठिकाण सत्यापित करा",
            "UPI QR जमा नोंदींशी जुळणारे बँक पासबुक तपासा",
            "अधिकृत पुरवठादाराकडून यंत्रसामग्री / मालाचे कोटेशन तपासा",
            "हमी कवचसाठी CGFMU / CGTMSE पोर्टलवर मंजुरी नोंदवा"
        )
        SupportedLanguage.BN -> listOf(
            "আবেদনকারীর আধার ও গ্রামীণ ব্যবসার ঠিকানা যাচাই করুন",
            "UPI QR জমার সাথে মিলিয়ে ব্যাঙ্ক পাসবুক পরীক্ষা করুন",
            "অনুমোদিত সরবরাহকারীর যন্ত্রপাতি / কাঁচামালের কোটেশন যাচাই করুন",
            "গ্যারান্টির জন্য CGFMU / CGTMSE পোর্টালে অনুমোদন নথিভুক্ত করুন"
        )
        SupportedLanguage.GU -> listOf(
            "અરજદારનું આધાર કાર્ડ અને ગ્રામીણ વ્યવસાયનું સ્થળ ચકાસો",
            "UPI QR જમા એન્ટ્રી સાથે મેળ ખાતી બેંક પાસબુક તપાસો",
            "અધિકૃત વિક્રેતા પાસેથી મશીનરી / કાચા માલનું ક્વોટેશન ચકાસો",
            "ગેરંટી સુરક્ષા માટે CGFMU / CGTMSE પોર્ટલ પર મંજૂરી નોંધો"
        )
        SupportedLanguage.EN -> listOf(
            "Verify applicant Aadhaar and rural enterprise location",
            "Review bank passbook matching UPI QR settlement credits",
            "Confirm machinery / inventory quotation from authorized supplier",
            "Mark sanction under CGFMU / CGTMSE portal for statutory guarantee issuance"
        )
    }

    fun localizedAudioGuideMap(netSurplus: Int): VillageAudioGuideText {
        val surplus = formatInr(netSurplus)
        val localized = mapOf(
            "hi" to "नमूना आकलन: आपकी मासिक बचत $surplus है। आप मुद्रा किशोर और PMEGP 35% सरकारी सब्सिडी के लिए पात्र हैं। बैंक आपसे कोई जमीन या मकान गिरवी नहीं मांग सकता।",
            "en" to "Sample assessment: Your net monthly surplus is $surplus. You qualify for MUDRA and PMEGP subsidy with zero collateral.",
            "kn" to "ಮಾದರಿ ಮೌಲ್ಯಮಾಪನ: ನಿಮ್ಮ ನಿವ್ವಳ ಮಾಸಿಕ ಉಳಿತಾಯ $surplus ಆಗಿದೆ. ನೀವು ಯಾವುದೇ ಆಸ್ತಿ ಅಡಮಾನವಿಲ್ಲದೆ ಮುದ್ರಾ ಮತ್ತು PMEGP 35% ಸಬ್ಸಿಡಿಗೆ ಅರ್ಹರಾಗಿದ್ದೀರಿ.",
            "ta" to "மாதிரி மதிப்பீடு: உங்கள் நிகர மாத சேமிப்பு $surplus. சொத்து அடமானம் இல்லாமல் முத்ரா மற்றும் PMEGP 35% மானியத்திற்கு தகுதியானவர்.",
            "te" to "నమూనా మదింపు: మీ నికర నెలవారీ మిగులు $surplus. ఎటువంటి ఆస్తి హామీ లేకుండా మీరు ముద్రా మరియు PMEGP 35% సబ్సిడీకి అర్హులు.",
            "mr" to "नमुना मूल्यांकन: तुमची निव्वळ मासिक बचत $surplus आहे. कोणतीही मालमत्ता गहाण न ठेवता तुम्ही मुद्रा आणि PMEGP 35% अनुदानासाठी पात्र आहात.",
            "bn" to "নমুনা মূল্যায়ন: আপনার নিট মাসিক সঞ্চয় $surplus। কোনো সম্পত্তি বন্ধক ছাড়াই আপনি মুদ্রা ও PMEGP ৩৫% ভর্তুকির যোগ্য।",
            "gu" to "નમૂના મૂલ્યાંકન: તમારી ચોખ્ખી માસિક બચત $surplus છે. કોઈપણ મિલકત ગીરો મૂક્યા વગર તમે મુદ્રા અને PMEGP 35% સબસિડી માટે પાત્ર છો."
        )
        return VillageAudioGuideText(
            hi = localized.getValue("hi"),
            en = localized.getValue("en"),
            localized = localized
        )
    }
}
