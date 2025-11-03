package com.example.coveragex.presentation.screens.userdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.coveragex.domain.model.User
import com.example.coveragex.presentation.components.UserAvatar
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions
import com.example.coveragex.presentation.theme.TextStyles

/**
 * Profile section component for user detail screen.
 */
@Composable
fun ProfileSection(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.BackgroundWhite)
            .padding(
                top = Dimensions.spacingGiant,
                bottom = Dimensions.paddingXXLarge
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Large profile picture
        Box(
            modifier = Modifier.size(Dimensions.avatarSizeLarge)
        ) {
            UserAvatar(
                imageUrl = user.pictureLarge,
                contentDescription = user.fullName,
                size = Dimensions.avatarSizeLarge
            )
        }

        Spacer(modifier = Modifier.height(Dimensions.spacingXXLarge))

        // Full name with refined typography
        Text(
            text = user.fullName,
            fontSize = TextStyles.Title1Size,
            fontWeight = TextStyles.Bold,
            color = AppColors.TextPrimary,
            letterSpacing = TextStyles.Title1Spacing
        )
    }
}
