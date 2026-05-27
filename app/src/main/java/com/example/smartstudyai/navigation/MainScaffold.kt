package com.example.smartstudyai.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Map out the bottom navigation items cleanly
    val menuItems = listOf(
        Triple(Screen.Home.route, Icons.Default.Dashboard, "Dashboard"),
        Triple(Screen.Notes.route, Icons.Default.Description, "Notes"),
        Triple(Screen.Planner.route, Icons.Default.CalendarMonth, "Planner"),
        Triple(Screen.AIAssistant.route, Icons.Default.AutoAwesome, "AI Tutor")
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                menuItems.forEach { item ->
                    val (route, icon, label) = item
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) },
                        selected = currentRoute == route,
                        onClick = {
                            if (currentRoute != route) {
                                navController.navigate(route) {
                                    popUpTo(Screen.Home.route) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        // The structural viewport controller hosting our views
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) { TemporaryScreenPlaceholder("🏠 Home Dashboard View") }
            composable(Screen.Notes.route) { TemporaryScreenPlaceholder("📝 Notes Workspace View") }
            composable(Screen.Planner.route) { TemporaryScreenPlaceholder("📅 Study Planner View") }
            composable(Screen.AIAssistant.route) { TemporaryScreenPlaceholder("🤖 AI Assistant Chat View") }
        }
    }
}

@Composable
fun TemporaryScreenPlaceholder(text: String) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text = text, style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
    }
}