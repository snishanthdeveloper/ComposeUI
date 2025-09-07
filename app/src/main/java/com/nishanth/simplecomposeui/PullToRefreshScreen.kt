package com.nishanth.simplecomposeui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullToRefreshScreen(
    items: List<String>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Local UI state for a short success message
    var showMessage by remember { mutableStateOf(false) }
    // Track previous refreshing state so we can detect the transition true -> false
    var wasRefreshing by remember { mutableStateOf(isRefreshing) }

    // Pull refresh state (official API)
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onRefresh
    )

    // When isRefreshing changes, detect the transition and show AnimatedVisibility
    LaunchedEffect(isRefreshing) {
        if (wasRefreshing && !isRefreshing) {
            // refresh just finished
            showMessage = true
            delay(1400)
            showMessage = false
        }
        wasRefreshing = isRefreshing
    }

    Column(modifier = modifier.fillMaxSize()) {
        // Animated banner that appears briefly after refresh completes
        AnimatedVisibility(
            visible = showMessage,
            enter = slideInVertically(initialOffsetY = { -it / 2 }) + fadeIn(animationSpec = tween(250)),
            exit = slideOutVertically(targetOffsetY = { -it / 2 }) + fadeOut(animationSpec = tween(200))
        ) {
            Text(
                text = "✅ Refreshed",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        // The content area with pull-to-refresh modifier applied
        Box(modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(items) { _, item ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(
                            text = item,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
            }

            // The built-in indicator that follows the drag
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPullToRefresh() {
    PullToRefreshScreen(
        items = List(6) { "Preview item ${it + 1}" },
        isRefreshing = false,
        onRefresh = {}
    )
}
