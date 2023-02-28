package com.example.lumi_kuke.di

import com.example.lumi_kuke.util.Constants.Companion.API_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> provideApiService(service: Class<T>): T {
        return retrofit.create(service)
    }
}