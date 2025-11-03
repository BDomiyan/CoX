package com.example.coveragex.domain.model

/**
 * Domain model representing a user in the application.
 *
 * This is a clean, platform-agnostic representation of a user that:
 * - Has no API/serialization dependencies
 * - Contains only business-relevant fields
 * - Is immutable for thread safety
 * - Can evolve independently from API changes
 *
 * The data layer is responsible for mapping API DTOs to this domain model.
 */
data class User(
    /**
     * Unique identifier for the user
     */
    val id: String,

    /**
     * User's full name (e.g., "Mr. John Doe")
     */
    val fullName: String,

    /**
     * User's email address
     */
    val email: String,

    /**
     * User's phone number
     */
    val phone: String,

    /**
     * URL to large profile picture
     */
    val pictureLarge: String,

    /**
     * URL to thumbnail profile picture
     */
    val pictureThumb: String,

    /**
     * User's location (e.g., "San Francisco, USA")
     */
    val location: String
)
