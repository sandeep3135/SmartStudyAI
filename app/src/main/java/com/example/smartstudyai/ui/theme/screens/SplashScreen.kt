package com.example.smartstudyai.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.smartstudyai.ui.theme.PrimaryBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashComplete: () -> Unit) {
    // Animation scale value holder
    val scale = remember { Animatable(0f) }

    // Run the bounce-in scale animation when screen mounts
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800
            )
        )
        delay(1600) // Hold the branded screen visually for stability
        onSplashComplete() // Exit to onboarding or dashboard routing
    }

    // High-end startup background gradient shell
    val splashGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF1E293B), Color(0xFF0F172A)) // Sleek premium dark canvas context
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(splashGradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(scale.value) // Ties the animation frame live
        ) {
            // App Central Core Icon Representational Shape Frame
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = "App Logo",
                tint = PrimaryBlue,
                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "SmartStudy AI",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Text(
                text = "Elevate Your Learning Matrix",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray.copy(alpha = 0.7f)
            )
        }
    }
}