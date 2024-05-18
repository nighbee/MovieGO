package com.ztktsn.moviego.data.network

import com.ztktsn.moviego.data.adapter.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface UserService {
    @POST("register")
    fun register(@Body registerRequest: RegisterRequest): Call<ResponseBody>
}