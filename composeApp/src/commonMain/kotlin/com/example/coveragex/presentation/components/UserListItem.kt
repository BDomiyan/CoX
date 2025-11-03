package com.example.coveragex.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.coveragex.domain.model.User
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions
import com.example.coveragex.presentation.theme.TextStyles

/**
 * User list item component with iOS-style design - thumbnail and name only.
 */
@Composable
fun UserListItem(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.BackgroundWhite)
                .clickable(onClick = onClick)
                .padding(
                    horizontal = Dimensions.paddingXLarge,
                    vertical = Dimensions.paddingMedium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // User avatar thumbnail
            UserAvatar(
                imageUrl = user.pictureThumb,
                contentDescription = user.fullName,
                size = Dimensions.avatarSizeSmall
            )

            Spacer(modifier = Modifier.width(Dimensions.spacingXLarge))

            // User name only
            Text(
                text = user.fullName,
                fontSize = TextStyles.BodySize,
                fontWeight = TextStyles.Semibold,
                color = AppColors.TextPrimary,
                letterSpacing = TextStyles.BodySpacing,
                modifier = Modifier.weight(1f)
            )

            // Chevron icon
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = AppColors.ChevronColor,
                modifier = Modifier.size(Dimensions.iconSizeMedium)
            )
        }

        // iOS-style divider with precise left padding
        Divider(
            modifier = Modifier.padding(start = Dimensions.dividerPaddingList),
            thickness = Dimensions.dividerThickness,
            color = AppColors.DividerColor
        )
    }
}
