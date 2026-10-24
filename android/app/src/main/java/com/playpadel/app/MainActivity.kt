package com.playpadel.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.playpadel.app.screens.ForgotPasswordScreen
import com.playpadel.app.screens.LoginScreen
import com.playpadel.app.screens.RegisterScreen
import com.playpadel.app.ui.theme.PlayPadelTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.playpadel.app.screens.ChangePasswordScreen
import com.playpadel.app.screens.HomeScreen
import com.playpadel.app.screens.ResetPasswordScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayPadelTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {

                    composable("login") {
                        LoginScreen(
                            onRegister = { navController.navigate("registre") },
                            onForgotPassword = { navController.navigate("recuperar") },
                            onLogin = {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("registre") {
                        RegisterScreen(
                            onBack = { navController.popBackStack() },
                            onRegistered = { navController.popBackStack() }
                        )
                    }

                    composable("recuperar") {
                        ForgotPasswordScreen(
                            onBack = { navController.popBackStack() },
                            onHasCodi = { navController.navigate("nova") }
                        )
                    }

                    composable("nova") {
                        ResetPasswordScreen(
                            onBack = { navController.popBackStack("login", false) },
                            onDone = { navController.popBackStack("login", false) }
                        )
                    }

                    composable("home") {
                        HomeScreen(
                            onChangePassword = { navController.navigate("canvi") },
                            onLogout = {
                                navController.navigate("login") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("canvi") {
                        ChangePasswordScreen(
                            onBack = { navController.popBackStack() },
                            onDone = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
