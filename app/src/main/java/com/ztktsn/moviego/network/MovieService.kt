package com.ztktsn.moviego.network



import com.ztktsn.moviego.adapter.LoginRequest
import com.ztktsn.moviego.adapter.LoginResponse
import com.ztktsn.moviego.adapter.RegisterRequest
import com.ztktsn.moviego.model.ActorResponse
import retrofit2.Call
import retrofit2.http.GET
import com.ztktsn.moviego.model.MovieApiResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST


interface MovieService {
    @POST("Users/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("Users/register")
    fun register(@Body registerRequest: RegisterRequest): Call<ResponseBody>

    @GET("3/movie/popular")
    fun fetchMovieList(): Call<MovieApiResponse>


    @GET("3/person/popular")
    fun fetchActorList(): Call<ActorResponse>


    @GET("3/movie/latest")
    fun fetchLatestList(): Call<MovieApiResponse>

    @GET("3/configuration")
    fun checkApiConnection(): Call<Any>
}