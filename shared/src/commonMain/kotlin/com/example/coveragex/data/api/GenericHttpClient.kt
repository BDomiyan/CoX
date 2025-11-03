// Generic HTTP client wrapper providing type-safe error handling for all network operations.
// SOLID: Single Responsibility - HTTP operations; Open/Closed - reusable for any API endpoint.
package com.example.coveragex.data.api

import com.example.coveragex.util.Logger
import com.example.coveragex.util.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**
 * Generic HTTP client wrapper that provides comprehensive error handling
 * and logging for all network operations.
 *
 * This class wraps Ktor HttpClient and converts all possible outcomes into
 * a type-safe NetworkResult, eliminating exception-based error handling.
 *
 * @param client The underlying Ktor HttpClient
 */
class GenericHttpClient(private val client: HttpClient) {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    companion object {
        private const val TAG = "GenericHttpClient"
    }

    /**
     * Performs a GET request and returns a type-safe result.
     *
     * @param url The endpoint URL
     * @param serializer KSerializer for deserializing the response
     * @param params Query parameters as key-value pairs
     * @return NetworkResult containing either success data or specific error type
     */
    suspend fun <T> get(
        url: String,
        serializer: KSerializer<T>,
        params: Map<String, String> = emptyMap()
    ): NetworkResult<T> {
        Logger.d(TAG, "GET request to: $url with params: $params")

        return try {
            val response: HttpResponse = client.get(url) {
                params.forEach { (key, value) ->
                    parameter(key, value)
                }
            }

            val statusCode = response.status.value
            Logger.d(TAG, "Response status: $statusCode")

            if (statusCode in 200..299) {
                runCatching {
                    val bodyString = response.bodyAsText()
                    json.decodeFromString(serializer, bodyString)
                }.fold(
                    onSuccess = {
                        Logger.d(TAG, "Successfully deserialized response")
                        NetworkResult.Success(it)
                    },
                    onFailure = {
                        Logger.e(TAG, "Serialization error", it)
                        NetworkResult.SerializationError(it)
                    }
                )
            } else {
                val errorBody = try {
                    response.bodyAsText()
                } catch (e: Exception) {
                    null
                }
                Logger.e(TAG, "HTTP error $statusCode: $errorBody")
                NetworkResult.HttpError(statusCode, errorBody)
            }
        } catch (e: IOException) {
            Logger.e(TAG, "Network error", e)
            NetworkResult.NetworkError(e)
        } catch (e: Exception) {
            Logger.e(TAG, "Unknown error", e)
            NetworkResult.UnknownError(e)
        }
    }

    // TODO: Add POST method for creating resources
    // suspend fun <T, R> post(url: String, body: T, serializer: KSerializer<R>): NetworkResult<R>

    // TODO: Add PUT method for updating resources
    // suspend fun <T, R> put(url: String, body: T, serializer: KSerializer<R>): NetworkResult<R>

    // TODO: Add DELETE method
    // suspend fun <T> delete(url: String, serializer: KSerializer<T>): NetworkResult<T>

    // TODO: Add retry logic with exponential backoff for transient failures
    // TODO: Add request timeout configuration
    // TODO: Add request/response interceptors for headers, auth tokens, etc.
}
