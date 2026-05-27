package com.example.smartstudyai.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.smartstudyai.ui.theme.*
import kotlinx.coroutines.launch

// Data model representing an onboarding page step
data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val accentColor: Color
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onOnboardingComplete: () -> Unit) {
    val pages = listOf(
        OnboardingPage(
            title = "AI-Powered Study Assistant",
            description = "Chat with a highly specialized AI tutor tailored completely to your engineering and analytical course tracks.",
            icon = Icons.Default.AutoAwesome,
            accentColor = PrimaryBlue
        ),
        OnboardingPage(
            title = "Generate Notes & Quizzes Instantly",
            description = "Drop your complex textbook PDFs into the system to extract summarized markdown files and mock practice questions in seconds.",
            icon = Icons.Default.MenuBook,
            accentColor = Color(0xFF7C3AED) // Deep Purple
        ),
        OnboardingPage(
            title = "Track Productivity Smarter",
            description = "Monitor class attendance thresholds, track task lists, and optimize your study velocity using real-time local analytics.",
            icon = Icons.Default.TrendingUp,
            accentColor = PriorityLow
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(24.dp)
    ) {
        // Horizontal sliding pager component
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { position ->
            val page = pages[position]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Large circular floating asset ring icon representation
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .background(page.accentColor.copy(alpha = 0.12f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = page.icon,
                        contentDescription = null,
                        tint = page.accentColor,
                        modifier = Modifier.size(64.dp)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = page.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = page.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextMuted,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // Bottom Controls Layout (Indicator Dots + Navigation Actions button)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) {
            // Center Dot Indicators Page tracker indicator layout system
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(pages.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .size(width = if (isSelected) 24.dp else 8.dp, height = 8.dp)
                            .background(
                                color = if (isSelected) PrimaryBlue else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                }
            }

            // Action Trigger Buttons placement execution layout container element rules
            Button(
                onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onOnboardingComplete()
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(
                    text = if (pagerState.currentPage == pages.size - 1) "Get Started" else "Next",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}