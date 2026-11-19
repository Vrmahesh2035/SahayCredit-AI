package com.example.repository

import com.example.model.SupportedLanguage
import com.example.model.VoiceAssistantResponse

internal object AiVoiceLocalizedTemplates {

    private enum class QueryTopic {
        MIDDLEMAN,
        SUBSIDY,
        UPI_KHATA,
        BRANCH_MANAGER,
        NO_ITR_GENERAL
    }

    private fun detectTopic(question: String): QueryTopic {
        val q = question.lowercase()
        return when {
            listOf("agent", "middleman", "commission", "bribe", "25,000", "दलाल", "कमीशन", "कमिशन", "ಏಜೆಂಟ್", "ಮಧ್ಯವರ್ತಿ", "ಕಮಿಷನ್", "இடைத்தரகர்", "தரகர்", "దళారీ", "కమీషన్", "দালাল", "દલાલ").any { it in q } ->
                QueryTopic.MIDDLEMAN
            listOf("pmegp", "subsidy", "35%", "grant", "सब्सिडी", "अनुदान", "ಸಬ್ಸಿಡಿ", "ಅನುದಾನ", "மானியம்", "సబ్సిడీ", "सबसिडी", "ভর্তুকি", "સબસિડી").any { it in q } ->
                QueryTopic.SUBSIDY
            listOf("upi", "phonepe", "gpay", "google pay", "qr", "khata", "बही", "ಖಾತಾ", "ஏடு", "ఖాతా", "वही", "খাতা", "ખાતાવહી").any { it in q } ->
                QueryTopic.UPI_KHATA
            listOf("manager", "branch", "collateral", "property", "प्रबंधक", "मैनेजर", "ವ್ಯವಸ್ಥಾಪಕ", "ಮ್ಯಾನೇಜರ್", "மேலாளர்", "మేనేజర్", "व्यवस्थापक", "ম্যানেজার", "મેનેજર").any { it in q } ->
                QueryTopic.BRANCH_MANAGER
            else -> QueryTopic.NO_ITR_GENERAL
        }
    }

    fun buildResponse(question: String, lang: SupportedLanguage): VoiceAssistantResponse {
        val topic = detectTopic(question)
        val spoken = spokenAnswer(topic, lang)
        val summary = englishSummary(topic)
        val bullets = bulletPoints(lang)
        val warning = middlemanWarning(lang)
        val followUps = followUps(lang)
        return VoiceAssistantResponse(
            spokenResponse = spoken,
            englishSummary = summary,
            keyAdviceBulletPoints = bullets,
            middlemanWarning = warning,
            followUpSuggestions = followUps,
            isPlaceholderResponse = true
        )
    }

