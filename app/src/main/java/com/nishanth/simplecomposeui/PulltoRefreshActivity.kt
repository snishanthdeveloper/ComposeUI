package com.nishanth.simplecomposeui

// IMPORTANT: imports for the pull-to-refresh APIs (official)
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

class PulltoRefreshActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MaterialTheme {
                val vm = MainViewModel()
                val items by vm.items.collectAsState()
                val isRefreshing by vm.isRefreshing.collectAsState()

                PullToRefreshScreen(
                    items = items,
                    isRefreshing = isRefreshing,
                    onRefresh = { vm.refresh() }
                )
            }
        }
    }
}
