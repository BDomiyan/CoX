package com.example.coveragex.domain.usecase

import com.example.coveragex.data.api.GenericHttpClient
import com.example.coveragex.data.api.RandomUserApiImpl
import com.example.coveragex.data.repository.UserRepositoryImpl
import com.example.coveragex.util.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetUsersUseCaseIntegrationTest {

    @Test
    fun `invoke with real repository fetches actual data`() = runTest {
        // Given
        val count = 5

        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }
        }

        val genericHttpClient = GenericHttpClient(httpClient)
        val randomUserApi = RandomUserApiImpl(genericHttpClient)
        val userRepository = UserRepositoryImpl(randomUserApi)
        val getUsersUseCase = GetUsersUseCase(userRepository)

        // When
        val result = getUsersUseCase(count)

        // Then
        assertTrue(result is NetworkResult.Success)
        assertEquals(count, (result as NetworkResult.Success).data.size)
    }
}
