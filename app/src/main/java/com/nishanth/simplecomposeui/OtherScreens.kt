package com.nishanth.simplecomposeui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SearchScreen() {
    ScreenContainer(title = "🔍 Search", color = Color(0xFFE8F5E9))
}

@Composable
fun ProfileScreen() {
    ScreenContainer(title = "👤 Profile", color = Color(0xFFFFF3E0))
}
