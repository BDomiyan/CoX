package com.example.coveragex.presentation.screens.userdetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions
import com.example.coveragex.presentation.theme.TextStyles

/**
 * Detail item component for displaying contact information.
 */
@Composable
fun DetailItem(
    icon: ImageVector,
    label: String,
    value: String,
    showDivider: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Dimensions.paddingXLarge,
                    vertical = Dimensions.paddingLarge
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon with refined styling
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = AppColors.SystemBlue,
                modifier = Modifier.size(Dimensions.iconSizeXLarge)
            )

            Spacer(modifier = Modifier.width(Dimensions.spacingXXLarge))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall)
            ) {
                Text(
                    text = label,
                    fontSize = TextStyles.FootnoteSize,
                    fontWeight = TextStyles.Regular,
                    color = AppColors.TextSecondary,
                    letterSpacing = TextStyles.FootnoteSpacing
                )

                Text(
                    text = value,
                    fontSize = TextStyles.BodySize,
                    fontWeight = TextStyles.Regular,
                    color = AppColors.TextPrimary,
                    letterSpacing = TextStyles.BodySpacing
                )
            }
        }

        if (showDivider) {
            Divider(
                modifier = Modifier.padding(start = Dimensions.dividerPaddingDetail),
                thickness = Dimensions.dividerThickness,
                color = AppColors.DividerColor
            )
        }
    }
}
