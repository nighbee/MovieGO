package com.ztktsn.moviego.model

import com.google.gson.annotations.SerializedName


data class MovieApi(
    val id: String,
    val title: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String
) {

    companion object {
        fun toMovie(MovieApi: MovieApi) = Movie(
            id = MovieApi.id,
            title = MovieApi.title,
            shortDescription = MovieApi.overview,
            rating = MovieApi.voteAverage.toDouble(),
            imageUrl = MovieApi.posterPath,
            duration = 100,
            genre = arrayListOf(Movie.Genre.COMEDY)
        )
    }
}