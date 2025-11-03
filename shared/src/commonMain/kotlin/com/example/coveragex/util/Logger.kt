package com.example.coveragex.util

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

/**
 * Centralized logger wrapping Napier.
 * Provides consistent logging interface across all platforms.
 */
object Logger {
    private var isInitialized = false

    /**
     * Initialize the logger. Call this once at app startup.
     * Safe to call multiple times - will only initialize once.
     */
    fun init() {
        if (!isInitialized) {
            Napier.base(DebugAntilog())
            isInitialized = true
        }
    }

    /**
     * Log debug message
     * @param tag Component or class name
     * @param message Log message
     */
    fun d(tag: String, message: String) {
        Napier.d(message, tag = tag)
    }

    /**
     * Log error message with optional throwable
     * @param tag Component or class name
     * @param message Error message
     * @param throwable Optional exception/error
     */
    fun e(tag: String, message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Napier.e(message, throwable, tag = tag)
        } else {
            Napier.e(message, tag = tag)
        }
    }

    /**
     * Log info message
     * @param tag Component or class name
     * @param message Log message
     */
    fun i(tag: String, message: String) {
        Napier.i(message, tag = tag)
    }

    /**
     * Log warning message
     * @param tag Component or class name
     * @param message Warning message
     */
    fun w(tag: String, message: String) {
        Napier.w(message, tag = tag)
    }
}
