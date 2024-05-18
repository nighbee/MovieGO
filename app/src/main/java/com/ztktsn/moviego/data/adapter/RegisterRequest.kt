package com.ztktsn.moviego.data.adapter
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirmpassword") val confirmPassword: String
)