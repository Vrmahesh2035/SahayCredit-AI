package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.TranslationsData
import com.example.data.strings.UiStrings
import com.example.model.BusinessCategory
import com.example.model.CreditAnalysisResult
import com.example.model.SupportedLanguage
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldDarkest
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.TabularMoneyStyle
import com.example.util.formatInr
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun BankDossierScreen(
    analysis: CreditAnalysisResult,
    borrowerName: String,
    businessName: String,
    businessType: String,
    village: String,
    district: String,
    state: String,
    socialCategory: String,
    gender: String,
    requestedLoanAmount: Int,
    currentLang: SupportedLanguage,
    onBackToEdit: () -> Unit,
    onSendToBranchManager: () -> Unit,
    onSpeakDossier: (String) -> Unit
) {
    val context = LocalContext.current
    val t = TranslationsData.get(currentLang)
    val ui = TranslationsData.ui(currentLang)
    val appNumber = remember(borrowerName, requestedLoanAmount) {
        "SC-MSME-${LocalDate.now().year}-${(100000..999999).random()}"
    }
    val appraisalDate = remember {
        LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
    }
    val localizedTrade = BusinessCategory.entries
        .firstOrNull { it.id == businessType }
        ?.let { t.businessTypes[it] }
        ?: businessType.replace('_', ' ').uppercase()

    val dossierSpeech = buildDossierSpeech(analysis, currentLang)

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Action Bar
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = onBackToEdit,
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                        modifier = Modifier
                            .heightIn(min = 42.dp)
                            .testTag("dossier_edit_button")
                    ) {
                        Text(
                            text = ui.dossierEditButton,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    Text(
                        text = ui.dossierReadyBadge,
                        style = MaterialTheme.typography.labelLarge,
                        color = EmeraldPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { onSpeakDossier(dossierSpeech) },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 46.dp)
                            .testTag("dossier_audio_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = t.listenAudio,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(text = t.listenAudio, style = MaterialTheme.typography.labelSmall)
                    }

                    OutlinedButton(
                        onClick = {
                            printDossierHtml(
                                context = context,
                                ui = ui,
                                appNumber = appNumber,
                                appraisalDate = appraisalDate,
                                borrowerName = borrowerName,
                                businessName = businessName,
                                businessType = localizedTrade,
                                village = village,
                                district = district,
                                state = state,
                                socialCategory = socialCategory,
                                gender = gender,
                                requestedLoanAmount = requestedLoanAmount,
                                analysis = analysis
                            )
                        },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 46.dp)
                            .testTag("dossier_print_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Print,
                            contentDescription = t.labels.printDossier,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(text = t.labels.printDossier, style = MaterialTheme.typography.labelSmall)
                    }

                    OutlinedButton(
                        onClick = {
                            shareDossierText(
                                context = context,
                                ui = ui,
                                appNumber = appNumber,
                                borrowerName = borrowerName,
                                businessName = businessName,
                                village = village,
                                district = district,
                                analysis = analysis
                            )
                        },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
                        modifier = Modifier
                            .heightIn(min = 46.dp)
                            .testTag("dossier_share_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = ui.shareChooserTitle,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Button(
                    onClick = onSendToBranchManager,
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                        .testTag("send_to_branch_manager_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBalance,
                        contentDescription = ui.sendToBranchManagerButton,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = ui.sendToBranchManagerButton,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }

        if (analysis.isPlaceholderResponse) {
            FirebaseNoticeBanner(currentLang = currentLang)
        }

        // Certified Printable Dossier Card
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outlineVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = SlateTextPrimary,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = ui.dossierComplianceBadge,
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                        Text(
                            text = "${ui.dossierAppIdPrefix}$appNumber",
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Text(
                        text = ui.dossierFormalHeading,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = String.format(ui.dossierDateConsentLine, appraisalDate),
                        style = MaterialTheme.typography.labelSmall,
                        color = EmeraldPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                HorizontalDivider(thickness = 1.5.dp, color = SlateTextPrimary)

                // Section 1: Enterprise Profile
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Business,
                            contentDescription = null,
                            tint = EmeraldPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = ui.dossierSec1Title,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            ProfileRow(ui.labelPromoter, borrowerName)
                            ProfileRow(ui.labelEnterprise, businessName)
                            ProfileRow(ui.labelTrade, localizedTrade)
                            ProfileRow(ui.labelCategoryGender, "$socialCategory · $gender")
                            ProfileRow(ui.labelLocation, "$village, $district, $state")
                            ProfileRow(ui.labelRequestedCredit, formatInr(requestedLoanAmount))
                            ProfileRow(ui.labelAssessedLimit, formatInr(analysis.recommendedLoanAmount))
                        }
                    }
                }

                // Section 2: Reconstructed Financial Viability
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = EmeraldPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = ui.dossierSec2Title,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        MetricBox(
                            label = ui.labelMonthlyInflow,
                            value = formatInr(analysis.estimatedMonthlyInflow),
                            sub = ui.subUpiSlips,
                            highlight = false,
                            modifier = Modifier.weight(1f)
                        )
                        MetricBox(
                            label = ui.labelOperatingCost,
                            value = formatInr(analysis.estimatedMonthlyOperatingExpense),
                            sub = ui.subMaterialsRent,
                            highlight = false,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        MetricBox(
                            label = ui.labelNetSurplus,
                            value = formatInr(analysis.netMonthlySurplus),
                            sub = ui.subEmiServicing,
                            highlight = true,
                            modifier = Modifier.weight(1f)
                        )
                        MetricBox(
                            label = ui.labelDscrCoverage,
                            value = "${String.format("%.2f", analysis.dscr)}x",
                            sub = ui.subBenchmark,
                            highlight = true,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                // Section 3: Explainability
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = ui.dossierSec3Title,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Surface(
                                color = EmeraldPrimary,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "${analysis.riskScore}/100 (${analysis.riskLevel})",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                        Text(
                            text = analysis.explainabilitySummary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                // Section 4: Scheme Entitlements
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = ui.dossierSec4Title,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    analysis.schemeMatches.forEachIndexed { idx, scheme ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "${idx + 1}. ${scheme.schemeName}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = scheme.subsidyOrBenefit,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = EmeraldPrimary
                                )
                                Text(
                                    text = "${ui.citationPrefix}${scheme.officialCitation}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = scheme.whyEligible,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                // Section 5: Branch Checklist
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = ui.dossierSec5Title,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    analysis.branchManagerChecklist.forEach { item ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = EmeraldPrimary,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(top = 2.dp)
                            )
                            Text(text = item, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }

                // Section 6: Digital Consent Declaration
                HorizontalDivider()
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = ui.consentTitle,
                        tint = EmeraldPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = ui.dossierConsentDeclaration,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun MetricBox(
    label: String,
    value: String,
    sub: String,
    highlight: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (highlight) EmeraldSoft else MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, if (highlight) EmeraldContainer else MaterialTheme.colorScheme.outline),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = if (highlight) EmeraldDeep else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = TabularMoneyStyle.merge(MaterialTheme.typography.titleLarge),
                color = if (highlight) EmeraldDarkest else SlateTextPrimary
            )
            Text(
                text = sub,
                style = MaterialTheme.typography.labelSmall,
                color = if (highlight) EmeraldPrimary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun buildDossierSpeech(analysis: CreditAnalysisResult, lang: SupportedLanguage): String {
    val inflow = formatInr(analysis.estimatedMonthlyInflow)
    val surplus = formatInr(analysis.netMonthlySurplus)
    val dscr = String.format("%.2f", analysis.dscr)
    return when (lang) {
        SupportedLanguage.HI ->
            "आपकी बैंक अप्रेजल रिपोर्ट तैयार है। आपका सत्यापित मासिक कारोबार $inflow है, और शुद्ध मासिक बचत $surplus है। आपकी किस्त चुकाने की क्षमता (DSCR) $dscr है, जो बैंक मानक 1.3 से काफी बेहतर है।"
        SupportedLanguage.TA ->
            "உங்கள் வங்கி கடன் அறிக்கை தயாராக உள்ளது. உங்களின் மாத வரவு $inflow மற்றும் நிகர சேமிப்பு $surplus. நீங்கள் முத்ரா மற்றும் PMEGP திட்டங்களுக்கு தகுதியானவர்."
        SupportedLanguage.TE ->
            "మీ బ్యాంక్ లోన్ నివేదిక సిద్ధమైంది. మీ నెలవారీ రాబడి $inflow, నికర మిగులు $surplus. మీ చెల్లింపు సామర్థ్యం $dscr గా ఉంది."
        SupportedLanguage.MR ->
            "आपला बँक अहवाल तयार आहे. आपला मासिक व्यवसाय $inflow असून शुद्ध मासिक नफा $surplus आहे. आपण मुद्रा व PMEGP योजनेसाठी पात्र आहात."
        SupportedLanguage.BN ->
            "আপনার ব্যাঙ্ক ডসিয়ার প্রস্তুত হয়েছে। আপনার মাসিক আয় $inflow এবং সঞ্চয় $surplus। আপনি মুদ্রা ও পিএমইজিপি প্রকল্পের যোগ্য।"
        SupportedLanguage.KN ->
            "ನಿಮ್ಮ ಬ್ಯಾಂಕ್ ವರದಿ ಸಿದ್ಧವಾಗಿದೆ. ಮಾಸಿಕ ವಹಿವಾಟು $inflow ಮತ್ತು ನಿವ್ವಳ ಉಳಿತಾಯ $surplus."
        SupportedLanguage.GU ->
            "તમારો બેંક રિપોર્ટ તૈયાર છે. તમારી માસિક આવક $inflow અને ચોખ્ખો નફો $surplus છે."
        SupportedLanguage.EN ->
            "Your Bank-Ready Viability Dossier is ready. Verified monthly inflow is $inflow, with a net monthly surplus of $surplus and DSCR of $dscr."
    }
}

private fun shareDossierText(
    context: Context,
    ui: UiStrings,
    appNumber: String,
    borrowerName: String,
    businessName: String,
    village: String,
    district: String,
    analysis: CreditAnalysisResult
) {
    val text = buildString {
        appendLine("SahayCredit AI - ${ui.dossierFormalHeading} ($appNumber)")
        appendLine("${ui.labelPromoter}: $borrowerName | ${ui.labelEnterprise}: $businessName")
        appendLine("${ui.labelLocation}: $village, $district")
        appendLine("${ui.labelMonthlyInflow}: ${formatInr(analysis.estimatedMonthlyInflow)} | ${ui.labelNetSurplus}: ${formatInr(analysis.netMonthlySurplus)}")
        appendLine("${ui.labelAssessedLimit}: ${formatInr(analysis.recommendedLoanAmount)} | ${ui.labelDscrCoverage}: ${String.format("%.2f", analysis.dscr)}x | ${ui.metricScoreLabel}: ${analysis.riskScore}/100")
        appendLine("${ui.dossierSec4Title}: ${analysis.schemeMatches.firstOrNull()?.schemeName ?: "MUDRA"}")
        appendLine("${ui.creditNoteSummaryTitle} ${analysis.explainabilitySummary}")
    }
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "SahayCredit AI - $appNumber")
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, ui.shareChooserTitle))
}

private fun printDossierHtml(
    context: Context,
    ui: UiStrings,
    appNumber: String,
    appraisalDate: String,
    borrowerName: String,
    businessName: String,
    businessType: String,
    village: String,
    district: String,
    state: String,
    socialCategory: String,
    gender: String,
    requestedLoanAmount: Int,
    analysis: CreditAnalysisResult
) {
    val webView = WebView(context)
    val html = """
        <html><body style="font-family:sans-serif;padding:24px;color:#0f172a;">
        <h2>${ui.dossierFormalHeading}</h2>
        <p><strong>${ui.dossierAppIdPrefix}</strong> $appNumber | ${String.format(ui.dossierDateConsentLine, appraisalDate)} | <strong>${ui.dossierComplianceBadge}</strong></p>
        <hr/>
        <h3>${ui.dossierSec1Title}</h3>
        <p>${ui.labelPromoter}: $borrowerName | ${ui.labelEnterprise}: $businessName ($businessType)<br/>
        ${ui.labelCategoryGender}: $socialCategory · $gender | ${ui.labelLocation}: $village, $district, $state<br/>
        ${ui.labelRequestedCredit}: ${formatInr(requestedLoanAmount)} | ${ui.labelAssessedLimit}: ${formatInr(analysis.recommendedLoanAmount)}</p>
        <h3>${ui.dossierSec2Title}</h3>
        <p>${ui.labelMonthlyInflow}: ${formatInr(analysis.estimatedMonthlyInflow)} | ${ui.labelOperatingCost}: ${formatInr(analysis.estimatedMonthlyOperatingExpense)}<br/>
        ${ui.labelNetSurplus}: ${formatInr(analysis.netMonthlySurplus)} | ${ui.labelDscrCoverage}: ${String.format("%.2f", analysis.dscr)}x | ${ui.metricScoreLabel}: ${analysis.riskScore}/100</p>
        <h3>${ui.dossierSec3Title}</h3>
        <p>${analysis.explainabilitySummary}</p>
        </body></html>
    """.trimIndent()
    webView.webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            val printManager = context.getSystemService(Context.PRINT_SERVICE) as? PrintManager
            val adapter = view?.createPrintDocumentAdapter("SahayCredit_$appNumber")
            if (printManager != null && adapter != null) {
                printManager.print("SahayCredit_Dossier_$appNumber", adapter, PrintAttributes.Builder().build())
            }
        }
    }
    webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
}
