package com.example.coveragex.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions
import com.example.coveragex.presentation.theme.Strings
import com.example.coveragex.presentation.theme.TextStyles

/**
 * iOS-style error state component with refined styling.
 */
@Composable
fun ErrorView(
    message: String = Strings.ERROR_LOAD_USERS,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(Dimensions.paddingXXLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Error icon with refined styling
            Icon(
                imageVector = Icons.Default.WifiOff,
                contentDescription = Strings.CD_ERROR,
                modifier = Modifier.size(Dimensions.iconSizeXXLarge),
                tint = AppColors.Gray3
            )

            Spacer(modifier = Modifier.height(Dimensions.spacingXXLarge))

            // Error message 
            Text(
                text = message,
                fontSize = TextStyles.BodySize,
                fontWeight = TextStyles.Regular,
                color = AppColors.TextSecondary,
                textAlign = TextAlign.Center,
                letterSpacing = TextStyles.BodySpacing,
                lineHeight = TextStyles.BodyLineHeight
            )

            Spacer(modifier = Modifier.height(Dimensions.spacingHuge))

            Button(
                onClick = onRetry,
                shape = RoundedCornerShape(Dimensions.cornerRadiusMedium),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.SystemBlue
                ),
                modifier = Modifier.height(Dimensions.buttonHeightLarge),
                contentPadding = PaddingValues(horizontal = Dimensions.buttonPaddingHorizontal)
            ) {
                Text(
                    text = Strings.ERROR_TRY_AGAIN,
                    fontSize = TextStyles.BodySize,
                    fontWeight = TextStyles.Semibold,
                    color = AppColors.White,
                    letterSpacing = TextStyles.BodySpacing
                )
            }
        }
    }
}
