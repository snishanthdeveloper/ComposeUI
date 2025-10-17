package com.nishanth.simplecomposeui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InfoSheet() {
    Column(Modifier.padding(16.dp)) {
        Text("App Information", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Text("Version: 1.0.0")
        Text("Build: Compose Material 3 Demo")
        Text("Developed by: You 🚀")
    }
}
