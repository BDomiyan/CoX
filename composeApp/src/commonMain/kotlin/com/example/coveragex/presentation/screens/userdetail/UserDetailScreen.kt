package com.example.coveragex.presentation.screens.userdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.coveragex.domain.model.User
import com.example.coveragex.presentation.components.DetailScreenSkeleton
import com.example.coveragex.presentation.screens.userdetail.components.ContactInfoSection
import com.example.coveragex.presentation.screens.userdetail.components.ContactInfoSection
import com.example.coveragex.presentation.screens.userdetail.components.ProfileSection
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions
import com.example.coveragex.presentation.theme.Strings
import com.example.coveragex.presentation.theme.TextStyles
import org.koin.compose.viewmodel.koinViewModel

/**
 * User detail screen showing full user information.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    user: User,
    onBackClick: () -> Unit,
    viewModel: UserDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(user.id) {
        viewModel.loadUser(user)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        Strings.SCREEN_TITLE_CONTACT,
                        fontSize = TextStyles.BodySize,
                        fontWeight = TextStyles.Semibold,
                        color = AppColors.TextPrimary,
                        letterSpacing = TextStyles.BodySpacing
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = Strings.CD_BACK,
                            tint = AppColors.SystemBlue,
                            modifier = Modifier.size(Dimensions.iconSizeLarge)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.BackgroundPrimary
                )
            )
        }
    ) { paddingValues ->
        when (val currentState = state) {
            is UserDetailState.Loading -> {
                DetailScreenSkeleton(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            is UserDetailState.Success -> {
                UserDetailContent(
                    user = currentState.user,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun UserDetailContent(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.BackgroundPrimary)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Section
        ProfileSection(
            user = user
        )

        Spacer(modifier = Modifier.height(Dimensions.spacingMassive))

        // Contact Information Section
        ContactInfoSection(
            user = user
        )

        Spacer(modifier = Modifier.height(Dimensions.spacingMassive))
    }
}
