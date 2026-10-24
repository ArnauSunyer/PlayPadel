package com.playpadel.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.playpadel.app.ui.theme.PlayPadelTheme

@Composable
fun ResetPasswordScreen(
    onBack: () -> Unit = {},
    onDone: () -> Unit = {}
) {
    var codi by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }

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
                .padding(horizontal = 32.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(
                "Nova contrasenya",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                "Introdueix el codi que has rebut per email i escull una contrasenya nova.",
                color = Color.White.copy(alpha = 0.8f)
            )

            CampText(codi, { codi = it }, "Codi rebut per email")
            CampText(
                password, { password = it }, "Nova contrasenya",
                visualTransformation = PasswordVisualTransformation(),
                teclat = KeyboardType.Password
            )
            CampText(
                password2, { password2 = it }, "Repeteix la contrasenya",
                visualTransformation = PasswordVisualTransformation(),
                teclat = KeyboardType.Password
            )

            Button(
                onClick = onDone,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Canviar contrasenya")
            }

            TextButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Tornar a l'inici de sessió")
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
}

@Preview(showBackground = true)
@Composable
fun ResetPasswordScreenPreview() {
    PlayPadelTheme {
        ResetPasswordScreen()
    }
}