package com.playpadel.app.data

data class RegisterRequest(
    val nom: String,
    val cognoms: String,
    val email: String,
    val telefon: String?,
    val password: String,
    val politicaAcceptada: Boolean,
    val acceptaComunicacions: Boolean
)

data class UserResponse(
    val id: Long,
    val nom: String,
    val cognoms: String,
    val email: String,
    val rol: String
)