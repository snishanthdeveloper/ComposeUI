package com.nishanth.simplecomposeui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.*

data class CareOption(
    val title: String,
    val subtitle: String = "",
    val icon: ImageVector,
    val isPrimary: Boolean,
    val angle: Float
)

@Composable
fun HealthcareEcosystemUI() {
    val primaryColor = Color(0xFF1B4B73)
    val lightBlue = Color(0xFF9FC5E8)
    val backgroundColor = Color(0xFFE8E2D8)

    val careOptions = listOf(
        CareOption("CRITICAL CARE\nHOSPITAL", "", Icons.Default.Home, true, 0f),
        CareOption("ASC+", "", Icons.Default.Home, true, 60f),
        CareOption("LOGISTICS\nHUB", "", Icons.Default.Home, false, 120f),
        CareOption("COMMUNITY\nHEALTH HUB", "", Icons.Default.Home, true, 180f),
        CareOption("MOBILE\nFLEET", "", Icons.Default.Home, false, 240f),
        CareOption("HOME", "", Icons.Default.Home, true, 300f),
        CareOption("BLUE +\nGREEN", "", Icons.Default.Home, false, 330f),
        CareOption("FLEX ZONE", "", Icons.Default.Home, false, 30f),
        CareOption("DIGITAL", "", Icons.Default.Home, false, 90f)
    )

    val centerLabels = listOf(
        "FOOD & NUTRITION",
        "TRANSPORTATION",
        "SOCIAL SUPPORT",
        "EMPLOYMENT",
        "EDUCATION",
        "HOUSING"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        // Canvas for drawing concentric circles and dotted lines
        Canvas(
            modifier = Modifier.size(400.dp)
        ) {
            val center = Offset(size.width / 2, size.height / 2)
            val maxRadius = size.width / 2 - 50.dp.toPx()

            // Draw concentric circles
            for (i in 1..9) {
                val radius = (maxRadius / 5) * i
                drawCircle(
                    color = if (i % 2 == 0) Color.Gray.copy(alpha = 0.3f) else Color.Gray.copy(alpha = 0.2f),
                    radius = radius,
                    center = center,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(
                        width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f))
                    )
                )
            }

            // Draw dotted lines to care options
            careOptions.forEach { option ->
                val angleRad = Math.toRadians(option.angle.toDouble())
                val startRadius = maxRadius / 6 * 2
                val endRadius = maxRadius - 20.dp.toPx()

                val startX = center.x + (startRadius * cos(angleRad)).toFloat()
                val startY = center.y + (startRadius * sin(angleRad)).toFloat()
                val endX = center.x + (endRadius * cos(angleRad)).toFloat()
                val endY = center.y + (endRadius * sin(angleRad)).toFloat()

                drawLine(
                    color = Color.Gray.copy(alpha = 0.4f),
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = 1.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f))
                )
            }
        }

        // Center person icon with background
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(primaryColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }

        // Center ring labels
        centerLabels.forEachIndexed { index, label ->
            val angle = (index * 60f) + 30f // Offset by 30 degrees
            val radius = 60.dp
            val angleRad = Math.toRadians(angle.toDouble())
            val x = (radius.value * cos(angleRad)).dp
            val y = (radius.value * sin(angleRad)).dp

            Box(
                modifier = Modifier.offset(x, y)
            ) {
                Text(
                    text = label,
                    fontSize = 8.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(60.dp)
                )
            }
        }

        // Care option cards positioned around the circle
        careOptions.forEach { option ->
            val radius = 180.dp
            val angleRad = Math.toRadians(option.angle.toDouble())
            val x = (radius.value * cos(angleRad)).dp
            val y = (radius.value * sin(angleRad)).dp

            Box(
                modifier = Modifier.offset(x, y)
            ) {
                CareOptionCard(
                    option = option,
                    primaryColor = primaryColor,
                    lightBlue = lightBlue
                )
            }
        }

        // Legend at bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            LegendItem(
                color = primaryColor,
                text = "PRIMARY PLACES OF CARE"
            )
            Spacer(modifier = Modifier.height(4.dp))
            LegendItem(
                color = lightBlue,
                text = "SHARED SUPPORT SYSTEMS"
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(2.dp)
                        )
                ) {
                    // Dotted pattern representation
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = Color.Gray,
                            radius = 1.dp.toPx(),
                            center = Offset(size.width / 3, size.height / 2)
                        )
                        drawCircle(
                            color = Color.Gray,
                            radius = 1.dp.toPx(),
                            center = Offset(2 * size.width / 3, size.height / 2)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "MULTI-SECTOR COMMUNITY PARTNERSHIPS",
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun CareOptionCard(
    option: CareOption,
    primaryColor: Color,
    lightBlue: Color
) {
    Card(
        modifier = Modifier
            .width(90.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (option.isPrimary) primaryColor else lightBlue
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = option.icon,
                contentDescription = option.title,
                tint = if (option.isPrimary) Color.White else primaryColor,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = option.title,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = if (option.isPrimary) Color.White else primaryColor,
                textAlign = TextAlign.Center,
                lineHeight = 10.sp
            )
            if (option.subtitle.isNotEmpty()) {
                Text(
                    text = option.subtitle,
                    fontSize = 7.sp,
                    color = if (option.isPrimary) Color.White.copy(alpha = 0.8f) else primaryColor.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun LegendItem(
    color: Color,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 10.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HealthcareEcosystemUIPreview() {
    MaterialTheme {
        HealthcareEcosystemUI()
    }
}