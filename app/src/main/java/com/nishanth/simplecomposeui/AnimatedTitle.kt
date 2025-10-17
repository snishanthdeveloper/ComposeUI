package com.nishanth.simplecomposeui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun AnimatedTitle(title: String, progress: Float) {
    val scale by animateFloatAsState(targetValue = 1f - (0.3f * progress))
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale)
    )
}
