package com.nishanth.simplecomposeui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun SmoothParallaxGallery() {
    val images = listOf(
        R.drawable.landscape_1,
        R.drawable.landscape_2,
        R.drawable.landscape_3,
        R.drawable.landscape_4,
        R.drawable.landscape_5
    )

    val scrollState = rememberScrollState()
    val cardWidth = 280.dp
    val spacing = 24.dp
    val cardWidthPx = with(LocalDensity.current) { cardWidth.toPx() }
    val spacingPx = with(LocalDensity.current) { spacing.toPx() }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .horizontalScroll(scrollState)
            .padding(horizontal = spacing)
    ) {
        images.forEachIndexed { index, image ->

            val cardCenter = remember {
                derivedStateOf {
                    (index * (cardWidthPx + spacingPx)) + cardWidthPx / 2
                }
            }

            val scrollCenter = remember {
                derivedStateOf {
                    scrollState.value + (cardWidthPx / 1.2f)
                }
            }

            val distanceFromCenter = abs(scrollCenter.value - cardCenter.value)
            val parallaxRatio = (1f - (distanceFromCenter / (cardWidthPx * 2))).coerceIn(0.3f, 1f)

            // Smooth transition of parallax depth
            val animatedDepth by animateFloatAsState(
                targetValue = parallaxRatio,
                animationSpec = tween(400)
            )

            ParallaxCard(
                imageRes = image,
                parallaxDepth = animatedDepth,
                modifier = Modifier.padding(end = spacing)
            )
        }
    }
}

@Composable
fun ParallaxCard(
    imageRes: Int,
    parallaxDepth: Float,
    modifier: Modifier = Modifier
) {
    val depthIntensity by remember(imageRes) {
        mutableFloatStateOf(Random.nextFloat().coerceIn(0.4f, 0.8f))
    }

    Box(
        modifier = modifier
            .width(280.dp)
            .height(360.dp)
            .clip(RoundedCornerShape(20.dp))
            .graphicsLayer {
                translationX = parallaxDepth * 10f // control how much it moves
            }
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // Image moves opposite to scroll for parallax depth illusion
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Parallax Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    translationX = -parallaxDepth * 15f * depthIntensity
                }
        )

        // Soft fade gradient for a cinematic feel
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.1f)
                        )
                    )
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSmoothParallaxGallery() {
    SmoothParallaxGallery()
}