    private fun spokenAnswer(topic: QueryTopic, lang: SupportedLanguage): String = when (lang) {
        SupportedLanguage.KN -> when (topic) {
            QueryTopic.MIDDLEMAN -> "ಮಾದರಿ ಮಾರ್ಗದರ್ಶಿ: ದಲ್ಲಾಳಿಗೆ ₹1 ಕೂಡ ಕಮಿಷನ್ ನೀಡಬೇಡಿ! ಎಲ್ಲಾ ಮುದ್ರಾ ಮತ್ತು PMEGP ಅರ್ಜಿಗಳು ಸಂಪೂರ್ಣ ಉಚಿತವಾಗಿವೆ. ಸಹಾಯ್ ಕ್ರೆಡಿಟ್ ವರದಿಯನ್ನು ನೇರವಾಗಿ ಬ್ಯಾಂಕ್ ಮ್ಯಾನೇಜರ್‌ಗೆ ತೋರಿಸಿ."
            QueryTopic.SUBSIDY -> "ಮಾದರಿ ಮಾರ್ಗದರ್ಶಿ: ಗ್ರಾಮೀಣ ಪ್ರದೇಶದಲ್ಲಿ SC, ST, OBC ಮತ್ತು ಮಹಿಳಾ ಉದ್ಯಮಿಗಳಿಗೆ PMEGP ಯೋಜನೆಯಡಿ 35% (ಸಾಮಾನ್ಯ ವರ್ಗಕ್ಕೆ 25%) ಸರ್ಕಾರಿ ಸಬ್ಸಿಡಿ ಸಿಗುತ್ತದೆ. ಇದು ನೇರವಾಗಿ ಬ್ಯಾಂಕ್ ಖಾತೆಗೆ ಜಮೆಯಾಗುತ್ತದೆ."
            QueryTopic.UPI_KHATA -> "ಮಾದರಿ ಮಾರ್ಗದರ್ಶಿ: ಹೌದು! RBI FIDD ಮಾರ್ಗಸೂಚಿಗಳ ಪ್ರಕಾರ ನಿಮ್ಮ 6 ತಿಂಗಳ PhonePe / Google Pay QR ವಹಿವಾಟು ಮತ್ತು ದೈನಂದಿನ ಖಾತಾ ಪುಸ್ತಕವನ್ನು ಬ್ಯಾಂಕ್ ಅಧಿಕೃತ ಆದಾಯದ ಪುರಾವೆಯಾಗಿ ಸ್ವೀಕರಿಸುತ್ತದೆ."
            QueryTopic.BRANCH_MANAGER -> "ಮಾದರಿ ಮಾರ್ಗದರ್ಶಿ: ಬ್ಯಾಂಕ್ ವ್ಯವಸ್ಥಾಪಕರು ನಿಮ್ಮ DSCR (>1.30), ನಿಯಮಿತ ವ್ಯಾಪಾರದ ನಗದು ಹರಿವು ಮತ್ತು 85% CGTMSE ಸರ್ಕಾರಿ ಖಾತರಿಯನ್ನು ನೋಡುತ್ತಾರೆ. ಸಹಾಯ್ ಕ್ರೆಡಿಟ್ ವರದಿಯಲ್ಲಿ ಇವೆಲ್ಲವೂ ಸಿದ್ಧವಾಗಿರುತ್ತವೆ."
            QueryTopic.NO_ITR_GENERAL -> "ನಮಸ್ಕಾರ! (ಮಾದರಿ ಮಾರ್ಗದರ್ಶಿ) ಯಾವುದೇ ITR ಅಥವಾ ಮಧ್ಯವರ್ತಿ ಇಲ್ಲದೆ ನೀವು ನೇರವಾಗಿ ಬ್ಯಾಂಕಿನಲ್ಲಿ ಮುದ್ರಾ ಯೋಜನೆಯಡಿ ₹10 ಲಕ್ಷದವರೆಗೆ ಸಾಲ ಪಡೆಯಬಹುದು. ನಿಮ್ಮ 6 ತಿಂಗಳ UPI ದಾಖಲೆಗಳನ್ನು ತೆಗೆದುಕೊಂಡು ಹೋಗಿ."
        }
        SupportedLanguage.HI -> when (topic) {
            QueryTopic.MIDDLEMAN -> "नमूना मार्गदर्शन: किसी भी दलाल को ₹1 भी कमीशन न दें! सभी मुद्रा, PMEGP और विश्वकर्मा आवेदन 100% निःशुल्क हैं। अपनी SahayCredit रिपोर्ट सीधे बैंक शाखा प्रबंधक को दें।"
            QueryTopic.SUBSIDY -> "नमूना मार्गदर्शन: ग्रामीण क्षेत्र में महिला, SC, ST व OBC उद्यमियों को PMEGP योजना में 35% (सामान्य वर्ग को 25%) सरकारी सब्सिडी मिलती है, जो सीधे लोन मूलधन में समायोजित होती है।"
            QueryTopic.UPI_KHATA -> "नमूना मार्गदर्शन: हाँ! RBI FIDD दिशानिर्देशों के तहत आपके 6 महीने के PhonePe/GPay QR लेन-देन और कच्ची बही-खाते को बैंक मुख्य आय प्रमाण के रूप में स्वीकार करता है।"
            QueryTopic.BRANCH_MANAGER -> "नमूना मार्गदर्शन: बैंक मैनेजर आपका DSCR (>1.30), नियमित कैशफ्लो और 85% CGTMSE सरकारी गारंटी देखते हैं। आपकी SahayCredit रिपोर्ट में ये सभी बिंदु शामिल हैं।"
            QueryTopic.NO_ITR_GENERAL -> "नमस्ते! (नमूना मार्गदर्शन) आप बिना किसी ITR या दलाल के सीधे बैंक में मुद्रा योजना के तहत ₹10 लाख तक का ऋण ले सकते हैं। अपने 6 महीने के UPI QR और बही-खाता रिकॉर्ड साथ रखें।"
        }
        SupportedLanguage.TA -> when (topic) {
            QueryTopic.MIDDLEMAN -> "மாதிரி வழிகாட்டி: எந்த இடைத்தரகருக்கும் ₹1 கூட கமிஷன் கொடுக்காதீர்கள்! அனைத்து முத்ரா மற்றும் PMEGP விண்ணப்பங்களும் 100% இலவசம். உங்கள் அறிக்கையை நேரடியாக வங்கி மேலாளரிடம் கொடுங்கள்."
            QueryTopic.SUBSIDY -> "மாதிரி வழிகாட்டி: கிராமப்புறங்களில் பெண்கள், SC, ST, OBC பிரிவினருக்கு PMEGP திட்டத்தில் 35% (பொது பிரிவுக்கு 25%) அரசு மானியம் கிடைக்கும்."
            QueryTopic.UPI_KHATA -> "மாதிரி வழிகாட்டி: ஆம்! உங்கள் 6 மாத PhonePe / GPay QR பரிவர்த்தனைகள் மற்றும் கணக்கு ஏடு விவரங்களை வங்கி அதிகாரப்பூர்வ வருமான ஆதாரமாக ஏற்றுக்கொள்கிறது."
            QueryTopic.BRANCH_MANAGER -> "மாதிரி வழிகாட்டி: வங்கி மேலாளர் உங்கள் DSCR (>1.30), தொடர் பணப்புழக்கம் மற்றும் 85% CGTMSE உத்தரவாதத்தைப் பார்ப்பார்."
            QueryTopic.NO_ITR_GENERAL -> "வணக்கம்! (மாதிரி வழிகாட்டி) ITR அல்லது இடைத்தரகர் இல்லாமல் நீங்கள் நேரடியாக வங்கியில் முத்ரா கடன் ₹10 லட்சம் வரை பெறலாம். உங்கள் UPI பதிவுகளை எடுத்துச் செல்லுங்கள்."
        }
        SupportedLanguage.TE -> when (topic) {
            QueryTopic.MIDDLEMAN -> "నమూనా మార్గదర్శి: ఏ దళారీకి ₹1 కూడా కమీషన్ ఇవ్వకండి! అన్ని ముద్రా మరియు PMEGP దరఖాస్తులు 100% ఉచితం. మీ నివేదికను నేరుగా బ్యాంక్ మేనేజర్‌కు చూపించండి."
            QueryTopic.SUBSIDY -> "నమూనా మార్గదర్శి: గ్రామీణ ప్రాంతాల్లో మహిళలు, SC, ST, OBC వర్గాలకు PMEGP పథకంలో 35% (జనరల్‌కు 25%) ప్రభుత్వ సబ్సిడీ లభిస్తుంది."
            QueryTopic.UPI_KHATA -> "నమూనా మార్గదర్శి: అవును! మీ 6 నెలల PhonePe / GPay QR లావాదేవీలు మరియు ఖాతా పుస్తకాన్ని బ్యాంక్ ఆదాయ రుజువుగా అంగీకరిస్తుంది."
            QueryTopic.BRANCH_MANAGER -> "నమూనా మార్గదర్శి: బ్రాంచ్ మేనేజర్ మీ DSCR (>1.30), నగదు ప్రవాహం మరియు 85% CGTMSE ప్రభుత్వ గ్యారంటీని పరిశీలిస్తారు."
            QueryTopic.NO_ITR_GENERAL -> "నమస్కారం! (నమూనా మార్గదర్శి) ఎటువంటి ITR లేదా దళారులు లేకుండా మీరు ముద్రా పథకం కింద నేరుగా బ్యాంక్ లోన్ పొందవచ్చు. మీ UPI రికార్డులను తీసుకెళ్లండి."
        }
        SupportedLanguage.MR -> when (topic) {
            QueryTopic.MIDDLEMAN -> "नमुना मार्गदर्शन: कोणत्याही दलालाला ₹1 ही कमिशन देऊ नका! सर्व मुद्रा आणि PMEGP अर्ज 100% मोफत आहेत. तुमचा अहवाल थेट बँक व्यवस्थापकांना द्या."
            QueryTopic.SUBSIDY -> "नमुना मार्गदर्शन: ग्रामीण भागात महिला, SC, ST व OBC उद्योजकांना PMEGP योजनेत 35% (सर्वसाधारण 25%) सरकारी अनुदान मिळते."
            QueryTopic.UPI_KHATA -> "नमुना मार्गदर्शन: होय! तुमचे 6 महिन्यांचे PhonePe / GPay QR व्यवहार आणि कच्ची वही बँक उत्पन्नाचा पुरावा म्हणून स्वीकारते."
            QueryTopic.BRANCH_MANAGER -> "नमुना मार्गदर्शन: बँक व्यवस्थापक तुमचा DSCR (>1.30), रोख प्रवाह आणि 85% CGTMSE हमी तपासतात."
            QueryTopic.NO_ITR_GENERAL -> "नमस्कार! (नमुना मार्गदर्शन) कोणत्याही ITR किंवा दलालाशिवाय तुम्ही थेट बँकेत मुद्रा योजनेअंतर्गत ₹10 लाखापर्यंत कर्ज मिळवू शकता."
        }
        SupportedLanguage.BN -> when (topic) {
            QueryTopic.MIDDLEMAN -> "নমুনা নির্দেশিকা: কোনো দালালকে ₹1-ও কমিশন দেবেন না! সমস্ত মুদ্রা ও PMEGP আবেদন ১০০% বিনামূল্যে হয়। সরাসরি ব্যাঙ্ক ম্যানেজারকে আপনার রিপোর্ট দেখান।"
            QueryTopic.SUBSIDY -> "নমুনা নির্দেশিকা: গ্রামীণ এলাকায় মহিলা, SC, ST ও OBC উদ্যোক্তাদের PMEGP প্রকল্পে ৩৫% (সাধারণ শ্রেণিতে ২৫%) সরকারি ভর্তুকি দেওয়া হয়।"
            QueryTopic.UPI_KHATA -> "নমুনা নির্দেশিকা: হ্যাঁ! আপনার ৬ মাসের PhonePe / GPay QR লেনদেন এবং হাতের খাতা ব্যাঙ্ক আয়ের প্রমাণ হিসেবে গ্রহণ করে।"
            QueryTopic.BRANCH_MANAGER -> "নমুনা নির্দেশিকা: ব্যাঙ্ক ম্যানেজার আপনার DSCR (>১.৩০), নগদ প্রবাহ এবং ৮৫% CGTMSE গ্যারান্টি যাচাই করেন।"
            QueryTopic.NO_ITR_GENERAL -> "নমস্কার! (নমুনা নির্দেশিকা) কোনো দালাল বা আইটিআর ছাড়াই আপনি সরাসরি ব্যাঙ্কে মুদ্রা ঋণ পেতে পারেন। আপনার UPI রেকর্ড ও পাসবুক সঙ্গে রাখুন।"
        }
        SupportedLanguage.GU -> when (topic) {
            QueryTopic.MIDDLEMAN -> "નમૂના માર્ગદર્શન: કોઈપણ દલાલને ₹1 પણ કમિશન આપશો નહીં! બધી મુદ્રા અને PMEGP અરજીઓ 100% મફત છે. તમારો રિપોર્ટ સીધો બેંક મેનેજરને આપો."
            QueryTopic.SUBSIDY -> "નમૂના માર્ગદર્શન: ગ્રામીણ વિસ્તારમાં મહિલા, SC, ST અને OBC ઉદ્યોગસાહસિકોને PMEGP હેઠળ 35% (સામાન્ય 25%) સરકારી સબસિડી મળે છે."
            QueryTopic.UPI_KHATA -> "નમૂના માર્ગદર્શન: હા! તમારા 6 મહિનાના PhonePe / GPay QR વ્યવહારો અને ખાતાવહી બેંક આવકના પુરાવા તરીકે સ્વીકારે છે."
            QueryTopic.BRANCH_MANAGER -> "નમૂના માર્ગદર્શન: બેંક મેનેજર તમારો DSCR (>1.30), રોકડ પ્રવાહ અને 85% CGTMSE ગેરંટી જુએ છે."
            QueryTopic.NO_ITR_GENERAL -> "નમસ્તે! (નમૂના માર્ગદર્શન) કોઈપણ ITR કે દલાલ વગર તમે સીધા બેંકમાં મુદ્રા લોન મેળવી શકો છો. તમારા UPI રેકોર્ડ સાથે રાખો."
        }
        SupportedLanguage.EN -> when (topic) {
            QueryTopic.MIDDLEMAN -> "Sample guidance: Never pay ₹1 in commission to any middleman! All MUDRA, PMEGP, and PM Vishwakarma applications are 100% free. Present your SahayCredit dossier directly to the Branch Manager."
            QueryTopic.SUBSIDY -> "Sample guidance: In rural areas, Women, SC, ST, and OBC entrepreneurs receive 35% capital subsidy (25% for General) under PMEGP, directly adjusted against loan principal."
            QueryTopic.UPI_KHATA -> "Sample guidance: Yes! Under RBI FIDD guidelines, banks accept 6 months of PhonePe / Google Pay QR statements and daily khata records as primary repayment proof."
            QueryTopic.BRANCH_MANAGER -> "Sample guidance: Branch managers verify your DSCR (>1.30), regular sales velocity, and 85% CGTMSE/CGFMU default guarantee—all included in your SahayCredit Dossier."
            QueryTopic.NO_ITR_GENERAL -> "Welcome! (Sample guidance) You can apply directly for MUDRA or PMEGP loans up to ₹10 Lakhs without ITR or third-party collateral. Carry your 6-month UPI statements and khata ledger."
        }
    }

