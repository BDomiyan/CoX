package com.example.coveragex

import androidx.compose.runtime.Composable
import com.example.coveragex.presentation.navigation.AppNavigation
import com.example.coveragex.presentation.theme.CoverageXTheme

/**
 * Main app composable.
 * Entry point for the Compose UI.
 */
@Composable
fun App() {
    CoverageXTheme {
        AppNavigation()
    }
}
