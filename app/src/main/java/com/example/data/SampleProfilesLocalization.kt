package com.example.data

import com.example.model.Gender
import com.example.model.RecordType
import com.example.model.SocialCategory
import com.example.model.SubmittedDossier
import com.example.model.SupportedLanguage
import com.example.repository.AiLocalizedTemplates
import java.util.Locale
import kotlin.math.roundToInt

object SampleProfilesLocalization {
    fun localizedTitle(presetId: String, lang: SupportedLanguage): String = when (presetId) {
        "ramesh-tailor" -> when (lang) {
            SupportedLanguage.HI -> "दर्जी रमेश - सिलाई केंद्र"
            SupportedLanguage.KN -> "ದರ್ಜಿ ರಮೇಶ್ - ಟೈಲರಿಂಗ್ ಕೇಂದ್ರ"
            SupportedLanguage.TA -> "தையல்காரர் ரமேஷ் - தையல் கடை"
            SupportedLanguage.TE -> "టైలర్ రమేష్ - టైలరింగ్ కేంద్రం"
            SupportedLanguage.MR -> "शिंपी रमेश - टेलरिंग केंद्र"
            SupportedLanguage.BN -> "দর্জি রমেশ - সেলাই কেন্দ্র"
            SupportedLanguage.GU -> "દરજી રમેશ - સિલાઈ કેન્દ્ર"
            SupportedLanguage.EN -> "Ramesh Tailoring Works"
        }
        "kamla-dairy" -> when (lang) {
            SupportedLanguage.HI -> "कमला देवी - दुग्ध उत्पादन केंद्र"
            SupportedLanguage.KN -> "ಕಮಲಾ ದೇವಿ - ಹೈನುಗಾರಿಕೆ ಘಟಕ"
            SupportedLanguage.TA -> "கமலா தேவி - பால் பண்ணை"
            SupportedLanguage.TE -> "కమలా దేవి - పాల డెయిరీ కేంద్రం"
            SupportedLanguage.MR -> "कमला देवी - दुग्ध उत्पादन केंद्र"
            SupportedLanguage.BN -> "কমলা দেবী - দুগ্ধ খামার"
            SupportedLanguage.GU -> "કમલા દેવી - ડેરી એકમ"
            SupportedLanguage.EN -> "Kamla Devi Dairy Unit"
        }
        "shankar-fabrication" -> when (lang) {
            SupportedLanguage.HI -> "शंकर वेल्डिंग व कृषि यंत्र"
            SupportedLanguage.KN -> "ಶಂಕರ್ ವೆಲ್ಡಿಂಗ್ ಮತ್ತು ಕೃಷಿ ಉಪಕರಣ"
            SupportedLanguage.TA -> "சங்கர் வெல்டிங் & விவசாய கருவிகள்"
            SupportedLanguage.TE -> "శంకర్ వెల్డింగ్ & వ్యవసాయ పనిముట్లు"
            SupportedLanguage.MR -> "शंकर वेल्डिंग व कृषी अवजारे"
            SupportedLanguage.BN -> "শঙ্কর ওয়েল্ডিং ও কৃষি যন্ত্র"
            SupportedLanguage.GU -> "શંકર વેલ્ડિંગ અને કૃષિ ઓજારો"
            SupportedLanguage.EN -> "Shankar Welding & Fabrication"
        }
        else -> when (lang) {
            SupportedLanguage.HI -> "राधा महिला स्वयं सहायता समूह (SHG)"
            SupportedLanguage.KN -> "ರಾಧಾ ಮಹಿಳಾ ಸ್ವಸಹಾಯ ಸಂಘ (SHG)"
            SupportedLanguage.TA -> "ராதா மகளிர் சுயஉதவி குழு (SHG)"
            SupportedLanguage.TE -> "రాధా మహిళా స్వయం సహాయక సంఘం (SHG)"
            SupportedLanguage.MR -> "राधा महिला बचत गट (SHG)"
            SupportedLanguage.BN -> "রাধা মহিলা স্বনির্ভর দল (SHG)"
            SupportedLanguage.GU -> "રાધા મહિલા સ્વસહાય જૂથ (SHG)"
            SupportedLanguage.EN -> "Radha Women SHG Spices & Food"
        }
    }

