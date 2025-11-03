package com.example.coveragex.presentation.screens.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coveragex.domain.model.User
import com.example.coveragex.presentation.theme.Strings
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for user detail screen.
 */
class UserDetailViewModel : ViewModel() {
    
    private val _state = MutableStateFlow<UserDetailState>(UserDetailState.Loading)
    val state: StateFlow<UserDetailState> = _state.asStateFlow()
    
    /**
     * Load user details with smooth transition
     */
    fun loadUser(user: User) {
        viewModelScope.launch {
            _state.value = UserDetailState.Loading
            delay(Strings.LOADING_DELAY_MS) // Smooth transition delay
            _state.value = UserDetailState.Success(user)
        }
    }
}
