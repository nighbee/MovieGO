package com.ztktsn.moviego.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserAPI {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://your-api-url.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService = retrofit.create(UserService::class.java)
}