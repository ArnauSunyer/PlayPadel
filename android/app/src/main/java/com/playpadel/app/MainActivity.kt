package com.playpadel.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.playpadel.app.data.SessionManager
import com.playpadel.app.screens.ChangePasswordScreen
import com.playpadel.app.screens.ForgotPasswordScreen
import com.playpadel.app.screens.HomeScreen
import com.playpadel.app.screens.LoginScreen
import com.playpadel.app.screens.RegisterScreen
import com.playpadel.app.screens.ResetPasswordScreen
import com.playpadel.app.ui.theme.PlayPadelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prepara el lloc on es guarda la sessió (token i nom)
        SessionManager.init(this)

        enableEdgeToEdge()
        setContent {
            PlayPadelTheme {
                val navController = rememberNavController()

                // Si ja hi ha sessió guardada, entrem directament a la Home
                val inici = if (SessionManager.estaLoguejat()) "home" else "login"

                NavHost(navController = navController, startDestination = inici) {

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
                                SessionManager.tancarSessio()
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