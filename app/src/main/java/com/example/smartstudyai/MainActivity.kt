package com.example.smartstudyai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.smartstudyai.navigation.MainScaffold
import com.example.smartstudyai.ui.theme.SmartStudyAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Keeps your UI looking modern under the system status bars
        setContent {
            SmartStudyAITheme {
                MainScaffold() // 👈 This launches your complete navigation shell engine!
            }
        }
    }
}