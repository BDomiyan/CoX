package com.example.coveragex.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions
import com.example.coveragex.presentation.theme.Strings
import com.example.coveragex.presentation.theme.TextStyles

/**
 * iOS-style empty state component with refined styling.
 */
@Composable
fun EmptyState(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(Dimensions.paddingXXLarge)
        ) {
            // Empty state icon with refined styling
            Icon(
                imageVector = Icons.Default.PersonOff,
                contentDescription = Strings.CD_NO_RESULTS,
                modifier = Modifier.size(Dimensions.iconSizeXXLarge),
                tint = AppColors.Gray3
            )

            Spacer(modifier = Modifier.height(Dimensions.spacingXXLarge))

            Text(
                text = message,
                fontSize = TextStyles.BodySize,
                fontWeight = TextStyles.Regular,
                color = AppColors.TextSecondary,
                textAlign = TextAlign.Center,
                letterSpacing = TextStyles.BodySpacing,
                lineHeight = TextStyles.BodyLineHeight
            )
        }
    }
}
