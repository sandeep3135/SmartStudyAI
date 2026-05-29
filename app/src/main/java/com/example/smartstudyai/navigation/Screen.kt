package com.example.smartstudyai.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home_screen")
    object Notes : Screen("notes_screen")
    object Planner : Screen("planner_screen")
    object AIAssistant : Screen("ai_assistant_screen")
    object Profile : Screen("profile")
    object EditProfile : Screen("edit_profile")
    object Login : Screen("login_screen")
    object Signup : Screen("signup_screen")
    object ForgotPassword : Screen("forgot_password_screen")
}