@file:JvmName("UserDataKt")

package com.example.todo_app.core

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserData(private val context: Context) {

    companion object {
        val EMAIL_KEY = stringPreferencesKey("email")
        val NAME_KEY = stringPreferencesKey("name")
        val PASSWORD_KEY = stringPreferencesKey("password")
    }

    val userEmail: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[EMAIL_KEY]
    }

    val userName: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[NAME_KEY]
    }

    val userPassword: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PASSWORD_KEY]
    }

    suspend fun saveUser(email: String, name: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
            preferences[NAME_KEY] = name
            preferences[PASSWORD_KEY] = password
        }
    }

    suspend fun clearUser() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}