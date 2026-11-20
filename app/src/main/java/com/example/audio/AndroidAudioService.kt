package com.example.audio

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import com.example.model.SupportedLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale
import java.util.UUID

data class AudioPlaybackState(
    val isSpeaking: Boolean = false,
    val currentSpeakingText: String = "",
    val lastPlayedText: String = "",
    val isTtsReady: Boolean = false
)

class AndroidAudioService(context: Context) {
    private val appContext = context.applicationContext
    private var tts: TextToSpeech? = null
    private var toneGenerator: ToneGenerator? = null
    private var pendingText: String? = null
    private var pendingLang: SupportedLanguage = SupportedLanguage.HI

    private val _state = MutableStateFlow(AudioPlaybackState())
    val state: StateFlow<AudioPlaybackState> = _state.asStateFlow()

    init {
        initTts()
    }

    private fun initTts() {
        try {
            tts = TextToSpeech(appContext) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    _state.update { it.copy(isTtsReady = true) }
                    tts?.setSpeechRate(0.92f)
                    tts?.setPitch(1.0f)
                    tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                        override fun onStart(utteranceId: String?) {
                            _state.update { it.copy(isSpeaking = true) }
                        }

                        override fun onDone(utteranceId: String?) {
                            _state.update { it.copy(isSpeaking = false, currentSpeakingText = "") }
                        }

                        @Deprecated("Deprecated in Java")
                        override fun onError(utteranceId: String?) {
                            _state.update { it.copy(isSpeaking = false, currentSpeakingText = "") }
                        }

                        override fun onError(utteranceId: String?, errorCode: Int) {
                            _state.update { it.copy(isSpeaking = false, currentSpeakingText = "") }
                        }
                    })

                    val queuedText = pendingText
                    val queuedLang = pendingLang
                    pendingText = null
                    if (!queuedText.isNullOrBlank()) {
                        speak(queuedText, queuedLang)
                    }
                } else {
                    Log.w(TAG, "TextToSpeech initialization failed with status=$status")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing TextToSpeech", e)
        }
    }

    fun playChime() {
        try {
            if (toneGenerator == null) {
                toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 65)
            }
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_ACK, 150)
        } catch (e: Exception) {
            Log.w(TAG, "ToneGenerator unavailable: ${e.message}")
        }
    }

    fun testSpeaker(lang: SupportedLanguage) {
        stop()
        playChime()
        speak(lang.soundTestPhrase, lang)
    }

    fun speak(text: String, language: SupportedLanguage) {
        val clean = text.trim()
        if (clean.isEmpty()) return

        _state.update {
            it.copy(
                isSpeaking = true,
                currentSpeakingText = clean,
                lastPlayedText = clean
            )
        }

        val engine = tts
        if (engine == null || !_state.value.isTtsReady) {
            pendingText = clean
            pendingLang = language
            return
        }

        playChime()

        val targetLocale = Locale.forLanguageTag(language.localeTag)
        val baseLocale = Locale.forLanguageTag(language.code)
        val fullAvail = engine.isLanguageAvailable(targetLocale)
        val baseAvail = engine.isLanguageAvailable(baseLocale)
        var textToSpeak = clean

        when {
            fullAvail >= TextToSpeech.LANG_AVAILABLE -> {
                engine.language = targetLocale
            }
            baseAvail >= TextToSpeech.LANG_AVAILABLE -> {
                engine.language = baseLocale
            }
            else -> {
                val fallbackEn = Locale.forLanguageTag("en-IN")
                engine.language = fallbackEn
                if (language != SupportedLanguage.EN && clean.length < 150) {
                    textToSpeak = language.phoneticFallback
                }
            }
        }

        val params = Bundle().apply {
            putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f)
        }
        val utteranceId = UUID.randomUUID().toString()
        engine.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, params, utteranceId)
    }

    fun stop() {
        pendingText = null
        try {
            tts?.stop()
        } catch (e: Exception) {
            Log.w(TAG, "Error stopping TTS: ${e.message}")
        }
        _state.update {
            it.copy(isSpeaking = false, currentSpeakingText = "")
        }
    }

    fun release() {
        stop()
        try {
            tts?.shutdown()
            toneGenerator?.release()
        } catch (e: Exception) {
            Log.w(TAG, "Error releasing audio resources: ${e.message}")
        } finally {
            tts = null
            toneGenerator = null
        }
    }

    companion object {
        private const val TAG = "AndroidAudioService"
    }
}
