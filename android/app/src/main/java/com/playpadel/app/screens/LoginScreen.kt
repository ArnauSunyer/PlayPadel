package com.playpadel.app.screens
import com.playpadel.app.ui.theme.PlayPadelTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playpadel.app.R


@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.playpadellogo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Box(
            Modifier.matchParentSize().background(
                Brush.verticalGradient(
                    0.0f to Color.Transparent,
                    0.5f to Color(0xFF0A2F6E).copy(alpha = 0.6f),
                    1.0f to Color(0xFF0A2F6E).copy(alpha = 0.95f)
                )
            )
        )

        Column(
            Modifier.fillMaxSize().systemBarsPadding().padding(start = 60.dp, end = 24.dp, top = 140.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.Center

        )

        {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email")},
                singleLine = true,
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Contrasenya")},
                singleLine = true,
                visualTransformation = if (visible) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = { visible = !visible }) {
                        Text(if (visible) "Amaga" else "Mostra")
                    }
                }
            )

            Spacer(Modifier.height(24.dp))

            Button(onClick = {}) { Text("Inicia sessió") }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PlayPadelTheme {
        LoginScreen()
    }
}
