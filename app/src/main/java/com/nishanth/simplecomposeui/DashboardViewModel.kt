package com.nishanth.simplecomposeui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.nishanth.simplecomposeui.model.DashboardCard
import kotlin.random.Random

class DashboardViewModel() : ViewModel() {
    val cards = List(20) { index ->
        DashboardCard(
            title = "Metric ${index + 1}",
            value = "${(100..999).random()}",
            color = Color(
                red = (0.5f..1f).random(),
                green = (0.4f..0.9f).random(),
                blue = (0.8f..1f).random()
            )
        )
    }

}

fun ClosedFloatingPointRange<Float>.random(): Float {
    return Random.nextFloat() * (endInclusive - start) + start
}

