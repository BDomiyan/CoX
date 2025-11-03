package com.example.coveragex.data.mapper

import com.example.coveragex.data.api.dto.UserDto
import com.example.coveragex.domain.model.User

/**
 * Mapper object for transforming UserDto (API layer) to User (domain layer).
 *
 * This mapper:
 * - Handles null values from API
 * - Combines multiple DTO fields into single domain fields
 * - Provides sensible defaults
 * - Generates stable IDs
 */
object UserMapper {

    /**
     * Converts a UserDto from the API to a domain User model.
     *
     * Mapping rules:
     * - fullName: Combines title, first, and last name with fallback to email or "Unknown"
     * - id: Uses UUID from login, falls back to email, then generates index-based ID
     * - location: Combines city and country
     * - All other fields default to empty strings if null
     *
     * @param dto The UserDto from the API
     * @param index The index of this user in the list (used for fallback ID generation)
     * @return Domain User model
     */
    fun fromDto(dto: UserDto, index: Int = 0): User {
        // Build full name from components
        val fullName = buildFullName(dto)

        // Generate stable ID
        val id = dto.login?.uuid
            ?: dto.email
            ?: "user_$index"

        // Format location
        val location = buildLocation(dto)

        return User(
            id = id,
            fullName = fullName,
            email = dto.email.orEmpty(),
            phone = dto.phone.orEmpty(),
            pictureLarge = dto.picture?.large.orEmpty(),
            pictureThumb = dto.picture?.thumbnail.orEmpty(),
            location = location
        )
    }

    /**
     * Builds a full name from name components.
     * Format: "Title First Last" (e.g., "Mr. John Doe")
     *
     * Fallback hierarchy:
     * 1. Combine title + first + last (if available)
     * 2. Use email if name is empty
     * 3. Use "Unknown" as last resort
     */
    private fun buildFullName(dto: UserDto): String {
        val nameParts = listOfNotNull(
            dto.name.title?.takeIf { it.isNotBlank() },
            dto.name.first?.takeIf { it.isNotBlank() },
            dto.name.last?.takeIf { it.isNotBlank() }
        )

        return when {
            nameParts.isNotEmpty() -> nameParts.joinToString(" ")
            !dto.email.isNullOrBlank() -> dto.email
            else -> "Unknown"
        }
    }

    /**
     * Builds a location string from location components.
     * Format: "City, Country" (e.g., "San Francisco, USA")
     *
     * Returns empty string if both city and country are null/blank.
     */
    private fun buildLocation(dto: UserDto): String {
        val city = dto.location?.city?.takeIf { it.isNotBlank() }
        val country = dto.location?.country?.takeIf { it.isNotBlank() }

        return when {
            city != null && country != null -> "$city, $country"
            city != null -> city
            country != null -> country
            else -> ""
        }
    }
}
