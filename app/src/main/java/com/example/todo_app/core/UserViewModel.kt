package com.example.todo_app.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val userData: UserData) : ViewModel() {

    val userEmail: StateFlow<String?> = userData.userEmail.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val userName: StateFlow<String?> = userData.userName.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val userPassword: StateFlow<String?> = userData.userPassword.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun saveUser(email: String, name: String, password: String) {
        viewModelScope.launch {
            userData.saveUser(email, name, password)
        }
    }

    fun clearUser() {
        viewModelScope.launch {
            userData.clearUser()
        }
    }
}