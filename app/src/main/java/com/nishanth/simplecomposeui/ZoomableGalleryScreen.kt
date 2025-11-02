package com.nishanth.simplecomposeui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun ZoomableGalleryScreen() {
    val images = remember {
        // Replace with your own drawable URLs or painterResources
        List(30) { "https://picsum.photos/300/200?random=$it" }
    }

    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val minScale = 1f
    val maxScale = 3f

    // Gesture detection for zoom & pan
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(minScale, maxScale)
                    offset += pan
                }
            }
    ) {
        // Apply transformation to grid
        GalleryGrid(
            images = images,
            scale = scale,
            offset = offset
        )
    }
}
