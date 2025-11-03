// API interface and implementation for Random User API endpoints.
package com.example.coveragex.data.api

import com.example.coveragex.data.api.dto.RandomUserResponseDto
import com.example.coveragex.util.NetworkResult
import kotlin.math.max


/**
 * Interface defining Random User API operations. Provides abstraction for testing and allows
 * multiple implementations.
 */
interface RandomUserApi {
    /**
     * Fetches a list of random users from the API.
     *
     * @param count Number of users to fetch (default: 20)
     * @return NetworkResult containing either the response DTO or an error
     */
    suspend fun fetchUsers(count: Int = 20): NetworkResult<RandomUserResponseDto>
}

/**
 * Implementation of RandomUserApi using GenericHttpClient.
 *
 * @param client The HTTP client for making requests
 */
class RandomUserApiImpl(private val client: GenericHttpClient) : RandomUserApi {

    companion object {
        private const val BASE_URL = "https://randomuser.me/api/"
    }

    /**
     * Fetches random users from the API.
     *
     * Endpoint: GET https://randomuser.me/api/?results={count}
     *
     * @param count Number of users to fetch
     * @return NetworkResult with RandomUserResponseDto or error
     */
    override suspend fun fetchUsers(count: Int): NetworkResult<RandomUserResponseDto> {
        val safeCount = max(1, count) // Ensure count is at least 1
        return client.get(
                url = BASE_URL,
                serializer = RandomUserResponseDto.serializer(),
                params = mapOf("results" to safeCount.toString())
        )
    }
}
