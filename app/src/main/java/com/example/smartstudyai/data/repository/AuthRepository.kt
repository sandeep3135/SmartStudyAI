package com.example.smartstudyai.data.repository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    // 🔍 Exposes the active user session token state dynamically
    val currentUser: FirebaseUser?
    val isUserLoggedIn: Flow<Boolean>

    // 🔐 Task 4 Framework Authentication Operations
    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<FirebaseUser>
    suspend fun signupWithEmailAndPassword(name: String, email: String, password: String): Result<FirebaseUser>
    suspend fun sendPasswordResetEmail(email: String): Result<Unit>
    suspend fun loginWithGoogle(idToken: String): Result<FirebaseUser>

    // 🚪 Session Termination
    suspend fun logout()
}