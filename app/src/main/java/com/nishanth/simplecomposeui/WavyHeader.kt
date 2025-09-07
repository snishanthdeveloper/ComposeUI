package com.nishanth.simplecomposeui


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WavyHeader() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        drawPath(
            path = androidx.compose.ui.graphics.Path().apply {
                moveTo(0f, size.height * 0.3f)
                quadraticBezierTo(
                    size.width * 0.25f, size.height * 0.4f,
                    size.width * 0.5f, size.height * 0.3f
                )
                quadraticBezierTo(
                    size.width * 0.85f, size.height * 0.8f,
                    size.width, size.height * 0.3f
                )
                lineTo(size.width, 0f)
                lineTo(0f, 0f)
                lineTo(1f, 1f)
                close()
            },
            color = Color(0xFF2196F3)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWavyHeader() {
    WavyHeader()
}
