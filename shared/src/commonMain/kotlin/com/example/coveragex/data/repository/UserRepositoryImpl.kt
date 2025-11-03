package com.example.coveragex.data.repository

import com.example.coveragex.data.api.RandomUserApi
import com.example.coveragex.data.mapper.UserMapper
import com.example.coveragex.domain.model.User
import com.example.coveragex.util.Logger
import com.example.coveragex.util.NetworkResult

/**
 * Implementation of UserRepository that fetches data from the Random User API.
 *
 * Responsibilities:
 * - Calls the API through RandomUserApi
 * - Maps API DTOs to domain models using UserMapper
 * - Propagates errors as NetworkResult variants
 * - Logs operations (without PII)
 *
 * @param api The Random User API interface
 */
class UserRepositoryImpl(
    private val api: RandomUserApi
) : UserRepository {

    companion object {
        private const val TAG = "UserRepository"
    }

    /**
     * Fetches users from the API and maps them to domain models.
     *
     * Flow:
     * 1. Call API to fetch users
     * 2. If successful, map DTOs to domain models
     * 3. Return success with domain models
     * 4. If error, propagate the error
     *
     * @param count Number of users to fetch
     * @return NetworkResult with list of domain User models or error
     */
    override suspend fun getUsers(count: Int): NetworkResult<List<User>> {
        Logger.d(TAG, "Fetching $count users from API")

        return when (val result = api.fetchUsers(count)) {
            is NetworkResult.Success -> {
                try {
                    // Map DTOs to domain models
                    val users = result.data.results.mapIndexed { index, userDto ->
                        UserMapper.fromDto(userDto, index)
                    }

                    Logger.d(TAG, "Successfully fetched and mapped ${users.size} users")
                    NetworkResult.Success(users)
                } catch (e: Exception) {
                    // Mapping error (shouldn't happen with proper null handling)
                    Logger.e(TAG, "Error mapping users", e)
                    NetworkResult.UnknownError(e)
                }
            }

            is NetworkResult.HttpError -> {
                Logger.e(TAG, "HTTP error fetching users: ${result.code}")
                result
            }

            is NetworkResult.NetworkError -> {
                Logger.e(TAG, "Network error fetching users", result.throwable)
                result
            }

            is NetworkResult.SerializationError -> {
                Logger.e(TAG, "Serialization error fetching users", result.throwable)
                result
            }

            is NetworkResult.UnknownError -> {
                Logger.e(TAG, "Unknown error fetching users", result.throwable)
                result
            }
        }
    }
}
