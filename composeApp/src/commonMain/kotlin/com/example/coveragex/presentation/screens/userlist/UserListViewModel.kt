package com.example.coveragex.presentation.screens.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coveragex.domain.usecase.GetUsersUseCase
import com.example.coveragex.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the user list screen.
 */
class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UserListState>(UserListState.Initial)
    val state: StateFlow<UserListState> = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadUsers()
    }

    /**
     * Load users from the API
     */
    fun loadUsers() {
        viewModelScope.launch {
            _state.value = UserListState.Loading
            fetchUsers()
        }
    }

    /**
     * Refresh users (for pull-to-refresh)
     */
    fun refresh() {
        viewModelScope.launch {
            // Set refreshing state while keeping current data visible
            val currentState = _state.value
            if (currentState is UserListState.Success) {
                _state.value = currentState.copy(isRefreshing = true)
            }
            
            fetchUsers()
        }
    }

    /**
     * Fetch users from the use case
     */
    private suspend fun fetchUsers() {
        when (val result = getUsersUseCase(count = 20)) {
            is NetworkResult.Success -> {
                val currentQuery = _searchQuery.value
                val filteredUsers = if (currentQuery.isBlank()) {
                    result.data
                } else {
                    result.data.filter { user ->
                        user.fullName.contains(currentQuery, ignoreCase = true)
                    }
                }
                
                _state.value = UserListState.Success(
                    users = result.data,
                    filteredUsers = filteredUsers,
                    isRefreshing = false
                )
            }
            is NetworkResult.HttpError -> {
                _state.value = UserListState.Error(
                    "Server error: ${result.code}"
                )
            }
            is NetworkResult.NetworkError -> {
                _state.value = UserListState.Error(
                    "Network error. Please check your connection."
                )
            }
            is NetworkResult.SerializationError -> {
                _state.value = UserListState.Error(
                    "Data parsing error"
                )
            }
            is NetworkResult.UnknownError -> {
                _state.value = UserListState.Error(
                    "An unexpected error occurred"
                )
            }
        }
    }

    /**
     * Update search query and filter users
     */
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query

        val currentState = _state.value
        if (currentState is UserListState.Success) {
            val filtered = if (query.isBlank()) {
                currentState.users
            } else {
                currentState.users.filter { user ->
                    user.fullName.contains(query, ignoreCase = true)
                }
            }

            _state.value = currentState.copy(filteredUsers = filtered)
        }
    }


}
