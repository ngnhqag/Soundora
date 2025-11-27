package com.soundlab.soundora.data.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKey {
    val USER_INFO = stringPreferencesKey("user_info")
    val LANGUAGE_CODE = stringPreferencesKey("language_code")
}
