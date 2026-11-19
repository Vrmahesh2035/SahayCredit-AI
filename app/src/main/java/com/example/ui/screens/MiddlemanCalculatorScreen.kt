package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.TranslationsData
import com.example.model.SupportedLanguage
import com.example.ui.components.PortalFooter
import com.example.ui.theme.AmberBorder
import com.example.ui.theme.AmberSoft
import com.example.ui.theme.AmberWarm
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.RoseAlert
import com.example.ui.theme.RoseBorder
import com.example.ui.theme.RoseSoft
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.TabularMoneyStyle
import com.example.util.formatInr
import com.example.viewmodel.CalculatorUiState
import kotlin.math.roundToInt

@Composable
fun MiddlemanCalculatorScreen(
    state: CalculatorUiState,
    currentLang: SupportedLanguage,
    onLoanAmountChange: (Int) -> Unit,
    onAgentPctChange: (Int) -> Unit,
    onMoneylenderRateChange: (Int) -> Unit,
    onTenureYearsChange: (Int) -> Unit,
    onSpeakText: (String) -> Unit
) {
    val t = TranslationsData.get(currentLang)
    val ui = TranslationsData.ui(currentLang)

    val agentCut = (state.loanAmount * state.agentCommissionPct) / 100
    val moneylenderInterest = ((state.loanAmount.toDouble() * (state.moneylenderRate / 100.0)) * state.tenureYears).roundToInt()
    val moneylenderTotal = state.loanAmount + moneylenderInterest

    val formalRate = 9.0
    val formalInterest = ((state.loanAmount.toDouble() * (formalRate / 100.0)) * state.tenureYears).roundToInt()
    val ruralSubsidy35 = (state.loanAmount * 0.35).roundToInt()
    val totalMoneySaved = agentCut + (moneylenderInterest - formalInterest) + ruralSubsidy35

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header & Sliders Card
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(18.dp))
                    Text(
                        text = ui.calcPdfTag,
                        style = MaterialTheme.typography.labelSmall,
                        color = EmeraldDeep,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = ui.calcTitle,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = ui.calcSubtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Button(
                    onClick = {
                        onSpeakText(
                            buildCalcSpeech(
                                currentLang,
                                state.loanAmount,
                                agentCut,
                                moneylenderInterest,
                                ruralSubsidy35,
                                totalMoneySaved
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.heightIn(min = 46.dp).testTag("calc_audio_button")
                ) {
                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = t.listenAudio, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(t.listenAudio)
                }

                HorizontalDivider()

                // Loan Amount Slider
                SliderBlock(
                    label = ui.calcLoanLabel,
                    valueLabel = formatInr(state.loanAmount),
                    valueColor = EmeraldDeep,
                    value = state.loanAmount.toFloat(),
                    range = 50000f..2000000f,
                    activeColor = EmeraldPrimary,
                    onValueChange = { v ->
                        onLoanAmountChange(((v / 25000f).roundToInt() * 25000).coerceIn(50000, 2000000))
                    },
                    tag = "calc_loan_slider"
                )

                // Agent Commission Slider
                SliderBlock(
                    label = ui.calcAgentCutLabel,
                    valueLabel = "${state.agentCommissionPct}% (${formatInr(agentCut)})",
                    valueColor = AmberWarm,
                    value = state.agentCommissionPct.toFloat(),
                    range = 5f..20f,
                    activeColor = AmberWarm,
                    onValueChange = { onAgentPctChange(it.roundToInt().coerceIn(5, 20)) },
                    tag = "calc_agent_slider"
                )

                // Moneylender Rate Slider
                SliderBlock(
                    label = ui.calcMoneylenderRateLabel,
                    valueLabel = "${state.moneylenderRate}${ui.calcPerAnnumSuffix}",
                    valueColor = RoseAlert,
                    value = state.moneylenderRate.toFloat(),
                    range = 18f..48f,
                    activeColor = RoseAlert,
                    onValueChange = { onMoneylenderRateChange(((it / 2f).roundToInt() * 2).coerceIn(18, 48)) },
                    tag = "calc_moneylender_slider"
                )

                // Tenure Pills
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = ui.calcTenureLabel,
                        style = MaterialTheme.typography.labelLarge
                    )
                    listOf(1, 3, 5).forEach { yr ->
                        val selected = state.tenureYears == yr
                        Surface(
                            onClick = { onTenureYearsChange(yr) },
                            shape = RoundedCornerShape(10.dp),
                            color = if (selected) EmeraldDeep else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.testTag("calc_tenure_$yr")
                        ) {
                            Text(
                                text = "$yr ${ui.calcYrSuffix}",
                                color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        // Option 1: Village Moneylender
        ComparisonCard(
            badge = ui.calcOpt1Badge,
            title = ui.calcOpt1Title,
            accentColor = RoseAlert,
            borderColor = RoseBorder,
            containerColor = MaterialTheme.colorScheme.surface,
            highlightBg = RoseSoft,
            rows = listOf(
                ui.calcPrincipalLabel to formatInr(state.loanAmount),
                String.format(ui.calcTotalInterestFormat, state.tenureYears) to "+${formatInr(moneylenderInterest)}",
                ui.calcGovtSubsidyLabel to ui.calcZeroValue
            ),
            summaryLabel = ui.calcOpt1SummaryLabel,
            summaryValue = formatInr(moneylenderTotal),
            footerNote = ui.calcOpt1Footer
        )

        // Option 2: Corrupt Middleman
        ComparisonCard(
            badge = ui.calcOpt2Badge,
            title = ui.calcOpt2Title,
            accentColor = AmberWarm,
            borderColor = AmberBorder,
            containerColor = MaterialTheme.colorScheme.surface,
            highlightBg = AmberSoft,
            rows = listOf(
                ui.calcSanctionedLoanLabel to formatInr(state.loanAmount),
                ui.calcAgentCutTakenLabel to "-${formatInr(agentCut)}",
                ui.calcActualCapitalLabel to formatInr(state.loanAmount - agentCut)
            ),
            summaryLabel = ui.calcOpt2SummaryLabel,
            summaryValue = ui.calcOpt2DelayValue,
            footerNote = ui.calcOpt2Footer
        )

        // Option 3: SahayCredit Direct File
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = EmeraldSoft,
            border = BorderStroke(2.dp, EmeraldPrimary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = ui.calcOpt3Badge,
                        style = MaterialTheme.typography.labelSmall,
                        color = EmeraldDeep,
                        fontWeight = FontWeight.Bold
                    )
                    Surface(shape = RoundedCornerShape(50.dp), color = EmeraldPrimary) {
                        Text(
                            text = ui.calcOpt3RecommendedChip,
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }

                Text(
                    text = ui.calcOpt3Title,
                    style = MaterialTheme.typography.titleLarge,
                    color = EmeraldDeep
                )

                MetricLine(ui.calcMiddlemanFeeLabel, ui.calcZeroValue)
                MetricLine(ui.calcBankRateLabel, "5% - 9.5% ${ui.calcPerAnnumSuffix.trimStart('%').trim()}")
                MetricLine(ui.calcRuralSubsidyLabel, "+${formatInr(ruralSubsidy35)}")

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = EmeraldPrimary,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = ui.calcTotalSavingsLabel,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                        Text(
                            text = formatInr(totalMoneySaved),
                            style = TabularMoneyStyle.merge(MaterialTheme.typography.titleLarge),
                            color = Color.White
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(16.dp))
                    Text(
                        text = ui.calcOpt3Footer,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // Field Insight Dark Box
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = SlateTextPrimary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.AutoMirrored.Filled.HelpOutline, contentDescription = null, tint = EmeraldPrimary)
                    Text(
                        text = ui.calcInsightTitle,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
                Text(
                    text = ui.calcInsightBody,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.85f)
                )
            }
        }

        PortalFooter(currentLang = currentLang)
    }
}

@Composable
private fun SliderBlock(
    label: String,
    valueLabel: String,
    valueColor: Color,
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    activeColor: Color,
    onValueChange: (Float) -> Unit,
    tag: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, style = MaterialTheme.typography.labelLarge)
            Text(
                text = valueLabel,
                style = TabularMoneyStyle.merge(MaterialTheme.typography.titleMedium),
                color = valueColor
            )
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = range,
            colors = SliderDefaults.colors(thumbColor = activeColor, activeTrackColor = activeColor),
            modifier = Modifier.testTag(tag)
        )
    }
}

