package com.nishanth.simplecomposeui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun GalleryGrid(
    images: List<String>,
    scale: Float,
    offset: Offset
) {
    val columns = 3
    val spacing = 8.dp

    val minScale = 1f
    val maxScale = 3f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                translationX = offset.x
                translationY = offset.y
            }
            .padding(spacing)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalArrangement = Arrangement.spacedBy(spacing),
            modifier = Modifier.fillMaxSize()
        ) {
            items(images.size) { index ->
                GalleryImageItem(imageUrl = images[index])
            }
        }
    }
}

@Composable
fun GalleryImageItem(imageUrl: String) {
    Surface(
        modifier = Modifier
            .aspectRatio(1.5f)
            .clip(RoundedCornerShape(12.dp)),
        tonalElevation = 4.dp,
        shadowElevation = 8.dp
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

