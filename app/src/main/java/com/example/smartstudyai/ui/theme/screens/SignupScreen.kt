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
import com.example.smartstudyai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    onNavigateToLogin: () -> Unit = {},
    onSignupSuccess: () -> Unit = {},
    authViewModel: com.example.smartstudyai.viewmodel.AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel() // 🧠 Added state machine engine
) {
    // 📝 Local Registration Input States
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // ⚙️ UI Control States
    val uiState = authViewModel.uiState
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
   var isLoading by remember { mutableStateOf(false) }
   var errorMessage by remember { mutableStateOf<String?>(null) }


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
                text = "Create Account",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = TextDark
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Join SmartStudyAI to unlock smart cloud notes & analytics",
                style = MaterialTheme.typography.bodyMedium,
                color = TextMuted,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            // ⚠️ Error Message Box Display
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

            // 👤 Full Name Input Field
            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    errorMessage = null
                },
                label = { Text("Full Name") },
                placeholder = { Text("Sandeep Kumar") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 📧 Email Input Field
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    errorMessage = null
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

            Spacer(modifier = Modifier.height(14.dp))

            // 🔑 Password Input Field
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = null
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

            Spacer(modifier = Modifier.height(14.dp))

            // 🔄 Confirm Password Input Field
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    errorMessage = null
                },
                label = { Text("Confirm Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                ),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            imageVector = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle confirm password visibility",
                            tint = TextMuted
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(28.dp))

            // 🚀 Action Execution Button
            Button(
                onClick = {
                    if (fullName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                        errorMessage = "Please fill in all layout fields."
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        errorMessage = "Please enter a valid email address."
                    } else if (password.length < 6) {
                        errorMessage = "Password must be at least 6 characters long."
                    } else if (password != confirmPassword) {
                        errorMessage = "Passwords do not match. Please verify again."
                    } else {
                        errorMessage = null // Clear any input error

                        // 🚀 TRIGGER FIREBASE ARCHITECTURE NOW
                        authViewModel.signUp(
                            name = fullName,
                            email = email,
                            password = password,
                            onSuccess = {
                                // Handled dynamically via MainScaffold gatekeeper state flow stream
                            }
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                shape = RoundedCornerShape(12.dp),
                // ✅ Watch both local checks and backend network state streams
                enabled = !isLoading && !uiState.isLoading
            ) {
                // ✅ Check if Firebase backend or local process is loading
                if (isLoading || uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.5.dp
                    )
                } else {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔑 Back to Login Link
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Already have an account? ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted
                )
                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue,
                    modifier = Modifier.clickable { onNavigateToLogin() }
                )
            }
        }
    }
}