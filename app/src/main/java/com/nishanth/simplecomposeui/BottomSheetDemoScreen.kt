package com.nishanth.simplecomposeui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nishanth.simplecomposeui.SheetType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDemoScreen(viewModel: BottomSheetViewModel = viewModel()) {

    val sheetType by viewModel.sheetType
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    var showSheet by remember { mutableStateOf(false) }

    // Main content
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Modal Bottom Sheet Demo") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.showSheet(SheetType.ACTIONS); showSheet = true }) {
                Icon(Icons.Default.Add, contentDescription = "Open Sheet")
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Tap the + button to open the bottom sheet", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(16.dp))
                Button(onClick = { viewModel.showSheet(SheetType.FILTERS); showSheet = true }) {
                    Text("Open Filter Sheet")
                }
                Spacer(Modifier.height(8.dp))
                Button(onClick = { viewModel.showSheet(SheetType.INFO); showSheet = true }) {
                    Text("Open Info Sheet")
                }
            }
        }

        // Show the sheet when required
        if (showSheet && sheetType != SheetType.NONE) {
            ModalBottomSheet(
                onDismissRequest = {
                    coroutineScope.launch {
                        sheetState.hide()
                        showSheet = false
                        viewModel.hideSheet()
                    }
                },
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() }
            ) {
                when (sheetType) {
                    SheetType.ACTIONS -> ActionSheet()
                    SheetType.FILTERS -> FilterSheet()
                    SheetType.INFO -> InfoSheet()
                    else -> {}
                }
            }
        }
    }
}
