package com.example.coveragex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.jetbrains.skia.Image as SkiaImage
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.Foundation.create
import platform.posix.memcpy

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
actual fun AsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier
) {
    val image by produceState<ImageBitmap?>(null, url) {
        try {
            val nsUrl = NSURL.URLWithString(url)
            val data = nsUrl?.let { NSData.create(contentsOfURL = it) }
            
            if (data != null) {
                // Convert NSData to ByteArray
                val bytes = ByteArray(data.length.toInt())
                bytes.usePinned { pinned ->
                    memcpy(pinned.addressOf(0), data.bytes, data.length.toULong())
                }
                
                // Use Skia to decode the image
                val skiaImage = SkiaImage.makeFromEncoded(bytes)
                value = skiaImage.toComposeImageBitmap()
            } else {
                value = null
            }
        } catch (e: Exception) {
            println("Error loading image: ${e.message}")
            value = null
        }
    }

    if (image != null) {
        Image(
            bitmap = image!!,
            contentDescription = contentDescription,
            modifier = modifier
        )
    } else {
        // Show placeholder icon when image fails to load
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = Color.White
        )
    }
}
