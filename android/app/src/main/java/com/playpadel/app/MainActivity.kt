package com.playpadel.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.playpadel.app.screens.LoginScreen
import com.playpadel.app.screens.RegisterScreen
import com.playpadel.app.ui.theme.PlayPadelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayPadelTheme {
                var pantalla by remember { mutableStateOf("login") }

                when (pantalla) {
                    "login" -> LoginScreen(
                        onRegister = { pantalla = "registre" }
                    )
                    "registre" -> RegisterScreen(
                        onBack = { pantalla = "login" },
                        onRegistered = { pantalla = "login" }
                    )
                }
            }
        }
    }
}