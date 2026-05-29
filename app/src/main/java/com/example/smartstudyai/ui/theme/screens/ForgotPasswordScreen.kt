package com.example.smartstudyai.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.smartstudyai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onNavigateBackToLogin: () -> Unit = {}
) {
    // 📝 Local Input State
    var email by remember { mutableStateOf("") }

    // ⚙️ UI Control States
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        // 🔙 Premium Top Back Navigation Button
        IconButton(
            onClick = onNavigateBackToLogin,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .statusBarsPadding()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Navigate Back",
                tint = TextDark
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🎓 Header Typography
            Text(
                text = "Reset Password",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = TextDark
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Enter your registered email below. We will send you a secure link to reset your account credentials.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextMuted,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ⚠️ Error Box Display
            if (errorMessage != null) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                ) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // 🎉 Success Confirmation Box
            if (successMessage != null) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)) // Light mint premium background tint
                ) {
                    Text(
                        text = successMessage!!,
                        color = Color(0xFF2E7D32), // Dark emerald green font
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // 📧 Target Email Input Field
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    errorMessage = null
                    successMessage = null
                },
                label = { Text("Email Address") },
                placeholder = { Text("example@student.com") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🚀 Action Button with verified layout modifier
            Button(
                onClick = {
                    if (email.isBlank()) {
                        errorMessage = "Please enter your email address."
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        errorMessage = "Please enter a valid email address."
                    } else {
                        isLoading = true
                        errorMessage = null
                        // Firebase password reset trigger will be added here in Task 4️⃣!
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                shape = RoundedCornerShape(12.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.5.dp
                    )
                } else {
                    Text(
                        text = "Send Reset Link",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}