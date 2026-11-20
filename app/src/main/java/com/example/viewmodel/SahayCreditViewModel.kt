package com.example.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.AndroidAudioService
import com.example.audio.AudioPlaybackState
import com.example.data.SampleProfilesData
import com.example.data.SampleProfilesLocalization
import com.example.data.TranslationsData
import com.example.model.AppTab
import com.example.model.BusinessCategory
import com.example.model.CreditAnalysisResult
import com.example.model.DossierStatus
import com.example.model.Gender
import com.example.model.InformalRecordPreset
import com.example.model.RecordType
import com.example.model.SocialCategory
import com.example.model.SubmittedDossier
import com.example.model.SupportedLanguage
import com.example.model.VoiceAssistantResponse
import com.example.repository.AiRepository
import com.example.repository.DossierRepository
import com.example.repository.PlaceholderAiRepository
import com.example.util.formatInr
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import kotlin.math.roundToInt

data class WizardUiState(
    val currentStep: Int = 1,
    val businessType: BusinessCategory = BusinessCategory.TAILOR,
    val ownerName: String = "Ramesh Kumar Solanki",
    val businessName: String = "Ramesh Tailoring Works",
    val village: String = "Pipariya Kalan",
    val district: String = "Narmadapuram",
    val state: String = "Madhya Pradesh",
    val socialCategory: SocialCategory = SocialCategory.OBC,
    val gender: Gender = Gender.MALE,
    val requestedLoanAmount: Int = 180000,
    val statedMonthlySales: Int = 62000,
    val recordType: RecordType = RecordType.UPI_QR,
    val recordContent: String = SampleProfilesData.SAMPLE_PROFILES.first().sampleContent,
    val selectedPresetId: String = "ramesh-tailor",
    val hasGivenConsent: Boolean = true,
    val isAnalyzing: Boolean = false,
    val analysisStage: Int = 1,
    val analysisProgress: Int = 20,
    val analysisResult: CreditAnalysisResult? = null,
    val errorMessage: String? = null
)

data class CalculatorUiState(
    val loanAmount: Int = 300000,
    val tenureYears: Int = 3,
    val agentCommissionPct: Int = 12,
    val moneylenderRate: Int = 30
)

data class VoiceModalUiState(
    val isOpen: Boolean = false,
    val queryText: String = "",
    val isRecording: Boolean = false,
    val isLoading: Boolean = false,
    val response: VoiceAssistantResponse? = null,
    val errorMessage: String? = null
)

data class PortalUiState(
    val filter: PortalFilter = PortalFilter.ALL,
    val searchQuery: String = "",
    val inspectedDossier: SubmittedDossier? = null
)

enum class PortalFilter { ALL, PENDING, SANCTIONED }

class SahayCreditViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = application.getSharedPreferences("sahay_prefs", Context.MODE_PRIVATE)
    private val aiRepository: AiRepository = PlaceholderAiRepository()
    private val dossierRepository = DossierRepository(application)
    val audioService = AndroidAudioService(application)

    val audioState: StateFlow<AudioPlaybackState> = audioService.state
    val dossiers: StateFlow<List<SubmittedDossier>> = dossierRepository.dossiers

    private val _currentLang = MutableStateFlow(loadSavedLanguage())
    val currentLang: StateFlow<SupportedLanguage> = _currentLang.asStateFlow()

    private val _activeTab = MutableStateFlow(AppTab.WIZARD)
    val activeTab: StateFlow<AppTab> = _activeTab.asStateFlow()

    private val _wizardState = MutableStateFlow(WizardUiState())
    val wizardState: StateFlow<WizardUiState> = _wizardState.asStateFlow()

    private val _calculatorState = MutableStateFlow(CalculatorUiState())
    val calculatorState: StateFlow<CalculatorUiState> = _calculatorState.asStateFlow()

    private val _voiceState = MutableStateFlow(VoiceModalUiState())
    val voiceState: StateFlow<VoiceModalUiState> = _voiceState.asStateFlow()

    private val _portalState = MutableStateFlow(PortalUiState())
    val portalState: StateFlow<PortalUiState> = _portalState.asStateFlow()

    private val _activeQuestionIndex = MutableStateFlow(0)
    val activeQuestionIndex: StateFlow<Int> = _activeQuestionIndex.asStateFlow()

    private fun loadSavedLanguage(): SupportedLanguage {
        val code = prefs.getString(KEY_SELECTED_LANG, null)
        return SupportedLanguage.entries.firstOrNull { it.code == code } ?: SupportedLanguage.HI
    }

    fun selectLanguage(lang: SupportedLanguage, speakGreeting: Boolean = true) {
        prefs.edit().putString(KEY_SELECTED_LANG, lang.code).apply()
        _currentLang.value = lang
        audioService.stop()
        if (speakGreeting) {
            audioService.speak(lang.greetingPhrase, lang)
        }

        // Refresh active AI-generated analysis in the newly selected language
        val currentWizard = _wizardState.value
        if (currentWizard.analysisResult != null && !currentWizard.isAnalyzing) {
            viewModelScope.launch {
                val localizedAnalysis = aiRepository.analyzeKhata(
                    businessType = currentWizard.businessType,
                    monthlyStatedIncome = currentWizard.statedMonthlySales,
                    loanRequired = currentWizard.requestedLoanAmount,
                    recordType = currentWizard.recordType,
                    recordContent = currentWizard.recordContent,
                    socialCategory = currentWizard.socialCategory,
                    gender = currentWizard.gender,
                    language = lang
                )
                _wizardState.update { it.copy(analysisResult = localizedAnalysis) }
            }
        }

        // Refresh active voice advisor response in the newly selected language
        val currentVoice = _voiceState.value
        if (currentVoice.response != null && currentVoice.queryText.isNotBlank() && !currentVoice.isLoading) {
            viewModelScope.launch {
                try {
                    val refreshed = aiRepository.askVoiceAdvisor(currentVoice.queryText, lang)
                    _voiceState.update { it.copy(response = refreshed) }
                } catch (_: Exception) {
                    // Keep existing response if silent refresh fails
                }
            }
        }
    }

    fun selectTab(tab: AppTab) {
        _activeTab.value = tab
        audioService.stop()
    }

    fun toggleHeroAudio() {
        if (audioState.value.isSpeaking) {
            audioService.stop()
        } else {
            val lang = _currentLang.value
            val welcome = TranslationsData.get(lang).prompts.heroWelcome
            audioService.speak(welcome, lang)
        }
    }

    fun testSpeaker() {
        audioService.testSpeaker(_currentLang.value)
    }

    fun replayLastAudio() {
        val last = audioState.value.lastPlayedText
        if (last.isNotBlank()) {
            audioService.speak(last, _currentLang.value)
        }
    }

    fun speakText(text: String) {
        audioService.speak(text, _currentLang.value)
    }

    fun stopAudio() {
        audioService.stop()
    }

    // Wizard actions
    fun setWizardStep(step: Int) {
        _wizardState.update { it.copy(currentStep = step) }
    }

    fun speakStepAudio(step: Int) {
        val lang = _currentLang.value
        val prompts = TranslationsData.get(lang).prompts
        val text = when (step) {
            1 -> prompts.chooseBusinessAudio
            2 -> prompts.recordUploadAudio
            3 -> prompts.analysisAudio
            4 -> prompts.step4Audio
            else -> prompts.dossierAudio
        }
        audioService.speak(text, lang)
    }

    fun updateBusinessType(type: BusinessCategory) =
        _wizardState.update { it.copy(businessType = type) }

    fun updateOwnerName(value: String) =
        _wizardState.update { it.copy(ownerName = value) }

    fun updateBusinessName(value: String) =
        _wizardState.update { it.copy(businessName = value) }

    fun updateVillage(value: String) =
        _wizardState.update { it.copy(village = value) }

    fun updateDistrict(value: String) =
        _wizardState.update { it.copy(district = value) }

    fun updateSocialCategory(category: SocialCategory) =
        _wizardState.update { it.copy(socialCategory = category) }

    fun updateGender(gender: Gender) =
        _wizardState.update { it.copy(gender = gender) }

    fun updateRequestedLoanAmount(amount: Int) =
        _wizardState.update { it.copy(requestedLoanAmount = amount) }

    fun updateStatedMonthlySales(sales: Int) =
        _wizardState.update { it.copy(statedMonthlySales = sales) }

    fun updateRecordType(type: RecordType) =
        _wizardState.update { it.copy(recordType = type) }

    fun updateRecordContent(content: String) =
        _wizardState.update { it.copy(recordContent = content) }

    fun updateConsent(consented: Boolean) =
        _wizardState.update { it.copy(hasGivenConsent = consented) }

    fun selectPreset(preset: InformalRecordPreset) {
        _wizardState.update {
            it.copy(
                selectedPresetId = preset.id,
                businessType = preset.businessType,
                ownerName = preset.ownerName,
                businessName = preset.title.substringBefore("(").trim(),
                village = preset.village,
                district = preset.district,
                state = preset.state,
                socialCategory = preset.socialCategory,
                gender = preset.gender,
                requestedLoanAmount = preset.requestedLoanAmount,
                statedMonthlySales = preset.statedMonthlySales,
                recordType = preset.recordType,
                recordContent = preset.sampleContent
            )
        }

        val lang = _currentLang.value
        val localizedTitle = SampleProfilesLocalization.localizedTitle(preset.id, lang)
        val ui = TranslationsData.ui(lang)
        val msg = String.format(
            ui.presetSelectedSpeechFormat,
            localizedTitle,
            formatInr(preset.statedMonthlySales),
            formatInr(preset.requestedLoanAmount)
        )
        audioService.speak(msg, lang)
    }

    fun runCreditAnalysis() {
        val current = _wizardState.value
        if (current.isAnalyzing || !current.hasGivenConsent || current.recordContent.isBlank()) return
        val activeLang = _currentLang.value

        viewModelScope.launch {
            _wizardState.update {
                it.copy(
                    isAnalyzing = true,
                    currentStep = 3,
                    analysisStage = 1,
                    analysisProgress = 30,
                    errorMessage = null
                )
            }

            delay(320)
            _wizardState.update { it.copy(analysisStage = 2, analysisProgress = 68) }

            val result = aiRepository.analyzeKhata(
                businessType = current.businessType,
                monthlyStatedIncome = current.statedMonthlySales,
                loanRequired = current.requestedLoanAmount,
                recordType = current.recordType,
                recordContent = current.recordContent,
                socialCategory = current.socialCategory,
                gender = current.gender,
                language = activeLang
            )

            _wizardState.update { it.copy(analysisStage = 3, analysisProgress = 96) }
            delay(250)

            _wizardState.update {
                it.copy(
                    analysisProgress = 100,
                    analysisResult = result,
                    isAnalyzing = false,
                    currentStep = 4
                )
            }
            speakStepAudio(4)
        }
    }

    fun finalizeDossier() {
        val ws = _wizardState.value
        val result = ws.analysisResult ?: return

        val appNum = "SC-${(100000..999999).random()}"
        val newDossier = SubmittedDossier(
            id = "dossier-${System.currentTimeMillis()}",
            applicationNumber = appNum,
            borrowerName = ws.ownerName.ifBlank { "Rural Entrepreneur" },
            businessName = ws.businessName.ifBlank { "Village Micro Enterprise" },
            businessType = ws.businessType,
            village = ws.village.ifBlank { "Rural Village" },
            district = ws.district.ifBlank { "District" },
            state = ws.state,
            loanRequested = ws.requestedLoanAmount,
            recommendedLoan = result.recommendedLoanAmount,
            dscr = result.dscr,
            riskScore = result.riskScore,
            riskLevel = result.riskLevel,
            primaryScheme = result.schemeMatches.firstOrNull()?.schemeName ?: "MUDRA Kishore",
            subsidyEntitlement = result.schemeMatches.getOrNull(1)?.subsidyOrBenefit ?: "35% Rural Subsidy",
            annualMoneylenderSaving = (ws.requestedLoanAmount * 0.22).roundToInt(),
            status = DossierStatus.PENDING_BRANCH_REVIEW,
            consentTimestamp = Instant.now().toString(),
            analysis = result,
            recordSummary = ws.recordContent.take(300) + if (ws.recordContent.length > 300) "..." else ""
        )

        dossierRepository.addDossier(newDossier)
        _wizardState.update { it.copy(currentStep = 5) }
        speakStepAudio(5)
    }

    // Calculator actions
    fun updateCalcLoanAmount(amount: Int) =
        _calculatorState.update { it.copy(loanAmount = amount) }

    fun updateCalcAgentPct(pct: Int) =
        _calculatorState.update { it.copy(agentCommissionPct = pct) }

    fun updateCalcMoneylenderRate(rate: Int) =
        _calculatorState.update { it.copy(moneylenderRate = rate) }

    fun updateCalcTenureYears(years: Int) =
        _calculatorState.update { it.copy(tenureYears = years) }

    // Portal actions
    fun updatePortalFilter(filter: PortalFilter) =
        _portalState.update { it.copy(filter = filter) }

    fun updatePortalSearch(query: String) =
        _portalState.update { it.copy(searchQuery = query) }

    fun inspectDossier(dossier: SubmittedDossier?) =
        _portalState.update { it.copy(inspectedDossier = dossier) }

    fun sanctionDossier(id: String) {
        dossierRepository.updateDossierStatus(id, DossierStatus.IN_PRINCIPLE_SANCTIONED)
        _portalState.update { state ->
            val currentInspected = state.inspectedDossier
            if (currentInspected?.id == id) {
                state.copy(inspectedDossier = currentInspected.copy(status = DossierStatus.IN_PRINCIPLE_SANCTIONED))
            } else {
                state
            }
        }
    }

    fun openDossierInViewer(dossier: SubmittedDossier) {
        val localized = SampleProfilesLocalization.localizeDossier(dossier, _currentLang.value)
        _wizardState.update {
            it.copy(
                businessType = localized.businessType,
                ownerName = localized.borrowerName,
                businessName = localized.businessName,
                village = localized.village,
                district = localized.district,
                state = localized.state,
                requestedLoanAmount = localized.loanRequested,
                statedMonthlySales = localized.analysis.estimatedMonthlyInflow,
                recordContent = localized.recordSummary,
                analysisResult = localized.analysis,
                currentStep = 5
            )
        }
        _portalState.update { it.copy(inspectedDossier = null) }
        _activeTab.value = AppTab.WIZARD
    }

    // Field questions
    fun selectFieldQuestion(index: Int) {
        _activeQuestionIndex.value = index
    }

    // Voice Assistant Modal
    fun openVoiceModal() =
        _voiceState.update { it.copy(isOpen = true) }

    fun closeVoiceModal() {
        audioService.stop()
        _voiceState.update { it.copy(isOpen = false, isRecording = false) }
    }

    fun updateVoiceQuery(text: String) =
        _voiceState.update { it.copy(queryText = text) }

    fun setVoiceRecording(recording: Boolean) =
        _voiceState.update { it.copy(isRecording = recording) }

    fun submitVoiceQuery(queryOverride: String? = null) {
        val text = (queryOverride ?: _voiceState.value.queryText).trim()
        if (text.isEmpty()) return

        viewModelScope.launch {
            _voiceState.update {
                it.copy(
                    queryText = text,
                    isLoading = true,
                    errorMessage = null
                )
            }
            try {
                val res = aiRepository.askVoiceAdvisor(text, _currentLang.value)
                _voiceState.update {
                    it.copy(
                        isLoading = false,
                        response = res
                    )
                }
                audioService.speak(res.spokenResponse, _currentLang.value)
            } catch (e: Exception) {
                val fallback = TranslationsData.ui(_currentLang.value).voiceErrorFallback
                _voiceState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.localizedMessage ?: fallback
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioService.release()
    }

    companion object {
        private const val KEY_SELECTED_LANG = "selected_language_code"
    }
}
