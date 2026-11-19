package com.example.data

import com.example.model.FieldQuestionItem
import com.example.model.SupportedLanguage

object FieldQuestionsData {
    fun localizedQuestions(lang: SupportedLanguage): List<LocalizedFieldQuestion> =
        FieldQuestionsTranslations.getTranslatedList(lang)

    val QUESTIONS: List<FieldQuestionItem> = listOf(
        FieldQuestionItem(
            id = 1,
            title = "1. Which schemes has this business heard of, and from whom?",
            titleHi = "१. इस व्यवसाय ने किन योजनाओं के बारे में सुना है, और किससे?",
            groundProblem = "Village owners only hear distorted rumors of schemes from local moneylenders, predatory agents, or tea-stall hearsay. They assume MUDRA or PMEGP require personal political connections or paying cash bribes.",
            groundProblemHi = "गांव के दर्जी व कारीगरों ने सिर्फ चाय की दुकान या दलालों से सुना होता है कि लोन के लिए \"पहुंच\" चाहिए या घूस देनी होगी। वे 70-80% आधिकारिक योजनाओं (PMEGP, Vishwakarma) से अनजान रहते हैं।",
            agenticAiSolution = "SahayCredit AI matches 7 central and state schemes instantly using visual, voice-first guidance in their mother tongue, citing official DFS/KVIC gazette notifications.",
            agenticAiSolutionHi = "सहाय-क्रेडिट AI मातृभाषा में बोलकर 7 केंद्रीय योजनाओं (मुद्रा, PMEGP 35% सब्सिडी, विश्वकर्मा 5% ब्याज) से सीधे मिलाता है।",
            stakeholder = "Business Correspondents & SHG Federations",
            metric = "Bridges the 70–80% scheme awareness deficit"
        ),
        FieldQuestionItem(
            id = 2,
            title = "2. What did the last loan attempt require, and where did it stall?",
            titleHi = "२. पिछले लोन प्रयास में क्या मांगा गया था, और फाइल कहाँ अटकी?",
            groundProblem = "Bank branch managers demanded 3 years of audited Income Tax Returns (ITR), chartered accountant balance sheets, and land mortgages. Rural tailors and fabricators cannot produce these, causing files to stall after 3-5 months.",
            groundProblemHi = "बैंक ने 3 साल का ITR और सीए की ऑडिट रिपोर्ट मांगी। गांव के छोटे उद्यमी के पास ये नहीं थे, और 4 महीने दौड़ने के बाद फाइल खारिज हो गई।",
            agenticAiSolution = "Reconstructs verified cash velocity from daily PhonePe/GPay QR logs, dairy milk pour slips, and handwritten bahi khata, establishing legal proof of repayment capacity under RBI FIDD guidelines.",
            agenticAiSolutionHi = "दैनिक UPI क्यूआर कोड और दूध की पर्ची से बैंक-मान्य कैशफ्लो बनाकर सीधे DSCR गणना करता है।",
            stakeholder = "Bank Branches & NBFCs",
            metric = "Eliminates 15–20 paper document barrier"
        ),
        FieldQuestionItem(
            id = 3,
            title = "3. What does the agent charge, and what does he actually do?",
            titleHi = "३. दलाल / एजेंट क्या शुल्क लेता है, और वह वास्तव में क्या करता है?",
            groundProblem = "Middlemen demand 10–15% upfront cash commission (e.g. ₹30,000 to ₹1,50,000), claiming they have \"exclusive arrangements\" with branch officers. In reality, they only photocopy forms and often run away.",
            groundProblemHi = "दलाल 10 से 15% नकद कमीशन (₹25,000 से ₹1,00,000) मांगते हैं। वास्तव में वे सिर्फ फोटोकॉपी कराते हैं और अंत में फाइल लटका देते हैं।",
            agenticAiSolution = "100% Free, direct bank file generation with zero commissions. Generates structured credit appraisal notes that branch managers can audit directly.",
            agenticAiSolutionHi = "100% निःशुल्क और सीधा। दलाल को ₹1 भी दिए बिना सीधे बैंक मैनेजर के लिए प्रमाणित फाइल बनती है।",
            stakeholder = "District Industries Centres & Vigilance",
            metric = "Saves ₹30,000–₹1,50,000 per micro-borrower"
        ),
        FieldQuestionItem(
            id = 4,
            title = "4. What informal records exist, and would the owner share them?",
            titleHi = "४. कौन से अनौपचारिक रिकॉर्ड मौजूद हैं, और क्या मालिक उन्हें साझा करेगा?",
            groundProblem = "Owners possess daily UPI settlement messages, handwritten customer diaries (khata bahi), cooperative milk passbooks, and raw material invoices, but fear sharing them due to privacy and tax concerns.",
            groundProblemHi = "दुकानदार के पास फोनपे/गूगल-पे, दूध की डायरी और कूरियर/सामान के बिल होते हैं, पर उन्हें डर रहता है कि कहीं टैक्स वाले परेशान न करें।",
            agenticAiSolution = "Strict consent architecture: Data is used exclusively for credit evaluation with explicit entrepreneur approval. Visual audio explanation guarantees data safety.",
            agenticAiSolutionHi = "उद्यमी की स्पष्ट डिजिटल सहमति से केवल बैंक लोन के लिए उपयोग किया जाता है। कोई कर नोटिस या दुरुपयोग नहीं।",
            stakeholder = "Borrower & Digital Public Infrastructure (UPI)",
            metric = "Unlocks informal records with 100% consent"
        ),
        FieldQuestionItem(
            id = 5,
            title = "5. What does a branch manager need to see before saying yes?",
            titleHi = "५. हां कहने से पहले बैंक शाखा प्रबंधक को क्या देखना आवश्यक है?",
            groundProblem = "Branch managers face personal audit liability if an unsecured loan defaults. They need calibrated, gender-and-region fair risk ratings, clear DSCR (>1.3), and statutory default guarantee citations.",
            groundProblemHi = "ब्रांच मैनेजर को ऑडिट का डर होता है। उन्हें यह विश्वास चाहिए कि उधारकर्ता समय पर किस्त देगा और यदि कोई समस्या आई तो CGFMU / CGTMSE गारंटी बैंक को बचाएगी।",
            agenticAiSolution = "Generates standard Credit Appraisal Note with DSCR calculation, 85% CGTMSE guarantee citation, and 4-point instant branch checklist, removing fear of audit penalty.",
            agenticAiSolutionHi = "डीएससीआर (किस्त चुकाने की क्षमता) और 85% सरकारी CGTMSE गारंटी के साथ 4-सूत्रीय त्वरित जांच पत्र प्रदान करता है।",
            stakeholder = "Branch Managers & Lead District Managers (LDM)",
            metric = "Converts subjective doubt into objective credit scores"
        ),
        FieldQuestionItem(
            id = 6,
            title = "6. Why do eligible applicants drop out midway?",
            titleHi = "६. पात्र आवेदक प्रक्रिया के बीच में ही क्यों छोड़ देते हैं?",
            groundProblem = "Repetitive bank branch visits (10-15 trips), intimidating financial English jargon, absence of file status feedback, and losing daily business wages while standing in queues.",
            groundProblemHi = "बार-बार बैंक के 10-15 चक्कर लगाना, अंग्रेजी के कठिन फॉर्म, और दुकान बंद रहने से दिन की मजदूरी का नुकसान। अंत में वे थककर साहूकार के पास चले जाते हैं।",
            agenticAiSolution = "Single-sitting digital appraisal, local language audio guidance, zero queues, and immediate Bank-Ready Dossier export directly on mobile or via Bank Mitra.",
            agenticAiSolutionHi = "मातृभाषा में सरल ऑडियो सहायता, एक बार में तैयार फाइल, और सीधे बैंक मित्र या शाखा में जमा करने की सुविधा।",
            stakeholder = "Fintech Inclusion & Rural Entrepreneurship",
            metric = "Cuts dropout rate from 65% to under 8%"
        )
    )
}
