package com.ztktsn.moviego.network



import com.ztktsn.moviego.model.ActorResponse
import retrofit2.Call
import retrofit2.http.GET
import com.ztktsn.moviego.model.MovieApiResponse


interface MovieService {

    @GET("3/movie/popular")
    fun fetchMovieList(): Call<MovieApiResponse>


    @GET("3/person/popular")
    fun fetchActorList(): Call<ActorResponse>


    @GET("3/movie/latest")
    fun fetchLatestList(): Call<MovieApiResponse>

    @GET("3/configuration")
    fun checkApiConnection(): Call<Any>
}