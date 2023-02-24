package com.example.labo1

import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType

private const val BASE_URL = "https://quentin.lurkin.xyz/courses/mobile/lab3/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
    .baseUrl(BASE_URL)
    .build()


interface QuotesApiServices  {
    @GET("quotes.json")//quotes.json
    suspend fun getQuotes():MutableMap<String,List<Quote>>
}

// singleton to limit high ressources use
object QuotesApi {
    val retrofitService : QuotesApiServices by lazy {
        retrofit.create(QuotesApiServices::class.java)
    }
}