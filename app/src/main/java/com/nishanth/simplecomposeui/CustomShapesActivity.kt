package com.nishanth.simplecomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class CustomShapesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                DiagonalCard()
            }
        }
    }
}

@Composable
fun DiagonalCard() {
    val diagonalShape = GenericShape { size, _ ->
        moveTo(0f, 0f)
        lineTo(size.width, 0f)
        lineTo(size.width, size.height * 0.8f)
        lineTo(0f, size.height)
        close()
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(diagonalShape)
            .background(Color(0xFF4CAF50))
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDiagonalCard() {
    DiagonalCard()
}