package com.nishanth.simplecomposeui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview
fun AnimatedBoxDemo() {
    var expanded by remember { mutableStateOf(false) }

    val size by animateDpAsState(
        targetValue = if (expanded) 200.dp else 120.dp,
        animationSpec = tween(
            durationMillis = 600,
            easing = LinearOutSlowInEasing
        ), label = "BoxSizeAnimation"
    )

    val color by animateColorAsState(
        targetValue = if (expanded) Color(0xFF7E57C2) else Color(0xFF26A69A),
        animationSpec = tween(durationMillis = 600), label = "BoxColorAnimation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { expanded = !expanded },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(20.dp))
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (expanded) "Tap to Shrink" else "Tap to Expand",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

