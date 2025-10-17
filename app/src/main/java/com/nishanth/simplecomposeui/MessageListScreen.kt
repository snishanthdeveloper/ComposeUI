package com.nishanth.simplecomposeui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageListScreen(viewModel: MessageViewModel = viewModel()) {

    val messages = viewModel.messages.value
    val refreshing = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope() // ✅

    PullToRefreshBox(
        isRefreshing = refreshing.value,
        onRefresh = {
            refreshing.value = true
            viewModel.refresh()

            coroutineScope.launch { // ✅ Correct scope for delay
                delay(1200)
                refreshing.value = false
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp)
        ) {
            messages.forEach { (header, items) ->

                stickyHeader {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = header,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                    }
                }

                items(items, key = { it.id }) { message ->
                    AnimatedMessageCard(message)
                }
            }
        }
    }
}

