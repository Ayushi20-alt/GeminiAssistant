package com.example.charmxhealth.data.manager

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import com.example.charmxhealth.domain.manager.LocalUserManager
import com.example.charmxhealth.presentation.util.Constants.APP_KEY
import com.example.charmxhealth.presentation.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow

class LocalUserManagerImpl(
    private val context : Context
): LocalUserManager {

    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings->
           settings[PreferencesKey.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences->
             preferences[PreferencesKey.APP_ENTRY]?: false
        }
    }
}

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKey{
     val APP_ENTRY = booleanPreferencesKey(name = APP_KEY)
}