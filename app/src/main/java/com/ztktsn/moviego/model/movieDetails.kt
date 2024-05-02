package com.ztktsn.moviego.model

import com.google.gson.annotations.SerializedName

data class movieDetails (
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("runtime") val runtime: Int,
    val genre: String
    ) {
    companion object{
        fun tomovie( movieApi: movieDetails)= movie(
            id = movieApi.id,
            title = movieApi.title,
            shortDescription = movieApi.overview,
            rating = movieApi.voteAverage.toDouble(),
            imageUrl = movieApi.posterPath,
            duration = 100,
        )
    }
}