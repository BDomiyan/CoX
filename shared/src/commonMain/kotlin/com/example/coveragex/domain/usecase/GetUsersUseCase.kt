package com.example.coveragex.domain.usecase

import com.example.coveragex.data.repository.UserRepository
import com.example.coveragex.domain.model.User
import com.example.coveragex.util.NetworkResult

/**
 * Use case for fetching a list of users.
 *
 * This use case:
 * - Provides a single entry point for the "get users" operation
 *
 * @param repository The user repository for data access
 */
class GetUsersUseCase(
    private val repository: UserRepository
) {

    /**
     * Executes the use case to fetch users.
     *
     * @param count Number of users to fetch (default: 20)
     * @return NetworkResult containing either a list of users or an error
     */
    suspend operator fun invoke(count: Int = 20): NetworkResult<List<User>> {
        // Input validation
        require(count > 0) { "Count must be positive" }
        require(count <= 100) { "Count must not exceed 100" }

        // Delegate to repository
        return repository.getUsers(count)
    }
}
