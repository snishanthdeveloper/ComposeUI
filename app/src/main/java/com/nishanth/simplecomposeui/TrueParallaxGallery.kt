package com.nishanth.simplecomposeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
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

@Composable
fun TrueParallaxGallery() {
    val images = listOf(
        R.drawable.landscape_1,
        R.drawable.landscape_2,
        R.drawable.landscape_3,
        R.drawable.landscape_4,
        R.drawable.landscape_5
    )

    val listState = rememberLazyListState()
    val cardWidth = 200.dp
    val spacing = 24.dp
    val cardWidthPx = with(LocalDensity.current) { cardWidth.toPx() + spacing.toPx() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = spacing),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            items(images.size) { index ->
                val scrollOffset by derivedStateOf {
                    val firstVisible = listState.firstVisibleItemIndex
                    val offset = listState.firstVisibleItemScrollOffset.toFloat()
                    ((index - firstVisible) * cardWidthPx - offset)
                }

                ParallaxCardV2(
                    imageRes = images[index],
                    parallaxOffset = scrollOffset
                )
            }
        }
    }
}

@Composable
fun ParallaxCardV2(
    imageRes: Int,
    parallaxOffset: Float,
    modifier: Modifier = Modifier
) {
    val parallaxIntensity = 0.15f // smaller = deeper parallax
    Box(
        modifier = modifier
            .width(300.dp)
            .height(400.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    translationX = -parallaxOffset * parallaxIntensity
                }
        )

        // Optional gradient overlay (remove if you want pure image)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black.copy(alpha = 0.1f))
                    )
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrueParallaxGallery() {
    TrueParallaxGallery()
}
