package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.audio.AudioPlaybackState
import com.example.data.TranslationsData
import com.example.model.SupportedLanguage
import com.example.ui.theme.AmberBorder
import com.example.ui.theme.AmberSoft
import com.example.ui.theme.AmberWarm
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.EmeraldDarkest
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft

@Composable
fun TopHeaderAndAudioBar(
    currentLang: SupportedLanguage,
    audioState: AudioPlaybackState,
    onLanguageSelected: (SupportedLanguage) -> Unit,
    onToggleHeroAudio: () -> Unit,
    onTestSound: () -> Unit,
    onReplayAudio: () -> Unit,
    onStopAudio: () -> Unit,
    onOpenVoiceAssistant: () -> Unit,
    onBrandClick: () -> Unit
) {
    val t = TranslationsData.get(currentLang)
    val ui = TranslationsData.ui(currentLang)
    var langMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // Zero Agent Fee Guarantee Top Bar (respects status bar notch/safe area)
        Surface(
            color = EmeraldDeep,
            contentColor = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = ui.topZeroAgentGuarantee,
                        tint = EmeraldAccent,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = ui.topZeroAgentGuarantee,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Surface(
                    onClick = onToggleHeroAudio,
                    color = EmeraldPrimary,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .heightIn(min = 34.dp)
                        .testTag("top_bar_audio_toggle")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = if (audioState.isSpeaking) Icons.Default.VolumeOff else Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = if (audioState.isSpeaking) t.stopAudio else t.listenAudio,
                            tint = if (audioState.isSpeaking) AmberBorder else Color.White,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = if (audioState.isSpeaking) t.stopAudio else t.listenAudio,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        // Main Brand + Language + Voice Assistant Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(onClick = onBrandClick)
                    .padding(end = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(EmeraldPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "स",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Column {
                    Text(
                        text = t.appName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = t.badge,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Language Dropdown
                Box {
                    OutlinedButton(
                        onClick = { langMenuExpanded = true },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .heightIn(min = 42.dp)
                            .testTag("language_selector_button")
                    ) {
                        Text(
                            text = currentLang.nativeScript,
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 1
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = t.selectLanguage,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = langMenuExpanded,
                        onDismissRequest = { langMenuExpanded = false }
                    ) {
                        SupportedLanguage.entries.forEach { lang ->
                            DropdownMenuItem(
                                text = {
                                    Text("${lang.nativeScript} (${lang.englishLabel})")
                                },
                                onClick = {
                                    langMenuExpanded = false
                                    onLanguageSelected(lang)
                                }
                            )
                        }
                    }
                }

                // Voice Assistant Trigger
                Button(
                    onClick = onOpenVoiceAssistant,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    modifier = Modifier
                        .heightIn(min = 42.dp)
                        .testTag("open_voice_assistant_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = t.askAssistant,
                        tint = EmeraldAccent,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = ui.voiceChipShort,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }

        // Audio Status Banner
        val hasAudioHistory = audioState.isSpeaking || audioState.lastPlayedText.isNotBlank()
        Surface(
            color = if (audioState.isSpeaking) AmberSoft else MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(
                width = 1.dp,
                color = if (audioState.isSpeaking) AmberBorder else MaterialTheme.colorScheme.outline
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = if (audioState.isSpeaking) Icons.Default.GraphicEq else Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Audio status",
                        tint = if (audioState.isSpeaking) AmberWarm else EmeraldPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = if (hasAudioHistory) {
                            val prefix = if (audioState.isSpeaking) {
                                ui.speakingPrefix
                            } else {
                                ui.lastAudioPrefix
                            }
                            val body = if (audioState.isSpeaking) audioState.currentSpeakingText else audioState.lastPlayedText
                            "$prefix\"$body\""
                        } else {
                            ui.audioReadyHint
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = if (hasAudioHistory) FontStyle.Italic else FontStyle.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(Modifier.width(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        onClick = onTestSound,
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier
                            .heightIn(min = 34.dp)
                            .testTag("test_sound_button")
                    ) {
                        Text(
                            text = ui.testSoundButton,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 7.dp)
                        )
                    }

                    AnimatedVisibility(visible = hasAudioHistory) {
                        if (audioState.isSpeaking) {
                            Surface(
                                onClick = onStopAudio,
                                shape = RoundedCornerShape(8.dp),
                                color = AmberWarm,
                                modifier = Modifier
                                    .heightIn(min = 34.dp)
                                    .testTag("audio_banner_stop_button")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 7.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Stop,
                                        contentDescription = ui.audioStopShort,
                                        tint = Color.White,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = ui.audioStopShort,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        } else {
                            Surface(
                                onClick = onReplayAudio,
                                shape = RoundedCornerShape(8.dp),
                                color = EmeraldPrimary,
                                modifier = Modifier
                                    .heightIn(min = 34.dp)
                                    .testTag("audio_banner_replay_button")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 7.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Replay,
                                        contentDescription = ui.audioReplayShort,
                                        tint = Color.White,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = ui.audioReplayShort,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Multilingual Quick Switch Strip
        Surface(
            color = EmeraldDarkest,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SupportedLanguage.entries.forEach { lang ->
                    val selected = lang == currentLang
                    Surface(
                        onClick = { onLanguageSelected(lang) },
                        shape = RoundedCornerShape(8.dp),
                        color = if (selected) EmeraldPrimary else EmeraldDeep,
                        border = if (selected) BorderStroke(1.dp, EmeraldAccent) else null,
                        modifier = Modifier.testTag("quick_lang_${lang.code}")
                    ) {
                        Text(
                            text = if (selected) "${lang.nativeScript} 🔊" else lang.nativeScript,
                            color = if (selected) Color.White else EmeraldSoft,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}
