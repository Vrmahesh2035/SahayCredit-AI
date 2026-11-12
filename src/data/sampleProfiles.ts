import { InformalRecordPreset } from '../types';

export const SAMPLE_PROFILES: InformalRecordPreset[] = [
  {
    id: 'ramesh-tailor',
    title: 'दर्जी रमेश - सिलाई केंद्र (Ramesh Tailoring Works)',
    businessType: 'tailor',
    recordType: 'UPI_QR',
    ownerName: 'Ramesh Kumar Solanki',
    village: 'Pipariya Kalan',
    district: 'Hoshangabad (Narmadapuram)',
    state: 'Madhya Pradesh',
    socialCategory: 'OBC',
    gender: 'Male',
    requestedLoanAmount: 180000,
    statedMonthlySales: 58000,
    summary: '3 mechanical sewing machines, steady daily QR code payments from villagers for festival suits and school uniforms.',
    sampleContent: `--- KHATA & UPI SUMMARY: RAMESH TAILORING WORKS ---
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
Past Credit Experience: Went to local middleman who demanded ₹25,000 advance bribe to get bank loan. Ramesh refused and stalled.`,
  },
  {
    id: 'kamla-dairy',
    title: 'कमला देवी - दुग्ध उत्पादन केंद्र (Kamla Devi Dairy Unit)',
    businessType: 'dairy',
    recordType: 'MILK_SLIP',
    ownerName: 'Kamla Devi Ahirwar',
    village: 'Chandanpur',
    district: 'Bareilly',
    state: 'Uttar Pradesh',
    socialCategory: 'SC',
    gender: 'Female',
    requestedLoanAmount: 250000,
    statedMonthlySales: 64000,
    summary: '4 milch cows, daily 34-38 liters cooperative milk pour record, weekly direct bank credit from dairy federation.',
    sampleContent: `--- COOPERATIVE MILK COLLECTION SLIP SUMMARY ---
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
Eligibility Potential: PMEGP 35% Special Woman/SC Subsidy + MUDRA Kishore.`,
  },
  {
    id: 'shankar-fabrication',
    title: 'शंकर वेल्डिंग व कृषि यंत्र (Shankar Welding & Fabrication)',
    businessType: 'fabrication',
    recordType: 'KHATA_BAHI',
    ownerName: 'Shankar Lal Panchal',
    village: 'Sikandrabad Dehat',
    district: 'Bulandshahr',
    state: 'Uttar Pradesh',
    socialCategory: 'OBC',
    gender: 'Male',
    requestedLoanAmount: 400000,
    statedMonthlySales: 125000,
    summary: 'Welds tractor trolleys, cultivator blades, iron window gates; has raw material supplier bills and steady UPI credits.',
    sampleContent: `--- HANDWRITTEN LEDGER & SUPPLIER INVOICE RECORD ---
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
Bank Barrier: Branch Manager asked for 3 years ITR & Audited balance sheet, which Shankar never had. Moneylender offered loan at 3% per month (36% annual interest)!`,
  },
  {
    id: 'radha-shg',
    title: 'राधा महिला स्वयं सहायता समूह (Radha SHG Spices & Food)',
    businessType: 'shg_enterprise',
    recordType: 'KHATA_BAHI',
    ownerName: 'Radha Bai & 9 SHG Members',
    village: 'Kalyanpur',
    district: 'Ranchi',
    state: 'Jharkhand',
    socialCategory: 'ST',
    gender: 'Female',
    requestedLoanAmount: 500000,
    statedMonthlySales: 110000,
    summary: '10 rural tribal women producing ground spices, papad, and mustard oil with verified SHG federation grading grade "A".',
    sampleContent: `--- NRLM SELF-HELP GROUP (SHG) RECORD & PASSBOOK ---
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
Key Government Entitlement: PMEGP 35% Capital Subsidy for Rural ST Women Group + NABARD NRLM Interest Subvention.`,
  },
];
