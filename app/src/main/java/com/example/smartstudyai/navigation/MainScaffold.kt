package com.example.smartstudyai.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smartstudyai.ui.theme.screens.HomeScreen
import com.example.smartstudyai.ui.theme.PrimaryBlue

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val menuItems = listOf(
        Triple(Screen.Home.route, Icons.Default.Dashboard, "Dashboard"),
        Triple(Screen.Notes.route, Icons.Default.Description, "Notes"),
        Triple(Screen.Planner.route, Icons.Default.CalendarMonth, "Planner"),
        Triple(Screen.AIAssistant.route, Icons.Default.AutoAwesome, "AI Tutor")
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White, // Clean slate background context
                tonalElevation = 8.dp
            ) {
                menuItems.forEach { item ->
                    val (route, icon, label) = item
                    val isSelected = currentRoute == route

                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = label,
                                tint = if (isSelected) PrimaryBlue else Color.Gray // #2 Dynamic active/inactive colors
                            )
                        },
                        label = {
                            Text(
                                text = label,
                                color = if (isSelected) PrimaryBlue else Color.Gray // #2 Text matches dynamic state
                            )
                        },
                        selected = isSelected,
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = PrimaryBlue.copy(alpha = 0.12f) // #2 Filled soft-purple selection highlight capsule
                        ),
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
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onFabClick = { /* Handle FAB trigger operations */ })
            }
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
        Text(text = text, style = MaterialTheme.typography.titleLarge)
    }
}