package com.nishanth.simplecomposeui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUITemplate()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeUITemplate() {
    val context = LocalContext.current
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val itemsList = remember { (1..20).map { "Item $it" } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compose UI Template") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2))
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Fav") },
                    label = { Text("Fav") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 📝 Text
            Text("Hello Compose!", fontSize = 24.sp, color = Color.Black)

            Spacer(modifier = Modifier.height(10.dp))

            // 🔡 TextField with hint
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Enter your name...") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 🔘 Button with Toast
            Button(onClick = {
                Toast.makeText(context, "Hello ${text.text.ifEmpty { "User" }}!", Toast.LENGTH_SHORT).show()
            }) {
                Text("Say Hello")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ❤️ Icon Button
            IconButton(onClick = { }) {
                Icon(Icons.Default.Favorite, contentDescription = "Fav", tint = Color.Red)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 🖼️ Image (replace with your drawable)
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(android.R.drawable.ic_menu_camera),
                    contentDescription = "Sample Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 📜 LazyColumn (List)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(itemsList) { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                Toast.makeText(context, "Clicked $item", Toast.LENGTH_SHORT).show()
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComposeUITemplate() {
    ComposeUITemplate()
}