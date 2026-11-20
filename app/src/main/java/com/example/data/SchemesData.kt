package com.example.data

import com.example.model.SchemeInfo

object SchemesData {
    val OFFICIAL_SCHEMES: List<SchemeInfo> = listOf(
        SchemeInfo(
            id = "mudra-kishore",
            name = "Pradhan Mantri MUDRA Yojana (PMMY) - Kishore Category",
            shortName = "MUDRA Kishore",
            agency = "Department of Financial Services, Ministry of Finance (DFS/RBI)",
            maxAmount = 500000L,
            subsidyPercentage = "Interest Subvention & 0% Collateral",
            interestRate = "8.5% - 10.25% p.a.",
            collateralRequirement = "Nil (Covered under Credit Guarantee Fund for Micro Units - CGFMU)",
            officialCitation = "PMMY Operational Guidelines (DFS/RBI Master Circular FIDD.MSME & NFS.BC.No.4/06.02.31/2023-24)",
            eligibilityHighlight = "Micro manufacturing, processing, trading, tailoring, fabrication & repair enterprises.",
            eligibleCategories = listOf("All micro-enterprises", "Tailors", "Fabricators", "Artisans", "Kirana", "Dairy"),
            keyBenefits = listOf(
                "No collateral or third-party guarantee required up to ₹10 Lakhs",
                "CGFMU credit guarantee covers the bank in full against non-willful default",
                "Repayment tenure up to 5-7 years with comfortable initial moratorium",
                "MUDRA Card (RuPay Debit Card) provided for flexible working capital withdrawal"
            ),
            documentsNeeded = listOf(
                "Identity & Address proof (Aadhaar / Voter ID)",
                "Proof of business activity (UPI QR statement, Shop registration or Udyam Assist Certificate)",
                "Bank statement or 6 months UPI collection record",
                "Quotation / Estimate for machinery or raw material purchase"
            )
        ),
        SchemeInfo(
            id = "pmegp-kvic",
            name = "Prime Minister's Employment Generation Programme (PMEGP)",
            shortName = "PMEGP Subsidy",
            agency = "Khadi & Village Industries Commission (KVIC), Ministry of MSME",
            maxAmount = 5000000L,
            subsidyPercentage = "25% to 35% Capital Subsidy (Direct Margin Money Grant)",
            interestRate = "Standard bank MCLR / EBLR (~9.0% - 9.75%)",
            collateralRequirement = "Nil up to ₹10 Lakhs (Covered under CGTMSE)",
            officialCitation = "MoMSME Notification No. PMEGP/Policy/2022-23; Scheme Revision guidelines 2024-25",
            eligibilityHighlight = "New micro manufacturing units up to ₹50L or service units up to ₹20L in rural/semi-urban areas.",
            eligibleCategories = listOf("Rural Women", "SC/ST", "OBC", "Rural General", "SHG Members"),
            keyBenefits = listOf(
                "35% non-repayable government grant for Special Category (Women, SC, ST, OBC, Rural, Differently-abled)",
                "25% grant for General Category rural entrepreneurs",
                "Borrower only needs to invest 5% to 10% own equity margin",
                "Subsidy kept in 3-year term deposit; automatically adjusted against loan principal on good conduct"
            ),
            documentsNeeded = listOf(
                "Project Profile Report / Cost estimate prepared via SahayCredit AI",
                "Aadhaar card & Rural residence proof",
                "Caste / Special category certificate (if claiming 35% subsidy)",
                "Educational certificate (8th pass for manufacturing above ₹10L / service above ₹5L)"
            )
        ),
        SchemeInfo(
            id = "pm-vishwakarma",
            name = "PM Vishwakarma Scheme for Traditional Artisans & Craftspeople",
            shortName = "PM Vishwakarma",
            agency = "Ministry of MSME & Ministry of Skill Development and Entrepreneurship",
            maxAmount = 300000L,
            subsidyPercentage = "8% Interest Subvention (Net effective interest 5% only!) + ₹15,000 Toolkit Grant",
            interestRate = "Fixed 5% p.a. concessional interest",
            collateralRequirement = "100% Collateral-Free (Guaranteed by MoMSME)",
            officialCitation = "PM Vishwakarma Scheme Guidelines 2023-24, Gazette Notification CG-DL-E-17082023-248135",
            eligibilityHighlight = "18 traditional artisan trades: Tailor (Darzi), Carpenter (Suthar), Blacksmith (Lohar), Welder, Potter (Kumhar), Basket Maker, Cobbler.",
            eligibleCategories = listOf("Tailors", "Carpenters", "Blacksmiths", "Traditional Fabricators", "Artisans"),
            keyBenefits = listOf(
                "Tranche 1: ₹1,00,000 for 18 months tenure at only 5% interest",
                "Tranche 2: ₹2,00,000 for 30 months tenure on successful repayment of Tranche 1",
                "Free ₹15,000 e-voucher for modern power tools and equipment",
                "PM Vishwakarma Certificate & ID card conferring formal recognition",
                "Digital incentive: ₹1 per digital transaction up to 100 transactions/month"
            ),
            documentsNeeded = listOf(
                "Aadhaar card linked with active mobile number",
                "Bank passbook or cancelled cheque",
                "Self-declaration of family trade / artisan activity verified by Gram Panchayat"
            )
        ),
        SchemeInfo(
            id = "stand-up-india",
            name = "Stand-Up India Scheme for Women and SC/ST Entrepreneurs",
            shortName = "Stand-Up India",
            agency = "Department of Financial Services & SIDBI",
            maxAmount = 10000000L,
            subsidyPercentage = "Up to 15% Margin Money convergence + Collateral Guarantee",
            interestRate = "Lowest applicable bank rate (MCLR + 3% + Tenor Premium)",
            collateralRequirement = "Nil up to ₹50 Lakhs if covered by Credit Guarantee Scheme for Stand Up India (CGFSI)",
            officialCitation = "DFS Stand-Up India Guidelines (Ref: F.No. 28/01/2015-CP) extended through 2025",
            eligibilityHighlight = "At least one SC/ST and at least one Woman borrower per bank branch for setting up greenfield units.",
            eligibleCategories = listOf("Women Entrepreneurs", "SC/ST Entrepreneurs", "Women SHG Federations"),
            keyBenefits = listOf(
                "Substantial credit from ₹10 Lakh to ₹1 Crore for viable expanding enterprises",
                "Working capital drawn via RuPay debit card overdraft facility",
                "Handholding support from SIDBI connect centers and DICs",
                "Concession in loan processing fees across scheduled commercial banks"
            ),
            documentsNeeded = listOf(
                "Proof of identity, address and SC/ST or Women ownership certificate (>51% shareholding)",
                "Brief project description & projected cash flows (generated by SahayCredit AI)",
                "Bank account statement for 6-12 months",
                "Lease agreement or proof of business premises"
            )
        ),
        SchemeInfo(
            id = "cgtmse-guarantee",
            name = "Credit Guarantee Fund Trust for Micro and Small Enterprises (CGTMSE)",
            shortName = "CGTMSE Guarantee",
            agency = "Ministry of MSME and SIDBI",
            maxAmount = 50000000L,
            subsidyPercentage = "85% Default Credit Guarantee to Bank (No Private Surety)",
            interestRate = "Standard Bank MSME Lending Rate (~8.8% - 10.5%)",
            collateralRequirement = "Zero Third-Party Guarantee or Physical Collateral",
            officialCitation = "CGTMSE Circular No. 222/2023-24; Credit Guarantee Scheme (CGS-I) Revisions",
            eligibilityHighlight = "New and existing Micro and Small Enterprises in manufacturing or service sectors.",
            eligibleCategories = listOf("Fabricators", "Food processing", "Packaging units", "Workshops", "Rural units"),
            keyBenefits = listOf(
                "Bank branch managers CANNOT ask for land or house mortgages up to ₹5 Crore",
                "85% guarantee coverage for loans up to ₹5 Lakhs, and for all Women/SC/ST/NER/Aspirational districts",
                "Annual guarantee fee reduced to just 0.37% for loans up to ₹1 Crore",
                "Eliminates the fear of branch managers regarding NPA audits"
            ),
            documentsNeeded = listOf(
                "Udyam Registration Certificate (free instant online registration via SahayCredit)",
                "Financial track record / UPI cashflow verification note",
                "Project appraisal note with DSCR calculation"
            )
        ),
        SchemeInfo(
            id = "dairy-deds-ahidf",
            name = "Animal Husbandry & Dairy Infrastructure Support (NABARD / AHIDF)",
            shortName = "Dairy Credit & Subsidy",
            agency = "Department of Animal Husbandry and Dairying (DAHD) & NABARD",
            maxAmount = 1500000L,
            subsidyPercentage = "25% (General) to 33.33% (SC/ST/Women) Capital Subsidy",
            interestRate = "3% Interest Subvention for eligible projects",
            collateralRequirement = "Nil up to ₹1.6 Lakhs (KCC Animal Husbandry) / CGTMSE for larger units",
            officialCitation = "DAHD AHIDF Operational Guidelines & NABARD Circular No. 89/DoR-GSS/2023",
            eligibilityHighlight = "Dairy farmers, milk collection centers, chilling units, cattle purchase, and fodder units.",
            eligibleCategories = listOf("Dairy Units", "Cattle Keepers", "Milk Cooperatives", "Women Milk Pourers"),
            keyBenefits = listOf(
                "Subsidy on purchase of 2 to 10 crossbred milch cows or Murrah buffaloes",
                "Subsidy for automated milking machines, bulk milk cooling units, and chaff cutters",
                "3% interest subvention paid directly by the central government",
                "Cooperative milk pour slips accepted by all public sector banks as certified primary income proof"
            ),
            documentsNeeded = listOf(
                "Cooperative / Private Dairy Milk Pour card or passbook (3-6 months)",
                "Aadhaar and Land / Shed possession details",
                "Veterinary doctor fitness certificate for cattle procurement"
            )
        ),
        SchemeInfo(
            id = "pm-svanidhi",
            name = "PM Street Vendor’s AtmaNirbhar Nidhi (PM SVANidhi)",
            shortName = "PM SVANidhi",
            agency = "Ministry of Housing and Urban Affairs (MoHUA)",
            maxAmount = 50000L,
            subsidyPercentage = "7% Interest Subsidy + up to ₹1,200 Cashback per year on UPI sales",
            interestRate = "Effective ~4.5% p.a. after interest subsidy",
            collateralRequirement = "100% Collateral-Free",
            officialCitation = "MoHUA SVANidhi Guidelines Notification F.No. K-12019(11)/1/2020-UPA-I",
            eligibilityHighlight = "Street vendors, mobile cart operators, roadside tailors, tea/snack stalls.",
            eligibleCategories = listOf("Street Vendors", "Cart Operators", "Roadside Tailors", "Village Market Sellers"),
            keyBenefits = listOf(
                "1st loan: ₹10,000 for 1 year tenure",
                "2nd loan: ₹20,000 on prompt repayment of 1st loan",
                "3rd loan: ₹50,000 on prompt repayment of 2nd loan",
                "Direct monthly cashback credited to bank account for digital UPI sales"
            ),
            documentsNeeded = listOf(
                "Aadhaar card & Mobile number",
                "Vending certificate or Letter of Recommendation (LoR) from local body or self-declaration"
            )
        )
    )
}
