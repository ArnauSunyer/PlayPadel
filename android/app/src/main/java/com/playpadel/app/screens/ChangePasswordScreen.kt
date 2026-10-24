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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playpadel.app.ui.theme.PlayPadelTheme

@Composable
fun ChangePasswordScreen(
    onBack: () -> Unit = {},
    onDone: () -> Unit = {}
) {
    var actual by remember { mutableStateOf("") }
    var nova by remember { mutableStateOf("") }
    var nova2 by remember { mutableStateOf("") }

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Canvia la contrasenya",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            CampText(
                actual, { actual = it }, "Contrasenya actual",
                visualTransformation = PasswordVisualTransformation(),
                teclat = KeyboardType.Password
            )
            CampText(
                nova, { nova = it }, "Nova contrasenya",
                visualTransformation = PasswordVisualTransformation(),
                teclat = KeyboardType.Password
            )
            CampText(
                nova2, { nova2 = it }, "Repeteix la nova contrasenya",
                visualTransformation = PasswordVisualTransformation(),
                teclat = KeyboardType.Password
            )

            Button(
                onClick = onDone,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Desar canvis")
            }

            TextButton(onClick = onBack) {
                Text("Cancel·lar")
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
fun ChangePasswordScreenPreview() {
    PlayPadelTheme {
        ChangePasswordScreen()
    }
}