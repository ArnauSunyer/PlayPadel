package com.playpadel.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playpadel.app.ui.theme.PlayPadelTheme

@Composable
fun HomeScreen(
    onChangePassword: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    var menuObert by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Capçalera: nom de l'app + menú de perfil
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "PlayPadel",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )

                Box {
                    IconButton(onClick = { menuObert = true }) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Perfil",
                            tint = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = menuObert,
                        onDismissRequest = { menuObert = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Canviar contrasenya") },
                            onClick = {
                                menuObert = false
                                onChangePassword()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Tancar sessió") },
                            onClick = {
                                menuObert = false
                                onLogout()
                            }
                        )
                    }
                }
            }

            Text(
                "Hola, jugador",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier.padding(top = 24.dp)
            )

            // Contingut: reserves i pistes
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                item {
                    Text(
                        "Les teves reserves",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
                item {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Encara no tens cap reserva.",
                            color = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                item {
                    Text(
                        "Pistes disponibles",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                items(pistesDemo) { pista ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(
                                    pista.nom,
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(pista.tipus, color = Color.White.copy(alpha = 0.7f))
                                Text(pista.preu, color = MaterialTheme.colorScheme.primary)
                            }
                            Button(onClick = {}) {
                                Text("Reservar")
                            }
                        }
                    }
                }
            }
        }
    }
}

private data class PistaDemo(val nom: String, val tipus: String, val preu: String)

private val pistesDemo = listOf(
    PistaDemo("Pista 1", "Interior · Cristall", "12 €/h"),
    PistaDemo("Pista 2", "Interior · Cristall", "12 €/h"),
    PistaDemo("Pista 3", "Exterior · Paret", "9 €/h"),
    PistaDemo("Pista 4", "Exterior · Paret", "9 €/h")
)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PlayPadelTheme {
        HomeScreen()
    }
}