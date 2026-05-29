package com.example.smartstudyai.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstudyai.data.repository.AuthRepository
import com.example.smartstudyai.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// ⚙️ Immutable UI State Wrapper
data class AuthUiState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val user: FirebaseUser? = null
)

class AuthViewModel(
    private val repository: AuthRepository = AuthRepositoryImpl()
) : ViewModel() {

    // 🔄 Reactive dynamic stream tracking user session persistence status globally
    val isUserLoggedIn: StateFlow<Boolean> = repository.isUserLoggedIn
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    // 📝 Single source of state truth for your login/signup forms
    var uiState by mutableStateOf(AuthUiState())
        private set

    // 🔑 Core Operation 1: Sign In Engine
    fun signIn(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null, successMessage = null)
            val result = repository.loginWithEmailAndPassword(email, password)
            uiState = result.fold(
                onSuccess = { authenticatedUser ->
                    onSuccess()
                    uiState.copy(isLoading = false, user = authenticatedUser)
                },
                onFailure = { error ->
                    uiState.copy(isLoading = false, errorMessage = error.localizedMessage ?: "Invalid email or password.")
                }
            )
        }
    }

    // 📝 Core Operation 2: Account Registration Engine
    fun signUp(name: String, email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null, successMessage = null)
            val result = repository.signupWithEmailAndPassword(name, email, password)
            uiState = result.fold(
                onSuccess = { registeredUser ->
                    onSuccess()
                    uiState.copy(isLoading = false, user = registeredUser)
                },
                onFailure = { error ->
                    uiState.copy(isLoading = false, errorMessage = error.localizedMessage ?: "Registration failed. Try again.")
                }
            )
        }
    }

    // 🔒 Core Operation 3: Identity Verification Recovery
    fun resetPassword(email: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null, successMessage = null)
            val result = repository.sendPasswordResetEmail(email)
            uiState = result.fold(
                onSuccess = {
                    uiState.copy(isLoading = false, successMessage = "A secure verification reset link has been dispatched to your inbox!")
                },
                onFailure = { error ->
                    uiState.copy(isLoading = false, errorMessage = error.localizedMessage ?: "Failed to transmit recovery email.")
                }
            )
        }
    }

    // 🛰️ Core Operation 4: Google OAuth Authorization
    fun signInWithGoogle(idToken: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null, successMessage = null)
            val result = repository.loginWithGoogle(idToken)
            uiState = result.fold(
                onSuccess = { googleUser ->
                    onSuccess()
                    uiState.copy(isLoading = false, user = googleUser)
                },
                onFailure = { error ->
                    uiState.copy(isLoading = false, errorMessage = error.localizedMessage ?: "Google Sign-In authentication rejected.")
                }
            )
        }
    }

    // 🚪 Core Operation 5: Terminate Global User Sessions
    fun signOut() {
        viewModelScope.launch {
            repository.logout()
            uiState = AuthUiState() // Reset global state completely on logout
        }
    }

    // 🧹 Helper operation to reset visual error alerts on layout configuration changes
    fun clearErrorMessages() {
        uiState = uiState.copy(errorMessage = null, successMessage = null)
    }
}