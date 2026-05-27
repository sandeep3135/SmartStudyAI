package com.example.smartstudyai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.smartstudyai.navigation.MainScaffold
import com.example.smartstudyai.ui.screens.OnboardingScreen
import com.example.smartstudyai.ui.screens.SplashScreen
import com.example.smartstudyai.ui.theme.SmartStudyAITheme

enum class AppAuthState {
    SPLASH, ONBOARDING, MAIN_DASHBOARD
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartStudyAITheme {
                // Central App state orchestration router variable holder
                var currentAppState by remember { mutableStateOf(AppAuthState.SPLASH) }

                when (currentAppState) {
                    AppAuthState.SPLASH -> {
                        SplashScreen(onSplashComplete = {
                            currentAppState = AppAuthState.ONBOARDING
                        })
                    }
                    AppAuthState.ONBOARDING -> {
                        OnboardingScreen(onOnboardingComplete = {
                            currentAppState = AppAuthState.MAIN_DASHBOARD
                        })
                    }
                    AppAuthState.MAIN_DASHBOARD -> {
                        MainScaffold()
                    }
                }
            }
        }
    }
}