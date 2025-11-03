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
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions

/**
 * Loading skeleton for detail screen with shimmer effect.
 */
@Composable
fun DetailScreenSkeleton(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.BackgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Section Skeleton
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.BackgroundWhite)
                .padding(
                    top = Dimensions.spacingGiant,
                    bottom = Dimensions.paddingXXLarge
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar skeleton
            Box(
                modifier = Modifier
                    .size(Dimensions.avatarSizeLarge)
                    .clip(CircleShape)
                    .background(AppColors.SkeletonColor.copy(alpha = alpha))
            )

            Spacer(modifier = Modifier.height(Dimensions.spacingXXLarge))

            // Name skeleton
            Box(
                modifier = Modifier
                    .width(Dimensions.skeletonNameWidth)
                    .height(Dimensions.skeletonNameHeight)
                    .clip(RoundedCornerShape(Dimensions.spacingSmall))
                    .background(AppColors.SkeletonColor.copy(alpha = alpha))
            )
        }

        Spacer(modifier = Modifier.height(Dimensions.spacingMassive))

        // Contact Information Section Skeleton
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.BackgroundWhite)
                .padding(vertical = Dimensions.spacingMedium)
        ) {
            repeat(3) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = Dimensions.paddingXLarge,
                            vertical = Dimensions.paddingLarge
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon skeleton
                    Box(
                        modifier = Modifier
                            .size(Dimensions.iconSizeXLarge)
                            .clip(CircleShape)
                            .background(AppColors.SkeletonColor.copy(alpha = alpha))
                    )

                    Spacer(modifier = Modifier.width(Dimensions.spacingXXLarge))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall)
                    ) {
                        // Label skeleton
                        Box(
                            modifier = Modifier
                                .width(Dimensions.skeletonLabelWidth)
                                .height(Dimensions.skeletonLabelHeight)
                                .clip(RoundedCornerShape(Dimensions.spacingXSmall))
                                .background(AppColors.SkeletonColor.copy(alpha = alpha))
                        )

                        // Value skeleton
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(Dimensions.skeletonValueHeight)
                                .clip(RoundedCornerShape(Dimensions.spacingXSmall))
                                .background(AppColors.SkeletonColor.copy(alpha = alpha))
                        )
                    }
                }

                if (index < 2) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = Dimensions.dividerPaddingDetail)
                            .height(Dimensions.dividerThickness)
                            .background(AppColors.DividerColor)
                    )
                }
            }
        }
    }
}
