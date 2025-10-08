package com.nishanth.simplecomposeui

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun ThemeSwitcherDemo() {
    var isDarkTheme by remember { mutableStateOf(false) }

    val transition = updateTransition(targetState = isDarkTheme, label = "themeTransition")
    val backgroundColor by transition.animateColor(label = "bgColor") {
        if (it) Color(0xFF121212) else Color(0xFFF9F9F9)
    }

    val gradient1 by transition.animateColor(label = "grad1") {
        if (it) Color(0xFFD0BCFF) else Color(0xFF6750A4)
    }
    val gradient2 by transition.animateColor(label = "grad2") {
        if (it) Color(0xFFB69DF8) else Color(0xFF9575CD)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = if (isDarkTheme) "Dark Theme 🌙" else "Light Theme ☀️",
                style = MaterialTheme.typography.headlineMedium,
                color = if (isDarkTheme) Color.White else Color.Black
            )

            Spacer(Modifier.height(16.dp))
            Switch(checked = isDarkTheme, onCheckedChange = { isDarkTheme = it })

            Spacer(Modifier.height(32.dp))
            AnimatedGradientCard("Primary Colors", gradient1, gradient2)
            AnimatedGradientCard("Tertiary + Secondary", Color(0xFFEFB8C8), Color(0xFF7D5260))
            AnimatedGradientCard("Inverse Surface", Color(0xFF1C1B1F), Color(0xFF6750A4))
        }
    }
}
