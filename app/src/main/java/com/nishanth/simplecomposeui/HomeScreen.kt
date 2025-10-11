package com.nishanth.simplecomposeui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun HomeScreen(navController: NavHostController) {
    ScreenContainer(title = "🏠 Home", color = Color(0xFFE3F2FD)) {
        // Ripple effect using Modifier.clickable
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable(
                    indication = ripple(
                        color = MaterialTheme.colorScheme.primary,
                        bounded = true
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    navController.navigate(Screen.Detail.route)
                }
                .padding(20.dp)
        ) {
            Text(
                text = "Go to Detail Page →",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}