    fun localizedSummary(presetId: String, lang: SupportedLanguage): String = when (presetId) {
        "ramesh-tailor" -> when (lang) {
            SupportedLanguage.HI -> "3 सिलाई मशीनें, त्योहारों और स्कूल यूनिफॉर्म के लिए ग्रामीणों से नियमित दैनिक UPI QR भुगतान।"
            SupportedLanguage.KN -> "3 ಹೊಲಿಗೆ ಯಂತ್ರಗಳು, ಹಬ್ಬದ ಬಟ್ಟೆ ಮತ್ತು ಶಾಲಾ ಸಮವಸ್ತ್ರಗಳಿಗಾಗಿ ದೈನಂದಿನ ನಿಯಮಿತ UPI QR ಪಾವತಿಗಳು."
            SupportedLanguage.TA -> "3 தையல் இயந்திரங்கள், சீருடை மற்றும் ஆடை தைப்பதற்கான தினசரி UPI QR பரிவர்த்தனைகள்."
            SupportedLanguage.TE -> "3 కుట్టు యంత్రాలు, పండుగ బట్టలు మరియు స్కూల్ యూనిఫాంల కోసం రోజువారీ UPI QR చెల్లింపులు."
            SupportedLanguage.MR -> "3 शिलाई मशीन, सणाचे कपडे आणि शालेय गणवेशासाठी गावकऱ्यांकडून रोजची UPI QR आवक."
            SupportedLanguage.BN -> "৩টি সেলাই মেশিন, উৎসবের পোশাক ও স্কুল ইউনিফর্মের জন্য নিয়মিত দৈনিক UPI QR লেনদেন।"
            SupportedLanguage.GU -> "૩ સિલાઈ મશીનો, તહેવારના કપડાં અને શાળાના યુનિફોર્મ માટે રોજિંદા UPI QR વ્યવહારો."
            SupportedLanguage.EN -> "3 mechanical sewing machines, steady daily QR code payments for festival suits and school uniforms."
        }
        "kamla-dairy" -> when (lang) {
            SupportedLanguage.HI -> "4 दुधारू गायें, डेयरी सहकारी समिति में रोज 34-38 लीटर दूध की पर्ची व साप्ताहिक सीधा बैंक भुगतान।"
            SupportedLanguage.KN -> "4 ಹಾಲು ಕೊಡುವ ಹಸುಗಳು, ಡೈರಿ ಸಂಘಕ್ಕೆ ದಿನಕ್ಕೆ 34–38 ಲೀಟರ್ ಹಾಲು ಪೂರೈಕೆ ಮತ್ತು ವಾರದ ನೇರ ಬ್ಯಾಂಕ್ ಪಾವತಿ."
            SupportedLanguage.TA -> "4 கறவை மாடுகள், பால் கூட்டுறவு சங்கத்தில் தினமும் 34–38 லிட்டர் பால் விநியோகம் மற்றும் வாராந்திர வங்கி வரவு."
            SupportedLanguage.TE -> "4 పాడి ఆవులు, పాల సహకార సంఘానికి రోజుకు 34–38 లీటర్ల పాల పోత మరియు వారం వారీ బ్యాంక్ జమ."
            SupportedLanguage.MR -> "4 दुभत्या गायी, दूध सहकारी संस्थेत रोज 34-38 लिटर दूध संकलन आणि थेट बँक खात्यात जमा."
            SupportedLanguage.BN -> "৪টি দুগ্ধবতী গরু, সমবায়ে দৈনিক ৩৪-৩৮ লিটার দুধ সরবরাহ এবং সাপ্তাহিক সরাসরি ব্যাঙ্ক পেমেন্ট।"
            SupportedLanguage.GU -> "૪ દૂધાળી ગાયો, દૂધ મંડળીમાં રોજ ૩૪-૩૮ લિટર દૂધ અને સાપ્તાહિક સીધી બેંક ચુકવણી."
            SupportedLanguage.EN -> "4 milch cows, daily 34-38 liters cooperative milk pour record, weekly direct bank credit."
        }
        "shankar-fabrication" -> when (lang) {
            SupportedLanguage.HI -> "ट्रैक्टर ट्रॉली, कल्टीवेटर ब्लेड व लोहे के गेट निर्माण; कच्चा माल बिल और नियमित UPI आवक।"
            SupportedLanguage.KN -> "ಟ್ರ್ಯಾಕ್ಟರ್ ಟ್ರಾಲಿ, ಕೃಷಿ ಉಪಕರಣ ಮತ್ತು ಕಬ್ಬಿಣದ ಗೇಟ್ ವೆಲ್ಡಿಂಗ್; ಸಾಮಗ್ರಿ ಬಿಲ್‌ಗಳು ಮತ್ತು ನಿಯಮಿತ UPI ಆದಾಯ."
            SupportedLanguage.TA -> "டிராக்டர் டிராலி, கலப்பை மற்றும் இரும்பு கேட் வெல்டிங்; மூலப்பொருள் ரசீதுகள் மற்றும் நிலையான UPI வரவு."
            SupportedLanguage.TE -> "ట్రాక్టర్ ట్రాలీ, నాగలి బ్లేడ్లు మరియు ఇనుప గేట్ల వెల్డింగ్; మెటీరియల్ బిల్లులు మరియు UPI రాబడి."
            SupportedLanguage.MR -> "ट्रॅक्टर ट्रॉली, नांगर व लोखंडी गेट वेल्डिंग; कच्चा माल बिले आणि नियमित UPI व्यवहार."
            SupportedLanguage.BN -> "ট্রাক্টর ট্রলি, চাষের যন্ত্রপাতি ও লোহার গেট ওয়েল্ডিং; কাঁচামালের বিল ও নিয়মিত UPI আয়।"
            SupportedLanguage.GU -> "ટ્રેક્ટર ટ્રોલી, ખેતીના ઓજારો અને લોખંડના ગેટનું વેલ્ડિંગ; કાચા માલના બિલ અને નિયમિત UPI આવક."
            SupportedLanguage.EN -> "Welds tractor trolleys, cultivator blades, iron gates; has supplier bills and steady UPI credits."
        }
        else -> when (lang) {
            SupportedLanguage.HI -> "10 आदिवासी ग्रामीण महिलाएं हल्दी-मसाला, पापड़ और सरसों तेल बनाती हैं; NRLM ग्रेड 'A' प्रमाणित।"
            SupportedLanguage.KN -> "ಮಸಾಲೆ, ಹಪ್ಪಳ ಮತ್ತು ಸಾಸಿವೆ ಎಣ್ಣೆ ತಯಾರಿಸುವ 10 ಗ್ರಾಮೀಣ ಮಹಿಳೆಯರ NRLM ದರ್ಜೆ 'A' ಪ್ರಮಾಣಿತ ಸಂಘ."
            SupportedLanguage.TA -> "மசாலா, அப்பளம் மற்றும் கடுகு எண்ணெய் தயாரிக்கும் 10 கிராமப்புற பெண்களின் NRLM தரம் 'A' குழு."
            SupportedLanguage.TE -> "మసాలాలు, అప్పడాలు, ఆవనూనె తయారు చేసే 10 మంది గ్రామీణ మహిళల NRLM గ్రేడ్ 'A' సంఘం."
            SupportedLanguage.MR -> "मसाले, पापड आणि मोहरी तेल बनवणाऱ्या १० ग्रामीण महिलांचा NRLM ग्रेड 'A' प्रमाणित बचत गट."
            SupportedLanguage.BN -> "মশলা, পাঁপড় ও সরিষার তেল প্রস্তুতকারক ১০ জন গ্রামীণ মহিলার NRLM গ্রেড 'A' স্বনির্ভর দল।"
            SupportedLanguage.GU -> "મસાલા, પાપડ અને સરસવનું તેલ બનાવતી ૧૦ ગ્રામીણ મહિલાઓનું NRLM ગ્રેડ 'A' પ્રમાણિત જૂથ."
            SupportedLanguage.EN -> "10 rural tribal women producing spices, papad, and mustard oil with verified NRLM grade 'A' record."
        }
    }

