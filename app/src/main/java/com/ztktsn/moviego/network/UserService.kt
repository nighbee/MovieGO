package com.ztktsn.moviego.network

import com.ztktsn.moviego.adapter.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface UserService {
    @POST("register")
    fun register(@Body registerRequest: RegisterRequest): Call<ResponseBody>
}