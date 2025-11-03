package com.example.coveragex.data.repository

import com.example.coveragex.domain.model.User
import com.example.coveragex.util.NetworkResult

/**
 * Repository interface for user data operations.
 *
 * This abstraction allows:
 * - Multiple implementations (API, cache, mock, etc.)
 * - Easy testing with fake implementations
 * - Separation of concerns between data sources and business logic
 * - Flexibility to change data sources without affecting consumers
 */
interface UserRepository {

    /**
     * Fetches a list of users.
     *
     * @param count Number of users to fetch (default: 20)
     * @return NetworkResult containing either a list of domain User models or an error
     */
    suspend fun getUsers(count: Int = 20): NetworkResult<List<User>>

}
