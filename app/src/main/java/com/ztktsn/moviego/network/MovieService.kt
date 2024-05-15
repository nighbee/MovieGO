package com.ztktsn.moviego.network


import retrofit2.Call
import retrofit2.http.GET
import com.ztktsn.moviego.model.MovieApiResponse


interface MovieService {

    @GET("3/movie/popular")
    fun fetchMovieList(): Call<MovieApiResponse>
}