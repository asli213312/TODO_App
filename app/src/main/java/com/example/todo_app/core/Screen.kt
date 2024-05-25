package com.example.todo_app.core

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object DashboardScreen: Screen("tasks_screen")
}