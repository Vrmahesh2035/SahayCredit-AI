package com.example.data

import com.example.model.BusinessCategory
import com.example.model.CreditAnalysisResult
import com.example.model.DossierStatus
import com.example.model.Gender
import com.example.model.InformalRecordPreset
import com.example.model.RecordType
import com.example.model.SchemeMatch
import com.example.model.SocialCategory
import com.example.model.SubmittedDossier
import com.example.model.VillageAudioGuideText

object SampleProfilesData {
    val SAMPLE_PROFILES: List<InformalRecordPreset> = listOf(
        InformalRecordPreset(
            id = "ramesh-tailor",
            title = "दर्जी रमेश - सिलाई केंद्र (Ramesh Tailoring Works)",
            businessType = BusinessCategory.TAILOR,
            recordType = RecordType.UPI_QR,
            ownerName = "Ramesh Kumar Solanki",
            village = "Pipariya Kalan",
            district = "Hoshangabad (Narmadapuram)",
            state = "Madhya Pradesh",
            socialCategory = SocialCategory.OBC,
            gender = Gender.MALE,
            requestedLoanAmount = 180000,
            statedMonthlySales = 58000,
            summary = "3 mechanical sewing machines, steady daily QR code payments from villagers for festival suits and school uniforms.",
            sampleContent = """--- KHATA & UPI SUMMARY: RAMESH TAILORING WORKS ---
Owner: Ramesh Kumar Solanki (OBC)
Business: Custom stitching, school uniforms, festive garments, alteration
Equipment in shop: 3 singer hand-operated machines, 1 iron press, 1 cutting table.
Daily PhonePe/GPay QR Receipts (Past 90 Days Sample):
- August Avg: ₹1,650/day (Total UPI: ₹49,500; Cash: ₹12,000)
- September Avg: ₹2,100/day (Uniform rush, Total: ₹63,000)
- October Avg: ₹2,450/day (Diwali festive stitching, Total: ₹73,500)
Average 3-Month Sales: ₹62,000/month.
Estimated Cloth/Thread/Needle Cost: ₹18,000/month.
Shop Rent & Electricity: ₹4,500/month.
Net Household & Business Surplus: ~₹39,500/month.
Loan Purpose: Purchase 2 High-Speed Industrial Jack Zigzag Overlock Sewing Machines (₹75,000) + Fabric stock (₹1,05,000). Total: ₹1,80,000.
Past Credit Experience: Went to local middleman who demanded ₹25,000 advance bribe to get bank loan. Ramesh refused and stalled."""
        ),
        InformalRecordPreset(
            id = "kamla-dairy",
            title = "कमला देवी - दुग्ध उत्पादन केंद्र (Kamla Devi Dairy Unit)",
            businessType = BusinessCategory.DAIRY,
            recordType = RecordType.MILK_SLIP,
            ownerName = "Kamla Devi Ahirwar",
            village = "Chandanpur",
            district = "Bareilly",
            state = "Uttar Pradesh",
            socialCategory = SocialCategory.SC,
            gender = Gender.FEMALE,
            requestedLoanAmount = 250000,
            statedMonthlySales = 64000,
            summary = "4 milch cows, daily 34-38 liters cooperative milk pour record, weekly direct bank credit from dairy federation.",
            sampleContent = """--- COOPERATIVE MILK COLLECTION SLIP SUMMARY ---
Producer Name: Kamla Devi (SC / Female Entrepreneur)
Cooperative Society: Parag Milk Cooperative Union (Center #14)
Livestock: 4 Sahiwal Cross cows (3 lactating, 1 dry)
Daily Pour Record (Morning & Evening):
- Daily Pour Volume: 34 to 38.5 Liters/day
- Average Fat Content: 4.2% - 4.6%, SNF: 8.5%
- Rate per Liter: ₹44.50/Litre
- Weekly Cooperative Milk Payment Slip Avg: ₹11,200/week (Direct Bank Credit into Bank of Baroda account)
Monthly Milk Inflow: ₹48,000 (from cooperative) + ₹16,000 (local village loose milk sales) = ₹64,000/month.
Monthly Cattle Feed (Khal, Churi, Green Fodder) Expenses: ₹21,000/month.
Veterinary & Medicine: ₹2,500/month.
Net Monthly Cash Surplus: ~₹40,500/month.
Loan Purpose: Purchase 2 High-Yield Murrah Buffaloes + Automated Milking Unit + Chaff Cutter (₹2,50,000).
Eligibility Potential: PMEGP 35% Special Woman/SC Subsidy + MUDRA Kishore."""
        ),
        InformalRecordPreset(
            id = "shankar-fabrication",
            title = "शंकर वेल्डिंग व कृषि यंत्र (Shankar Welding & Fabrication)",
            businessType = BusinessCategory.FABRICATION,
            recordType = RecordType.KHATA_BAHI,
            ownerName = "Shankar Lal Panchal",
            village = "Sikandrabad Dehat",
            district = "Bulandshahr",
            state = "Uttar Pradesh",
            socialCategory = SocialCategory.OBC,
            gender = Gender.MALE,
            requestedLoanAmount = 400000,
            statedMonthlySales = 125000,
            summary = "Welds tractor trolleys, cultivator blades, iron window gates; has raw material supplier bills and steady UPI credits.",
            sampleContent = """--- HANDWRITTEN LEDGER & SUPPLIER INVOICE RECORD ---
Workshop: Shankar Iron Fabrication & Agri Implements
Owner: Shankar Lal Panchal (Artisan / Vishwakarma Blacksmith family)
Products: Tractor trolley repair, harrow discs, iron railings, shutter fabrication.
Monthly Inflow Analysis (From Bahi Khata + Paytm Soundbox):
- Customer UPI Receipts: ₹72,000/month (62 transactions/month)
- Cash Receipts from local farmers: ₹53,000/month
- Total Gross Inflow: ₹1,25,000/month.
Verified Operating Expenses:
- Steel angle/channel purchase from Jindal dealer: ₹58,000/month (Invoices available)
- Welding rods, gas cylinders, grinding wheels: ₹9,500/month
- Workshop 3-Phase Electricity: ₹5,200/month
- Assistant boy helper: ₹8,000/month
Total Operating Cost: ₹80,700/month.
Net Monthly Operating Surplus: ₹44,300/month.
Loan Needed: ₹4,00,000 for Heavy Plate Bending & CNC Plasma Sheet Cutter to double manufacturing speed.
Bank Barrier: Branch Manager asked for 3 years ITR & Audited balance sheet, which Shankar never had. Moneylender offered loan at 3% per month (36% annual interest)!"""
        ),
        InformalRecordPreset(
            id = "radha-shg",
            title = "राधा महिला स्वयं सहायता समूह (Radha SHG Spices & Food)",
            businessType = BusinessCategory.SHG_ENTERPRISE,
            recordType = RecordType.KHATA_BAHI,
            ownerName = "Radha Bai & 9 SHG Members",
            village = "Kalyanpur",
            district = "Ranchi",
            state = "Jharkhand",
            socialCategory = SocialCategory.ST,
            gender = Gender.FEMALE,
            requestedLoanAmount = 500000,
            statedMonthlySales = 110000,
            summary = "10 rural tribal women producing ground spices, papad, and mustard oil with verified SHG federation grading grade \"A\".",
            sampleContent = """--- NRLM SELF-HELP GROUP (SHG) RECORD & PASSBOOK ---
SHG Name: Radha Mahila Gramin Utthan Swavalambi Samuh
Reg No: NRLM/JH/RAN/2021/0498, Grade: A (NRLM Certified)
Members: 10 Women Micro-entrepreneurs (ST Community)
Enterprise: Processing & packaging pure turmeric, coriander powder, cold-press mustard oil.
Financial Performance:
- Monthly sales across 14 village weekly haats + 2 mini-marts: ₹1,10,000/month.
- Regular weekly savings deposit into Gramin Bank: ₹2,000/week without single delay for 28 months.
- Raw spice purchase (whole turmeric roots, mustard seed): ₹52,000/month.
- Food-grade pouches & sticker printing: ₹8,000/month.
- Group Net Surplus: ~₹50,000/month.
Loan Request: ₹5,00,000 for Commercial Pulverizer (Spices Grinding Machine), Oil Expeller, and FSSAI packaging unit.
Key Government Entitlement: PMEGP 35% Capital Subsidy for Rural ST Women Group + NABARD NRLM Interest Subvention."""
        )
    )

