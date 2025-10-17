package com.nishanth.simplecomposeui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionSheet() {
    Column(Modifier.padding(16.dp)) {
        Text("Quick Actions", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { /* Do something */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Add New Item")
        }
        Button(onClick = { /* Do something */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Share")
        }
    }
}
