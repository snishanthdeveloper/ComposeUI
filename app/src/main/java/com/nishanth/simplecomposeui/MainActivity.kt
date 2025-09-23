package com.nishanth.simplecomposeui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlassDashboardScreen()
        }

    }
}

@Composable
fun SimpleComposeApp() {
    // State to hold input text
    var name by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    // Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Compose!",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Input field
        BasicTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                if (name.text.isEmpty()) {
                    Text(
                        text = "Enter your name...",
                        color = androidx.compose.ui.graphics.Color.Gray
                    )
                }
                innerTextField() // 👈 Displays the actual text input
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Button
        Button(onClick = {
            Toast.makeText(
                context,
                "Hello, ${name.text.ifEmpty { "User" }}!",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Text(text = "Say Hello")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Display user input
        if (name.text.isNotEmpty()) {
            Text(text = "Hello, ${name.text}!", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSimpleComposeApp() {
    SimpleComposeApp()
}