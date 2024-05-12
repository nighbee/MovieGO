package com.ztktsn.moviego.network


import com.ztktsn.moviego.model.Movie
import com.ztktsn.moviego.model.movieApiResponse
import com.ztktsn.moviego.model.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface MovieService {
    @GET("3/movie/popular")
    suspend fun fetchMovieList(@Query("page") page: Int): movieApiResponse

    @GET("3/movie/{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") movieId: Int): MovieDetails
    fun getMovieByName(@Query("name") name:String) : Call<List<Movie>>
}