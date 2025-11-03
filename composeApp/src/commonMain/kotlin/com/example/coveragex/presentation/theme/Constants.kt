package com.example.coveragex.presentation.theme

/**
 * Application-wide constants
 */
object Constants {
    // Animation durations (in milliseconds)
    object Animation {
        const val SHIMMER_DURATION = 1000
        const val FADE_DURATION = 300
        const val SLIDE_DURATION = 400
    }
    
    // Shimmer animation values
    object Shimmer {
        const val ALPHA_MIN = 0.3f
        const val ALPHA_MAX = 0.7f
        const val SKELETON_ITEM_COUNT = 5
    }
    
    // Skeleton placeholder widths (as fractions)
    object SkeletonWidth {
        const val NAME_FRACTION = 0.6f
        const val EMAIL_FRACTION = 0.8f
    }
}
