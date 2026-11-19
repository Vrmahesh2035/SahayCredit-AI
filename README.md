# SahayCredit AI — Native Android App

Native Kotlin + Jetpack Compose Android port of **SahayCredit AI**, a voice-guided, multilingual credit readiness and government scheme bridge for rural MSMEs.

## Key Features
- **8-Language Voice & UI Support**: Hindi (`हिंदी`), English, Tamil (`தமிழ்`), Telugu (`తెలుగు`), Marathi (`मराठी`), Bengali (`বাংলা`), Kannada (`ಕನ್ನಡ`), and Gujarati (`ગુજરાતી`) powered by Android `TextToSpeech`, chime cues, and `SpeechRecognizer` mic input.
- **5-Step Entrepreneur Credit Wizard**:
  1. Business & village profile (6 rural trade categories, social category/gender subsidy eligibility, monthly revenue, loan slider ₹50K–₹10L).
  2. Informal cashflow & ledger entry (PhonePe/GPay QR logs, milk pour slips, handwritten khata, mandi invoices, 4 realistic presets, explicit consent check).
  3. Cash velocity & DSCR verification progress.
  4. Matched government schemes (MUDRA PMMY, PMEGP 25–35% Subsidy, PM Vishwakarma, CGTMSE 85% Guarantee) with official citations.
  5. Printable & shareable **Bank-Ready Viability Dossier** (`PrintManager` HTML print/PDF + `Intent.ACTION_SEND`).
- **Middleman Trap Calculator**: Interactive comparison of moneylender interest (18–48% p.a.), corrupt middleman commissions (5–20%), and direct 0%-fee formal credit with 35% rural subsidy.
- **Branch Manager & Bank Mitra Portal**: Local JSON persistence (`DossierRepository`), search/filter, dossier inspection modal, one-tap in-principle sanctioning, and full appraisal note viewer.
- **Field Discovery Q&A**: Bilingual explorer covering 6 core rural credit bottlenecks and measurable outcomes.

## AI Notice
Since Firebase setup was declined during generation, AI credit appraisal and voice advisor responses run through `PlaceholderAiRepository` with clearly labeled sample/calibrated outputs (`FirebaseNoticeBanner`). Enable Firebase AI Logic to connect live Gemini models.
