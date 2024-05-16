package com.ztktsn.moviego.network


import com.ztktsn.moviego.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import com.ztktsn.moviego.model.MovieApiResponse
import retrofit2.http.Query


interface MovieService {


    @GET("3/movie/popular")
    fun fetchMovieList(): Call<MovieApiResponse>
    fun getMovieByName(@Query("name") name: String): Call<List<Movie>>
}