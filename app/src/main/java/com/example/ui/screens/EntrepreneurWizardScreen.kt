package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.audio.AudioPlaybackState
import com.example.data.TranslationsData
import com.example.model.BusinessCategory
import com.example.model.Gender
import com.example.model.InformalRecordPreset
import com.example.model.RecordType
import com.example.model.SocialCategory
import com.example.model.SupportedLanguage
import com.example.ui.components.BankDossierScreen
import com.example.ui.components.PortalFooter
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.EmeraldDarkest
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.viewmodel.WizardUiState

@Composable
fun EntrepreneurWizardScreen(
    wizardState: WizardUiState,
    currentLang: SupportedLanguage,
    audioState: AudioPlaybackState,
    onToggleHeroAudio: () -> Unit,
    onOpenVoiceAssistant: () -> Unit,
    onSpeakStepAudio: (Int) -> Unit,
    onSetStep: (Int) -> Unit,
    onSelectBusinessType: (BusinessCategory) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onBusinessNameChange: (String) -> Unit,
    onVillageChange: (String) -> Unit,
    onDistrictChange: (String) -> Unit,
    onCategoryChange: (SocialCategory) -> Unit,
    onGenderChange: (Gender) -> Unit,
    onLoanAmountChange: (Int) -> Unit,
    onMonthlySalesChange: (Int) -> Unit,
    onSelectPreset: (InformalRecordPreset) -> Unit,
    onRecordTypeChange: (RecordType) -> Unit,
    onRecordContentChange: (String) -> Unit,
    onConsentChange: (Boolean) -> Unit,
    onRunAnalysis: () -> Unit,
    onFinalizeDossier: () -> Unit,
    onNavigateToPortal: () -> Unit,
    onSpeakText: (String) -> Unit
) {
    val t = TranslationsData.get(currentLang)
    val ui = TranslationsData.ui(currentLang)

    BackHandler(enabled = wizardState.currentStep > 1) {
        val target = when (wizardState.currentStep) {
            5 -> 4
            4 -> 2
            3 -> 2
            else -> 1
        }
        onSetStep(target)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Welcome Banner
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = EmeraldDeep,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            colors = listOf(EmeraldDeep, EmeraldDarkest)
                        )
                    )
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = EmeraldAccent,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = t.badge,
                        style = MaterialTheme.typography.labelSmall,
                        color = EmeraldAccent,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = ui.heroTitle,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )

                Text(
                    text = ui.heroSubtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = EmeraldSoft
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onToggleHeroAudio,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = EmeraldDarkest
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .testTag("hero_listen_audio_button")
                    ) {
                        Icon(
                            imageVector = if (audioState.isSpeaking) Icons.Default.VolumeOff else Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = t.listenAudio,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = if (audioState.isSpeaking) t.stopAudio else t.listenAudio,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    OutlinedButton(
                        onClick = onOpenVoiceAssistant,
                        border = BorderStroke(1.dp, EmeraldAccent),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = EmeraldPrimary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .testTag("hero_ask_assistant_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = t.askAssistant,
                            tint = EmeraldAccent,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(text = t.askAssistant, style = MaterialTheme.typography.labelLarge)
                    }
                }

                // Rural Credit Pillars Strip
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color.White.copy(alpha = 0.08f),
                    border = BorderStroke(1.dp, EmeraldAccent.copy(alpha = 0.25f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = ui.pillarsTitle,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        PillarRow(ui.pillarNoItr)
                        PillarRow(ui.pillarZeroFee)
                        PillarRow(ui.pillarSubsidy)
                        PillarRow(ui.pillarNoCollateral)
                    }
                }
            }
        }

        // 5-Step Progress Stepper
        Surface(
            shape = RoundedCornerShape(16.dp),
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = String.format(ui.stepFlowFormat, wizardState.currentStep),
                        style = MaterialTheme.typography.labelLarge,
                        color = EmeraldDeep
                    )
                    OutlinedButton(
                        onClick = { onSpeakStepAudio(wizardState.currentStep) },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.heightIn(min = 36.dp).testTag("step_audio_button")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = t.listenAudio, modifier = Modifier.size(14.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(t.listenAudio, style = MaterialTheme.typography.labelSmall)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val stepTitles = listOf(
                        1 to t.steps.businessProfile,
                        2 to t.steps.informalProof,
                        3 to t.steps.aiAnalysis,
                        4 to t.steps.schemeMatches,
                        5 to t.steps.bankDossier
                    )
                    stepTitles.forEach { (stepNum, _) ->
                        val isActive = wizardState.currentStep == stepNum
                        val isPast = stepNum < wizardState.currentStep
                        val canClick = isPast || (wizardState.analysisResult != null && stepNum <= 4)
                        Surface(
                            onClick = {
                                if (canClick) {
                                    onSetStep(stepNum)
                                    onSpeakStepAudio(stepNum)
                                }
                            },
                            enabled = canClick || isActive,
                            shape = RoundedCornerShape(10.dp),
                            color = when {
                                isActive -> EmeraldDeep
                                isPast -> EmeraldSoft
                                else -> MaterialTheme.colorScheme.surfaceVariant
                            },
                            border = BorderStroke(
                                1.dp,
                                if (isPast) EmeraldPrimary.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outline
                            ),
                            modifier = Modifier.weight(1f).testTag("wizard_stepper_$stepNum")
                        ) {
                            Box(
                                modifier = Modifier.padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(
                                            when {
                                                isActive -> Color.White
                                                isPast -> EmeraldPrimary
                                                else -> MaterialTheme.colorScheme.outlineVariant
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (isPast) "✓" else stepNum.toString(),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = when {
                                            isActive -> EmeraldDeep
                                            isPast -> Color.White
                                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Active Step Body
        when (wizardState.currentStep) {
            1 -> WizardStep1BusinessProfile(
                state = wizardState,
                currentLang = currentLang,
                onSelectBusinessType = onSelectBusinessType,
                onOwnerNameChange = onOwnerNameChange,
                onBusinessNameChange = onBusinessNameChange,
                onVillageChange = onVillageChange,
                onDistrictChange = onDistrictChange,
                onCategoryChange = onCategoryChange,
                onGenderChange = onGenderChange,
                onLoanAmountChange = onLoanAmountChange,
                onMonthlySalesChange = onMonthlySalesChange,
                onNextStep = {
                    onSetStep(2)
                    onSpeakStepAudio(2)
                }
            )
            2 -> WizardStep2InformalRecords(
                state = wizardState,
                currentLang = currentLang,
                onSelectPreset = onSelectPreset,
                onRecordTypeChange = onRecordTypeChange,
                onRecordContentChange = onRecordContentChange,
                onConsentChange = onConsentChange,
                onBack = { onSetStep(1) },
                onAnalyze = onRunAnalysis
            )
            3 -> WizardStep3Analyzing(state = wizardState, currentLang = currentLang)
            4 -> wizardState.analysisResult?.let { analysis ->
                WizardStep4Schemes(
                    analysis = analysis,
                    currentLang = currentLang,
                    onSpeakText = onSpeakText,
                    onBackToRecords = { onSetStep(2) },
                    onGenerateDossier = onFinalizeDossier
                )
            }
            5 -> wizardState.analysisResult?.let { analysis ->
                BankDossierScreen(
                    analysis = analysis,
                    borrowerName = wizardState.ownerName,
                    businessName = wizardState.businessName,
                    businessType = wizardState.businessType.id,
                    village = wizardState.village,
                    district = wizardState.district,
                    state = wizardState.state,
                    socialCategory = ui.socialCategoryLabels[wizardState.socialCategory] ?: wizardState.socialCategory.label,
                    gender = ui.genderLabels[wizardState.gender] ?: wizardState.gender.label,
                    requestedLoanAmount = wizardState.requestedLoanAmount,
                    currentLang = currentLang,
                    onBackToEdit = { onSetStep(1) },
                    onSendToBranchManager = onNavigateToPortal,
                    onSpeakDossier = onSpeakText
                )
            }
        }

        PortalFooter(currentLang = currentLang)
    }
}

@Composable
private fun PillarRow(text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = EmeraldAccent,
            modifier = Modifier.size(15.dp).padding(top = 2.dp)
        )
        Text(text = text, style = MaterialTheme.typography.labelSmall, color = EmeraldSoft)
    }
}
