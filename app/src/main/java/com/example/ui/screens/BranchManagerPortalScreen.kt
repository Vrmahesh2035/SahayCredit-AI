package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.SampleProfilesLocalization
import com.example.data.TranslationsData
import com.example.data.strings.UiStrings
import com.example.model.DossierStatus
import com.example.model.SubmittedDossier
import com.example.model.SupportedLanguage
import com.example.ui.components.PortalFooter
import com.example.ui.theme.AmberSoft
import com.example.ui.theme.AmberWarm
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.TabularMoneyStyle
import com.example.util.formatInr
import com.example.viewmodel.PortalFilter
import com.example.viewmodel.PortalUiState

@Composable
fun BranchManagerPortalScreen(
    dossiers: List<SubmittedDossier>,
    portalState: PortalUiState,
    currentLang: SupportedLanguage,
    onFilterChange: (PortalFilter) -> Unit,
    onSearchChange: (String) -> Unit,
    onInspectDossier: (SubmittedDossier?) -> Unit,
    onSanctionDossier: (String) -> Unit,
    onOpenPrintableDossier: (SubmittedDossier) -> Unit,
    onSpeakText: (String) -> Unit
) {
    val ui = TranslationsData.ui(currentLang)
    val localizedDossiers = dossiers.map { SampleProfilesLocalization.localizeDossier(it, currentLang) }
    val filtered = localizedDossiers.filter { d ->
        val matchesFilter = when (portalState.filter) {
            PortalFilter.ALL -> true
            PortalFilter.PENDING -> d.status == DossierStatus.PENDING_BRANCH_REVIEW
            PortalFilter.SANCTIONED -> d.status == DossierStatus.IN_PRINCIPLE_SANCTIONED
        }
        val q = portalState.searchQuery.trim().lowercase()
        val matchesSearch = q.isEmpty() ||
            d.borrowerName.lowercase().contains(q) ||
            d.businessName.lowercase().contains(q) ||
            d.village.lowercase().contains(q) ||
            d.primaryScheme.lowercase().contains(q)
        matchesFilter && matchesSearch
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Portal Header & Efficiency KPIs
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(Icons.Default.AccountBalance, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(18.dp))
                    Text(
                        text = ui.portalTag,
                        style = MaterialTheme.typography.labelSmall,
                        color = EmeraldDeep,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = ui.portalTitle,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = ui.portalSubtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedButton(
                    onClick = { onSpeakText(ui.portalAudioBriefSpeech) },
                    modifier = Modifier.heightIn(min = 42.dp).testTag("portal_audio_brief_button")
                ) {
                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(ui.portalAudioBriefButton)
                }

                // 2x2 Operational KPIs
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    KpiBox(
                        label = ui.kpiDecisionLabel,
                        value = ui.kpiDecisionValue,
                        sub = ui.kpiDecisionSub,
                        modifier = Modifier.weight(1f)
                    )
                    KpiBox(
                        label = ui.kpiAgentFeeLabel,
                        value = ui.kpiAgentFeeValue,
                        sub = ui.kpiAgentFeeSub,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    KpiBox(
                        label = ui.kpiAvgDscrLabel,
                        value = "1.68x",
                        sub = ui.kpiAvgDscrSub,
                        modifier = Modifier.weight(1f)
                    )
                    KpiBox(
                        label = ui.kpiGuaranteeLabel,
                        value = "85% CGTMSE",
                        sub = ui.kpiGuaranteeSub,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Filter Chips + Search
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(
                PortalFilter.ALL to String.format(ui.filterAllFormat, localizedDossiers.size),
                PortalFilter.PENDING to ui.filterPending,
                PortalFilter.SANCTIONED to ui.filterSanctioned
            ).forEach { (filter, label) ->
                val selected = portalState.filter == filter
                Surface(
                    onClick = { onFilterChange(filter) },
                    shape = RoundedCornerShape(10.dp),
                    color = if (selected) EmeraldDeep else MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, if (selected) EmeraldDeep else MaterialTheme.colorScheme.outline),
                    modifier = Modifier.weight(1f).testTag("portal_filter_${filter.name.lowercase()}")
                ) {
                    Text(
                        text = label,
                        color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
                        maxLines = 1
                    )
                }
            }
        }

        OutlinedTextField(
            value = portalState.searchQuery,
            onValueChange = onSearchChange,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = ui.portalSearchPlaceholder) },
            placeholder = { Text(ui.portalSearchPlaceholder) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().testTag("portal_search_input")
        )

        // Dossiers List
        if (filtered.isEmpty()) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = ui.portalEmptyTitle,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = ui.portalEmptySubtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            filtered.forEach { dossier ->
                DossierListCard(
                    dossier = dossier,
                    ui = ui,
                    onInspect = { onInspectDossier(dossier) },
                    onSanction = { onSanctionDossier(dossier.id) }
                )
            }
        }

        PortalFooter(currentLang = currentLang)
    }

    // Inspect Modal
    portalState.inspectedDossier?.let { rawInspected ->
        val inspected = SampleProfilesLocalization.localizeDossier(rawInspected, currentLang)
        BackHandler(onBack = { onInspectDossier(null) })
        Dialog(
            onDismissRequest = { onInspectDossier(null) },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 580.dp)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${inspected.borrowerName} · ${inspected.businessName}",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "${inspected.applicationNumber} · ${inspected.village}, ${inspected.district}",
                                style = MaterialTheme.typography.labelSmall,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        IconButton(onClick = { onInspectDossier(null) }) {
                            Icon(Icons.Default.Close, contentDescription = ui.closeLabel)
                        }
                    }

                    HorizontalDivider()

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        KpiBox(ui.modalRecommendedLoan, formatInr(inspected.recommendedLoan), "DSCR ${String.format("%.2f", inspected.dscr)}x", Modifier.weight(1f))
                        KpiBox(ui.modalRiskScore, "${inspected.riskScore}/100", inspected.subsidyEntitlement, Modifier.weight(1f))
                    }

                    Text(ui.modalRecordSummaryTitle, style = MaterialTheme.typography.labelLarge)
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = inspected.recordSummary,
                            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Monospace),
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(ui.modalExplainabilityTitle, style = MaterialTheme.typography.labelLarge)
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = EmeraldSoft,
                        border = BorderStroke(1.dp, EmeraldContainer),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = inspected.analysis.explainabilitySummary,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    OutlinedButton(
                        onClick = { onOpenPrintableDossier(inspected) },
                        modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).testTag("modal_view_full_dossier_button")
                    ) {
                        Text(ui.modalViewFullButton)
                    }

                    if (inspected.status != DossierStatus.IN_PRINCIPLE_SANCTIONED) {
                        Button(
                            onClick = { onSanctionDossier(inspected.id) },
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                            modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).testTag("modal_sanction_button")
                        ) {
                            Text(ui.modalSanctionFullButton)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DossierListCard(
    dossier: SubmittedDossier,
    ui: UiStrings,
    onInspect: () -> Unit,
    onSanction: () -> Unit
) {
    val isSanctioned = dossier.status == DossierStatus.IN_PRINCIPLE_SANCTIONED
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth().testTag("dossier_card_${dossier.id}")
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = dossier.borrowerName, style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "${dossier.businessName} · ${dossier.village}, ${dossier.district}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = dossier.applicationNumber,
                        style = MaterialTheme.typography.labelSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = if (isSanctioned) EmeraldContainer else AmberSoft
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = if (isSanctioned) Icons.Default.CheckCircle else Icons.Default.Schedule,
                            contentDescription = null,
                            tint = if (isSanctioned) EmeraldDeep else AmberWarm,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = ui.statusLabels[dossier.status] ?: dossier.status.name,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (isSanctioned) EmeraldDeep else AmberWarm
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(ui.dossierCardLoanScheme, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        text = formatInr(dossier.recommendedLoan),
                        style = TabularMoneyStyle.merge(MaterialTheme.typography.titleMedium),
                        color = EmeraldDeep
                    )
                    Text(dossier.primaryScheme, style = MaterialTheme.typography.labelSmall)
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(ui.dossierCardDscrScore, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        text = "${String.format("%.2f", dossier.dscr)}x · ${dossier.riskScore}/100",
                        style = TabularMoneyStyle.merge(MaterialTheme.typography.titleMedium)
                    )
                    Text(dossier.subsidyEntitlement, style = MaterialTheme.typography.labelSmall, color = EmeraldPrimary)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onInspect,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                    modifier = Modifier.weight(1f).heightIn(min = 44.dp).testTag("inspect_dossier_${dossier.id}")
                ) {
                    Text(ui.inspectButton)
                }
                if (!isSanctioned) {
                    Button(
                        onClick = onSanction,
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                        modifier = Modifier.weight(1f).heightIn(min = 44.dp).testTag("sanction_dossier_${dossier.id}")
                    ) {
                        Icon(Icons.Default.Bolt, contentDescription = null, modifier = Modifier.size(15.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(ui.sanctionButton)
                    }
                }
            }
        }
    }
}

@Composable
private fun KpiBox(
    label: String,
    value: String,
    sub: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = TabularMoneyStyle.merge(MaterialTheme.typography.titleMedium), color = EmeraldDeep)
            Text(sub, style = MaterialTheme.typography.labelSmall, color = EmeraldPrimary)
        }
    }
}
