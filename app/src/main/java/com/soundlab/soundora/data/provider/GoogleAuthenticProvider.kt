package com.soundlab.soundora.data.provider

import android.app.Activity

interface GoogleAuthenticProvider {
    suspend fun signIn(activity: Activity): Boolean
    fun isSignedIn(): Boolean
    suspend fun signOut(activity: Activity)
}