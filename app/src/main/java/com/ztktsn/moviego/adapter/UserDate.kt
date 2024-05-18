package com.ztktsn.moviego.adapter
import com.google.gson.annotations.SerializedName
data class UserDate(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
