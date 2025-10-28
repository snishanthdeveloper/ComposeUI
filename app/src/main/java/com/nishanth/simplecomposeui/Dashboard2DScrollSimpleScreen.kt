package com.nishanth.simplecomposeui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.nishanth.simplecomposeui.model.DashboardCard
import kotlin.math.roundToInt


@Composable
fun Dashboard2DScrollScreen(viewModel: DashboardViewModel = DashboardViewModel()) {
    var translation by remember { mutableStateOf(Offset.Zero) }
    var worldSize by remember { mutableStateOf(IntSize.Zero) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    val newOffset = translation + dragAmount

                    // Clamp scroll offset to world bounds
                    val maxX = 0f
                    val minX = -(worldSize.width - containerSize.width).coerceAtLeast(0).toFloat()
                    val maxY = 0f
                    val minY = -(worldSize.height - containerSize.height).coerceAtLeast(0).toFloat()

                    translation = Offset(
                        x = newOffset.x.coerceIn(minX, maxX),
                        y = newOffset.y.coerceIn(minY, maxY)
                    )
                }
            }
            .onSizeChanged { containerSize = it }
    ) {
        DashboardGrid(
            cards = viewModel.cards,
            translation = translation,
            onWorldSizeMeasured = { worldSize = it }
        )
    }
}

// ---------- Dashboard Grid ----------
@Composable
fun DashboardGrid(
    cards: List<DashboardCard>,
    translation: Offset,
    onWorldSizeMeasured: (IntSize) -> Unit
) {
    val columns = 4
    val cardWidth = 200.dp
    val cardHeight = 100.dp
    val gap = 10.dp
    val density = LocalDensity.current

    val (worldWidthPx, worldHeightPx) = with(density) {
        val cardWidthPx = cardWidth.toPx()
        val cardHeightPx = cardHeight.toPx()
        val gapPx = gap.toPx()
        val rows = (cards.size + columns - 1) / columns
        val widthPx = (columns * cardWidthPx) + ((columns + 1) * gapPx)
        val heightPx = (rows * cardHeightPx) + ((rows + 1) * gapPx)
        widthPx.roundToInt() to heightPx.roundToInt()
    }

    // Notify parent to clamp scroll
    LaunchedEffect(worldWidthPx, worldHeightPx) {
        onWorldSizeMeasured(IntSize(worldWidthPx, worldHeightPx))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                translationX = translation.x
                translationY = translation.y
            }
    ) {
        Column(modifier = Modifier.padding(gap)) {
            val rows = (cards.size + columns - 1) / columns
            for (row in 0 until rows) {
                Row(horizontalArrangement = Arrangement.spacedBy(gap)) {
                    for (col in 0 until columns) {
                        val index = row * columns + col
                        if (index < cards.size) {
                            DashboardCardItem(cards[index], Modifier.size(cardWidth, cardHeight))
                        } else {
                            Spacer(modifier = Modifier.size(cardWidth, cardHeight))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(gap))
            }
        }
    }
}

// ---------- Card Item ----------
@Composable
fun DashboardCardItem(card: DashboardCard, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = card.color.copy(alpha = 0.9f),
        tonalElevation = 4.dp,
        shadowElevation = 8.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(card.title, style = MaterialTheme.typography.titleMedium, color = Color.White)
            Text(
                card.value,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
    }
}

// ---------- Preview ----------
@Preview(showBackground = true)
@Composable
fun PreviewDashboard2DScroll() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        Dashboard2DScrollScreen()
    }
}