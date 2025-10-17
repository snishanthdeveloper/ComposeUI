package com.nishanth.simplecomposeui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ContentList(modifier: Modifier = Modifier) {
    val imageHeight = 220.dp
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxSize()
    ) {
        item {
            // Parallax Header
            Box(
                modifier = Modifier
                    .height(imageHeight)
                    .fillMaxWidth()
                    .graphicsLayer {
                        translationY = scrollState.firstVisibleItemScrollOffset * 0.5f
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        items(25) { index ->
            ListItem(
                headlineContent = { Text("Item #$index") },
                supportingContent = { Text("Swipe or scroll to see the toolbar animate!") }
            )
            Divider()
        }
    }
}