@Composable
private fun ComparisonCard(
    badge: String,
    title: String,
    accentColor: Color,
    borderColor: Color,
    containerColor: Color,
    highlightBg: Color,
    rows: List<Pair<String, String>>,
    summaryLabel: String,
    summaryValue: String,
    footerNote: String
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = containerColor,
        border = BorderStroke(1.dp, borderColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = badge, style = MaterialTheme.typography.labelSmall, color = accentColor, fontWeight = FontWeight.Bold)
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            rows.forEach { (k, v) -> MetricLine(k, v) }
            Surface(shape = RoundedCornerShape(10.dp), color = highlightBg, modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = summaryLabel, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                    Text(text = summaryValue, style = TabularMoneyStyle, color = accentColor)
                }
            }
            Text(text = footerNote, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun MetricLine(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, style = TabularMoneyStyle.merge(MaterialTheme.typography.bodyMedium))
    }
}

private fun buildCalcSpeech(
    lang: SupportedLanguage,
    loanAmount: Int,
    agentCut: Int,
    moneylenderInterest: Int,
    ruralSubsidy35: Int,
    totalMoneySaved: Int
): String {
    val l = formatInr(loanAmount)
    val a = formatInr(agentCut)
    val m = formatInr(moneylenderInterest)
    val r = formatInr(ruralSubsidy35)
    val s = formatInr(totalMoneySaved)
    return when (lang) {
        SupportedLanguage.HI -> "अगर आप $l का लोन लेते हैं, तो दलाल आपसे $a का कमीशन काट लेता है, और साहूकार $m का भारी ब्याज वसूल लेता है। सरकारी योजना में शून्य दलाली है और $r तक की सब्सिडी मिल सकती है। आप कुल $s बचा रहे हैं!"
        SupportedLanguage.TA -> "நீங்கள் $l கடன் வாங்கினால், இடைத்தரகர் $a கமிஷன், வட்டிக்காரர் $m வட்டி பறித்துக்கொள்வர். அரசு திட்டத்தில் 0% கமிஷன் மற்றும் $r மானியம் மூலம் மொத்தம் $s சேமிக்கலாம்!"
        SupportedLanguage.TE -> "మీరు $l లోన్ తీసుకుంటే, దళారీ $a కమీషన్, వడ్డీ వ్యాపారి $m వడ్డీ తీసుకుంటారు. ప్రభుత్వ పథకంలో ₹0 కమీషన్ మరియు $r సబ్సిడీతో మొత్తం $s ఆదా అవుతుంది!"
        SupportedLanguage.MR -> "जर तुम्ही $l चे कर्ज घेतले, तर दलाल $a कमिशन आणि सावकार $m व्याज कापतो. सरकारी योजनेत शून्य दलाली व $r अनुदान मिळून एकूण $s बचत होते!"
        SupportedLanguage.BN -> "যদি আপনি $l ঋণ নেন, দালাল $a কেটে নেয় এবং মহাজন $m সুদ নেয়। সরকারি প্রকল্পে শূন্য খরচে $r অনুদান সহ মোট $s সঞ্চয় হয়!"
        SupportedLanguage.KN -> "ನೀವು $l ಸಾಲ ಪಡೆದರೆ, ಮಧ್ಯವರ್ತಿ $a ಕಮಿಷನ್ ಮತ್ತು ಲೇವಾದೇವಿಗಾರ $m ಬಡ್ಡಿ ಪಡೆಯುತ್ತಾರೆ. ಸರ್ಕಾರಿ ಯೋಜನೆಯಲ್ಲಿ ಶೂನ್ಯ ದಲ್ಲಾಳಿ ಮತ್ತು $r ಸಬ್ಸಿಡಿ ಸೇರಿದಂತೆ ಒಟ್ಟು $s ಉಳಿತಾಯವಾಗುತ್ತದೆ!"
        SupportedLanguage.GU -> "જો તમે $l ની લોન લો છો, તો દલાલ $a કમિશન અને શાહુકાર $m વ્યાજ વસૂલે છે. સરકારી યોજનામાં શૂન્ય દલાલી અને $r સબસિડી સાથે કુલ $s બચશે!"
        SupportedLanguage.EN -> "For a loan of $l, a middleman takes $a in cuts, and a moneylender charges $m in interest. Under official schemes with $r subsidy you save $s!"
    }
}
