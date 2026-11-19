package com.example.data

import com.example.model.SupportedLanguage

data class LocalizedFieldQuestion(
    val id: Int,
    val title: String,
    val secondaryTitle: String,
    val groundProblem: String,
    val agenticAiSolution: String,
    val stakeholder: String,
    val metric: String
)

internal object FieldQuestionsTranslations {
    fun getTranslatedList(lang: SupportedLanguage): List<LocalizedFieldQuestion> = when (lang) {
        SupportedLanguage.HI -> listOf(
            LocalizedFieldQuestion(
                id = 1,
                title = "१. इस व्यवसाय ने किन योजनाओं के बारे में सुना है, और किससे?",
                secondaryTitle = "1. Which schemes has this business heard of, and from whom?",
                groundProblem = "गांव के दर्जी व कारीगरों ने सिर्फ चाय की दुकान या दलालों से सुना होता है कि लोन के लिए \"पहुंच\" चाहिए या घूस देनी होगी। वे 70-80% आधिकारिक योजनाओं (PMEGP, Vishwakarma) से अनजान रहते हैं।",
                agenticAiSolution = "सहाय-क्रेडिट AI मातृभाषा में बोलकर 7 केंद्रीय योजनाओं (मुद्रा, PMEGP 35% सब्सिडी, विश्वकर्मा 5% ब्याज) से सीधे मिलाता है।",
                stakeholder = "बिजनेस कॉरेस्पॉन्डेंट एवं महिला SHG फेडरेशन",
                metric = "योजना जागरूकता की 70–80% कमी को दूर करता है"
            ),
            LocalizedFieldQuestion(
                id = 2,
                title = "२. पिछले लोन प्रयास में क्या मांगा गया था, और फाइल कहाँ अटकी?",
                secondaryTitle = "2. What did the last loan attempt require, and where did it stall?",
                groundProblem = "बैंक ने 3 साल का ITR और सीए की ऑडिट रिपोर्ट मांगी। गांव के छोटे उद्यमी के पास ये नहीं थे, और 4 महीने दौड़ने के बाद फाइल खारिज हो गई।",
                agenticAiSolution = "दैनिक UPI क्यूआर कोड और दूध की पर्ची से बैंक-मान्य कैशफ्लो बनाकर सीधे DSCR गणना करता है।",
                stakeholder = "बैंक शाखाएं एवं NBFC संस्थान",
                metric = "15–20 कागजी दस्तावेजों की बाधा को समाप्त करता है"
            ),
            LocalizedFieldQuestion(
                id = 3,
                title = "३. दलाल / एजेंट क्या शुल्क लेता है, और वह वास्तव में क्या करता है?",
                secondaryTitle = "3. What does the agent charge, and what does he actually do?",
                groundProblem = "दलाल 10 से 15% नकद कमीशन (₹25,000 से ₹1,00,000) मांगते हैं। वास्तव में वे सिर्फ फोटोकॉपी कराते हैं और अंत में फाइल लटका देते हैं।",
                agenticAiSolution = "100% निःशुल्क और सीधा। दलाल को ₹1 भी दिए बिना सीधे बैंक मैनेजर के लिए प्रमाणित फाइल बनती है।",
                stakeholder = "जिला उद्योग केंद्र (DIC) एवं सतर्कता विभाग",
                metric = "प्रति उद्यमी ₹30,000–₹1,50,000 की सीधी बचत"
            ),
            LocalizedFieldQuestion(
                id = 4,
                title = "४. कौन से अनौपचारिक रिकॉर्ड मौजूद हैं, और क्या मालिक उन्हें साझा करेगा?",
                secondaryTitle = "4. What informal records exist, and would the owner share them?",
                groundProblem = "दुकानदार के पास फोनपे/गूगल-पे, दूध की डायरी और सामान के बिल होते हैं, पर उन्हें डर रहता है कि कहीं टैक्स वाले परेशान न करें।",
                agenticAiSolution = "उद्यमी की स्पष्ट डिजिटल सहमति से केवल बैंक लोन के लिए उपयोग किया जाता है। कोई कर नोटिस या दुरुपयोग नहीं।",
                stakeholder = "उद्यमी एवं डिजिटल पब्लिक इंफ्रास्ट्रक्चर (UPI)",
                metric = "100% सहमति के साथ अनौपचारिक रिकॉर्ड का उपयोग"
            ),
            LocalizedFieldQuestion(
                id = 5,
                title = "५. हां कहने से पहले बैंक शाखा प्रबंधक को क्या देखना आवश्यक है?",
                secondaryTitle = "5. What does a branch manager need to see before saying yes?",
                groundProblem = "ब्रांच मैनेजर को ऑडिट का डर होता है। उन्हें यह विश्वास चाहिए कि उधारकर्ता समय पर किस्त देगा और यदि कोई समस्या आई तो CGFMU / CGTMSE गारंटी बैंक को बचाएगी।",
                agenticAiSolution = "डीएससीआर (किस्त चुकाने की क्षमता) और 85% सरकारी CGTMSE गारंटी के साथ 4-सूत्रीय त्वरित जांच पत्र प्रदान करता है।",
                stakeholder = "बैंक शाखा प्रबंधक एवं लीड डिस्ट्रिक्ट मैनेजर (LDM)",
                metric = "व्यक्तिगत संदेह को सटीक क्रेडिट स्कोर में बदलता है"
            ),
            LocalizedFieldQuestion(
                id = 6,
                title = "६. पात्र आवेदक प्रक्रिया के बीच में ही क्यों छोड़ देते हैं?",
                secondaryTitle = "6. Why do eligible applicants drop out midway?",
                groundProblem = "बार-बार बैंक के 10-15 चक्कर लगाना, अंग्रेजी के कठिन फॉर्म, और दुकान बंद रहने से दिन की मजदूरी का नुकसान। अंत में वे थककर साहूकार के पास चले जाते हैं।",
                agenticAiSolution = "मातृभाषा में सरल ऑडियो सहायता, एक बार में तैयार फाइल, और सीधे बैंक मित्र या शाखा में जमा करने की सुविधा।",
                stakeholder = "वित्तीय समावेशन एवं ग्रामीण उद्यमिता मिशन",
                metric = "ड्रॉपआउट दर को 65% से घटाकर 8% से कम करता है"
            )
        )

        SupportedLanguage.KN -> listOf(
            LocalizedFieldQuestion(
                id = 1,
                title = "೧. ಈ ಉದ್ಯಮವು ಯಾವ ಯೋಜನೆಗಳ ಬಗ್ಗೆ ಕೇಳಿದೆ, ಮತ್ತು ಯಾರಿಂದ?",
                secondaryTitle = "1. Which schemes has this business heard of, and from whom?",
                groundProblem = "ಗ್ರಾಮದ ಟೈಲರ್‌ಗಳು ಮತ್ತು ಕುಶಲಕರ್ಮಿಗಳು ಸಾಲ ಪಡೆಯಲು ಪ್ರಭಾವ ಬೇಕು ಅಥವಾ ಲಂಚ ಕೊಡಬೇಕು ಎಂಬ ವದಂತಿಗಳನ್ನು ಚಹಾ ಅಂಗಡಿ ಅಥವಾ ದಲ್ಲಾಳಿಗಳಿಂದ ಮಾತ್ರ ಕೇಳಿರುತ್ತಾರೆ. 70–80% ಸರ್ಕಾರಿ ಯೋಜನೆಗಳ ಅರಿವು ಇರುವುದಿಲ್ಲ.",
                agenticAiSolution = "ಸಹಾಯ್ ಕ್ರೆಡಿಟ್ AI ಮಾತೃಭಾಷೆಯಲ್ಲಿ ಧ್ವನಿ ಮಾರ್ಗದರ್ಶನದೊಂದಿಗೆ ಮುದ್ರಾ, PMEGP 35% ಸಬ್ಸಿಡಿ ಮತ್ತು ವಿಶ್ವಕರ್ಮ 5% ಬಡ್ಡಿ ಯೋಜನೆಗಳಿಗೆ ನೇರವಾಗಿ ಜೋಡಿಸುತ್ತದೆ.",
                stakeholder = "ವ್ಯಾಪಾರ ಪ್ರತಿನಿಧಿಗಳು (BC) ಮತ್ತು ಮಹಿಳಾ SHG ಒಕ್ಕೂಟಗಳು",
                metric = "70–80% ಯೋಜನಾ ಅರಿವಿನ ಕೊರತೆಯನ್ನು ನೀಗಿಸುತ್ತದೆ"
            ),
            LocalizedFieldQuestion(
                id = 2,
                title = "೨. ಹಿಂದಿನ ಸಾಲದ ಪ್ರಯತ್ನದಲ್ಲಿ ಏನು ಕೇಳಲಾಗಿತ್ತು, ಮತ್ತು ಫೈಲ್ ಎಲ್ಲಿ ನಿಂತಿತು?",
                secondaryTitle = "2. What did the last loan attempt require, and where did it stall?",
                groundProblem = "ಬ್ಯಾಂಕ್ ವ್ಯವಸ್ಥಾಪಕರು 3 ವರ್ಷಗಳ ITR, CA ಆಡಿಟ್ ವರದಿ ಮತ್ತು ಆಸ್ತಿ ಅಡಮಾನ ಕೇಳಿದರು. ಸಣ್ಣ ಗ್ರಾಮೀಣ ಉದ್ಯಮಿಗಳ ಬಳಿ ಇವು ಇರದ ಕಾರಣ 3–5 ತಿಂಗಳ ನಂತರ ಅರ್ಜಿ ತಿರಸ್ಕೃತಗೊಂಡಿತು.",
                agenticAiSolution = "ದೈನಂದಿನ PhonePe/GPay QR ವಹಿವಾಟು, ಹಾಲಿನ ಚೀಟಿ ಮತ್ತು ಖಾತಾ ಪುಸ್ತಕದಿಂದ RBI FIDD ಮಾನ್ಯತೆಯ ನಗದು ಹರಿವು ಹಾಗೂ DSCR ಲೆಕ್ಕಾಚಾರವನ್ನು ನಿರ್ಮಿಸುತ್ತದೆ.",
                stakeholder = "ಬ್ಯಾಂಕ್ ಶಾಖೆಗಳು ಮತ್ತು NBFC ಸಂಸ್ಥೆಗಳು",
                metric = "15–20 ಕಾಗದದ ದಾಖಲೆಗಳ ಅಡಚಣೆಯನ್ನು ನಿವಾರಿಸುತ್ತದೆ"
            ),
            LocalizedFieldQuestion(
                id = 3,
                title = "೩. ಏಜೆಂಟ್ / ದಲ್ಲಾಳಿ ಎಷ್ಟು ಶುಲ್ಕ ಪಡೆಯುತ್ತಾನೆ, ಮತ್ತು ನಿಜವಾಗಿ ಏನು ಮಾಡುತ್ತಾನೆ?",
                secondaryTitle = "3. What does the agent charge, and what does he actually do?",
                groundProblem = "ದಲ್ಲಾಳಿಗಳು 10–15% ನಗದು ಕಮಿಷನ್ (₹30,000 ದಿಂದ ₹1,50,000) ಕೇಳುತ್ತಾರೆ. ವಾಸ್ತವದಲ್ಲಿ ಅವರು ಕೇವಲ ಫಾರ್ಮ್ ಜೆರಾಕ್ಸ್ ಮಾಡಿಸಿ ಫೈಲ್ ವಿಳಂಬ ಮಾಡುತ್ತಾರೆ.",
                agenticAiSolution = "100% ಉಚಿತ ಮತ್ತು ನೇರ ಅರ್ಜಿ. ಮಧ್ಯವರ್ತಿಗೆ ₹1 ನೀಡದೆ ಬ್ಯಾಂಕ್ ವ್ಯವಸ್ಥಾಪಕರು ನೇರವಾಗಿ ಪರಿಶೀಲಿಸಬಹುದಾದ ಸಾಲ ವರದಿಯನ್ನು ಸಿದ್ಧಪಡಿಸುತ್ತದೆ.",
                stakeholder = "ಜಿಲ್ಲಾ ಕೈಗಾರಿಕಾ ಕೇಂದ್ರ (DIC) ಮತ್ತು ಜಾಗೃತ ದಳ",
                metric = "ಪ್ರತಿ ಸಾಲಗಾರರಿಗೆ ₹30,000–₹1,50,000 ಉಳಿತಾಯ"
            ),
            LocalizedFieldQuestion(
                id = 4,
                title = "೪. ಯಾವ ಅನೌಪಚಾರಿಕ ದಾಖಲೆಗಳು ಲಭ್ಯವಿವೆ, ಮತ್ತು ಮಾಲೀಕರು ಹಂಚಿಕೊಳ್ಳುತ್ತಾರೆಯೇ?",
                secondaryTitle = "4. What informal records exist, and would the owner share them?",
                groundProblem = "ಅಂಗಡಿಯವರ ಬಳಿ UPI ಪಾವತಿ, ಕೈಬರಹದ ಖಾತಾ ಡೈರಿ, ಹಾಲಿನ ಪಾಸ್‌ಬುಕ್ ಮತ್ತು ಬಿಲ್‌ಗಳಿರುತ್ತವೆ; ಆದರೆ ತೆರಿಗೆ ತೊಂದರೆಯ ಭಯದಿಂದ ಹಂಚಿಕೊಳ್ಳಲು ಹಿಂಜರಿಯುತ್ತಾರೆ.",
                agenticAiSolution = "ಸಂಪೂರ್ಣ ಡಿಜಿಟಲ್ ಸಮ್ಮತಿಯೊಂದಿಗೆ ಕೇವಲ ಸಾಲ ಮೌಲ್ಯಮಾಪನಕ್ಕೆ ಮಾತ್ರ ಬಳಸಲಾಗುತ್ತದೆ. ಸ್ಥಳೀಯ ಭಾಷೆಯ ಆಡಿಯೋ ವಿವರಣೆ ಡೇಟಾ ಸುರಕ್ಷತೆಯನ್ನು ಖಚಿತಪಡಿಸುತ್ತದೆ.",
                stakeholder = "ಸಾಲಗಾರರು ಮತ್ತು UPI ಡಿಜಿಟಲ್ ವ್ಯವಸ್ಥೆ",
                metric = "100% ಸಮ್ಮತಿಯೊಂದಿಗೆ ಅನೌಪಚಾರಿಕ ದಾಖಲೆಗಳ ಬಳಕೆ"
            ),
            LocalizedFieldQuestion(
                id = 5,
                title = "೫. ಸಾಲ ಮಂಜೂರು ಮಾಡುವ ಮೊದಲು ಬ್ಯಾಂಕ್ ಶಾಖಾ ವ್ಯವಸ್ಥಾಪಕರು ಏನು ನೋಡಬೇಕು?",
                secondaryTitle = "5. What does a branch manager need to see before saying yes?",
                groundProblem = "ಭದ್ರತಾ ರಹಿತ ಸಾಲ ಬಾಕಿಯಾದರೆ ಆಡಿಟ್ ಭಯ ಬ್ಯಾಂಕ್ ವ್ಯವಸ್ಥಾಪಕರಿಗೆ ಇರುತ್ತದೆ. ಅವರಿಗೆ DSCR (>1.30) ಮತ್ತು CGFMU / CGTMSE ಸರ್ಕಾರಿ ಖಾತರಿ ಪುರಾವೆ ಬೇಕಾಗುತ್ತದೆ.",
                agenticAiSolution = "DSCR ಗಣನೆ, 85% CGTMSE ಖಾತರಿ ಉಲ್ಲೇಖ ಮತ್ತು 4-ಅಂಶಗಳ ತಪಾಸಣಾ ಪಟ್ಟಿಯೊಂದಿಗೆ ಪ್ರಮಾಣಿತ ಸಾಲ ಮೌಲ್ಯಮಾಪನ ಪತ್ರವನ್ನು ನೀಡುತ್ತದೆ.",
                stakeholder = "ಬ್ಯಾಂಕ್ ಶಾಖಾ ವ್ಯವಸ್ಥಾಪಕರು ಮತ್ತು LDM",
                metric = "ಸಂದೇಹವನ್ನು ನಿಖರ ಕ್ರೆಡಿಟ್ ಸ್ಕೋರ್ ಆಗಿ ಪರಿವರ್ತಿಸುತ್ತದೆ"
            ),
            LocalizedFieldQuestion(
                id = 6,
                title = "೬. ಅರ್ಹ ಅರ್ಜಿದಾರರು ಪ್ರಕ್ರಿಯೆಯ ಮಧ್ಯದಲ್ಲೇ ಏಕೆ ಕೈಬಿಡುತ್ತಾರೆ?",
                secondaryTitle = "6. Why do eligible applicants drop out midway?",
                groundProblem = "10–15 ಬಾರಿ ಬ್ಯಾಂಕ್ ಅಲೆದಾಟ, ಕಠಿಣ ಇಂಗ್ಲಿಷ್ ಫಾರ್ಮ್‌ಗಳು ಮತ್ತು ಅಂಗಡಿ ಮುಚ್ಚಿ ಕೂಲಿ ನಷ್ಟವಾಗುವುದರಿಂದ ಅರ್ಜಿದಾರರು ಬೇಸತ್ತು ಲೇವಾದೇವಿಗಾರರ ಬಳಿ ಹೋಗುತ್ತಾರೆ.",
                agenticAiSolution = "ಒಂದೇ ಹಂತದ ಡಿಜಿಟಲ್ ಮೌಲ್ಯಮಾಪನ, ಮಾತೃಭಾಷೆಯಲ್ಲಿ ಧ್ವನಿ ಮಾರ್ಗದರ್ಶನ ಮತ್ತು ಮೊಬೈಲ್‌ನಲ್ಲೇ ತಕ್ಷಣದ ಬ್ಯಾಂಕ್ ವರದಿ.",
                stakeholder = "ಆರ್ಥಿಕ ಒಳಗೊಳ್ಳುವಿಕೆ ಮತ್ತು ಗ್ರಾಮೀಣ ಉದ್ಯಮ",
                metric = "ಅರ್ಜಿ ಕೈಬಿಡುವ ದರವನ್ನು 65% ರಿಂದ 8% ಕ್ಕಿಂತ ಕಡಿಮೆ ಮಾಡುತ್ತದೆ"
            )
        )

        SupportedLanguage.TA -> listOf(
            LocalizedFieldQuestion(
                id = 1,
                title = "1. இந்த வணிகம் எந்தத் திட்டங்களைப் பற்றி கேள்விப்பட்டுள்ளது, யாரிடமிருந்து?",
                secondaryTitle = "1. Which schemes has this business heard of, and from whom?",
                groundProblem = "கிராமப்புற தையல்காரர்களும் கைவினைஞர்களும் கடன் பெற சிபாரிசு அல்லது லஞ்சம் தேவை என்று தரகர்கள் மூலம் தவறான தகவல்களையே கேட்கிறார்கள்.",
                agenticAiSolution = "சஹாய் கிரெடிட் AI தாய்மொழியில் குரல் வழிகாட்டுதலுடன் முத்ரா, PMEGP 35% மானியம் மற்றும் விஸ்வகர்மா 5% வட்டித் திட்டங்களை உடனே இணைக்கிறது.",
                stakeholder = "வங்கி முகவர்கள் & மகளிர் சுயஉதவி குழு கூட்டமைப்பு",
                metric = "70–80% திட்ட விழிப்புணர்வு இடைவெளியை நிரப்புகிறது"
            ),
            LocalizedFieldQuestion(
                id = 2,
                title = "2. முந்தைய கடன் முயற்சியில் என்ன கேட்கப்பட்டது, கோப்பு எங்கே நின்றது?",
                secondaryTitle = "2. What did the last loan attempt require, and where did it stall?",
                groundProblem = "வங்கி 3 ஆண்டு ITR மற்றும் தணிக்கை அறிக்கை கேட்டது. சிறிய கிராம தொழில்முனைவோரிடம் இவை இல்லாததால் 4 மாதங்களுக்குப் பிறகு விண்ணப்பம் நிராகரிக்கப்பட்டது.",
                agenticAiSolution = "தினசரி UPI QR பதிவுகள், பால் சீட்டு மற்றும் கணக்கு ஏடு மூலம் RBI FIDD விதிகளின்படி DSCR பணப்புழக்க ஆதாரத்தை உருவாக்குகிறது.",
                stakeholder = "வங்கி கிளைகள் & நிதி நிறுவனங்கள்",
                metric = "15–20 காகித ஆவணத் தடையை நீக்குகிறது"
            ),
            LocalizedFieldQuestion(
                id = 3,
                title = "3. இடைத்தரகர் என்ன கட்டணம் வசூலிக்கிறார், உண்மையில் என்ன செய்கிறார்?",
                secondaryTitle = "3. What does the agent charge, and what does he actually do?",
                groundProblem = "தரகர்கள் 10–15% கமிஷன் (₹30,000–₹1,50,000) கேட்கிறார்கள். உண்மையில் படிவங்களை நகல் எடுப்பதைத் தவிர வேறொன்றும் செய்வதில்லை.",
                agenticAiSolution = "100% இலவச மற்றும் நேரடி வங்கி அறிக்கை. தரகருக்கு ₹1 கூட கொடுக்காமல் மேலாளருக்கான சான்றளிக்கப்பட்ட கடன் கோப்பை உருவாக்குகிறது.",
                stakeholder = "மாவட்ட தொழில் மையம் (DIC) & கண்காணிப்பு",
                metric = "ஒவ்வொரு தொழில்முனைவோருக்கும் ₹30,000–₹1,50,000 சேமிப்பு"
            ),
            LocalizedFieldQuestion(
                id = 4,
                title = "4. என்ன முறைசாரா பதிவுகள் உள்ளன, உரிமையாளர் அவற்றைப் பகிர்வாரா?",
                secondaryTitle = "4. What informal records exist, and would the owner share them?",
                groundProblem = "வணிகர்களிடம் UPI பதிவுகள், கணக்கு ஏடு மற்றும் பால் பாஸ்புக் இருந்தாலும் வரி பயத்தால் பகிரத் தயங்குகிறார்கள்.",
                agenticAiSolution = "தொழில்முனைவோரின் தெளிவான டிஜிட்டல் ஒப்புதலுடன் வங்கி கடன் மதிப்பீட்டிற்கு மட்டுமே தரவு பயன்படுத்தப்படுகிறது.",
                stakeholder = "விண்ணப்பதாரர் & UPI டிஜிட்டல் கட்டமைப்பு",
                metric = "100% ஒப்புதலுடன் முறைசாரா பதிவுகளை பயன்படுத்துகிறது"
            ),
            LocalizedFieldQuestion(
                id = 5,
                title = "5. ஒப்புதல் அளிப்பதற்கு முன் வங்கி கிளை மேலாளர் என்ன பார்க்க வேண்டும்?",
                secondaryTitle = "5. What does a branch manager need to see before saying yes?",
                groundProblem = "அடமானமில்லாத கடன் வாராக்கடனானால் தணிக்கை சிக்கல் வருமோ என்ற அச்சம் மேலாளர்களுக்கு உள்ளது. அவர்களுக்கு DSCR (>1.30) மற்றும் CGTMSE உத்தரவாதம் தேவை.",
                agenticAiSolution = "DSCR கணக்கீடு, 85% CGTMSE உத்தரவாத மேற்கோள் மற்றும் 4-அம்ச சரிபார்ப்பு பட்டியலுடன் முழு கடன் அறிக்கையை வழங்குகிறது.",
                stakeholder = "வங்கி கிளை மேலாளர்கள் & LDM",
                metric = "சந்தேகத்தை துல்லியமான கடன் மதிப்பெண்ணாக மாற்றுகிறது"
            ),
            LocalizedFieldQuestion(
                id = 6,
                title = "6. தகுதியான விண்ணப்பதாரர்கள் பாதியிலேயே ஏன் விலகுகிறார்கள்?",
                secondaryTitle = "6. Why do eligible applicants drop out midway?",
                groundProblem = "10–15 முறை வங்கிக்கு அலைவது, கடினமான ஆங்கிலப் படிவங்கள் மற்றும் கடை மூடுவதால் ஏற்படும் வருமான இழப்பு ஆகியவற்றால் கந்துவட்டிக்காரர்களிடம் செல்கின்றனர்.",
                agenticAiSolution = "ஒரே அமர்வில் டிஜிட்டல் மதிப்பீடு, உள்ளூர் மொழி குரல் உதவி மற்றும் உடனடி வங்கி அறிக்கை.",
                stakeholder = "நிதி உள்ளடக்கம் & கிராமப்புற தொழில்",
                metric = "பாதியில் கைவிடும் விகிதத்தை 65%லிருந்து 8% கீழ் குறைக்கிறது"
            )
        )

        SupportedLanguage.TE -> listOf(
            LocalizedFieldQuestion(
                id = 1,
                title = "1. ఈ వ్యాపారం ఏ పథకాల గురించి విన్నది, మరియు ఎవరి నుండి?",
                secondaryTitle = "1. Which schemes has this business heard of, and from whom?",
                groundProblem = "గ్రామీణ టైలర్లు, చేతివృత్తులవారు లోన్ కావాలంటే పలుకుబడి లేదా లంచం ఇవ్వాలని దళారుల ద్వారా మాత్రమే వింటారు. 70–80% ప్రభుత్వ పథకాల గురించి తెలియదు.",
                agenticAiSolution = "సహాయ్ క్రెడిట్ AI మాతృభాషలో వాయిస్ గైడెన్స్ ద్వారా ముద్రా, PMEGP 35% సబ్సిడీ, విశ్వకర్మ 5% వడ్డీ పథకాలతో నేరుగా అనుసంధానిస్తుంది.",
                stakeholder = "బ్యాంక్ మిత్రాలు & మహిళా SHG సమాఖ్యలు",
                metric = "70–80% పథకాల అవగాహన లోపాన్ని భర్తీ చేస్తుంది"
            ),
            LocalizedFieldQuestion(
                id = 2,
                title = "2. గత లోన్ ప్రయత్నంలో ఏమి అడిగారు, ఫైల్ ఎక్కడ ఆగిపోయింది?",
                secondaryTitle = "2. What did the last loan attempt require, and where did it stall?",
                groundProblem = "బ్యాంక్ 3 ఏళ్ల ITR మరియు CA ఆడిట్ రిపోర్ట్ అడిగింది. గ్రామీణ చిరువ్యాపారుల వద్ద ఇవి లేక 3–5 నెలల తర్వాత ఫైల్ తిరస్కరించబడింది.",
                agenticAiSolution = "రోజువారీ UPI QR చెల్లింపులు, పాల స్లిప్పులు మరియు ఖాతా పుస్తకం నుండి RBI FIDD ఆమోదిత DSCR నివేదికను తయారు చేస్తుంది.",
                stakeholder = "బ్యాంక్ బ్రాంచీలు & NBFC సంస్థలు",
                metric = "15–20 కాగితపు పత్రాల అడ్డంకిని తొలగిస్తుంది"
            ),
            LocalizedFieldQuestion(
                id = 3,
                title = "3. ఏజెంట్ ఎంత కమీషన్ తీసుకుంటాడు, మరియు నిజంగా ఏమి చేస్తాడు?",
                secondaryTitle = "3. What does the agent charge, and what does he actually do?",
                groundProblem = "దళారులు 10–15% కమీషన్ (₹30,000–₹1,50,000) డిమాండ్ చేస్తారు. వాస్తవానికి వారు కేవలం ఫారాలు జిరాక్స్ తీసి ఫైల్‌ను ఆలస్యం చేస్తారు.",
                agenticAiSolution = "100% ఉచిత మరియు నేరుగా బ్యాంక్ ఫైల్ తయారీ. మధ్యవర్తికి ₹1 ఇవ్వకుండా మేనేజర్ పరిశీలించే క్రెడిట్ నోట్ ఇస్తుంది.",
                stakeholder = "జిల్లా పరిశ్రమల కేంద్రం (DIC) & విజిలెన్స్",
                metric = "ప్రతి వ్యాపారికి ₹30,000–₹1,50,000 ఆదా చేస్తుంది"
            ),
            LocalizedFieldQuestion(
                id = 4,
                title = "4. ఏ అనౌపచారిక రికార్డులు ఉన్నాయి, యజమాని వాటిని పంచుకుంటారా?",
                secondaryTitle = "4. What informal records exist, and would the owner share them?",
                groundProblem = "వ్యాపారుల వద్ద UPI లావాదేవీలు, ఖాతా పుస్తకం, పాల పాస్‌బుక్ ఉన్నా పన్ను సమస్యల భయంతో చూపించడానికి వెనుకాడతారు.",
                agenticAiSolution = "వ్యాపారి డిజిటల్ సమ్మతితో కేవలం బ్యాంక్ లోన్ మదింపు కోసం మాత్రమే ఉపయోగిస్తారు. స్థానిక భాషలో ఆడియో భరోసా ఇస్తుంది.",
                stakeholder = "రుణగ్రహీత & UPI డిజిటల్ వ్యవస్థ",
                metric = "100% సమ్మతితో అనౌపచారిక రికార్డులను వినియోగిస్తుంది"
            ),
            LocalizedFieldQuestion(
                id = 5,
                title = "5. ఆమోదించే ముందు బ్రాంచ్ మేనేజర్ ఏమి చూడాలి?",
                secondaryTitle = "5. What does a branch manager need to see before saying yes?",
                groundProblem = "హామీ లేని రుణం ఎన్‌పిఏ అయితే ఆడిట్ భయం మేనేజర్లకు ఉంటుంది. వారికి DSCR (>1.30) మరియు CGFMU / CGTMSE ప్రభుత్వ గ్యారంటీ రుజువు కావాలి.",
                agenticAiSolution = "DSCR గణన, 85% CGTMSE గ్యారంటీ ఉత్తర్వు మరియు 4-అంశాల తనిఖీ జాబితాతో క్రెడిట్ అప్రైజల్ నోట్ అందిస్తుంది.",
                stakeholder = "బ్యాంక్ బ్రాంచ్ మేనేజర్లు & LDM",
                metric = "సందేహాన్ని ఖచ్చితమైన క్రెడిట్ స్కోర్‌గా మారుస్తుంది"
            ),
            LocalizedFieldQuestion(
                id = 6,
                title = "6. అర్హులైన దరఖాస్తుదారులు మధ్యలోనే ఎందుకు వదిలేస్తున్నారు?",
                secondaryTitle = "6. Why do eligible applicants drop out midway?",
                groundProblem = "10–15 సార్లు బ్యాంకు చుట్టూ తిరగడం, కష్టమైన ఇంగ్లీష్ ఫారాలు, దుకాణం మూసివేయడం వల్ల నష్టం జరిగి చివరకు వడ్డీ వ్యాపారుల వద్దకు వెళ్తారు.",
                agenticAiSolution = "ఒకే సిట్టింగ్‌లో డిజిటల్ మదింపు, మాతృభాషలో వాయిస్ సహాయం మరియు తక్షణ బ్యాంక్ నివేదిక.",
                stakeholder = "ఆర్థిక సమ్మిళితం & గ్రామీణ వ్యాపారం",
                metric = "మధ్యలో ఆపే రేటును 65% నుండి 8% లోపుకు తగ్గిస్తుంది"
            )
        )

        SupportedLanguage.MR -> listOf(
            LocalizedFieldQuestion(
                id = 1,
                title = "१. या व्यवसायाने कोणत्या योजनांबद्दल ऐकले आहे, आणि कोणाकडून?",
                secondaryTitle = "1. Which schemes has this business heard of, and from whom?",
                groundProblem = "गावातील शिंपी व कारागीर फक्त चहाच्या टपरीवर किंवा दलालांकडून ऐकतात की कर्जासाठी ओळख किंवा लाच लागते. ते 70–80% सरकारी योजनांपासून अनभिज्ञ असतात.",
                agenticAiSolution = "सहाय क्रेडिट AI मातृभाषेतून बोलून मुद्रा, PMEGP 35% सबसिडी आणि विश्वकर्मा 5% व्याज यांसारख्या 7 योजनांशी थेट जोडतो.",
                stakeholder = "बँक मित्र आणि महिला बचत गट महासंघ",
                metric = "70–80% योजना जागरूकतेची दरी भरून काढते"
            ),
            LocalizedFieldQuestion(
                id = 2,
                title = "२. मागील कर्ज प्रयत्नात काय मागितले गेले, आणि फाइल कुठे अडकली?",
                secondaryTitle = "2. What did the last loan attempt require, and where did it stall?",
                groundProblem = "बँकेने 3 वर्षांचा ITR आणि CA ऑडिट रिपोर्ट मागितला. लहान उद्योजकांकडे हे नसल्याने 4 महिन्यांनंतर फाइल नामंजूर झाली.",
                agenticAiSolution = "दैनिक UPI QR नोंदी, दुधाची पावती आणि वही खात्यावरून RBI FIDD मान्य DSCR रोख प्रवाह तयार करतो.",
                stakeholder = "बँक शाखा आणि NBFC संस्था",
                metric = "15–20 कागदपत्रांचा अडथळा दूर करते"
            ),
            LocalizedFieldQuestion(
                id = 3,
                title = "३. दलाल किती शुल्क घेतो, आणि तो प्रत्यक्षात काय करतो?",
                secondaryTitle = "3. What does the agent charge, and what does he actually do?",
                groundProblem = "दलाल 10–15% रोख कमिशन (₹30,000–₹1,50,000) मागतात. प्रत्यक्षात ते फक्त फॉर्म झेरॉक्स करतात आणि फाइल रखडवतात.",
                agenticAiSolution = "100% मोफत आणि थेट बँक फाइल निर्मिती. दलालाला ₹1 ही न देता शाखा व्यवस्थापकांसाठी प्रमाणित अहवाल बनतो.",
                stakeholder = "जिल्हा उद्योग केंद्र (DIC) व दक्षता विभाग",
                metric = "प्रत्येक उद्योजकाचे ₹30,000–₹1,50,000 वाचवते"
            ),
            LocalizedFieldQuestion(
                id = 4,
                title = "४. कोणते अनौपचारिक रेकॉर्ड उपलब्ध आहेत, आणि मालक ते शेअर करतील का?",
                secondaryTitle = "4. What informal records exist, and would the owner share them?",
                groundProblem = "दुकानदाराकडे UPI नोंदी, हाताने लिहिलेली वही व दुधाचे पासबुक असते, पण कर विभागाच्या भीतीने ते दाखवायला घाबरतात.",
                agenticAiSolution = "उद्योजकाच्या डिजिटल संमतीने फक्त बँक कर्जाच्या मूल्यांकनासाठीच माहिती वापरली जाते.",
                stakeholder = "अर्जदार आणि UPI डिजिटल प्रणाली",
                metric = "100% संमतीने अनौपचारिक नोंदींचा वापर"
            ),
            LocalizedFieldQuestion(
                id = 5,
                title = "५. हो म्हणण्यापूर्वी बँक शाखा व्यवस्थापकाला काय पाहणे आवश्यक असते?",
                secondaryTitle = "5. What does a branch manager need to see before saying yes?",
                groundProblem = "तारणमुक्त कर्ज बुडाले तर ऑडिटची जबाबदारी येईल अशी भीती व्यवस्थापकांना असते. त्यांना DSCR (>1.30) व CGFMU/CGTMSE हमी हवी असते.",
                agenticAiSolution = "DSCR गणना, 85% CGTMSE हमी संदर्भ आणि 4-मुद्द्यांची तपासणी यादी असलेला कर्ज मूल्यांकन अहवाल देतो.",
                stakeholder = "बँक शाखा व्यवस्थापक आणि LDM",
                metric = "शंकेचे रूपांतर अचूक क्रेडिट स्कोअरमध्ये करते"
            ),
            LocalizedFieldQuestion(
                id = 6,
                title = "६. पात्र अर्जदार प्रक्रियेच्या मधूनच का सोडून देतात?",
                secondaryTitle = "6. Why do eligible applicants drop out midway?",
                groundProblem = "बँकेच्या 10–15 फेऱ्या, कठीण इंग्रजी फॉर्म आणि दुकान बंद राहिल्याने होणारे रोजंदारीचे नुकसान यामुळे ते सावकाराकडे जातात.",
                agenticAiSolution = "एकाच बैठकीत डिजिटल मूल्यांकन, मातृभाषेत ऑडिओ मदत आणि मोबाईलवर त्वरित बँक अहवाल.",
                stakeholder = "आर्थिक समावेशन आणि ग्रामीण उद्योजकता",
                metric = "अर्ज सोडण्याचे प्रमाण 65% वरून 8% खाली आणते"
            )
        )

        SupportedLanguage.BN -> listOf(
            LocalizedFieldQuestion(
                id = 1,
                title = "১. এই ব্যবসা কোন প্রকল্পগুলোর কথা শুনেছে, এবং কার কাছ থেকে?",
                secondaryTitle = "1. Which schemes has this business heard of, and from whom?",
                groundProblem = "গ্রামের দর্জি ও কারিগররা শুধু চায়ের দোকান বা দালালের কাছে শুনেছে যে ঋণের জন্য ঘুষ বা তদবির লাগে। ৭০–৮০% সরকারি প্রকল্পের খবর তারা জানেন না।",
                agenticAiSolution = "সহায় ক্রেডিট AI মাতৃভাষায় ভয়েস সহায়তায় মুদ্রা, PMEGP ৩৫% ভর্তুকি এবং বিশ্বকর্মা ৫% সুদের প্রকল্পে সরাসরি যুক্ত করে।",
                stakeholder = "ব্যাঙ্ক মিত্র ও মহিলা স্বনির্ভর দল ফেডারেশন",
                metric = "৭০–৮০% প্রকল্প সচেতনতার ঘাটতি পূরণ করে"
            ),
            LocalizedFieldQuestion(
                id = 2,
                title = "২. আগের ঋণের চেষ্টায় কী চাওয়া হয়েছিল, এবং ফাইল কোথায় আটকে যায়?",
                secondaryTitle = "2. What did the last loan attempt require, and where did it stall?",
                groundProblem = "ব্যাঙ্ক ৩ বছরের আইটিআর ও সিএ অডিট রিপোর্ট চেয়েছিল। গ্রামীণ ছোট উদ্যোক্তাদের কাছে তা না থাকায় ৪ মাস ঘোরার পর ফাইল বাতিল হয়।",
                agenticAiSolution = "দৈনিক UPI QR লেনদেন, দুধের স্লিপ এবং খাতা বহি থেকে RBI FIDD অনুমোদিত নগদ প্রবাহ ও DSCR তৈরি করে।",
                stakeholder = "ব্যাঙ্ক শাখা ও NBFC প্রতিষ্ঠান",
                metric = "১৫–২০টি কাগজপত্রের জটিল বাধা দূর করে"
            ),
            LocalizedFieldQuestion(
                id = 3,
                title = "৩. দালাল কত ফি নেয়, এবং সে আসলে কী করে?",
                secondaryTitle = "3. What does the agent charge, and what does he actually do?",
                groundProblem = "দালালরা ১০–১৫% নগদ কমিশন (₹৩০,০০০–₹১,৫০,০০০) দাবি করে। বাস্তবে তারা শুধু ফর্ম ফটোকপি করে এবং ফাইল ঝুলিয়ে রাখে।",
                agenticAiSolution = "১০০% বিনামূল্যে সরাসরি ব্যাঙ্ক ফাইল তৈরি। দালালকে ₹১ না দিয়েই ম্যানেজারের জন্য যাচাইকৃত রিপোর্ট তৈরি হয়।",
                stakeholder = "জেলা শিল্প কেন্দ্র (DIC) ও ভিজিল্যান্স",
                metric = "প্রতি উদ্যোক্তার ₹৩০,০০০–₹১,৫০,০০০ সাশ্রয় করে"
            ),
            LocalizedFieldQuestion(
                id = 4,
                title = "৪. কী ধরনের অনানুষ্ঠানিক নথি আছে, এবং মালিক কি সেগুলো শেয়ার করবেন?",
                secondaryTitle = "4. What informal records exist, and would the owner share them?",
                groundProblem = "দোকানদারের কাছে UPI লেনদেন, হাতের খাতা ও দুধের পাসবুক থাকলেও করের ঝামেলার ভয়ে তা দেখাতে চান না।",
                agenticAiSolution = "উদ্যোক্তার স্পষ্ট ডিজিটাল সম্মতি নিয়ে শুধুমাত্র ব্যাঙ্ক ঋণ মূল্যায়নের জন্যই তথ্য ব্যবহার করা হয়।",
                stakeholder = "ঋণগ্রহীতা ও UPI ডিজিটাল পরিকাঠামো",
                metric = "১০০% সম্মতির সাথে অনানুষ্ঠানিক নথি ব্যবহার"
            ),
            LocalizedFieldQuestion(
                id = 5,
                title = "৫. হ্যাঁ বলার আগে ব্যাঙ্ক শাখা ব্যবস্থাপককে কী দেখতে হয়?",
                secondaryTitle = "5. What does a branch manager need to see before saying yes?",
                groundProblem = "জামানতহীন ঋণ খেলাপি হলে অডিটের ভয়ে ম্যানেজাররা দ্বিধা করেন। তাদের DSCR (>১.৩০) ও CGFMU/CGTMSE গ্যারান্টি প্রমাণ দরকার।",
                agenticAiSolution = "DSCR হিসাব, ৮৫% CGTMSE গ্যারান্টি সূত্র এবং ৪-দফা চেকলিস্টসহ একটি মানসম্মত ক্রেডিট অ্যাপ্রাইজাল নোট প্রদান করে।",
                stakeholder = "ব্যাঙ্ক শাখা ব্যবস্থাপক ও LDM",
                metric = "ব্যক্তিগত সন্দেহকে সঠিক ক্রেডিট স্কোরে রূপান্তর করে"
            ),
            LocalizedFieldQuestion(
                id = 6,
                title = "৬. যোগ্য আবেদনকারীরা মাঝপথে কেন প্রক্রিয়া ছেড়ে দেন?",
                secondaryTitle = "6. Why do eligible applicants drop out midway?",
                groundProblem = "১০–১৫ বার ব্যাঙ্কে যাতায়াত, কঠিন ইংরেজি ফর্ম এবং দোকান বন্ধ থাকায় দিনের আয় নষ্ট হওয়ায় তারা মহাজনের কাছে চলে যান।",
                agenticAiSolution = "এক বসায় ডিজিটাল মূল্যায়ন, মাতৃভাষায় অডিও গাইড এবং তাৎক্ষণিক ব্যাঙ্ক ডসিয়ার।",
                stakeholder = "আর্থিক অন্তর্ভুক্তি ও গ্রামীণ উদ্যোগ",
                metric = "ঝরে পড়ার হার ৬৫% থেকে ৮%-এর নিচে নামিয়ে আনে"
            )
        )

        SupportedLanguage.GU -> listOf(
            LocalizedFieldQuestion(
                id = 1,
                title = "૧. આ વ્યવસાયે કઈ યોજનાઓ વિશે સાંભળ્યું છે, અને કોની પાસેથી?",
                secondaryTitle = "1. Which schemes has this business heard of, and from whom?",
                groundProblem = "ગામના દરજી અને કારીગરો માત્ર ચાની કીટલી કે દલાલો પાસેથી સાંભળે છે કે લોન માટે લાગવગ કે લાંચ જોઈએ. તેઓ 70–80% સરકારી યોજનાઓથી અજાણ હોય છે.",
                agenticAiSolution = "સહાય ક્રેડિટ AI માતૃભાષામાં અવાજ માર્ગદર્શન સાથે મુદ્રા, PMEGP 35% સબસિડી અને વિશ્વકર્મા 5% વ્યાજ યોજનાઓ સાથે સીધું જોડે છે.",
                stakeholder = "બેંક મિત્ર અને મહિલા SHG ફેડરેશન",
                metric = "70–80% યોજના જાગૃતિની ખામી પૂરી કરે છે"
            ),
            LocalizedFieldQuestion(
                id = 2,
                title = "૨. છેલ્લા લોન પ્રયાસમાં શું માંગવામાં આવ્યું હતું, અને ફાઇલ ક્યાં અટકી?",
                secondaryTitle = "2. What did the last loan attempt require, and where did it stall?",
                groundProblem = "બેંકે 3 વર્ષના ITR અને CA ઓડિટ રિપોર્ટ માંગ્યા. નાના ગ્રામીણ ઉદ્યોગસાહસિક પાસે આ ન હોવાથી 4 મહિના પછી ફાઇલ નકારી કાઢવામાં આવી.",
                agenticAiSolution = "દૈનિક UPI QR, દૂધની સ્લિપ અને ખાતાવહી પરથી RBI FIDD માન્ય કેશફ્લો અને DSCR ગણતરી તૈયાર કરે છે.",
                stakeholder = "બેંક શાખાઓ અને NBFC સંસ્થાઓ",
                metric = "15–20 કાગળના દસ્તાવેજોનો અવરોધ દૂર કરે છે"
            ),
            LocalizedFieldQuestion(
                id = 3,
                title = "૩. દલાલ શું ચાર્જ લે છે, અને તે ખરેખર શું કરે છે?",
                secondaryTitle = "3. What does the agent charge, and what does he actually do?",
                groundProblem = "દલાલો 10–15% રોકડ કમિશન (₹30,000–₹1,50,000) માંગે છે. હકીકતમાં તેઓ માત્ર ફોર્મની ફોટોકોપી કરાવે છે અને ફાઇલ અટકાવે છે.",
                agenticAiSolution = "100% મફત અને સીધી બેંક ફાઇલ. દલાલને ₹1 પણ આપ્યા વગર મેનેજર માટે પ્રમાણિત અહેવાલ બને છે.",
                stakeholder = "જિલ્લા ઉદ્યોગ કેન્દ્ર (DIC) અને વિજિલન્સ",
                metric = "દરેક અરજદારના ₹30,000–₹1,50,000 બચાવે છે"
            ),
            LocalizedFieldQuestion(
                id = 4,
                title = "૪. કયા અનૌપચારિક રેકોર્ડ મોજૂદ છે, અને માલિક તે શેર કરશે ખરા?",
                secondaryTitle = "4. What informal records exist, and would the owner share them?",
                groundProblem = "વેપારી પાસે UPI વ્યવહારો, ખાતાવહી અને દૂધની પાસબુક હોય છે, પરંતુ ટેક્સના ડરથી શેર કરતા ખચકાય છે.",
                agenticAiSolution = "ઉદ્યોગસાહસિકની સ્પષ્ટ ડિજિટલ સંમતિથી જ માત્ર બેંક લોન મૂલ્યાંકન માટે ડેટા વપરાય છે.",
                stakeholder = "લોન અરજદાર અને UPI ડિજિટલ માળખું",
                metric = "100% સંમતિ સાથે અનૌપચારિક રેકોર્ડનો ઉપયોગ"
            ),
            LocalizedFieldQuestion(
                id = 5,
                title = "૫. હા પાડતા પહેલા બેંક શાખા પ્રબંધકે શું જોવું જરૂરી છે?",
                secondaryTitle = "5. What does a branch manager need to see before saying yes?",
                groundProblem = "ગીરો વગરની લોન અટકે તો ઓડિટનો ડર મેનેજરને હોય છે. તેમને DSCR (>1.30) અને CGFMU/CGTMSE સરકારી ગેરંટીનો પુરાવો જોઈએ છે.",
                agenticAiSolution = "DSCR ગણતરી, 85% CGTMSE ગેરંટી સંદર્ભ અને 4-મુદ્દાની ચેકલિસ્ટ સાથેનો ક્રેડિટ રિપોર્ટ આપે છે.",
                stakeholder = "બેંક શાખા પ્રબંધક અને LDM",
                metric = "શંકાને સચોટ ક્રેડિટ સ્કોરમાં બદલે છે"
            ),
            LocalizedFieldQuestion(
                id = 6,
                title = "૬. પાત્ર અરજદારો પ્રક્રિયાની વચ્ચેથી જ કેમ છોડી દે છે?",
                secondaryTitle = "6. Why do eligible applicants drop out midway?",
                groundProblem = "10–15 વખત બેંકના ધક્કા, અઘરા અંગ્રેજી ફોર્મ અને દુકાન બંધ રહેવાથી થતા નુકસાનને કારણે તેઓ શાહુકાર પાસે જતા રહે છે.",
                agenticAiSolution = "એક જ બેઠકમાં ડિજિટલ મૂલ્યાંકન, માતૃભાષામાં અવાજ સહાય અને તાત્કાલિક બેંક રિપોર્ટ.",
                stakeholder = "નાણાકીય સમાવેશ અને ગ્રામીણ ઉદ્યોગ",
                metric = "અધવચ્ચે છોડવાનો દર 65% થી ઘટાડીને 8% નીચે લાવે છે"
            )
        )

        SupportedLanguage.EN -> listOf(
            LocalizedFieldQuestion(
                id = 1,
                title = "1. Which schemes has this business heard of, and from whom?",
                secondaryTitle = "१. इस व्यवसाय ने किन योजनाओं के बारे में सुना है, और किससे?",
                groundProblem = "Village owners only hear distorted rumors of schemes from local moneylenders, predatory agents, or tea-stall hearsay. They assume MUDRA or PMEGP require personal political connections or cash bribes.",
                agenticAiSolution = "SahayCredit AI matches 7 central and state schemes instantly using visual, voice-first guidance in their mother tongue, citing official DFS/KVIC gazette notifications.",
                stakeholder = "Business Correspondents & SHG Federations",
                metric = "Bridges the 70–80% scheme awareness deficit"
            ),
            LocalizedFieldQuestion(
                id = 2,
                title = "2. What did the last loan attempt require, and where did it stall?",
                secondaryTitle = "२. पिछले लोन प्रयास में क्या मांगा गया था, और फाइल कहाँ अटकी?",
                groundProblem = "Bank branch managers demanded 3 years of audited Income Tax Returns (ITR), chartered accountant balance sheets, and land mortgages. Rural tailors and fabricators cannot produce these, causing files to stall after 3–5 months.",
                agenticAiSolution = "Reconstructs verified cash velocity from daily PhonePe/GPay QR logs, dairy milk pour slips, and handwritten bahi khata, establishing legal proof of repayment capacity under RBI FIDD guidelines.",
                stakeholder = "Bank Branches & NBFCs",
                metric = "Eliminates 15–20 paper document barrier"
            ),
            LocalizedFieldQuestion(
                id = 3,
                title = "3. What does the agent charge, and what does he actually do?",
                secondaryTitle = "३. दलाल / एजेंट क्या शुल्क लेता है, और वह वास्तव में क्या करता है?",
                groundProblem = "Middlemen demand 10–15% upfront cash commission (₹30,000 to ₹1,50,000), claiming exclusive arrangements with branch officers. In reality, they only photocopy forms and delay files.",
                agenticAiSolution = "100% Free, direct bank file generation with zero commissions. Generates structured credit appraisal notes that branch managers can audit directly.",
                stakeholder = "District Industries Centres & Vigilance",
                metric = "Saves ₹30,000–₹1,50,000 per micro-borrower"
            ),
            LocalizedFieldQuestion(
                id = 4,
                title = "4. What informal records exist, and would the owner share them?",
                secondaryTitle = "४. कौन से अनौपचारिक रिकॉर्ड मौजूद हैं, और क्या मालिक उन्हें साझा करेगा?",
                groundProblem = "Owners possess daily UPI settlement messages, handwritten customer diaries (khata bahi), cooperative milk passbooks, and raw material invoices, but fear sharing them due to privacy and tax concerns.",
                agenticAiSolution = "Strict consent architecture: Data is used exclusively for credit evaluation with explicit entrepreneur approval. Local audio explanations guarantee transparency.",
                stakeholder = "Borrower & Digital Public Infrastructure (UPI)",
                metric = "Unlocks informal records with 100% consent"
            ),
            LocalizedFieldQuestion(
                id = 5,
                title = "5. What does a branch manager need to see before saying yes?",
                secondaryTitle = "५. हां कहने से पहले बैंक शाखा प्रबंधक को क्या देखना आवश्यक है?",
                groundProblem = "Branch managers face personal audit liability if an unsecured loan defaults. They need calibrated risk ratings, clear DSCR (>1.30), and statutory default guarantee citations.",
                agenticAiSolution = "Generates a standard Credit Appraisal Note with DSCR calculation, 85% CGTMSE guarantee citation, and a 4-point instant branch checklist, removing audit anxiety.",
                stakeholder = "Branch Managers & Lead District Managers (LDM)",
                metric = "Converts subjective doubt into objective credit scores"
            ),
            LocalizedFieldQuestion(
                id = 6,
                title = "6. Why do eligible applicants drop out midway?",
                secondaryTitle = "६. पात्र आवेदक प्रक्रिया के बीच में ही क्यों छोड़ देते हैं?",
                groundProblem = "Repetitive bank branch visits (10–15 trips), intimidating financial English jargon, absence of status feedback, and losing daily shop wages while waiting in queues.",
                agenticAiSolution = "Single-sitting digital appraisal, mother-tongue audio guidance, zero queues, and immediate Bank-Ready Dossier export on mobile or via Bank Mitra.",
                stakeholder = "Fintech Inclusion & Rural Entrepreneurship",
                metric = "Cuts dropout rate from 65% to under 8%"
            )
        )
    }
}
