package com.example.squarerepos.remote

import com.example.squarerepos.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

val json = Json { ignoreUnknownKeys = true }

val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY // Set logging level to include body
}

// Create OkHttpClient and add the logging interceptor
val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BuildConfig.BASE_URL)
    .client(client)
    .build()

object SquareApiClient {
    val retrofitService: SquareApiInterface by lazy {
        retrofit.create(SquareApiInterface::class.java)
    }
}