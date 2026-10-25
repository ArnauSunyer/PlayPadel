package com.playpadel.app.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParser
import com.playpadel.app.data.RegisterRequest
import com.playpadel.app.data.RetrofitClient
import java.io.IOException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel : ViewModel() {

    var carregant by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var registrat by mutableStateOf(false)
        private set

    fun registrar(
        nom: String,
        cognoms: String,
        email: String,
        telefon: String,
        password: String,
        acceptaPrivacitat: Boolean,
        acceptaMarketing: Boolean
    ) {
        error = null
        carregant = true

        viewModelScope.launch {
            try {
                RetrofitClient.api.register(
                    RegisterRequest(
                        nom = nom.trim(),
                        cognoms = cognoms.trim(),
                        email = email.trim(),
                        telefon = telefon.trim().ifBlank { null },
                        password = password,
                        politicaAcceptada = acceptaPrivacitat,
                        acceptaComunicacions = acceptaMarketing
                    )
                )
                registrat = true
            } catch (e: HttpException) {
                error = missatgeError(e)
            } catch (e: IOException) {
                error = "No es pot connectar amb el servidor: ${e.message}"
            } finally {
                carregant = false
            }
        }
    }

    fun netejarError() {
        error = null
    }

    // Llegeix el missatge d'error que envia el backend en JSON
    private fun missatgeError(e: HttpException): String {
        val cos = e.response()?.errorBody()?.string()
        try {
            val json = JsonParser.parseString(cos).asJsonObject
            val errors = json.getAsJsonArray("errors")
            if (errors != null && errors.size() > 0) {
                return errors[0].asJsonObject.get("defaultMessage").asString
            }
            val missatge = json.get("message")?.asString
            if (!missatge.isNullOrBlank()) return missatge
        } catch (_: Exception) {
        }
        return when (e.code()) {
            400 -> "Revisa les dades del formulari"
            409 -> "Aquest email ja està registrat"
            else -> "Error del servidor (${e.code()})"
        }
    }
}
