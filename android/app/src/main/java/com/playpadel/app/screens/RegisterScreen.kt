package com.playpadel.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playpadel.app.ui.theme.PlayPadelTheme

@Composable
fun RegisterScreen(
    onBack: () -> Unit = {},
    onRegistered: () -> Unit = {},
    viewModel: RegisterViewModel = viewModel()
) {
    var nom by remember { mutableStateOf("") }
    var cognom by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefon by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var acceptarPrivacitat by remember { mutableStateOf(false) }
    var acceptarMarketing by remember { mutableStateOf(false) }
    var errorLocal by remember { mutableStateOf<String?>(null) }

    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(start = 32.dp, end = 32.dp, top = 64.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Crea el teu compte",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            CampText(nom, { nom = it }, "Nom")
            CampText(cognom, { cognom = it }, "Cognoms")
            CampText(email, { email = it }, "Email", teclat = KeyboardType.Email)
            CampText(telefon, { telefon = it }, "Telèfon (opcional)", teclat = KeyboardType.Phone)
            CampText(
                password, { password = it }, "Contrasenya",
                visualTransformation = PasswordVisualTransformation(),
                teclat = KeyboardType.Password
            )
            CampText(
                password2, { password2 = it }, "Repeteix la contrasenya",
                visualTransformation = PasswordVisualTransformation(),
                teclat = KeyboardType.Password
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = acceptarPrivacitat,
                    onCheckedChange = { acceptarPrivacitat = it }
                )
                Text("Accepto la política de privacitat", color = Color.White)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = acceptarMarketing,
                    onCheckedChange = { acceptarMarketing = it }
                )
                Text("Vull rebre comunicacions comercials", color = Color.White)
            }

            // Error de l'app (contrasenyes) o del servidor (email repetit, validacions...)
            val missatgeError = errorLocal ?: viewModel.error
            if (missatgeError != null) {
                Text(missatgeError, color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = {
                    if (password != password2) {
                        errorLocal = "Les contrasenyes no coincideixen"
                    } else {
                        errorLocal = null
                        viewModel.registrar(
                            nom, cognom, email, telefon, password,
                            acceptarPrivacitat, acceptarMarketing
                        )
                    }
                },
                enabled = acceptarPrivacitat && !viewModel.carregant,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (viewModel.carregant) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Crear compte")
                }
            }

            TextButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Ja tinc compte")
            }
        }

        FletxaEnrere(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .systemBarsPadding()
                .padding(8.dp)
        )
    }

    // Diàleg quan el servidor confirma que s'ha creat el compte
    if (viewModel.registrat) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Compte creat") },
            text = { Text("El teu compte s'ha creat correctament. Ja pots iniciar sessió.") },
            confirmButton = {
                TextButton(onClick = onRegistered) {
                    Text("Iniciar sessió")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PlayPadelTheme {
        RegisterScreen()
    }
}