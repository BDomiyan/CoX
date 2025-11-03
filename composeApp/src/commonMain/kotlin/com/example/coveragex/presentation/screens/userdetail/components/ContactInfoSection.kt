package com.example.coveragex.presentation.screens.userdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coveragex.domain.model.User
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions
import com.example.coveragex.presentation.theme.Strings

/**
 * Contact information section component for user detail screen.
 */
@Composable
fun ContactInfoSection(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.BackgroundWhite)
            .padding(vertical = Dimensions.spacingMedium)
    ) {
        DetailItem(
            icon = Icons.Default.Email,
            label = Strings.LABEL_EMAIL,
            value = user.email,
            showDivider = true
        )

        DetailItem(
            icon = Icons.Default.Phone,
            label = Strings.LABEL_PHONE,
            value = user.phone,
            showDivider = true
        )

        DetailItem(
            icon = Icons.Default.LocationOn,
            label = Strings.LABEL_LOCATION,
            value = user.location,
            showDivider = false
        )
    }
}
