package com.ztktsn.moviego.network



import retrofit2.Call
import retrofit2.http.GET
import com.ztktsn.moviego.model.MovieApiResponse
import retrofit2.Response


interface MovieService {

    @GET("3/movie/popular")
    fun fetchMovieList(): Call<MovieApiResponse>

    @GET("search/movie")
    suspend fun searchMovies(
    ): Response<MovieApiResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
    ): Response<MovieApiResponse>

    @GET("3/configuration")
    fun checkApiConnection(): Call<Any>
}