package com.example.smartstudyai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.smartstudyai.ui.theme.*

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground) // Clean off-white studio background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 🧠 1. USER PROFILE SECTION & ENGAGEMENT STREAK
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(PrimaryBlue.copy(alpha = 0.2f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = "Profile", tint = PrimaryBlue)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Welcome Back, Sandeep",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = TextDark
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("🔥 5 Day Study Streak", style = MaterialTheme.typography.bodyMedium, color = Color(0xFFF59E0B), fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                    IconButton(onClick = { /* Open Profile Settings */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = TextMuted)
                    }
                }
            }

            // 💡 2. SMART INSIGHT WIDGET
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PrimaryContainer.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lightbulb, contentDescription = "Insight", tint = PrimaryBlue)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Smart Insight: You study best between 7PM–9PM!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // 🎯 3. DAILY GOAL PROGRESS TRACKER
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("🎯 Daily Study Goal", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text("60%", color = PrimaryBlue, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = { 0.6f },
                            modifier = Modifier.fillMaxWidth().height(8.dp),
                            color = PrimaryBlue,
                            trackColor = AppBackground
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("3/5 Tasks completed today", style = MaterialTheme.typography.bodySmall, color = TextMuted)
                    }
                }
            }

            // 📊 4. IMPROVED STATS METRICS GRID
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MetricCard(modifier = Modifier.weight(1f), title = "Attendance", score = "85%", trend = "📈 Up 5% this week", icon = Icons.Default.TrendingUp, iconColor = PriorityLow)
                        MetricCard(modifier = Modifier.weight(1f), title = "Pending Tasks", score = "4 Left", trend = "⏰ 4 Due today", icon = Icons.Default.AccessTime, iconColor = PriorityHigh)
                    }
                }
            }

            // 📅 5. UPCOMING TASKS WITH PRIORITY COLORS
            item {
                Column {
                    Text("📅 Upcoming Tasks", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                    TaskRow(title = "Revise DBMS Chapter 3", priorityColor = PriorityHigh, priorityLabel = "High")
                    TaskRow(title = "Complete Compiler Assignment", priorityColor = PriorityMedium, priorityLabel = "Medium")
                    TaskRow(title = "Prepare Quiz Notes", priorityColor = PriorityLow, priorityLabel = "Low")
                }
            }

            // ✨ 6. AI SECTION WITH STRONGER BRANDING GRADIENT
            item {
                val aiGradient = Brush.horizontalGradient(listOf(Color(0xFF6366F1), Color(0xFFA855F7)))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Column(
                        modifier = Modifier
                            .background(aiGradient)
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = "AI", tint = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("✨ AI Engine Workspace", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Text("Summarize notes or instantly generate dynamic mock quizzes.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("AI Summarizer", color = Color(0xFF6366F1), fontWeight = FontWeight.Bold)
                            }
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Generate Quiz", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            // 🚀 7. KILLER FEATURE: QUICK UPLOAD PDF WIDGET
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.UploadFile, contentDescription = "Upload", modifier = Modifier.size(40.dp), tint = PrimaryBlue)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Quick Upload Study PDF", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("Drop any textbook PDF here to scan notes instantly", style = MaterialTheme.typography.bodySmall, color = TextMuted)
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = { /* Trigger System PDF Picker */ }) {
                            Text("Select PDF Document")
                        }
                    }
                }
            }

            // Padding to ensure lists don't hide beneath the bottom navigation panel
            item { Spacer(modifier = Modifier.height(60.dp)) }
        }
    }
}

// Reusable Metric Card Sub-Widget
@Composable
fun MetricCard(modifier: Modifier, title: String, score: String, trend: String, icon: androidx.compose.ui.graphics.vector.ImageVector, iconColor: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(title, style = MaterialTheme.typography.bodyMedium, color = TextMuted, fontWeight = FontWeight.Medium)
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Text(score, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = TextDark)
            Spacer(modifier = Modifier.height(4.dp))
            Text(trend, style = MaterialTheme.typography.bodySmall, color = TextMuted)
        }
    }
}

// Reusable Task Row Sub-Widget with Priority Left Border Line
@Composable
fun TaskRow(title: String, priorityColor: Color, priorityLabel: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(12.dp).background(priorityColor, CircleShape))
                Spacer(modifier = Modifier.width(12.dp))
                Text(title, style = MaterialTheme.typography.bodyLarge, color = TextDark, fontWeight = FontWeight.Medium)
            }
            Text(
                text = priorityLabel,
                style = MaterialTheme.typography.bodySmall,
                color = priorityColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(priorityColor.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}