    private fun englishSummary(topic: QueryTopic): String = when (topic) {
        QueryTopic.MIDDLEMAN -> "Sample guidance: Never pay agent commissions. Present the SahayCredit dossier directly to the bank."
        QueryTopic.SUBSIDY -> "Sample guidance: Claim 25%–35% PMEGP rural capital subsidy via KVIC e-Portal."
        QueryTopic.UPI_KHATA -> "Sample guidance: Banks accept 6-month UPI QR bank settlements and khata ledgers without ITR."
        QueryTopic.BRANCH_MANAGER -> "Sample guidance: Branch managers evaluate DSCR (>1.30) and 85% CGTMSE guarantee protection."
        QueryTopic.NO_ITR_GENERAL -> "Sample guidance: Apply directly for MUDRA or PMEGP loans without ITR or collateral."
    }

    private fun bulletPoints(lang: SupportedLanguage): List<String> = when (lang) {
        SupportedLanguage.KN -> listOf(
            "UPI QR ದೈನಂದಿನ ಜಮಾ ತೋರಿಸುವ 6 ತಿಂಗಳ ಬ್ಯಾಂಕ್ ಖಾತೆ ಸ್ಟೇಟ್‌ಮೆಂಟ್ ಪ್ರಿಂಟ್ ತೆಗೆದುಕೊಳ್ಳಿ",
            "ಬ್ಯಾಂಕ್ ಶಾಖಾ ವ್ಯವಸ್ಥಾಪಕರಲ್ಲಿ ಮುದ್ರಾ (PMMY) ಕಿಶೋರ ಅಥವಾ PMEGP 35% ಸಬ್ಸಿಡಿ ಅರ್ಜಿ ಕೇಳಿ",
            "RBI FIDD ಮತ್ತು CGTMSE ಸಾಲ ಖಾತರಿ ಸುತ್ತೋಲೆಗಳಿರುವ SahayCredit ವರದಿಯನ್ನು ಸಲ್ಲಿಸಿ"
        )
        SupportedLanguage.HI -> listOf(
            "दैनिक UPI QR जमा दिखाने वाला 6 महीने का बैंक स्टेटमेंट प्रिंट निकालें",
            "बैंक शाखा प्रबंधक से मुद्रा (PMMY) किशोर या PMEGP 35% सब्सिडी फॉर्म मांगें",
            "RBI FIDD और CGTMSE गारंटी संदर्भों वाली SahayCredit प्रमाणित रिपोर्ट प्रस्तुत करें"
        )
        SupportedLanguage.TA -> listOf(
            "தினசரி UPI QR வரவுகளைக் காட்டும் 6 மாத வங்கி அறிக்கையை அச்சிட்டு எடுத்துச் செல்லுங்கள்",
            "வங்கி கிளை மேலாளரிடம் முத்ரா (PMMY) கிஷோர் அல்லது PMEGP 35% மானிய படிவத்தைக் கேளுங்கள்",
            "RBI FIDD மற்றும் CGTMSE உத்தரவாத சுற்றறிக்கைகள் கொண்ட SahayCredit அறிக்கையை சமர்ப்பிக்கவும்"
        )
        SupportedLanguage.TE -> listOf(
            "రోజువారీ UPI QR జమలను చూపే 6 నెలల బ్యాంక్ స్టేట్‌మెంట్ ప్రింట్ తీసుకోండి",
            "బ్యాంక్ మేనేజర్‌ను ముద్రా (PMMY) కిషోర్ లేదా PMEGP 35% సబ్సిడీ ఫారమ్ అడగండి",
            "RBI FIDD మరియు CGTMSE గ్యారంటీ ఉత్తర్వులతో కూడిన SahayCredit నివేదికను అందజేయండి"
        )
        SupportedLanguage.MR -> listOf(
            "दैनिक UPI QR जमा दर्शवणारे ६ महिन्यांचे बँक स्टेटमेंट प्रिंट काढा",
            "बँक व्यवस्थापकांकडे मुद्रा (PMMY) किशोर किंवा PMEGP 35% अनुदान अर्ज मागा",
            "RBI FIDD आणि CGTMSE हमी संदर्भांसह SahayCredit प्रमाणित अहवाल सादर करा"
        )
        SupportedLanguage.BN -> listOf(
            "দৈনিক UPI QR জমা দেখানো ৬ মাসের ব্যাঙ্ক স্টেটমেন্ট প্রিন্ট করে নিয়ে যান",
            "ব্যাঙ্ক ম্যানেজারের কাছে মুদ্রা (PMMY) কিশোর বা PMEGP ৩৫% ভর্তুকির ফর্ম চান",
            "RBI FIDD এবং CGTMSE গ্যারান্টি সূত্রসহ SahayCredit প্রত্যয়িত রিপোর্ট জমা দিন"
        )
        SupportedLanguage.GU -> listOf(
            "દૈનિક UPI QR જમા દર્શાવતું 6 મહિનાનું બેંક સ્ટેટમેન્ટ પ્રિન્ટ કાઢો",
            "બેંક મેનેજર પાસે મુદ્રા (PMMY) કિશોર અથવા PMEGP 35% સબસિડી ફોર્મ માંગો",
            "RBI FIDD અને CGTMSE ગેરંટી સંદર્ભો સાથેનો SahayCredit રિપોર્ટ રજૂ કરો"
        )
        SupportedLanguage.EN -> listOf(
            "Print 6 months of bank account statements reflecting UPI QR daily settlements",
            "Request the Branch Manager for MUDRA (PMMY) Kishore or PMEGP 35% capital subsidy form",
            "Present the SahayCredit certified Dossier citing RBI FIDD & CGTMSE credit guarantee circulars"
        )
    }

