package com.example.coveragex.presentation.theme

import androidx.compose.ui.graphics.Color

/**
 * iOS-style color palette
 */
object AppColors {
    // Primary colors
    val Black = Color(0xFF000000)
    val White = Color(0xFFFFFFFF)
    
    // Background colors
    val BackgroundPrimary = Color(0xFFFAFAFA)
    val BackgroundSecondary = Color(0xFFF9F9F9)
    val BackgroundWhite = Color(0xFFFFFFFF)
    
    // Text colors
    val TextPrimary = Color(0xFF000000)
    val TextSecondary = Color(0xFF8E8E93)
    val TextTertiary = Color(0xFF999999)
    
    // iOS System colors
    val SystemBlue = Color(0xFF007AFF)
    val SystemRed = Color(0xFFFF3B30)
    
    // Gray scale (iOS)
    val Gray1 = Color(0xFF8E8E93)
    val Gray2 = Color(0xFFC7C7CC)
    val Gray3 = Color(0xFFD1D1D6)
    val Gray4 = Color(0xFFE5E5EA)
    val Gray5 = Color(0xFFE8E8ED)
    
    // Component specific
    val SearchBackground = Color(0xFFE8E8ED)
    val AvatarBackground = Color(0xFFE5E5EA)
    val DividerColor = Color(0xFFD1D1D6)
    val ChevronColor = Color(0xFFD1D1D6)
    val SkeletonColor = Color(0xFFE5E5EA)
}

// Legacy support - will be removed
val PrimaryBlack = AppColors.Black
val PrimaryWhite = AppColors.White
val BackgroundLight = AppColors.BackgroundPrimary
val BackgroundWhite = AppColors.BackgroundWhite
val TextPrimary = AppColors.TextPrimary
val TextSecondary = AppColors.TextSecondary
val TextTertiary = AppColors.TextTertiary
val ErrorRed = AppColors.SystemRed
val ErrorBackground = Color(0xFFFFE5E5)
val AccentBlue = AppColors.SystemBlue
val BorderLight = Color(0xFFE0E0E0)
val SearchBackground = AppColors.SearchBackground
val SearchIcon = AppColors.Gray1
