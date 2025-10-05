package com.nishanth.simplecomposeui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
 Simple Card applied with Gradient styles
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun GradientBackgroundShowcase() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1️⃣ Linear Gradient Card
        GradientCard(
            title = "Linear",
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF6A11CB),
                    Color(0xFF2575FC)
                )
            )
        )

        // 2️⃣ Radial Gradient Card
        GradientCard(
            title = "Radial",
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFFC5C7D),
                    Color(0xFF6A82FB)
                )
            )
        )

        // 3️⃣ Sweep Gradient Card
        GradientCard(
            title = "Sweep",
            brush = Brush.sweepGradient(
                colors = listOf(
                    Color(0xFFFF9A9E),
                    Color(0xFFFAD0C4),
                    Color(0xFFB2FEFA)
                )
            )
        )
    }
}

@Composable
fun GradientCard(title: String, brush: Brush) {
    Box(
        modifier = Modifier
            .size(110.dp)
            .graphicsLayer {
                shadowElevation = 10f
                shape = RoundedCornerShape(16.dp)
                clip = true
            }
            .background(brush),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
