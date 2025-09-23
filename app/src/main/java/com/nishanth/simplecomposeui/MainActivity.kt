package com.nishanth.simplecomposeui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HorizontalListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[HorizontalListViewModel::class.java]

        setContent {
            MainScreen(viewModel = viewModel)
        }

        viewModel.loadData()

    }
}

// Main screen composable
@Composable
fun MainScreen(viewModel: HorizontalListViewModel) {
    val listItems by viewModel.listItems.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Home Screen",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // Loading indicator
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Horizontal list
            /*HorizontalAnimatedList(
                listItems = listItems,
                modifier = Modifier.padding(top = 16.dp),
                onItemClick = { item ->
                    // Navigate to next screen
                    Toast.makeText(context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()

                },
                onItemLongPress = { item ->
                    Toast.makeText(context, "Long pressed: ${item.title}", Toast.LENGTH_SHORT)
                        .show()
                }
            )*/
            HorizontalListCombinedScreen(listItems)
        }

        // Additional content can go here
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun HorizontalListCombinedScreen(
    listItems: List<ListItem>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // allow scrolling if needed
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("With Scroll (Auto-Center)", style = MaterialTheme.typography.titleMedium)

        HorizontalAnimatedListWithScroll(
            listItems = listItems,
            onItemClick = {},        // Disabled click
            onItemLongPress = {}     // Disabled long press
        )

        Text("Without Scroll", style = MaterialTheme.typography.titleMedium)

        HorizontalAnimatedList(
            listItems = listItems,
            onItemClick = {},
            onItemLongPress = {}
        )
    }
}
