@file:OptIn(ExperimentalSerializationApi::class)

// Data Transfer Objects matching Random User API JSON structure with custom serializers.
package com.example.coveragex.data.api.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive

/**
 * Root response from Random User API
 */
@Serializable
data class RandomUserResponseDto(
    val results: List<UserDto>,
    val info: InfoDto? = null
)

/**
 * Individual user data from API
 */
@Serializable
data class UserDto(
    val gender: String? = null,
    val name: NameDto,
    val location: LocationDto? = null,
    val email: String? = null,
    val login: LoginDto? = null,
    val dob: DobDto? = null,
    val registered: RegisteredDto? = null,
    val phone: String? = null,
    val cell: String? = null,
    val id: IdDto? = null,
    val picture: PictureDto? = null,
    val nat: String? = null
)

/**
 * User's name components
 */
@Serializable
data class NameDto(
    val title: String? = null,
    val first: String? = null,
    val last: String? = null
)

/**
 * User's profile pictures
 */
@Serializable
data class PictureDto(
    val large: String? = null,
    val medium: String? = null,
    val thumbnail: String? = null
)

/**
 * User's location information
 */
@Serializable
data class LocationDto(
    val street: StreetDto? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    @Serializable(with = PostcodeSerializer::class)
    val postcode: String? = null,
    val coordinates: CoordinatesDto? = null,
    val timezone: TimezoneDto? = null
)

/**
 * Street address details
 */
@Serializable
data class StreetDto(
    val number: Int? = null,
    val name: String? = null
)

/**
 * Geographic coordinates
 */
@Serializable
data class CoordinatesDto(
    val latitude: String? = null,
    val longitude: String? = null
)

/**
 * Timezone information
 */
@Serializable
data class TimezoneDto(
    val offset: String? = null,
    val description: String? = null
)

/**
 * Login credentials
 */
@Serializable
data class LoginDto(
    val uuid: String? = null,
    val username: String? = null,
    val password: String? = null,
    val salt: String? = null,
    val md5: String? = null,
    val sha1: String? = null,
    val sha256: String? = null
)

/**
 * Date of birth information
 */
@Serializable
data class DobDto(
    val date: String? = null,
    val age: Int? = null
)

/**
 * Registration date information
 */
@Serializable
data class RegisteredDto(
    val date: String? = null,
    val age: Int? = null
)

/**
 * ID information
 */
@Serializable
data class IdDto(
    val name: String? = null,
    val value: String? = null
)

/**
 * API response metadata
 */
@Serializable
data class InfoDto(
    val seed: String? = null,
    val results: Int? = null,
    val page: Int? = null,
    val version: String? = null
)

/**
 * Custom serializer for postcode field.
 *
 * The Random User API returns postcode as either:
 * - String: "12345" (for most countries)
 * - Int: 12345 (for some countries like Australia)
 *
 * This serializer handles both cases and always returns a String or null.
 */
object PostcodeSerializer : KSerializer<String?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Postcode", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: String?) {
        if (value != null) {
            encoder.encodeString(value)
        } else {
            encoder.encodeNull()
        }
    }

    override fun deserialize(decoder: Decoder): String? {
        return try {
            // Try to get as JsonElement to check type
            val jsonDecoder = decoder as? JsonDecoder
            if (jsonDecoder != null) {
                val element = jsonDecoder.decodeJsonElement()
                when (val primitive = element.jsonPrimitive) {
                    else -> {
                        // Check if it's a number or string
                        primitive.intOrNull?.toString() ?: primitive.content
                    }
                }
            } else {
                // Fallback: try as string
                decoder.decodeString()
            }
        } catch (e: Exception) {
            // If all else fails, return null
            null
        }
    }
}
