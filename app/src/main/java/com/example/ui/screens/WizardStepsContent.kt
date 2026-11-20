package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.SampleProfilesData
import com.example.data.SampleProfilesLocalization
import com.example.data.TranslationsData
import com.example.model.BusinessCategory
import com.example.model.CreditAnalysisResult
import com.example.model.Gender
import com.example.model.InformalRecordPreset
import com.example.model.RecordType
import com.example.model.SocialCategory
import com.example.model.SupportedLanguage
import com.example.ui.components.FirebaseNoticeBanner
import com.example.ui.theme.AmberBorder
import com.example.ui.theme.AmberSoft
import com.example.ui.theme.AmberWarm
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.TabularMoneyStyle
import com.example.util.formatInr
import com.example.viewmodel.WizardUiState
import kotlin.math.roundToInt

@Composable
fun WizardStep1BusinessProfile(
    state: WizardUiState,
    currentLang: SupportedLanguage,
    onSelectBusinessType: (BusinessCategory) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onBusinessNameChange: (String) -> Unit,
    onVillageChange: (String) -> Unit,
    onDistrictChange: (String) -> Unit,
    onCategoryChange: (SocialCategory) -> Unit,
    onGenderChange: (Gender) -> Unit,
    onLoanAmountChange: (Int) -> Unit,
    onMonthlySalesChange: (Int) -> Unit,
    onNextStep: () -> Unit
) {
    val t = TranslationsData.get(currentLang)
    val ui = TranslationsData.ui(currentLang)
    var categoryExpanded by remember { mutableStateOf(false) }
    var genderExpanded by remember { mutableStateOf(false) }

    val cards = listOf(
        Triple(BusinessCategory.TAILOR, Icons.Default.ContentCut, ui.businessDescriptions[BusinessCategory.TAILOR].orEmpty()),
        Triple(BusinessCategory.DAIRY, Icons.Default.LocalDrink, ui.businessDescriptions[BusinessCategory.DAIRY].orEmpty()),
        Triple(BusinessCategory.FABRICATION, Icons.Default.Build, ui.businessDescriptions[BusinessCategory.FABRICATION].orEmpty()),
        Triple(BusinessCategory.SHG_ENTERPRISE, Icons.Default.Groups, ui.businessDescriptions[BusinessCategory.SHG_ENTERPRISE].orEmpty()),
        Triple(BusinessCategory.KIRANA, Icons.Default.Storefront, ui.businessDescriptions[BusinessCategory.KIRANA].orEmpty()),
        Triple(BusinessCategory.VISHWAKARMA_ARTISAN, Icons.Default.Handyman, ui.businessDescriptions[BusinessCategory.VISHWAKARMA_ARTISAN].orEmpty())
    )

    Surface(
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = ui.step1Title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = ui.step1Subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // 2-column Business Category Grid
            cards.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowItems.forEach { (category, icon, desc) ->
                        val selected = state.businessType == category
                        BusinessCard(
                            label = t.businessTypes[category] ?: category.name,
                            desc = desc,
                            icon = icon,
                            selected = selected,
                            onClick = { onSelectBusinessType(category) },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("business_card_${category.id}")
                        )
                    }
                }
            }

            HorizontalDivider()

            // Owner & Location Fields
            OutlinedTextField(
                value = state.ownerName,
                onValueChange = onOwnerNameChange,
                label = { Text(ui.ownerNameLabel) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().testTag("owner_name_input")
            )
            OutlinedTextField(
                value = state.businessName,
                onValueChange = onBusinessNameChange,
                label = { Text(ui.shopNameLabel) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().testTag("business_name_input")
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = state.village,
                    onValueChange = onVillageChange,
                    label = { Text(t.labels.village) },
                    singleLine = true,
                    modifier = Modifier.weight(1f).testTag("village_input")
                )
                OutlinedTextField(
                    value = state.district,
                    onValueChange = onDistrictChange,
                    label = { Text(t.labels.district) },
                    singleLine = true,
                    modifier = Modifier.weight(1f).testTag("district_input")
                )
            }

            // Social Category & Gender Dropdowns
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        onClick = { categoryExpanded = true },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .testTag("social_category_dropdown")
                    ) {
                        Text(
                            text = "${t.labels.category.substringBefore(" ")}: ${ui.socialCategoryLabels[state.socialCategory] ?: state.socialCategory.label}",
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = t.labels.category)
                    }
                    DropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        SocialCategory.entries.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(ui.socialCategoryLabels[cat] ?: cat.label) },
                                onClick = {
                                    categoryExpanded = false
                                    onCategoryChange(cat)
                                }
                            )
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        onClick = { genderExpanded = true },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .testTag("gender_dropdown")
                    ) {
                        Text(
                            text = "${t.labels.gender}: ${ui.genderLabels[state.gender] ?: state.gender.label}",
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = t.labels.gender)
                    }
                    DropdownMenu(
                        expanded = genderExpanded,
                        onDismissRequest = { genderExpanded = false }
                    ) {
                        Gender.entries.forEach { g ->
                            DropdownMenuItem(
                                text = { Text(ui.genderLabels[g] ?: g.label) },
                                onClick = {
                                    genderExpanded = false
                                    onGenderChange(g)
                                }
                            )
                        }
                    }
                }
            }

            // Monthly Revenue Input & Loan Amount Slider
            OutlinedTextField(
                value = state.statedMonthlySales.toString(),
                onValueChange = { txt ->
                    val parsed = txt.filter { it.isDigit() }.toIntOrNull()
                    if (parsed != null) onMonthlySalesChange(parsed.coerceIn(5000, 5000000))
                    else if (txt.isEmpty()) onMonthlySalesChange(0)
                },
                label = { Text(t.labels.monthlyIncome) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().testTag("monthly_sales_input")
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = t.labels.loanAmount, style = MaterialTheme.typography.labelLarge)
                    Text(
                        text = formatInr(state.requestedLoanAmount),
                        style = TabularMoneyStyle.merge(MaterialTheme.typography.titleMedium),
                        color = EmeraldDeep
                    )
                }
                Slider(
                    value = state.requestedLoanAmount.toFloat(),
                    onValueChange = { v ->
                        val stepped = ((v / 25000f).roundToInt() * 25000).coerceIn(50000, 1000000)
                        onLoanAmountChange(stepped)
                    },
                    valueRange = 50000f..1000000f,
                    colors = SliderDefaults.colors(
                        thumbColor = EmeraldPrimary,
                        activeTrackColor = EmeraldPrimary
                    ),
                    modifier = Modifier.testTag("wizard_loan_slider")
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(ui.shishuHint, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(ui.kishoreHint, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(ui.tarunHint, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Button(
                onClick = onNextStep,
                colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp)
                    .testTag("wizard_step1_next_button")
            ) {
                Text(
                    text = ui.step1NextButton,
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
private fun BusinessCard(
    label: String,
    desc: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        color = if (selected) EmeraldSoft else MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = if (selected) 2.dp else 1.dp,
            color = if (selected) EmeraldPrimary else MaterialTheme.colorScheme.outline
        ),
        modifier = modifier.heightIn(min = 112.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = EmeraldContainer
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = EmeraldDeep,
                        modifier = Modifier.padding(6.dp).size(20.dp)
                    )
                }
                if (selected) {
                    Surface(
                        color = EmeraldContainer,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "✓",
                            color = EmeraldDeep,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WizardStep2InformalRecords(
    state: WizardUiState,
    currentLang: SupportedLanguage,
    onSelectPreset: (InformalRecordPreset) -> Unit,
    onRecordTypeChange: (RecordType) -> Unit,
    onRecordContentChange: (String) -> Unit,
    onConsentChange: (Boolean) -> Unit,
    onBack: () -> Unit,
    onAnalyze: () -> Unit
) {
    val t = TranslationsData.get(currentLang)
    val ui = TranslationsData.ui(currentLang)

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
            Text(
                text = ui.step2Title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = ui.step2Subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(text = t.labels.orChoosePreset, style = MaterialTheme.typography.labelLarge)

            SampleProfilesData.SAMPLE_PROFILES.forEach { preset ->
                val selected = state.selectedPresetId == preset.id
                Surface(
                    onClick = { onSelectPreset(preset) },
                    shape = RoundedCornerShape(12.dp),
                    color = if (selected) EmeraldSoft else MaterialTheme.colorScheme.surface,
                    border = BorderStroke(if (selected) 1.5.dp else 1.dp, if (selected) EmeraldPrimary else MaterialTheme.colorScheme.outline),
                    modifier = Modifier.fillMaxWidth().testTag("preset_${preset.id}")
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = SampleProfilesLocalization.localizedTitle(preset.id, currentLang),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = SampleProfilesLocalization.localizedSummary(preset.id, currentLang),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = String.format(
                                ui.presetSalesLoanFormat,
                                formatInr(preset.statedMonthlySales),
                                formatInr(preset.requestedLoanAmount)
                            ),
                            style = TabularMoneyStyle.merge(MaterialTheme.typography.labelSmall),
                            color = EmeraldDeep
                        )
                    }
                }
            }

            Text(
                text = ui.selectRecordTypeLabel,
                style = MaterialTheme.typography.labelLarge
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RecordType.entries.forEach { rType ->
                    val selected = state.recordType == rType
                    Surface(
                        onClick = { onRecordTypeChange(rType) },
                        shape = RoundedCornerShape(10.dp),
                        color = if (selected) EmeraldDeep else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.testTag("record_type_${rType.id}")
                    ) {
                        Text(
                            text = ui.recordTypeLabels[rType] ?: rType.displayLabel,
                            color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            OutlinedTextField(
                value = state.recordContent,
                onValueChange = onRecordContentChange,
                label = { Text(t.labels.uploadRecords) },
                minLines = 5,
                maxLines = 8,
                textStyle = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Monospace),
                modifier = Modifier.fillMaxWidth().testTag("record_content_input")
            )

            // Digital Consent Box
            Surface(
                onClick = { onConsentChange(!state.hasGivenConsent) },
                shape = RoundedCornerShape(12.dp),
                color = EmeraldSoft,
                border = BorderStroke(1.dp, EmeraldContainer),
                modifier = Modifier.fillMaxWidth().testTag("consent_checkbox_row")
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Checkbox(
                        checked = state.hasGivenConsent,
                        onCheckedChange = onConsentChange,
                        colors = CheckboxDefaults.colors(checkedColor = EmeraldPrimary)
                    )
                    Column {
                        Text(
                            text = ui.consentTitle,
                            style = MaterialTheme.typography.labelLarge,
                            color = EmeraldDeep
                        )
                        Text(
                            text = t.labels.consentStatement,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onBack,
                    modifier = Modifier.heightIn(min = 48.dp).testTag("wizard_step2_back_button")
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(ui.backLabel)
                }

                Button(
                    onClick = onAnalyze,
                    enabled = !state.isAnalyzing && state.hasGivenConsent && state.recordContent.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.heightIn(min = 48.dp).testTag("wizard_analyze_button")
                ) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(t.labels.analyzeRecords, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
fun WizardStep3Analyzing(
    state: WizardUiState,
    currentLang: SupportedLanguage
) {
    val ui = TranslationsData.ui(currentLang)
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(2.dp, EmeraldPrimary),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(shape = RoundedCornerShape(50.dp), color = EmeraldContainer) {
                Text(
                    text = ui.step3Badge,
                    color = EmeraldDeep,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            Text(
                text = ui.step3Title,
                style = MaterialTheme.typography.titleLarge
            )

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = ui.analysisProgressLabel,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "${state.analysisProgress}%",
                        style = TabularMoneyStyle,
                        color = EmeraldDeep
                    )
                }
                LinearProgressIndicator(
                    progress = { state.analysisProgress / 100f },
                    color = EmeraldPrimary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth().height(10.dp)
                )
            }

            val stages = listOf(
                1 to ui.stage1Label,
                2 to ui.stage2Label,
                3 to ui.stage3Label
            )

            stages.forEach { (idx, label) ->
                val done = state.analysisStage >= idx
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (done) EmeraldSoft else MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, if (done) EmeraldPrimary.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outline),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = if (done) EmeraldPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(text = label, style = MaterialTheme.typography.bodyMedium)
                        }
                        Text(
                            text = if (done) ui.stageDoneLabel else "...",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldDeep
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WizardStep4Schemes(
    analysis: CreditAnalysisResult,
    currentLang: SupportedLanguage,
    onSpeakText: (String) -> Unit,
    onBackToRecords: () -> Unit,
    onGenerateDossier: () -> Unit
) {
    val t = TranslationsData.get(currentLang)
    val ui = TranslationsData.ui(currentLang)

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
            if (analysis.isPlaceholderResponse) {
                FirebaseNoticeBanner(currentLang = currentLang)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = ui.step4Badge,
                        style = MaterialTheme.typography.labelSmall,
                        color = EmeraldPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = ui.step4Title,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Surface(shape = RoundedCornerShape(10.dp), color = EmeraldContainer) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Default.Shield, contentDescription = null, tint = EmeraldDeep, modifier = Modifier.size(14.dp))
                        Text(ui.zeroFeeChip, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = EmeraldDeep)
                    }
                }
            }

            // 4 Quick Metric Cards (2x2)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MetricTile(ui.metricVerifiedIncome, formatInr(analysis.estimatedMonthlyInflow), false, Modifier.weight(1f))
                MetricTile(ui.metricNetSurplus, formatInr(analysis.netMonthlySurplus), true, Modifier.weight(1f))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MetricTile(ui.metricDscrLabel, "${String.format("%.2f", analysis.dscr)}x (>1.30)", true, Modifier.weight(1f))
                MetricTile(ui.metricScoreLabel, "${analysis.riskScore}/100 (${analysis.riskLevel})", true, Modifier.weight(1f))
            }

            // Audio Explanation Callout
            val guideText = analysis.villageAudioGuideText.textFor(currentLang)
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = AmberSoft,
                border = BorderStroke(1.dp, AmberBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = guideText, style = MaterialTheme.typography.bodyMedium)
                    Button(
                        onClick = { onSpeakText(guideText) },
                        colors = ButtonDefaults.buttonColors(containerColor = AmberWarm),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.heightIn(min = 38.dp).testTag("step4_audio_explain_button")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = t.listenAudio, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(t.listenAudio, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

            Text(
                text = ui.recommendedSchemesTitle,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )

            analysis.schemeMatches.forEach { match ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = match.schemeName, style = MaterialTheme.typography.titleMedium)
                        Surface(shape = RoundedCornerShape(50.dp), color = EmeraldContainer) {
                            Text(
                                text = match.subsidyOrBenefit,
                                color = EmeraldDeep,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        Text(
                            text = "${ui.citationPrefix}${match.officialCitation}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(text = match.interestBenefit, style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = match.whyEligible,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Explainability Summary
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = ui.creditNoteSummaryTitle,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(text = analysis.explainabilitySummary, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onBackToRecords,
                    modifier = Modifier.heightIn(min = 48.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(ui.editRecordsButton)
                }

                Button(
                    onClick = onGenerateDossier,
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.heightIn(min = 48.dp).testTag("generate_dossier_button")
                ) {
                    Icon(Icons.Default.Description, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(t.labels.generateDossier, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
private fun MetricTile(
    label: String,
    value: String,
    highlight: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (highlight) EmeraldSoft else MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, if (highlight) EmeraldContainer else MaterialTheme.colorScheme.outline),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = if (highlight) EmeraldDeep else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = TabularMoneyStyle.merge(MaterialTheme.typography.titleMedium),
                color = if (highlight) EmeraldDeep else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
