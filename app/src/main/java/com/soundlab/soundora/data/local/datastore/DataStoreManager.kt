package com.soundlab.soundora.data.local.datastore

import com.soundlab.soundora.domain.model.User
import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    suspend fun saveUserInfo(user: User)
    fun getUserInfo(): Flow<User?>

    suspend fun saveLanguageCode(languageCode : String)
    fun getLanguageCode(): Flow<String?>
}