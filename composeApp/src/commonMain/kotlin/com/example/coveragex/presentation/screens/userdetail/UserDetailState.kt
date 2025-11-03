package com.example.coveragex.presentation.screens.userdetail

import com.example.coveragex.domain.model.User

/**
 * UI state for user detail screen.
 */
sealed class UserDetailState {
    data object Loading : UserDetailState()
    data class Success(val user: User) : UserDetailState()
}
