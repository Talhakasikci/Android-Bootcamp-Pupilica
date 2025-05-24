package com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.remote

import android.os.Build
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object ApiClient {

    private const val BASE_URL = "http://kasimadalan.pe.hu/"

    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("HTTP", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}