package com.ztktsn.moviego.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object movieApi {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()

            val newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNGY3YzlhOTdjMTgwZDdmNmVkNWIxZWM1YTI4YzhhZSIsInN1YiI6IjY2MWNlYzk0OTMxZWExMDE2MzY0ZjdhMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.924GfSS6yD-yi8uwdY1dSmjzqbCdZnGNJY3kAfGBupk")
                .build()

            chain.proceed(newRequest)
        }

        .build()

    val api: movieService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .build()
            .create(movieService::class.java)
    }
}