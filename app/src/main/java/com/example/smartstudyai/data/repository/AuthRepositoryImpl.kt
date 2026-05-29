package com.example.smartstudyai.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : AuthRepository {

    // 🔍 Dynamic session tracking token active updates
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override val isUserLoggedIn: Flow<Boolean> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser != null)
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }

    // 🔑 Email & Password Login Core
    override suspend fun loginWithEmailAndPassword(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user ?: throw Exception("User session profile is completely empty.")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // 📝 Registration Layout Integration & Profile Update
    override suspend fun signupWithEmailAndPassword(name: String, email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user ?: throw Exception("Registration session engine failed.")

            // Push full name payload data instantly inside cloud token reference profile
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()
            user.updateProfile(profileUpdates).await()

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // 🔒 Trigger Verification Identity Password Recovery
    override suspend fun sendPasswordResetEmail(email: String): Result<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // 🛰️ Google Sign-In Single-Tap Secure Handshake Credentials Handler
    override suspend fun loginWithGoogle(idToken: String): Result<FirebaseUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            val user = result.user ?: throw Exception("Google OAuth verification returned an empty profile token.")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // 🚪 Close User Cloud Sessions Instantly
    override suspend fun logout() {
        firebaseAuth.signOut()
    }
}