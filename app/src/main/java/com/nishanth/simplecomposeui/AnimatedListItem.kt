package com.nishanth.simplecomposeui

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay

@Composable
fun AnimatedListItem(
    item: ListItem,
    maxScaleFactor: Float,
    modifier: Modifier = Modifier,
    onItemClick: (ListItem) -> Unit,
    onItemLongPress: (ListItem) -> Unit,
    shouldAnimate: Boolean = false,
    zIndex: Float = 0f
) {
    var animationScale by remember { mutableStateOf(1f) }

    // Animation for zoom effect
    val scale by animateFloatAsState(
        targetValue = animationScale,
        animationSpec = tween(durationMillis = 300, easing = EaseInOutCubic),
        label = "item_scale"
    )

    // Trigger animation when shouldAnimate changes
    LaunchedEffect(shouldAnimate) {
        if (shouldAnimate) {
            animationScale = maxScaleFactor
            delay(500)
            animationScale = 1f
        }
    }

    Card(
        modifier = modifier
            .width(200.dp)
            .height(150.dp)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                clip = false // Don't clip the scaled content
            )
            .zIndex(zIndex) // Bring animating items to front
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onItemClick(item) },
                    onLongPress = { onItemLongPress(item) }
                )
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Placeholder for image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Image",
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}