    private fun middlemanWarning(lang: SupportedLanguage): String = when (lang) {
        SupportedLanguage.KN -> "ಸ್ಥಳೀಯ ಏಜೆಂಟ್‌ಗಳಿಗೆ 10–15% ಕಮಿಷನ್ ಅಥವಾ ಮುಂಗಡ ನಗದು ಎಂದಿಗೂ ನೀಡಬೇಡಿ. ಎಲ್ಲಾ ಜನಸಮರ್ಥ್ (JanSamarth) ಅರ್ಜಿಗಳು 100% ಉಚಿತವಾಗಿವೆ."
        SupportedLanguage.HI -> "कभी भी किसी दलाल या एजेंट को 10–15% कमीशन या अग्रिम नकद न दें। सभी जनसमर्थ (JanSamarth) आवेदन 100% निःशुल्क हैं।"
        SupportedLanguage.TA -> "உள்ளூர் தரகர்களுக்கு 10–15% கமிஷன் அல்லது முன்பணம் ஒருபோதும் கொடுக்காதீர்கள். அனைத்து ஜன்சமர்த் விண்ணப்பங்களும் 100% இலவசம்."
        SupportedLanguage.TE -> "స్థానిక ఏజెంట్లకు 10–15% కమీషన్ లేదా ముందుస్తు నగదు ఎప్పుడూ ఇవ్వకండి. అన్ని జనసమర్థ్ (JanSamarth) దరఖాస్తులు 100% ఉచితం."
        SupportedLanguage.MR -> "कोणत्याही एजंटला 10–15% कमिशन किंवा आगाऊ रोख कधीही देऊ नका. सर्व जनसमर्थ (JanSamarth) अर्ज 100% मोफत आहेत."
        SupportedLanguage.BN -> "স্থানীয় এজেন্টদের কখনোই ১০–১৫% কমিশন বা অগ্রিম নগদ দেবেন না। সমস্ত জনসমর্থ (JanSamarth) আবেদন ১০০% বিনামূল্যে হয়।"
        SupportedLanguage.GU -> "સ્થાનિક એજન્ટોને ક્યારેય 10–15% કમિશન કે અગાઉથી રોકડ આપશો નહીં. બધી જનસમર્થ (JanSamarth) અરજીઓ 100% મફત છે."
        SupportedLanguage.EN -> "NEVER pay 10-15% cut or upfront processing cash to local agents. All JanSamarth applications are 100% free of charge."
    }

