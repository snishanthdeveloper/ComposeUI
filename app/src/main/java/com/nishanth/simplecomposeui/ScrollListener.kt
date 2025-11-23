package com.nishanth.simplecomposeui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow


@Composable
fun ScrollListener(listState: LazyListState, viewModel: DemoViewModel) {
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleItemIndex ->
            val totalItems = listState.layoutInfo.totalItemsCount

            if (lastVisibleItemIndex != null &&
                lastVisibleItemIndex >= totalItems - 3 &&
                !viewModel.isLoading
            ) {
                viewModel.loadMore()
            }
        }
    }
}
