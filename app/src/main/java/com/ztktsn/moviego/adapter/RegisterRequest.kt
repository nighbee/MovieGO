package com.ztktsn.moviego.adapter
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirmpassword") val confirmPassword: String
)