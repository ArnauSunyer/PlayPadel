package com.playpadel.app.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playpadel.app.data.LoginRequest
import com.playpadel.app.data.RetrofitClient
import com.playpadel.app.data.SessionManager
import com.playpadel.app.data.missatgeDelServidor
import java.io.IOException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {

    var carregant by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var loguejat by mutableStateOf(false)
        private set

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            error = "Introdueix l'email i la contrasenya"
            return
        }
        error = null
        carregant = true

        viewModelScope.launch {
            try {
                val resposta = RetrofitClient.api.login(LoginRequest(email.trim(), password))
                SessionManager.guardarSessio(resposta.token, resposta.usuari.nom)
                loguejat = true
            } catch (e: HttpException) {
                error = missatgeDelServidor(e)
            } catch (e: IOException) {
                error = "No es pot connectar amb el servidor"
            } finally {
                carregant = false
            }
        }
    }
}