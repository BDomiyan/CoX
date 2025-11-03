package com.example.coveragex.util

/**
 * A sealed class representing the result of a network operation.
 * This eliminates exception-based error handling and forces consumers to handle all cases.
 *
 * @param T The type of data returned on success
 */
sealed class NetworkResult<out T> {
    /**
     * Successful network operation with data
     */
    data class Success<T>(val data: T) : NetworkResult<T>()

    /**
     * HTTP error response (4xx, 5xx status codes)
     */
    data class HttpError(val code: Int, val body: String?) : NetworkResult<Nothing>()

    /**
     * Network connectivity error (no internet, timeout, etc.)
     */
    data class NetworkError(val throwable: Throwable) : NetworkResult<Nothing>()

    /**
     * JSON serialization/deserialization error
     */
    data class SerializationError(val throwable: Throwable) : NetworkResult<Nothing>()

    /**
     * Unknown or unexpected error
     */
    data class UnknownError(val throwable: Throwable) : NetworkResult<Nothing>()
}
