package com.playpadel.app.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://localhost:8080/"

    // Afegeix "Authorization: Bearer <token>" a totes les peticions si hi ha sessió
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val peticio = chain.request()
            val token = SessionManager.token
            val novaPeticio = if (token != null) {
                peticio.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            } else {
                peticio
            }
            chain.proceed(novaPeticio)
        }
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}