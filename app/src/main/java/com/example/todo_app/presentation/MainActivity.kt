package com.example.todo_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo_app.core.Screen
import com.example.todo_app.core.UserData
import com.example.todo_app.core.UserViewModel
import com.example.todo_app.presentation.dashboard.DashboardScreen
import com.example.todo_app.presentation.login.LoginScreen
import com.example.todo_app.presentation.register.RegisterScreen
import com.example.todo_app.presentation.splash_screen.SplashScreen
import com.example.todo_app.presentation.theme.ui.TODO_AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODO_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val userData = UserData(applicationContext)
                    val userViewModel: UserViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                                    @Suppress("UNCHECKED_CAST")
                                    return UserViewModel(userData) as T
                                }
                                throw IllegalArgumentException("Unknown ViewModel class")
                            }
                        }
                    )

                    NavHost(
                        navController = navController,
                        startDestination = if (UserData.PASSWORD_KEY.name.isBlank())
                                            Screen.SplashScreen.route
                                        else Screen.RegisterScreen.route
                    ) {
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController)
                        }
                        composable(route = Screen.RegisterScreen.route) {
                            RegisterScreen(
                                navController = navController,
                                userViewModel =  userViewModel
                            )
                        }
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(userData, navController)
                        }
                        composable(route = Screen.DashboardScreen.route) {
                            DashboardScreen()
                        }
                    }
                }
            }
        }
    }
}