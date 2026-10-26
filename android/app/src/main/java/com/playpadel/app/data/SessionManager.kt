package com.playpadel.app.data

import android.content.Context
import android.content.SharedPreferences

object SessionManager {

    private const val PREFS = "playpadel_sessio"
    private const val CLAU_TOKEN = "token"
    private const val CLAU_NOM = "nom"

    private lateinit var prefs: SharedPreferences

    // Cal cridar-ho una vegada en arrencar l'app (a MainActivity)
    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

    val token: String?
        get() = prefs.getString(CLAU_TOKEN, null)

    val nom: String?
        get() = prefs.getString(CLAU_NOM, null)

    fun estaLoguejat(): Boolean = token != null

    fun guardarSessio(token: String, nom: String) {
        prefs.edit().putString(CLAU_TOKEN, token).putString(CLAU_NOM, nom).apply()
    }

    fun tancarSessio() {
        prefs.edit().clear().apply()
    }
}