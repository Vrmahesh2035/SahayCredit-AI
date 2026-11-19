package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.SahayCreditApp
import com.example.ui.theme.SahayCreditTheme
import com.example.viewmodel.SahayCreditViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SahayCreditTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                val sahayViewModel: SahayCreditViewModel = viewModel()
                SahayCreditApp(
                    viewModel = sahayViewModel,
                    windowSizeClass = windowSizeClass
                )
            }
        }
    }
}
