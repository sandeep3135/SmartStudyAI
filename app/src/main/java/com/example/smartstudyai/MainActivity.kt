package com.example.smartstudyai

import android.content.Context
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

        // 🧠 Access persistent Android storage preferences
        val sharedPreferences = getSharedPreferences("smart_study_prefs", Context.MODE_PRIVATE)

        setContent {
            SmartStudyAITheme {
                // Central App state orchestration router variable holder
                var currentAppState by remember { mutableStateOf(AppAuthState.SPLASH) }

                when (currentAppState) {
                    AppAuthState.SPLASH -> {
                        SplashScreen(onSplashComplete = {
                            // 🔍 Check if this is the user's first time opening the application
                            val isFirstTime = sharedPreferences.getBoolean("is_first_time_user", true)

                            if (isFirstTime) {
                                currentAppState = AppAuthState.ONBOARDING
                            } else {
                                currentAppState = AppAuthState.MAIN_DASHBOARD
                            }
                        })
                    }
                    AppAuthState.ONBOARDING -> {
                        OnboardingScreen(onOnboardingComplete = {
                            // 💾 Save state preference flag to disk persistently so onboarding is skipped forever
                            sharedPreferences.edit().putBoolean("is_first_time_user", false).apply()

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