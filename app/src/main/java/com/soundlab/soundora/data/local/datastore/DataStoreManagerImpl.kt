package com.soundlab.soundora.data.local.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.soundlab.soundora.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class DataStoreManagerImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreManager {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun saveUserInfo(user: User) {
        try {
            val userString = json.encodeToString(user)
            dataStore.edit { preferences ->
                preferences[DataStoreKey.USER_INFO] = userString
            }
        } catch (e: Exception) {
            Log.e("DataStoreManager", "Error saving user to preferences ${e.message}")
        }
    }

    override fun getUserInfo(): Flow<User?> {
        return dataStore.data
            .map { preferences ->
                val userString = preferences[DataStoreKey.USER_INFO]
                userString?.let {
                    try {
                        json.decodeFromString<User>(it)
                    } catch (e: Exception) {
                        Log.e("DataStoreManager", "Error decoding user JSON: ${e.message}")
                        null
                    }
                }
            }
    }

    override suspend fun saveLanguageCode(languageCode: String) {
        try {
            dataStore.edit { preferences ->
                preferences[DataStoreKey.LANGUAGE_CODE] = languageCode
            }
        } catch (e: Exception) {
            Log.e("DataStoreManager", "Error saving language code: ${e.message}")
        }
    }

    override fun getLanguageCode(): Flow<String?> {
        return dataStore.data
            .map { preferences ->
                preferences[DataStoreKey.LANGUAGE_CODE]
            }
    }
}