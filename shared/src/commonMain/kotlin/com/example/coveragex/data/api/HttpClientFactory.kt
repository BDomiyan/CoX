// Factory for creating configured Ktor HttpClient with platform-specific engines.
// SOLID: Single Responsibility - HTTP client creation; Factory pattern for object creation.
package com.example.coveragex.data.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Factory for creating configured HttpClient instances.
 *
 * Platform-specific engines are automatically selected:
 * - Android: OkHttp engine (from ktor-client-okhttp dependency)
 * - iOS: Darwin engine (from ktor-client-darwin dependency)
 *
 * Ktor uses ServiceLoader to find the appropriate engine on the classpath.
 */
object HttpClientFactory {

    /**
     * Creates a configured HttpClient with:
     * - Content negotiation for JSON serialization
     * - Logging for debugging
     * - Timeout configuration
     *
     * @return Configured HttpClient instance
     */
    fun createClient(): HttpClient = HttpClient {
        // Install JSON content negotiation
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true  // Ignore fields not in our DTOs
                prettyPrint = true         // Pretty print for debugging
                isLenient = true           // Be lenient with malformed JSON
                encodeDefaults = true      // Include default values when encoding
            })
        }

        // Install logging for debugging
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    com.example.coveragex.util.Logger.d("HttpClient", message)
                }
            }
            level = LogLevel.INFO 
        }
    }
}