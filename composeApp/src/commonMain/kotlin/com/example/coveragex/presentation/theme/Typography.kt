package com.example.coveragex.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    // App title
    displayLarge = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary
    ),
    // User name in list
    bodyLarge = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    ),
    // Email/location in list
    bodyMedium = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        color = TextSecondary
    ),
    // Error message
    titleMedium = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    ),
    // Retry button
    labelLarge = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium,
        color = AccentBlue
    )
)
