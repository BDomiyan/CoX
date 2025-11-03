package com.example.coveragex.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import coil.compose.AsyncImage as CoilAsyncImage

@Composable
actual fun AsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier
) {
    CoilAsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier,
        placeholder = rememberVectorPainter(Icons.Default.Person),
        error = rememberVectorPainter(Icons.Default.Person)
    )
}
