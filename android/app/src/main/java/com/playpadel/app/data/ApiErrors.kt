package com.playpadel.app.data

import com.google.gson.JsonParser
import retrofit2.HttpException

fun missatgeDelServidor(e: HttpException): String {
    val cos = e.response()?.errorBody()?.string()
    try {
        val missatge = JsonParser.parseString(cos).asJsonObject.get("message")?.asString
        if (!missatge.isNullOrBlank()) return missatge
    } catch (_: Exception) {
    }
    return when (e.code()) {
        400 -> "Revisa les dades del formulari"
        401 -> "Email o contrasenya incorrectes"
        403 -> "No tens permís per fer aquesta acció"
        409 -> "Aquest email ja està registrat"
        else -> "Error del servidor (${e.code()})"
    }
}