    val INITIAL_DOSSIERS: List<SubmittedDossier> = listOf(
        SubmittedDossier(
            id = "dossier-ramesh-1",
            applicationNumber = "SC-MSME-2026-849102",
            borrowerName = "Ramesh Kumar Solanki",
            businessName = "Ramesh Tailoring Works",
            businessType = BusinessCategory.TAILOR,
            village = "Pipariya Kalan",
            district = "Narmadapuram",
            state = "Madhya Pradesh",
            loanRequested = 180000,
            recommendedLoan = 180000,
            dscr = 1.84,
            riskScore = 88,
            riskLevel = "Low Risk",
            primaryScheme = "MUDRA Kishore (PMMY)",
            subsidyEntitlement = "0% Mortgage + CGFMU Guarantee",
            annualMoneylenderSaving = 48600,
            status = DossierStatus.PENDING_BRANCH_REVIEW,
            consentTimestamp = "2026-11-06T10:14:00.000Z",
            recordSummary = "Daily PhonePe/GPay QR averaging ₹1,800 to ₹2,450/day. Monthly revenue ₹62,000, verified 3 singer machines.",
            analysis = CreditAnalysisResult(
                estimatedMonthlyInflow = 62000,
                estimatedMonthlyOperatingExpense = 22500,
                netMonthlySurplus = 39500,
                recommendedLoanAmount = 180000,
                estimatedMonthlyEMI = 3960,
                dscr = 1.84,
                riskLevel = "Low Risk",
                riskScore = 88,
                viabilityVerdict = "Viable Bank Candidate",
                explainabilitySummary = "Steady UPI transaction frequency (42 trans/month) and high repeat festival tailoring revenue. DSCR of 1.84x provides ample safety margin over the 1.30 benchmark.",
                schemeMatches = listOf(
                    SchemeMatch(
                        schemeName = "Pradhan Mantri MUDRA Yojana (PMMY) - Kishore",
                        categoryTag = "Collateral-Free",
                        subsidyOrBenefit = "Nil Collateral (CGFMU Guarantee)",
                        maxLimit = "Up to ₹5,00,000",
                        officialCitation = "PMMY Circular DFS/RBI FIDD.MSME.BC.No.4/2023-24",
                        whyEligible = "Existing tailoring unit with proven UPI inflow for machinery upgrade.",
                        interestBenefit = "MCLR-linked rate 9.1% p.a."
                    )
                ),
                branchManagerChecklist = listOf(
                    "Verify applicant Aadhaar and rural enterprise location",
                    "Review bank passbook matching PhonePe QR settlements",
                    "Verify invoice for Jack zigzag sewing machines"
                ),
                villageAudioGuideText = VillageAudioGuideText(
                    hi = "रमेश जी, आपका UPI रिकॉर्ड मजबूत है और आपकी किस्त चुकाने की क्षमता उत्कृष्ट है।",
                    en = "Ramesh, your UPI cashflow is solid and your repayment capacity is strong."
                )
            )
        ),
        SubmittedDossier(
            id = "dossier-kamla-2",
            applicationNumber = "SC-MSME-2026-619284",
            borrowerName = "Kamla Devi Ahirwar",
            businessName = "Kamla Devi Dairy Unit",
            businessType = BusinessCategory.DAIRY,
            village = "Chandanpur",
            district = "Bareilly",
            state = "Uttar Pradesh",
            loanRequested = 250000,
            recommendedLoan = 250000,
            dscr = 1.95,
            riskScore = 92,
            riskLevel = "Low Risk",
            primaryScheme = "PMEGP Special Category Subsidy",
            subsidyEntitlement = "35% Rural SC/Woman Grant (₹87,500)",
            annualMoneylenderSaving = 67500,
            status = DossierStatus.IN_PRINCIPLE_SANCTIONED,
            consentTimestamp = "2026-11-05T14:30:00.000Z",
            recordSummary = "4 milch cows, Parag milk cooperative weekly slips averaging ₹11,200/week directly into bank.",
            analysis = CreditAnalysisResult(
                estimatedMonthlyInflow = 64000,
                estimatedMonthlyOperatingExpense = 23500,
                netMonthlySurplus = 40500,
                recommendedLoanAmount = 250000,
                estimatedMonthlyEMI = 5500,
                dscr = 1.95,
                riskLevel = "Low Risk",
                riskScore = 92,
                viabilityVerdict = "Viable Bank Candidate",
                explainabilitySummary = "Cooperative dairy slips provide statutory proof of primary income. 35% PMEGP margin money grant reduces bank risk to under 65%.",
                schemeMatches = listOf(
                    SchemeMatch(
                        schemeName = "Prime Minister's Employment Generation Programme (PMEGP)",
                        categoryTag = "Capital Grant",
                        subsidyOrBenefit = "35% Margin Money Subsidy",
                        maxLimit = "Up to ₹20,00,000",
                        officialCitation = "KVIC PMEGP Special Category Guidelines",
                        whyEligible = "Rural SC Woman entrepreneur establishing expanded animal husbandry unit.",
                        interestBenefit = "Government grant adjusted against principal after 3 years"
                    )
                ),
                branchManagerChecklist = listOf(
                    "Verify Parag dairy cooperative passbook entries",
                    "Inspect cattle shed and water arrangement",
                    "Submit margin money claim to KVIC portal"
                ),
                villageAudioGuideText = VillageAudioGuideText(
                    hi = "कमला देवी जी, आपको 35% सरकारी अनुदान (सब्सिडी) का लाभ मिलेगा।",
                    en = "Kamla Devi, you are entitled to a 35% non-repayable government capital grant."
                )
            )
        ),
        SubmittedDossier(
            id = "dossier-shankar-3",
            applicationNumber = "SC-MSME-2026-492019",
            borrowerName = "Shankar Lal Panchal",
            businessName = "Shankar Welding & Agri Implements",
            businessType = BusinessCategory.FABRICATION,
            village = "Sikandrabad Dehat",
            district = "Bulandshahr",
            state = "Uttar Pradesh",
            loanRequested = 400000,
            recommendedLoan = 400000,
            dscr = 1.62,
            riskScore = 84,
            riskLevel = "Low Risk",
            primaryScheme = "PM Vishwakarma & CGTMSE",
            subsidyEntitlement = "5% Concessional Interest + 85% CGTMSE Guarantee",
            annualMoneylenderSaving = 108000,
            status = DossierStatus.PENDING_BRANCH_REVIEW,
            consentTimestamp = "2026-11-04T09:20:00.000Z",
            recordSummary = "Paytm Soundbox UPI receipts ₹72,000/mo, steel raw material dealer invoices ₹58,000/mo.",
            analysis = CreditAnalysisResult(
                estimatedMonthlyInflow = 125000,
                estimatedMonthlyOperatingExpense = 80700,
                netMonthlySurplus = 44300,
                recommendedLoanAmount = 400000,
                estimatedMonthlyEMI = 8800,
                dscr = 1.62,
                riskLevel = "Low Risk",
                riskScore = 84,
                viabilityVerdict = "Viable Bank Candidate",
                explainabilitySummary = "Strong cash velocity and agricultural equipment fabrication orders. Covered under PM Vishwakarma and 85% CGTMSE default guarantee.",
                schemeMatches = listOf(
                    SchemeMatch(
                        schemeName = "PM Vishwakarma Scheme",
                        categoryTag = "Concessional Artisan Credit",
                        subsidyOrBenefit = "5% Interest Rate Subvention",
                        maxLimit = "Up to ₹3,00,000 in two tranches",
                        officialCitation = "PM Vishwakarma Scheme Guidelines 2023-24",
                        whyEligible = "Traditional artisan (welder/blacksmith) trade recognized under scheme.",
                        interestBenefit = "Government pays 8% interest subsidy directly to bank"
                    )
                ),
                branchManagerChecklist = listOf(
                    "Verify workshop machinery and 3-phase electricity connection",
                    "Review steel supplier purchase invoices",
                    "Check Udyam registration certificate"
                ),
                villageAudioGuideText = VillageAudioGuideText(
                    hi = "शंकर जी, आपकी वेल्डिंग दुकान के लिए 5% ब्याज दर पर विश्वकर्मा योजना उपलब्ध है।",
                    en = "Shankar, you qualify for PM Vishwakarma with only 5% interest."
                )
            )
        )
    )
}
