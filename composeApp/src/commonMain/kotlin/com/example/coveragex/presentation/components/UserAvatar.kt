package com.example.coveragex.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions

/**
 * Reusable circular avatar component with placeholder.
 */
@Composable
fun UserAvatar(
    imageUrl: String,
    contentDescription: String?,
    size: Dp = Dimensions.avatarSizeMedium,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(AppColors.AvatarBackground),
        contentAlignment = Alignment.Center
    ) {
        if (imageUrl.isEmpty()) {
            // Show placeholder icon when no image URL
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = contentDescription,
                modifier = Modifier.size(size * 0.5f),
                tint = AppColors.Gray1
            )
        } else {
            // Show image directly using AsyncImage
            AsyncImage(
                url = imageUrl,
                contentDescription = contentDescription,
                modifier = Modifier.size(size)
            )
        }
    }
}
