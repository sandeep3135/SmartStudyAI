package com.example.smartstudyai.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    background = AppBackground,
    surface = SurfaceCard,
    primaryContainer = PrimaryContainer,
    onBackground = TextDark,
    onSurface = TextDark,
    onSurfaceVariant = TextMuted
)

@Composable
fun SmartStudyAITheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}