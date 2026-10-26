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
data class LoginRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val token: String,
    val tipus: String,
    val expiraEnSegons: Long,
    val usuari: UserResponse
)