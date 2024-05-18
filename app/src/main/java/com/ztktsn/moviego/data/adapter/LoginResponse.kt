package com.ztktsn.moviego.data.adapter
import com.google.gson.annotations.SerializedName
data class LoginResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("password") val password:String,
    @SerializedName("email") val email:String
)
