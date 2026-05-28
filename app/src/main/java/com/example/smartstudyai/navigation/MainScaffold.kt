package com.example.smartstudyai.navigation

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smartstudyai.ui.theme.screens.HomeScreen
import com.example.smartstudyai.ui.theme.screens.ProfileScreen
import com.example.smartstudyai.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuItems = listOf(
        Triple(Screen.Home.route, Icons.Default.Dashboard, "Dashboard"),
        Triple(Screen.Notes.route, Icons.Default.Description, "Notes"),
        Triple(Screen.Planner.route, Icons.Default.CalendarMonth, "Planner"),
        Triple(Screen.AIAssistant.route, Icons.Default.AutoAwesome, "AI Tutor")
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = AppBackground,
                modifier = Modifier.width(320.dp),
                drawerShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)
            ) {
                // 👤 USER PROFILE CARD
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .background(PrimaryBlue.copy(alpha = 0.12f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = PrimaryBlue, modifier = Modifier.size(26.dp))
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text("Sandeep", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold, color = TextDark)
                            Text("Premium Study Tier", style = MaterialTheme.typography.bodyMedium, color = TextMuted, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                // 🔥 STUDY STREAK CARD
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = Color(0xFFF59E0B), modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("5 Day Study Streak", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = TextDark)
                            }
                            Text("75%", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.ExtraBold, color = PrimaryBlue)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        LinearProgressIndicator(
                            progress = { 0.75f },
                            modifier = Modifier.fillMaxWidth().height(6.dp),
                            color = PrimaryBlue,
                            trackColor = AppBackground
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("3 days left to break your high record!", style = MaterialTheme.typography.bodySmall, color = TextMuted)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text("NAVIGATION & SETTINGS", style = MaterialTheme.typography.labelMedium, color = TextMuted, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 24.dp, bottom = 8.dp))

                // ⚙️ MENU ITEMS
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = null, tint = if (currentRoute == Screen.Profile.route) PrimaryBlue else TextMuted) },
                        label = { Text("View Profile Summary", fontWeight = FontWeight.SemiBold, color = if (currentRoute == Screen.Profile.route) PrimaryBlue else TextDark) },
                        selected = currentRoute == Screen.Profile.route,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Screen.Profile.route)
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = PrimaryContainer, unselectedContainerColor = Color.White)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Edit, contentDescription = null, tint = if (currentRoute == Screen.EditProfile.route) PrimaryBlue else TextMuted) },
                        label = { Text("Edit Profile Details", fontWeight = FontWeight.SemiBold, color = if (currentRoute == Screen.EditProfile.route) PrimaryBlue else TextDark) },
                        selected = currentRoute == Screen.EditProfile.route,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Screen.EditProfile.route)
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = PrimaryContainer, unselectedContainerColor = Color.White)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = null, tint = TextMuted) },
                        label = { Text("App Settings", fontWeight = FontWeight.SemiBold, color = TextDark) },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() } },
                        shape = RoundedCornerShape(12.dp),
                        colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White)
                    )
                }
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 8.dp,
                    // ✅ FIXED: Using window padding instead of a hard box container ensures icons center flawlessly
                    windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Bottom)
                ) {
                    menuItems.forEach { item ->
                        val (route, icon, label) = item
                        val isSelected = currentRoute == route

                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = label,
                                    tint = if (isSelected) PrimaryBlue else Color.Gray,
                                    modifier = Modifier.size(22.dp) // Perfectly scaled asset footprint
                                )
                            },
                            label = {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSelected) PrimaryBlue else Color.Gray,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            selected = isSelected,
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = PrimaryBlue.copy(alpha = 0.12f)
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
                // 🏠 Dashboard
                composable(Screen.Home.route) {
                    HomeScreen(
                        onFabClick = { /* Handle FAB Action */ },
                        onMenuClick = { scope.launch { drawerState.open() } }
                    )
                }

                // 📝 Notes Workspace with Premium Empty State Injection
                composable(Screen.Notes.route) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(AppBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(32.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .background(PrimaryBlue.copy(alpha = 0.1f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Description,
                                    contentDescription = null,
                                    tint = PrimaryBlue,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "📘 No Notes Yet",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = TextDark
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Create your first smart note to begin organizing your curriculum modules.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextMuted,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = { /* Direct database insertion trigger later */ },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Create Note", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                // 📅 Planner Workspace
                composable(Screen.Planner.route) { TemporaryScreenPlaceholder("📅 Study Planner View") }

                // 🤖 AI Assistant Workspace
                composable(Screen.AIAssistant.route) { TemporaryScreenPlaceholder("🤖 AI Assistant Chat View") }

                // 👤 Profile Destinies
                composable(Screen.Profile.route) { ProfileScreen() }
                composable(Screen.EditProfile.route) { TemporaryScreenPlaceholder("✏️ Edit Profile Detail View Screen") }
            }
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