    private fun followUps(lang: SupportedLanguage): List<String> = when (lang) {
        SupportedLanguage.KN -> listOf(
            "ಮ್ಯಾನೇಜರ್ ಆಸ್ತಿ ದಾಖಲೆ ಕೇಳಿದರೆ ನಾನು ಏನು ಹೇಳಬೇಕು?",
            "PMEGP 35% ಸಬ್ಸಿಡಿ ನನ್ನ ಖಾತೆಗೆ ಹೇಗೆ ಜಮೆಯಾಗುತ್ತದೆ?",
            "ನಗದು ಮತ್ತು UPI ಮಾರಾಟವಿದ್ದರೆ ಮುದ್ರಾ ಕಿಶೋರ ಸಾಲ ಸಿಗುತ್ತದೆಯೇ?"
        )
        SupportedLanguage.HI -> listOf(
            "अगर मैनेजर जमीन या घर के कागज मांगे तो मुझे क्या कहना चाहिए?",
            "PMEGP की 35% सब्सिडी मेरे खाते में कैसे आती है?",
            "दैनिक नकद और UPI बिक्री होने पर क्या मुद्रा किशोर लोन मिलेगा?"
        )
        SupportedLanguage.TA -> listOf(
            "மேலாளர் சொத்து ஆவணம் கேட்டால் நான் என்ன சொல்ல வேண்டும்?",
            "PMEGP 35% மானியம் எனது வங்கி கணக்கில் எப்படி வரும்?",
            "ரொக்கம் மற்றும் UPI விற்பனை இருந்தால் முத்ரா கிஷோர் கடன் கிடைக்குமா?"
        )
        SupportedLanguage.TE -> listOf(
            "మేనేజర్ ఆస్తి పత్రాలు అడిగితే నేను ఏమి చెప్పాలి?",
            "PMEGP 35% సబ్సిడీ నా ఖాతాలోకి ఎలా వస్తుంది?",
            "నగదు మరియు UPI అమ్మకాలు ఉంటే ముద్రా కిషోర్ లోన్ లభిస్తుందా?"
        )
        SupportedLanguage.MR -> listOf(
            "मॅनेजरने मालमत्तेची कागदपत्रे मागितली तर मी काय सांगावे?",
            "PMEGP 35% अनुदान माझ्या खात्यात कसे जमा होते?",
            "रोख व UPI विक्री असल्यास मुद्रा किशोर कर्ज मिळू शकते का?"
        )
        SupportedLanguage.BN -> listOf(
            "ম্যানেজার জমির দলিল চাইলে আমার কী বলা উচিত?",
            "PMEGP ৩৫% ভর্তুকি আমার অ্যাকাউন্টে কীভাবে জমা হবে?",
            "নগদ ও UPI বিক্রি থাকলে কি মুদ্রা কিশোর ঋণ পাওয়া যাবে?"
        )
        SupportedLanguage.GU -> listOf(
            "મેનેજર મિલકતના કાગળો માંગે તો મારે શું કહેવું જોઈએ?",
            "PMEGP 35% સબસિડી મારા ખાતામાં કેવી રીતે જમા થાય છે?",
            "રોકડ અને UPI વેચાણ હોય તો મુદ્રા કિશોર લોન મળી શકે?"
        )
        SupportedLanguage.EN -> listOf(
            "What should I say if the manager demands property papers?",
            "How does PMEGP 35% subsidy get credited to my account?",
            "Can I get MUDRA Kishore if my daily sales are in cash & UPI?"
        )
    }
}
