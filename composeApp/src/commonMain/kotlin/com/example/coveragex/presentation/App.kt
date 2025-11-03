package com.example.coveragex.presentation

import androidx.compose.runtime.Composable
import com.example.coveragex.presentation.navigation.AppNavigation
import com.example.coveragex.presentation.theme.CoverageXTheme

@Composable
fun App() {
    CoverageXTheme {
        AppNavigation()
    }
}
