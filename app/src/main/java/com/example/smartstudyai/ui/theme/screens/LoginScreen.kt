package com.example.smartstudyai.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartstudyai.ui.theme.*
import com.example.smartstudyai.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToSignup: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    authViewModel: AuthViewModel = viewModel() // 🧠 Added the central state machine engine
) {
    // 📝 Local Input States
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // ⚙️ UI Control States
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // 🛰️ Live State stream reference from Firebase
    val uiState = authViewModel.uiState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🎓 Header Section
            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = TextDark
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Sign in to resume your smart study sessions",
                style = MaterialTheme.typography.bodyMedium,
                color = TextMuted,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ⚠️ Error Message Display Area (Combines manual validation & Firebase server replies)
            val displayError = errorMessage ?: uiState.errorMessage
            if (displayError != null) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                ) {
                    Text(
                        text = displayError,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // 📧 Email Text Input Field
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    errorMessage = null // Clear errors dynamically on input change
                    authViewModel.clearErrorMessages()
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

            Spacer(modifier = Modifier.height(16.dp))

            // 🔑 Password Input Field with Visibility Toggle Switch
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = null
                    authViewModel.clearErrorMessages()
                },
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle password visibility",
                            tint = TextMuted
                        )
                    }
                }
            )

            // 🔒 Forgot Password Link Row
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue,
                    modifier = Modifier.clickable { onNavigateToForgotPassword() }
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // 🚀 Action Execution Button
            Button(
                onClick = {
                    // 🔍 Step-by-Step Layout Validation Logic
                    if (email.isBlank() || password.isBlank()) {
                        errorMessage = "Please enter both email and password."
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        errorMessage = "Please enter a valid email address."
                    } else if (password.length < 6) {
                        errorMessage = "Password must be at least 6 characters long."
                    } else {
                        errorMessage = null
                        // 🚀 TRIGGER SECURE FIREBASE LOGIN PIPELINE
                        authViewModel.signIn(
                            email = email,
                            password = password,
                            onSuccess = { onLoginSuccess() }
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                shape = RoundedCornerShape(12.dp),
                enabled = !uiState.isLoading // Disable interaction when processing network operations
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp), // ✅ Safe native layout canvas modification modifier
                        color = Color.White,
                        strokeWidth = 2.5.dp
                    )
                } else {
                    Text(
                        text = "Sign In", // ✅ RESTORED: Visible text font signature for clean asset alignment
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 📝 Signup Context Split Link Row
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Don't have an account? ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted
                )
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue,
                    modifier = Modifier.clickable { onNavigateToSignup() }
                )
            }
        }
    }
}