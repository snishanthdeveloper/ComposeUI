package com.nishanth.simplecomposeui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HorizontalAnimatedList(
    listItems: List<ListItem>,
    modifier: Modifier = Modifier,
    onItemClick: (ListItem) -> Unit,
    onItemLongPress: (ListItem) -> Unit
) {
    var firstItemShouldAnimate by remember { mutableStateOf(false) }
    var animatingItemId by remember { mutableStateOf<String?>(null) }

    // Animate first item when list is loaded
    LaunchedEffect(listItems) {
        if (listItems.isNotEmpty()) {
            firstItemShouldAnimate = true
            delay(800) // Reset after animation completes
            firstItemShouldAnimate = false
        }
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(listItems) { index, item ->
            val shouldAnimate = (index == 0 && firstItemShouldAnimate) ||
                    (animatingItemId == item.id)

            AnimatedListItem(
                item = item,
                onItemClick = onItemClick,
                onItemLongPress = { clickedItem ->
                    animatingItemId = clickedItem.id
                    // Reset animation state after animation completes
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(800)
                        animatingItemId = null
                    }
                    onItemLongPress(clickedItem)
                },
                shouldAnimate = shouldAnimate
            )
        }
    }
}
