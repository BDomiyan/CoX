package com.example.coveragex.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.coveragex.presentation.theme.Constants
import com.example.coveragex.presentation.theme.Dimensions

/**
 * Shimmer loading skeleton for user list items.
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        repeat(Constants.Shimmer.SKELETON_ITEM_COUNT) {
            ShimmerUserItem()
            if (it < Constants.Shimmer.SKELETON_ITEM_COUNT - 1) {
                Spacer(modifier = Modifier.height(Dimensions.shimmerItemSpacing))
            }
        }
    }
}

@Composable
private fun ShimmerUserItem() {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = Constants.Shimmer.ALPHA_MIN,
        targetValue = Constants.Shimmer.ALPHA_MAX,
        animationSpec = infiniteRepeatable(
            animation = tween(Constants.Animation.SHIMMER_DURATION),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(Dimensions.shimmerItemPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar placeholder
        Box(
            modifier = Modifier
                .size(Dimensions.shimmerAvatarSize)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = alpha))
        )

        Spacer(modifier = Modifier.width(Dimensions.spacingXLarge))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Name placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(Constants.SkeletonWidth.NAME_FRACTION)
                    .height(Dimensions.shimmerNameHeight)
                    .clip(RoundedCornerShape(Dimensions.shimmerCornerRadius))
                    .background(Color.LightGray.copy(alpha = alpha))
            )

            Spacer(modifier = Modifier.height(Dimensions.spacingMedium))

            // Email placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(Constants.SkeletonWidth.EMAIL_FRACTION)
                    .height(Dimensions.shimmerEmailHeight)
                    .clip(RoundedCornerShape(Dimensions.shimmerCornerRadius))
                    .background(Color.LightGray.copy(alpha = alpha))
            )
        }
    }
}
