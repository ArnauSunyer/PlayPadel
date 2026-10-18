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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playpadel.app.ui.theme.CourtBlue
import com.playpadel.app.ui.theme.CourtBlueDark
import com.playpadel.app.ui.theme.PlayPadelTheme

@Composable
fun RegisterScreen(
    onBack: () -> Unit = {},
    onRegistered: () -> Unit = {}
) {
    var nom by remember { mutableStateOf("") }
    var cognom by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefon by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var password2 by remember {mutableStateOf("")}

    var acceptarPrivacitat by remember { mutableStateOf(false) }
    var acceptarMarketing by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(CourtBlue, CourtBlueDark)))
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Crea el teu compte",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            CampText(nom, { nom = it }, "Nom")
            CampText(cognom, { cognom = it }, "Cognoms")
            CampText(email, { email = it }, "Email")
            CampText(telefon,{telefon = it},"Telèfon(opcional)")
            CampText(password, { password = it }, "Contrasenya", visualTransformation = PasswordVisualTransformation(), teclat = KeyboardType.Password)
            CampText(password2, { password2 = it }, "Repeteix la contrasenya", visualTransformation = PasswordVisualTransformation(), teclat = KeyboardType.Password)
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

            Button(
                onClick = onRegistered,
                enabled = acceptarPrivacitat,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Crear compte")
            }

            TextButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Ja tinc compte")
            }
        }
    }
}

@Composable
private fun CampText(
    valor: String,
    alCanviar: (String) -> Unit,
    etiqueta: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    teclat: KeyboardType = KeyboardType.Text

) {
    OutlinedTextField(
        value = valor,
        onValueChange = alCanviar,
        placeholder = { Text(etiqueta) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = teclat),

    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PlayPadelTheme {
        RegisterScreen()
    }
}