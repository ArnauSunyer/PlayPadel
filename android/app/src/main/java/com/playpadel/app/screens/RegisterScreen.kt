package com.playpadel.app.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun RegisterScreen (
    onBack: () -> Unit = {},
    onRegistered: () -> Unit = {}
){

    var nom by remember { mutableStateOf("") }
    var cognom by remember {mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    var acceptarPrivacitat by remember {mutableStateOf(false)}
    var acceptarCookies by remember {mutableStateOf(false)}

}