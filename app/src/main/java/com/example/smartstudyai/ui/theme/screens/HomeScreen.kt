package com.example.smartstudyai.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NoteAdd
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.smartstudyai.ui.theme.*

@Composable
fun HomeScreen(onFabClick: () -> Unit = {}, onMenuClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp), // #1: Extra bottom breathing space
            verticalArrangement = Arrangement.spacedBy(24.dp) // #1: Increased whitespace separation between sections
        ) {
            // 👤 1. RESTORED USER PROFILE ROW WITH ELEVATED RIGHT TRIGGER MENU BADGE
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // ✅ PERFECTLY RESTORED: Profile Person badge icon returns to its rightful left corner spot
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .background(PrimaryBlue.copy(alpha = 0.12f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile Icon",
                                tint = PrimaryBlue,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Welcome Back, Sandeep",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = TextDark,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "🔥 5 Day Study Streak",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFF59E0B),
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    // ✅ PREMIUM UPGRADE: Elevated Staggered Hamburger Card on the right margin corner
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .shadow(2.dp, RoundedCornerShape(12.dp))
                            .background(Color.White, RoundedCornerShape(12.dp))
                            .clickable { onMenuClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuOpen,
                            contentDescription = "Open Slide Menu",
                            tint = PrimaryBlue,
                            modifier = Modifier
                                .size(22.dp)
                                .background(Color.Transparent) // Force transparency to prevent raw white box clipping
                        )
                    }
                }
            }

            // 💡 2. SMART INSIGHT WIDGET
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PrimaryContainer.copy(alpha = 0.6f)),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lightbulb, contentDescription = "Insight", tint = PrimaryBlue)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Smart Insight: You study best between 7PM–9PM!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = PrimaryBlue,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // 🎯 3. UPGRADED DAILY GOAL SECTION WITH STATUS MOTIVATION (#4)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().shadow(1.dp, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("🎯 Daily Study Goal", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextDark)
                            }
                            Text("60%", color = PrimaryBlue, fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleMedium)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        LinearProgressIndicator(
                            progress = { 0.6f },
                            modifier = Modifier.fillMaxWidth().height(10.dp), // Thicker, cleaner progress presentation
                            color = PrimaryBlue,
                            trackColor = AppBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("🔥 You're 60% toward today's goal! 2 tasks remaining.", style = MaterialTheme.typography.bodyMedium, color = PrimaryBlue, fontWeight = FontWeight.Medium)
                    }
                }
            }

            // 📂 4. NEW QUICK ACTIONS ROW MODULE (#6)
            item {
                Column {
                    Text("⚡ Quick Actions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextDark)
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        QuickActionItem(icon = Icons.Default.UploadFile, label = "Upload PDF")
                        QuickActionItem(icon = Icons.AutoMirrored.Filled.NoteAdd, label = "Add Note")
                        QuickActionItem(icon = Icons.Default.AddTask, label = "Add Task")
                        QuickActionItem(icon = Icons.Default.Psychology, label = "Ask AI")
                    }
                }
            }

            // 📊 5. THREE-COLUMN METRICS WITH NEW STUDY TIME CARD (#3)
            item {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text("📊 Daily Metrics", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextDark)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        MetricCard(modifier = Modifier.weight(1f), title = "Attendance", score = "85%", label = "📈 Up 5%", iconColor = PriorityLow)
                        MetricCard(modifier = Modifier.weight(1f), title = "Pending", score = "4 Left", label = "⏰ Due Today", iconColor = PriorityHigh)
                        MetricCard(modifier = Modifier.weight(1f), title = "Study Time", score = "2h 45m", label = "⚡ Productive", iconColor = PrimaryBlue)
                    }
                }
            }

            // 📅 6. UPCOMING TASKS PANEL WITH ENHANCED CARD SPACING (#1)
            item {
                Column {
                    Text("📅 Upcoming Tasks", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextDark)
                    Spacer(modifier = Modifier.height(10.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) { // #1: Better breathing space between single list rows
                        TaskRow(title = "Revise DBMS Chapter 3", priorityColor = PriorityHigh, priorityLabel = "High")
                        TaskRow(title = "Complete Compiler Assignment", priorityColor = PriorityMedium, priorityLabel = "Medium")
                        TaskRow(title = "Prepare Quiz Notes", priorityColor = PriorityLow, priorityLabel = "Low")
                    }
                }
            }

            // ✨ 7. AI SECTION WITH STRONG HIGH-CONTRAST ACTION CTA BUTTONS (#5)
            item {
                val aiGradient = Brush.horizontalGradient(listOf(Color(0xFF4F46E5), Color(0xFF7C3AED)))
                Card(
                    modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp)),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Column(
                        modifier = Modifier
                            .background(aiGradient)
                            .padding(20.dp)
                            .fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = "AI", tint = Color.White, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("✨ AI Engine Workspace", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Summarize your course modules or instantly generate dynamic mock quizzes.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.85f))
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f).shadow(2.dp, RoundedCornerShape(10.dp))
                            ) {
                                Text("AI Summarizer", color = TextDark, fontWeight = FontWeight.ExtraBold)
                            }
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f).shadow(2.dp, RoundedCornerShape(10.dp))
                            ) {
                                Text("Generate Quiz", color = TextDark, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                }
            }
        }

        // ➕ 8. FLOATING ACTION BUTTON IMPLEMENTATION (#7)
        FloatingActionButton(
            onClick = onFabClick,
            containerColor = PrimaryBlue,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 20.dp)
                .shadow(6.dp, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Quick Action Fab",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

// Custom Reusable Sub-Component for Quick Action Items (#6) - PREMIUM ALIGNED EDITION
@Composable
fun QuickActionItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { /* Action trigger */ }
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .shadow(2.dp, RoundedCornerShape(14.dp)) // Added clean uniform shadow depth drop
                .background(Color.White, RoundedCornerShape(14.dp)), // Unified solid premium card base surface
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = PrimaryBlue,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Transparent) // Completely eliminates separate box clipping lines
            )
        }
        Spacer(modifier = Modifier.height(8.dp)) // Sleek whitespace breathing space
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )
    }
}

@Composable
fun MetricCard(modifier: Modifier, title: String, score: String, label: String, iconColor: Color) {
    Card(
        modifier = modifier.shadow(1.dp, RoundedCornerShape(14.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                title,
                style = MaterialTheme.typography.labelSmall,
                color = TextMuted,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                score,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = TextDark,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Box(
                modifier = Modifier
                    .background(iconColor.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                Text(
                    label,
                    style = MaterialTheme.typography.labelSmall,
                    color = iconColor,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun TaskRow(title: String, priorityColor: Color, priorityLabel: String) {
    Card(
        modifier = Modifier.fillMaxWidth().shadow(0.5.dp, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(10.dp).background(priorityColor, CircleShape))
                Spacer(modifier = Modifier.width(12.dp))
                Text(title, style = MaterialTheme.typography.bodyMedium, color = TextDark, fontWeight = FontWeight.SemiBold)
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