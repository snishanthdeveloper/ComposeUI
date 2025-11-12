package com.nishanth.simplecomposeui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun AdvancedAnimatedBox() {
    var toggled by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (toggled) 360f else 0f,
        animationSpec = tween(800), label = "Rotation"
    )

    val alphaValue by animateFloatAsState(
        targetValue = if (toggled) 1f else 0.5f,
        animationSpec = tween(600), label = "Alpha"
    )

    val cornerRadius by animateDpAsState(
        targetValue = if (toggled) 64.dp else 8.dp,
        animationSpec = tween(700), label = "CornerRadius"
    )

    val color by animateColorAsState(
        targetValue = if (toggled) Color(0xFF42A5F5) else Color(0xFFFF7043),
        animationSpec = tween(700), label = "Color"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { toggled = !toggled },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    rotationZ = rotation
                    alpha = alphaValue
                }
                .clip(RoundedCornerShape(cornerRadius))
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (toggled) "Animated!" else "Tap Me",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
