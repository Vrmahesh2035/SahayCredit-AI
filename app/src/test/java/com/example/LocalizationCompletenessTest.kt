package com.example

import com.example.data.FieldQuestionsData
import com.example.data.SampleProfilesData
import com.example.data.SampleProfilesLocalization
import com.example.data.TranslationsData
import com.example.model.BusinessCategory
import com.example.model.DossierStatus
import com.example.model.Gender
import com.example.model.RecordType
import com.example.model.SocialCategory
import com.example.model.SupportedLanguage
import com.example.repository.PlaceholderAiRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LocalizationCompletenessTest {

    private val expectedScriptRanges: Map<SupportedLanguage, IntRange> = mapOf(
        SupportedLanguage.HI to (0x0900..0x097F),
        SupportedLanguage.MR to (0x0900..0x097F),
        SupportedLanguage.KN to (0x0C80..0x0CFF),
        SupportedLanguage.TA to (0x0B80..0x0BFF),
        SupportedLanguage.TE to (0x0C00..0x0C7F),
        SupportedLanguage.BN to (0x0980..0x09FF),
        SupportedLanguage.GU to (0x0A80..0x0AFF)
    )

    private fun String.containsNativeScript(lang: SupportedLanguage): Boolean {
        val range = expectedScriptRanges[lang] ?: return any { it.isLetter() }
        return any { ch -> ch.code in range }
    }

    @Test
    fun allLanguagesHaveNativeUiStringsAndValidPlaceholders() {
        val enUi = TranslationsData.ui(SupportedLanguage.EN)

        SupportedLanguage.entries.forEach { lang ->
            val t = TranslationsData.get(lang)
            val ui = TranslationsData.ui(lang)

            assertTrue("${lang.code} nativeScript should match script", lang.nativeScript.containsNativeScript(lang))
            assertTrue("${lang.code} greetingPhrase should match script", lang.greetingPhrase.containsNativeScript(lang))
            assertTrue("${lang.code} soundTestPhrase should match script", lang.soundTestPhrase.containsNativeScript(lang))

            assertTrue("${lang.code} tagline should match script", t.tagline.containsNativeScript(lang))
            assertTrue("${lang.code} listenAudio should match script", t.listenAudio.containsNativeScript(lang))
            assertTrue("${lang.code} prompts.heroWelcome should match script", t.prompts.heroWelcome.containsNativeScript(lang))

            assertTrue("${lang.code} heroTitle should match script", ui.heroTitle.containsNativeScript(lang))
            assertTrue("${lang.code} portalTitle should match script", ui.portalTitle.containsNativeScript(lang))
            assertTrue("${lang.code} calcTitle should match script", ui.calcTitle.containsNativeScript(lang))
            assertTrue("${lang.code} fieldQaSubtitle should match script", ui.fieldQaSubtitle.containsNativeScript(lang))
            assertTrue("${lang.code} voiceHeaderTitle should match script", ui.voiceHeaderTitle.containsNativeScript(lang))
            assertTrue("${lang.code} dossierFormalHeading should match script", ui.dossierFormalHeading.containsNativeScript(lang))
            assertTrue("${lang.code} footerTitle should not be blank", ui.footerTitle.isNotBlank())
            assertTrue("${lang.code} footerHelplineText should match script", ui.footerHelplineText.containsNativeScript(lang))

            if (lang != SupportedLanguage.EN) {
                assertNotEquals("${lang.code} heroTitle must differ from English", enUi.heroTitle, ui.heroTitle)
                assertNotEquals("${lang.code} navSchemes must differ from English", enUi.navSchemes, ui.navSchemes)
                assertNotEquals("${lang.code} calcTitle must differ from English", enUi.calcTitle, ui.calcTitle)
                assertNotEquals("${lang.code} portalTitle must differ from English", enUi.portalTitle, ui.portalTitle)
            }

            // Enum maps completeness
            assertEquals(BusinessCategory.entries.size, ui.businessDescriptions.size)
            assertEquals(SocialCategory.entries.size, ui.socialCategoryLabels.size)
            assertEquals(Gender.entries.size, ui.genderLabels.size)
            assertEquals(RecordType.entries.size, ui.recordTypeLabels.size)
            assertEquals(DossierStatus.entries.size, ui.statusLabels.size)
            assertTrue("${lang.code} voiceQuickQuestions must not be empty", ui.voiceQuickQuestions.isNotEmpty())
            ui.voiceQuickQuestions.forEach { q ->
                assertTrue("${lang.code} quick question should match script: $q", q.containsNativeScript(lang))
            }

            // Verify String.format templates do not throw
            assertTrue(String.format(ui.stepFlowFormat, 2).contains("2"))
            assertTrue(String.format(ui.presetSalesLoanFormat, "₹58,000", "₹1,80,000").contains("₹58,000"))
            assertTrue(String.format(ui.calcTotalInterestFormat, 3).contains("3"))
            assertTrue(String.format(ui.filterAllFormat, 4).contains("4"))
            assertTrue(String.format(ui.dossierDateConsentLine, "19 Nov 2026").contains("19 Nov 2026"))
            assertTrue(String.format(ui.presetSelectedSpeechFormat, "Test", "₹50,000", "₹2,00,000").contains("Test"))
        }
    }

    @Test
    fun allLanguagesHaveLocalizedFieldQuestionsAndSampleProfiles() {
        SupportedLanguage.entries.forEach { lang ->
            val questions = FieldQuestionsData.localizedQuestions(lang)
            assertEquals(6, questions.size)
            questions.forEach { q ->
                assertTrue("${lang.code} Q${q.id} title should match script", q.title.containsNativeScript(lang))
                assertTrue("${lang.code} Q${q.id} problem should match script", q.groundProblem.containsNativeScript(lang))
                assertTrue("${lang.code} Q${q.id} solution should match script", q.agenticAiSolution.containsNativeScript(lang))
                assertTrue("${lang.code} Q${q.id} stakeholder should not be blank", q.stakeholder.isNotBlank())
                assertTrue("${lang.code} Q${q.id} metric should not be blank", q.metric.isNotBlank())
            }

            SampleProfilesData.SAMPLE_PROFILES.forEach { preset ->
                val title = SampleProfilesLocalization.localizedTitle(preset.id, lang)
                val summary = SampleProfilesLocalization.localizedSummary(preset.id, lang)
                assertTrue("${lang.code} preset ${preset.id} title should match script", title.containsNativeScript(lang))
                assertTrue("${lang.code} preset ${preset.id} summary should match script", summary.containsNativeScript(lang))
            }

            SampleProfilesData.INITIAL_DOSSIERS.forEach { dossier ->
                val localized = SampleProfilesLocalization.localizeDossier(dossier, lang)
                assertTrue("${lang.code} dossier ${dossier.id} primaryScheme should match script", localized.primaryScheme.containsNativeScript(lang))
                assertTrue("${lang.code} dossier ${dossier.id} summary should match script", localized.recordSummary.containsNativeScript(lang))
                assertTrue("${lang.code} dossier ${dossier.id} explainability should match script", localized.analysis.explainabilitySummary.containsNativeScript(lang))
                assertTrue("${lang.code} dossier ${dossier.id} audio guide should match script", localized.analysis.villageAudioGuideText.textFor(lang).containsNativeScript(lang))
            }
        }
    }

    @Test
    fun aiRepositoryRespondsInAllSelectedLanguages() = runBlocking {
        val repo = PlaceholderAiRepository()
        val queries = listOf(
            "Agent asked 25000 commission",
            "How do I get 35% PMEGP subsidy?",
            "PhonePe QR and khata bahi proof",
            "Branch manager meeting",
            "Loan without ITR"
        )

        SupportedLanguage.entries.forEach { lang ->
            val analysis = repo.analyzeKhata(
                businessType = BusinessCategory.TAILOR,
                monthlyStatedIncome = 62000,
                loanRequired = 180000,
                recordType = RecordType.UPI_QR,
                recordContent = "UPI QR daily 2000",
                socialCategory = SocialCategory.OBC,
                gender = Gender.MALE,
                language = lang
            )
            assertTrue("${lang.code} analysis riskLevel script check", analysis.riskLevel.containsNativeScript(lang))
            assertTrue("${lang.code} analysis explainability script check", analysis.explainabilitySummary.containsNativeScript(lang))
            assertTrue("${lang.code} analysis checklist non-empty", analysis.branchManagerChecklist.isNotEmpty())
            analysis.branchManagerChecklist.forEach { item ->
                assertTrue("${lang.code} checklist script check: $item", item.containsNativeScript(lang))
            }
            assertTrue(
                "${lang.code} villageAudioGuideText script check",
                analysis.villageAudioGuideText.textFor(lang).containsNativeScript(lang)
            )
            assertFalse("${lang.code} schemeMatches non-empty", analysis.schemeMatches.isEmpty())
            analysis.schemeMatches.forEach { scheme ->
                assertTrue("${lang.code} schemeName script check: ${scheme.schemeName}", scheme.schemeName.containsNativeScript(lang))
                assertTrue("${lang.code} whyEligible script check: ${scheme.whyEligible}", scheme.whyEligible.containsNativeScript(lang))
            }

            queries.forEach { q ->
                val voice = repo.askVoiceAdvisor(q, lang)
                assertTrue("${lang.code} spokenResponse script check", voice.spokenResponse.containsNativeScript(lang))
                assertTrue("${lang.code} middlemanWarning script check", voice.middlemanWarning.containsNativeScript(lang))
                assertFalse("${lang.code} keyAdviceBulletPoints non-empty", voice.keyAdviceBulletPoints.isEmpty())
                voice.keyAdviceBulletPoints.forEach { b ->
                    assertTrue("${lang.code} bullet script check: $b", b.containsNativeScript(lang))
                }
            }
        }
    }
}
