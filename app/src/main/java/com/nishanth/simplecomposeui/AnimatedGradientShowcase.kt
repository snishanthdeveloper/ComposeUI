package com.nishanth.simplecomposeui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimatedGradientShowcase() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedGradientCard(
            title = "Linear", brushType = BrushType.Linear
        )

        AnimatedGradientCard(
            title = "Radial", brushType = BrushType.Radial
        )

        AnimatedGradientCard(
            title = "Sweep", brushType = BrushType.Sweep
        )
    }
}

enum class BrushType { Linear, Radial, Sweep }

@Composable
fun AnimatedGradientCard(title: String, brushType: BrushType) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteAnim")

    // Animate float from 0f → 360f repeatedly
    val animatedValue by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rotateAnim"
    )

    // Choose gradient brush based on type
    val brush = when (brushType) {
        BrushType.Linear -> Brush.linearGradient(
            colors = listOf(
                Color(0xFF6A11CB), Color(0xFF2575FC)
            ), start = Offset(0f, 0f), end = Offset(animatedValue, animatedValue)
        )

        BrushType.Radial -> Brush.radialGradient(
            colors = listOf(Color(0xFFFC5C7D), Color(0xFF6A82FB)),
            center = Offset(animatedValue, animatedValue),
            radius = 300f
        )

        BrushType.Sweep -> Brush.sweepGradient(
            colors = listOf(Color(0xFFFF9A9E), Color(0xFFFAD0C4), Color(0xFFB2FEFA)),
            center = Offset(animatedValue, animatedValue)
        )
    }

    Box(modifier = Modifier
        .size(110.dp)
        .graphicsLayer {
            shadowElevation = 10f
            shape = RoundedCornerShape(16.dp)
            clip = true
        }
        .background(brush), contentAlignment = Alignment.Center) {
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
