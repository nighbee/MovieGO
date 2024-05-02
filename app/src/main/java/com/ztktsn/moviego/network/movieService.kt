package com.ztktsn.moviego.network


import com.ztktsn.moviego.model.movie
import com.ztktsn.moviego.model.movieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface movieService {
    @GET("3/movie/popular")
    suspend fun fetchMovieList(@Query("page") page: Int): movieApiResponse

    @GET("3/movie/{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") movieId: Int): movieDetails
    fun getMovieByName(@Query("name") name:String) : Call<List<movie>>
}