package com.example.coveragex.presentation.screens.userlist

import com.example.coveragex.domain.model.User

/**
 * UI state for the user list screen.
 */
sealed class UserListState {
    /**
     * Initial state before any data is loaded
     */
    data object Initial : UserListState()

    /**
     * Loading state while fetching data
     */
    data object Loading : UserListState()

    /**
     * Success state with user data
     * @param users All users from API
     * @param filteredUsers Users after applying search filter
     * @param isRefreshing Whether pull-to-refresh is active
     */
    data class Success(
        val users: List<User>,
        val filteredUsers: List<User>,
        val isRefreshing: Boolean = false
    ) : UserListState()

    /**
     * Error state when data fetch fails
     * @param message Error message to display
     */
    data class Error(val message: String) : UserListState()
}
