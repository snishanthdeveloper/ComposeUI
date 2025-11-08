package com.nishanth.simplecomposeui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random


@Composable
fun RecompositionCounterBox(count: Int) {
    val color = remember(count) { randomColor() }

    // Log recomposition to track in console
    Log.d("RecompositionDemo", "Box recomposed! count = $count")

    Box(
        modifier = Modifier
            .size(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Recomposition #$count",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}

fun randomColor(): Color {
    val random = Random(System.currentTimeMillis())
    return Color(
        red = random.nextFloat(),
        green = random.nextFloat(),
        blue = random.nextFloat()
    )
}
