package com.soundlab.soundora.data.provider

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.soundlab.soundora.R
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthenticProviderImpl(): GoogleAuthenticProvider {
    private val tag = "Firebase AuthManager: "
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun isSignedIn(): Boolean {
        val user = firebaseAuth.currentUser
        return if (user != null) {
            Log.d(tag, "Already signed in → uid=${user.uid}, email=${user.email}")
            true
        } else {
            Log.d(tag, "Not signed in (currentUser=null)")
            false
        }
    }


    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override suspend fun signIn(activity: Activity): Boolean {
        if (isSignedIn()) return true

        try {
            val credentialManager = CredentialManager.create(activity)
            val result = buildCredentialRequest(activity, credentialManager)
            return handleSignIn(result)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e(tag, "SignIn error: ${e.message}", e)
            return false
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private suspend fun handleSignIn(result: GetCredentialResponse): Boolean {
        val credential = result.credential

        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                println(tag + "name: ${tokenCredential.displayName}")
                println(tag + "email: ${tokenCredential.id}")
                println(tag + "image: ${tokenCredential.profilePictureUri}")

                val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)
                val authRequest = firebaseAuth.signInWithCredential(authCredential).await()

                return authRequest.user != null
            } catch (e: GoogleIdTokenParsingException) {
                Log.e(tag, "GoogleIdTokenParsingException: ${e.message}")
                return false
            }
        } else {
            Log.e(tag, "credential is not GoogleIdTokenCredential")
            return false
        }
    }

    private suspend fun buildCredentialRequest(
        activity: Activity,
        credentialManager: CredentialManager
    ): GetCredentialResponse {
        val request = GetCredentialRequest.Builder().addCredentialOption(
            GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(ContextCompat.getString(activity, R.string.web_client_id))
                .setAutoSelectEnabled(false)
                .build()
        ).build()
        return credentialManager.getCredential(context = activity, request = request)
    }

    override suspend fun signOut(activity: Activity) {
        // For sign out, we can use application context
        val credentialManager = CredentialManager.create(activity)
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
        firebaseAuth.signOut()
    }
}