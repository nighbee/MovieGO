package com.ztktsn.moviego.data.network



import com.ztktsn.moviego.data.adapter.LoginRequest
import com.ztktsn.moviego.data.adapter.LoginResponse
import com.ztktsn.moviego.data.adapter.RegisterRequest
import com.ztktsn.moviego.data.model.ActorResponse
import retrofit2.Call
import retrofit2.http.GET
import com.ztktsn.moviego.data.model.MovieApiResponse
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


    @GET("3/movie/now_playing")
    fun fetchLatestList(): Call<MovieApiResponse>

    @GET("3/configuration")
    fun checkApiConnection(): Call<Any>
}