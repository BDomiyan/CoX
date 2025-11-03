package com.example.coveragex.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions
import com.example.coveragex.presentation.theme.Strings
import com.example.coveragex.presentation.theme.TextStyles

/**
 * iOS-style search bar component with refined styling.
 */
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.searchBarHeight)
            .background(AppColors.SearchBackground, RoundedCornerShape(Dimensions.cornerRadiusSmall))
            .padding(horizontal = Dimensions.paddingSmall),
        singleLine = true,
        cursorBrush = SolidColor(AppColors.SystemBlue),
        textStyle = TextStyle(
            fontSize = TextStyles.BodySize,
            fontWeight = TextStyles.Regular,
            color = AppColors.TextPrimary,
            letterSpacing = TextStyles.BodySpacing
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = Strings.CD_SEARCH,
                    tint = AppColors.Gray1,
                    modifier = Modifier.size(Dimensions.iconSizeSmall)
                )

                Spacer(modifier = Modifier.width(Dimensions.spacingMedium))

                Box(modifier = Modifier.weight(1f)) {
                    if (query.isEmpty()) {
                        Text(
                            text = Strings.SEARCH_PLACEHOLDER,
                            color = AppColors.Gray1,
                            fontSize = TextStyles.BodySize,
                            fontWeight = TextStyles.Regular,
                            letterSpacing = TextStyles.BodySpacing
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}
