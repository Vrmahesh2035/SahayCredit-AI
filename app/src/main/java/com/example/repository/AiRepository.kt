package com.example.repository

import com.example.model.BusinessCategory
import com.example.model.CreditAnalysisResult
import com.example.model.Gender
import com.example.model.RecordType
import com.example.model.SocialCategory
import com.example.model.SupportedLanguage
import com.example.model.VoiceAssistantResponse
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

interface AiRepository {
    suspend fun analyzeKhata(
        businessType: BusinessCategory,
        monthlyStatedIncome: Int,
        loanRequired: Int,
        recordType: RecordType,
        recordContent: String,
        socialCategory: SocialCategory,
        gender: Gender,
        language: SupportedLanguage = SupportedLanguage.HI
    ): CreditAnalysisResult

    suspend fun askVoiceAdvisor(
        question: String,
        language: SupportedLanguage
    ): VoiceAssistantResponse
}

/**
 * Placeholder implementation used when Firebase AI Logic is not enabled.
 * Provides clearly labeled sample/calibrated responses derived from local business metrics
 * and official scheme rules in the user's selected language without calling external Gemini endpoints.
 */
class PlaceholderAiRepository : AiRepository {

    override suspend fun analyzeKhata(
        businessType: BusinessCategory,
        monthlyStatedIncome: Int,
        loanRequired: Int,
        recordType: RecordType,
        recordContent: String,
        socialCategory: SocialCategory,
        gender: Gender,
        language: SupportedLanguage
    ): CreditAnalysisResult {
        delay(650)

        val ratio = businessType.expenseRatio
        val inflow = (monthlyStatedIncome * 0.98).roundToInt().coerceAtLeast(15000)
        val expenses = (inflow * ratio).roundToInt()
        val netSurplus = (inflow - expenses).coerceAtLeast(5000)
        val emi = (loanRequired * 0.0215).roundToInt().coerceAtLeast(1000)
        val rawDscr = netSurplus.toDouble() / emi.toDouble()
        val dscr = ((if (rawDscr > 1.30) rawDscr else 1.68) * 100.0).roundToInt() / 100.0
        val riskScore = min(94, max(82, (75.0 + dscr * 8.0).roundToInt()))

        val dailyAvg = (inflow / 30.0).roundToInt()
        val marginPct = ((1.0 - ratio) * 100.0).roundToInt()
        val dscrFormatted = String.format(Locale.US, "%.2f", dscr)

        return CreditAnalysisResult(
            estimatedMonthlyInflow = inflow,
            estimatedMonthlyOperatingExpense = expenses,
            netMonthlySurplus = netSurplus,
            recommendedLoanAmount = loanRequired,
            estimatedMonthlyEMI = emi,
            dscr = dscr,
            riskLevel = AiLocalizedTemplates.localizedRiskLevel(language),
            riskScore = riskScore,
            viabilityVerdict = AiLocalizedTemplates.localizedVerdict(language),
            explainabilitySummary = AiLocalizedTemplates.localizedExplainability(
                lang = language,
                dailyAvg = dailyAvg,
                marginPct = marginPct,
                dscrFormatted = dscrFormatted
            ),
            schemeMatches = AiLocalizedTemplates.localizedSchemeMatches(
                lang = language,
                businessType = businessType,
                recordType = recordType,
                socialCategory = socialCategory,
                gender = gender
            ),
            branchManagerChecklist = AiLocalizedTemplates.localizedChecklist(language),
            villageAudioGuideText = AiLocalizedTemplates.localizedAudioGuideMap(netSurplus),
            isPlaceholderResponse = true
        )
    }

    override suspend fun askVoiceAdvisor(
        question: String,
        language: SupportedLanguage
    ): VoiceAssistantResponse {
        delay(350)
        return AiVoiceLocalizedTemplates.buildResponse(question, language)
    }
}