    fun localizeDossier(dossier: SubmittedDossier, lang: SupportedLanguage): SubmittedDossier {
        val isSeeded = dossier.id == "dossier-ramesh-1" ||
            dossier.id == "dossier-kamla-2" ||
            dossier.id == "dossier-shankar-3"
        if (!isSeeded) return dossier

        val (recordType, category, gender) = when (dossier.id) {
            "dossier-ramesh-1" -> Triple(RecordType.UPI_QR, SocialCategory.OBC, Gender.MALE)
            "dossier-kamla-2" -> Triple(RecordType.MILK_SLIP, SocialCategory.SC, Gender.FEMALE)
            else -> Triple(RecordType.KHATA_BAHI, SocialCategory.OBC, Gender.MALE)
        }
        val schemes = AiLocalizedTemplates.localizedSchemeMatches(
            lang = lang,
            businessType = dossier.businessType,
            recordType = recordType,
            socialCategory = category,
            gender = gender
        )
        val inflow = dossier.analysis.estimatedMonthlyInflow.coerceAtLeast(1)
        val dailyAvg = (inflow / 30.0).roundToInt()
        val marginPct = ((dossier.analysis.netMonthlySurplus * 100.0) / inflow).roundToInt()
        val dscrFormatted = String.format(Locale.US, "%.2f", dossier.dscr)

        val localizedSummary = when (dossier.id) {
            "dossier-ramesh-1" -> localizedSummary("ramesh-tailor", lang)
            "dossier-kamla-2" -> localizedSummary("kamla-dairy", lang)
            else -> localizedSummary("shankar-fabrication", lang)
        }

        val localizedPrimaryScheme = when (dossier.id) {
            "dossier-kamla-2" -> schemes.getOrNull(1)?.schemeName ?: schemes.first().schemeName
            "dossier-shankar-3" -> schemes.getOrNull(2)?.schemeName ?: schemes.first().schemeName
            else -> schemes.first().schemeName
        }

        val localizedSubsidy = when (dossier.id) {
            "dossier-kamla-2" -> schemes.getOrNull(1)?.subsidyOrBenefit ?: dossier.subsidyEntitlement
            "dossier-shankar-3" -> schemes.getOrNull(2)?.subsidyOrBenefit ?: dossier.subsidyEntitlement
            else -> schemes.first().subsidyOrBenefit
        }

        val updatedAnalysis = dossier.analysis.copy(
            riskLevel = AiLocalizedTemplates.localizedRiskLevel(lang),
            viabilityVerdict = AiLocalizedTemplates.localizedVerdict(lang),
            explainabilitySummary = AiLocalizedTemplates.localizedExplainability(
                lang = lang,
                dailyAvg = dailyAvg,
                marginPct = marginPct,
                dscrFormatted = dscrFormatted
            ),
            schemeMatches = schemes,
            branchManagerChecklist = AiLocalizedTemplates.localizedChecklist(lang),
            villageAudioGuideText = AiLocalizedTemplates.localizedAudioGuideMap(dossier.analysis.netMonthlySurplus)
        )

        return dossier.copy(
            riskLevel = updatedAnalysis.riskLevel,
            primaryScheme = localizedPrimaryScheme,
            subsidyEntitlement = localizedSubsidy,
            recordSummary = localizedSummary,
            analysis = updatedAnalysis
        )
    }
}

