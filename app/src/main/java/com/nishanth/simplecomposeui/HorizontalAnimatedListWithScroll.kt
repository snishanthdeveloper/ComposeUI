package com.nishanth.simplecomposeui

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HorizontalAnimatedListWithScroll(
    listItems: List<ListItem>,
    modifier: Modifier = Modifier,
    onItemClick: (ListItem) -> Unit,
    onItemLongPress: (ListItem) -> Unit
) {
    var firstItemShouldAnimate by remember { mutableStateOf(false) }
    var animatingItemId by remember { mutableStateOf<String?>(null) }
    var animatingItemIndex by remember { mutableStateOf(-1) }

    // LazyListState to control scroll position
    val listState = rememberLazyListState()

    // Get screen dimensions for responsive scaling
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // Calculate responsive scale factor based on screen size
    val maxScaleFactor = remember(screenWidth, screenHeight) {
        val itemWidth = 200.dp
        val itemHeight = 150.dp

        val maxWidthScale = (screenWidth * 0.8f) / itemWidth
        val maxHeightScale = (screenHeight * 0.4f) / itemHeight

        minOf(maxWidthScale, maxHeightScale, 2.5f)
    }

    // Calculate if any item is currently animating
    val isAnyItemAnimating = firstItemShouldAnimate || animatingItemId != null

    // Animate container height based on animation state and responsive scale
    val containerHeight by animateIntAsState(
        targetValue = if (isAnyItemAnimating) (150 * maxScaleFactor).toInt() else 150,
        animationSpec = tween(durationMillis = 300),
        label = "container_height"
    )

    // Calculate dynamic padding with better logic
    val horizontalPadding by animateIntAsState(
        targetValue = when {
            isAnyItemAnimating -> {
                val extraSpace = (200 * (maxScaleFactor - 1) / 2).toInt()
                // Ensure minimum padding for first few items
                maxOf(50, 16 + extraSpace)
            }
            else -> 16
        },
        animationSpec = tween(durationMillis = 300),
        label = "horizontal_padding"
    )

    // Auto-scroll to keep animating item visible
    LaunchedEffect(animatingItemIndex, isAnyItemAnimating) {
        if (animatingItemIndex >= 0 && isAnyItemAnimating) {
            // Calculate the position to center the animating item
            val itemWidth = 200 + 32 // item width + spacing
            val scrollOffset = maxOf(0, (animatingItemIndex * itemWidth) - (screenWidth.value.toInt() / 2 - 100))

            listState.animateScrollToItem(
                index = maxOf(0, animatingItemIndex - 1),
                scrollOffset = -scrollOffset.coerceAtMost(100)
            )
        }
    }

    // Animate first item when list is loaded
    LaunchedEffect(listItems) {
        if (listItems.isNotEmpty()) {
            firstItemShouldAnimate = true
            animatingItemIndex = 0
            delay(800)
            firstItemShouldAnimate = false
            animatingItemIndex = -1
        }
    }

    LazyRow(
        state = listState,
        modifier = modifier
            .fillMaxWidth()
            .height(containerHeight.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(
            horizontal = horizontalPadding.dp,
            vertical = 0.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(listItems) { index, item ->
            val shouldAnimate = (index == 0 && firstItemShouldAnimate) ||
                    (animatingItemId == item.id)

            AnimatedListItem(
                item = item,
                maxScaleFactor = maxScaleFactor,
                onItemClick = onItemClick,
                onItemLongPress = { clickedItem ->
                    animatingItemId = clickedItem.id
                    animatingItemIndex = index
                    // Reset animation state after animation completes
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(800)
                        animatingItemId = null
                        animatingItemIndex = -1
                    }
                    onItemLongPress(clickedItem)
                },
                shouldAnimate = shouldAnimate
            )
        }
    }
}