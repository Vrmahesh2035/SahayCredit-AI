package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.data.TranslationsData
import com.example.model.AppTab
import com.example.ui.components.TopHeaderAndAudioBar
import com.example.ui.components.VoiceAssistantDialog
import com.example.ui.screens.BranchManagerPortalScreen
import com.example.ui.screens.EntrepreneurWizardScreen
import com.example.ui.screens.FieldQuestionsScreen
import com.example.ui.screens.MiddlemanCalculatorScreen
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldSoft
import com.example.viewmodel.SahayCreditViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {
    @Serializable
    data object WizardRoute : AppRoute

    @Serializable
    data object CalculatorRoute : AppRoute

    @Serializable
    data object PortalRoute : AppRoute

    @Serializable
    data object QuestionsRoute : AppRoute
}

private data class NavDestinationItem(
    val tab: AppTab,
    val route: AppRoute,
    val icon: ImageVector,
    val label: String
)

@Composable
fun SahayCreditApp(
    viewModel: SahayCreditViewModel,
    windowSizeClass: WindowSizeClass
) {
    val currentLang by viewModel.currentLang.collectAsStateWithLifecycle()
    val activeTab by viewModel.activeTab.collectAsStateWithLifecycle()
    val audioState by viewModel.audioState.collectAsStateWithLifecycle()
    val wizardState by viewModel.wizardState.collectAsStateWithLifecycle()
    val calculatorState by viewModel.calculatorState.collectAsStateWithLifecycle()
    val voiceState by viewModel.voiceState.collectAsStateWithLifecycle()
    val portalState by viewModel.portalState.collectAsStateWithLifecycle()
    val dossiers by viewModel.dossiers.collectAsStateWithLifecycle()
    val activeQuestionIndex by viewModel.activeQuestionIndex.collectAsStateWithLifecycle()

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val scrollState = rememberScrollState()

    val isExpandedLayout = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    val ui = TranslationsData.ui(currentLang)

    val navItems = listOf(
        NavDestinationItem(
            tab = AppTab.WIZARD,
            route = AppRoute.WizardRoute,
            icon = Icons.Default.AutoAwesome,
            label = ui.navSchemes
        ),
        NavDestinationItem(
            tab = AppTab.CALCULATOR,
            route = AppRoute.CalculatorRoute,
            icon = Icons.Default.Calculate,
            label = ui.navSavings
        ),
        NavDestinationItem(
            tab = AppTab.PORTAL,
            route = AppRoute.PortalRoute,
            icon = Icons.Default.AccountBalance,
            label = ui.navBankDesk
        ),
        NavDestinationItem(
            tab = AppTab.QUESTIONS,
            route = AppRoute.QuestionsRoute,
            icon = Icons.AutoMirrored.Filled.HelpOutline,
            label = ui.navFieldQa
        )
    )

    val navigateToTab: (AppTab) -> Unit = { targetTab ->
        viewModel.selectTab(targetTab)
        val targetRoute = navItems.first { it.tab == targetTab }.route
        navController.navigate(targetRoute) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    LaunchedEffect(activeTab, wizardState.currentStep) {
        scrollState.scrollTo(0)
    }

    // Keep Navigation Compose synced if ViewModel changes activeTab (e.g., openDossierInViewer)
    LaunchedEffect(activeTab) {
        val targetItem = navItems.first { it.tab == activeTab }
        val alreadyHere = when (targetItem.route) {
            AppRoute.WizardRoute -> currentDestination?.hasRoute<AppRoute.WizardRoute>() == true
            AppRoute.CalculatorRoute -> currentDestination?.hasRoute<AppRoute.CalculatorRoute>() == true
            AppRoute.PortalRoute -> currentDestination?.hasRoute<AppRoute.PortalRoute>() == true
            AppRoute.QuestionsRoute -> currentDestination?.hasRoute<AppRoute.QuestionsRoute>() == true
        }
        if (!alreadyHere) {
            navController.navigate(targetItem.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    // Sync ViewModel tab when user presses system Back across tabs
    LaunchedEffect(currentDestination) {
        val routeTab = when {
            currentDestination?.hasRoute<AppRoute.WizardRoute>() == true -> AppTab.WIZARD
            currentDestination?.hasRoute<AppRoute.CalculatorRoute>() == true -> AppTab.CALCULATOR
            currentDestination?.hasRoute<AppRoute.PortalRoute>() == true -> AppTab.PORTAL
            currentDestination?.hasRoute<AppRoute.QuestionsRoute>() == true -> AppTab.QUESTIONS
            else -> null
        }
        if (routeTab != null && routeTab != activeTab) {
            viewModel.selectTab(routeTab)
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopHeaderAndAudioBar(
                currentLang = currentLang,
                audioState = audioState,
                onLanguageSelected = { lang -> viewModel.selectLanguage(lang) },
                onToggleHeroAudio = { viewModel.toggleHeroAudio() },
                onTestSound = { viewModel.testSpeaker() },
                onReplayAudio = { viewModel.replayLastAudio() },
                onStopAudio = { viewModel.stopAudio() },
                onOpenVoiceAssistant = { viewModel.openVoiceModal() },
                onBrandClick = { navigateToTab(AppTab.WIZARD) }
            )
        },
        bottomBar = {
            if (!isExpandedLayout) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 4.dp,
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                ) {
                    navItems.forEach { item ->
                        val selected = activeTab == item.tab
                        NavigationBarItem(
                            selected = selected,
                            onClick = { navigateToTab(item.tab) },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = EmeraldDeep,
                                selectedTextColor = EmeraldDeep,
                                indicatorColor = EmeraldSoft
                            ),
                            modifier = Modifier.testTag("nav_tab_${item.tab.route}")
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (isExpandedLayout) {
                NavigationRail(
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    navItems.forEach { item ->
                        val selected = activeTab == item.tab
                        NavigationRailItem(
                            selected = selected,
                            onClick = { navigateToTab(item.tab) },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = EmeraldDeep,
                                selectedTextColor = EmeraldDeep,
                                indicatorColor = EmeraldSoft
                            ),
                            modifier = Modifier.testTag("nav_rail_${item.tab.route}")
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 840.dp)
                        .verticalScroll(scrollState)
                        .padding(horizontal = 14.dp, vertical = 14.dp)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = AppRoute.WizardRoute
                    ) {
                        composable<AppRoute.WizardRoute> {
                            EntrepreneurWizardScreen(
                                wizardState = wizardState,
                                currentLang = currentLang,
                                audioState = audioState,
                                onToggleHeroAudio = { viewModel.toggleHeroAudio() },
                                onOpenVoiceAssistant = { viewModel.openVoiceModal() },
                                onSpeakStepAudio = { step -> viewModel.speakStepAudio(step) },
                                onSetStep = { step -> viewModel.setWizardStep(step) },
                                onSelectBusinessType = { viewModel.updateBusinessType(it) },
                                onOwnerNameChange = { viewModel.updateOwnerName(it) },
                                onBusinessNameChange = { viewModel.updateBusinessName(it) },
                                onVillageChange = { viewModel.updateVillage(it) },
                                onDistrictChange = { viewModel.updateDistrict(it) },
                                onCategoryChange = { viewModel.updateSocialCategory(it) },
                                onGenderChange = { viewModel.updateGender(it) },
                                onLoanAmountChange = { viewModel.updateRequestedLoanAmount(it) },
                                onMonthlySalesChange = { viewModel.updateStatedMonthlySales(it) },
                                onSelectPreset = { viewModel.selectPreset(it) },
                                onRecordTypeChange = { viewModel.updateRecordType(it) },
                                onRecordContentChange = { viewModel.updateRecordContent(it) },
                                onConsentChange = { viewModel.updateConsent(it) },
                                onRunAnalysis = { viewModel.runCreditAnalysis() },
                                onFinalizeDossier = { viewModel.finalizeDossier() },
                                onNavigateToPortal = { navigateToTab(AppTab.PORTAL) },
                                onSpeakText = { viewModel.speakText(it) }
                            )
                        }

                        composable<AppRoute.CalculatorRoute> {
                            MiddlemanCalculatorScreen(
                                state = calculatorState,
                                currentLang = currentLang,
                                onLoanAmountChange = { viewModel.updateCalcLoanAmount(it) },
                                onAgentPctChange = { viewModel.updateCalcAgentPct(it) },
                                onMoneylenderRateChange = { viewModel.updateCalcMoneylenderRate(it) },
                                onTenureYearsChange = { viewModel.updateCalcTenureYears(it) },
                                onSpeakText = { viewModel.speakText(it) }
                            )
                        }

                        composable<AppRoute.PortalRoute> {
                            BranchManagerPortalScreen(
                                dossiers = dossiers,
                                portalState = portalState,
                                currentLang = currentLang,
                                onFilterChange = { viewModel.updatePortalFilter(it) },
                                onSearchChange = { viewModel.updatePortalSearch(it) },
                                onInspectDossier = { viewModel.inspectDossier(it) },
                                onSanctionDossier = { viewModel.sanctionDossier(it) },
                                onOpenPrintableDossier = { dossier ->
                                    viewModel.openDossierInViewer(dossier)
                                },
                                onSpeakText = { viewModel.speakText(it) }
                            )
                        }

                        composable<AppRoute.QuestionsRoute> {
                            FieldQuestionsScreen(
                                activeQuestionIndex = activeQuestionIndex,
                                currentLang = currentLang,
                                onSelectQuestion = { viewModel.selectFieldQuestion(it) },
                                onSpeakText = { viewModel.speakText(it) },
                                onNavigateToWizard = { navigateToTab(AppTab.WIZARD) }
                            )
                        }
                    }
                }
            }
        }
    }

    VoiceAssistantDialog(
        uiState = voiceState,
        currentLang = currentLang,
        onDismiss = { viewModel.closeVoiceModal() },
        onQueryChange = { viewModel.updateVoiceQuery(it) },
        onSetRecording = { viewModel.setVoiceRecording(it) },
        onSubmitQuery = { viewModel.submitVoiceQuery(it) },
        onSpeakText = { viewModel.speakText(it) }
    )
}
