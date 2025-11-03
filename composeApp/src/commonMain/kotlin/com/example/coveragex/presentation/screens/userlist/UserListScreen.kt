package com.example.coveragex.presentation.screens.userlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.example.coveragex.domain.model.User
import com.example.coveragex.presentation.components.*
import com.example.coveragex.presentation.theme.AppColors
import com.example.coveragex.presentation.theme.Dimensions
import com.example.coveragex.presentation.theme.Strings
import com.example.coveragex.presentation.theme.TextStyles
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel


/**
 * Main user list screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    onUserClick: (User) -> Unit,
    viewModel: UserListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.BackgroundPrimary)
                    .padding(horizontal = Dimensions.paddingXLarge)
            ) {
                Spacer(modifier = Modifier.height(Dimensions.spacingLarge))

                // App title - iOS Large Title style
                Text(
                    text = Strings.SCREEN_TITLE_CONTACTS,
                    fontSize = TextStyles.LargeTitleSize,
                    fontWeight = TextStyles.Bold,
                    color = AppColors.TextPrimary,
                    letterSpacing = TextStyles.LargeTitleSpacing
                )

                Spacer(modifier = Modifier.height(Dimensions.spacingXXLarge))

                // Search bar
                SearchBar(
                    query = searchQuery,
                    onQueryChange = viewModel::onSearchQueryChange
                )

                Spacer(modifier = Modifier.height(Dimensions.paddingSmall))
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(AppColors.BackgroundPrimary)
        ) {
            when (val currentState = state) {
                is UserListState.Initial,
                is UserListState.Loading -> {
                    LoadingIndicator()
                }

                is UserListState.Success -> {
                    if (currentState.filteredUsers.isEmpty()) {
                        EmptyState(
                            message = if (searchQuery.isBlank()) {
                                Strings.EMPTY_NO_USERS
                            } else {
                                "No results found for \"$searchQuery\""
                            }
                        )
                    } else {
                        UserListWithRefresh(
                            users = currentState.filteredUsers,
                            isRefreshing = currentState.isRefreshing,
                            onUserClick = onUserClick,
                            onRefresh = viewModel::refresh
                        )
                    }
                }

                is UserListState.Error -> {
                    ErrorView(
                        message = currentState.message,
                        onRetry = viewModel::loadUsers
                    )
                }
            }
        }
    }
}

@Composable
private fun UserListWithRefresh(
    users: List<User>,
    isRefreshing: Boolean,
    onUserClick: (User) -> Unit,
    onRefresh: () -> Unit
) {
    val listState = rememberLazyListState()
    var refreshOffset by remember { mutableStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    val maxRefreshOffset = 120f
    val refreshThreshold = 80f
    
    // Check if we're at the top
    val isAtTop = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0 && 
            listState.firstVisibleItemScrollOffset == 0
        }
    }
    
    // Reset offset when refresh completes with animation
    LaunchedEffect(isRefreshing) {
        if (!isRefreshing && refreshOffset > 0) {
            // Animate the offset back to 0
            kotlinx.coroutines.delay(100) // Small delay to show completion
            refreshOffset = 0f
            isDragging = false
        }
    }
    
    // Custom nested scroll connection for pull-to-refresh
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                // When pulling down (negative scroll) at the top
                if (isAtTop.value && available.y < 0 && refreshOffset > 0) {
                    val consumed = -available.y.coerceAtMost(refreshOffset)
                    refreshOffset -= consumed
                    return Offset(0f, -consumed)
                }
                return Offset.Zero
            }
            
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                // When pulling down at the top with remaining scroll
                if (isAtTop.value && available.y > 0 && !isRefreshing) {
                    val dragConsumed = available.y * 0.5f
                    refreshOffset = (refreshOffset + dragConsumed).coerceIn(0f, maxRefreshOffset)
                    return Offset(0f, available.y)
                }
                return Offset.Zero
            }
            
            override suspend fun onPreFling(available: Velocity): Velocity {
                // Trigger refresh if threshold is met
                if (refreshOffset >= refreshThreshold && !isRefreshing) {
                    isDragging = true
                    onRefresh()
                } else {
                    // Animate back to 0 if threshold not met
                    refreshOffset = 0f
                    isDragging = false
                }
                return Velocity.Zero
            }
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Pull-to-refresh indicator at the top
            AnimatedVisibility(
                visible = (refreshOffset > 0 || isRefreshing) && (isDragging || isRefreshing),
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(AppColors.BackgroundWhite),
                    contentAlignment = Alignment.Center
                ) {
                    if (isRefreshing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(28.dp),
                            color = AppColors.SystemBlue,
                            strokeWidth = 3.dp
                        )
                    } else if (refreshOffset > 0) {
                        val progress = (refreshOffset / refreshThreshold).coerceIn(0f, 1f)
                        val rotation = progress * 180f
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            tint = AppColors.SystemBlue.copy(alpha = progress),
                            modifier = Modifier
                                .size(28.dp)
                                .graphicsLayer { rotationZ = rotation }
                        )
                    }
                }
            }
            
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppColors.BackgroundWhite)
            ) {
                items(
                    items = users,
                    key = { it.id }
                ) { user ->
                    UserListItem(
                        user = user,
                        onClick = { onUserClick(user) }
                    )
                }
                
                // Bottom spacing
                item {
                    Spacer(modifier = Modifier.height(Dimensions.spacingXXXLarge))
                }
            }
        }
    }
}
