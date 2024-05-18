package com.ztktsn.moviego.data.model

sealed class MovieState {

        data class Loading(val isLoading: Boolean) : MovieState()
        data class Success(val items: List<Movie>) : MovieState()
        data class Error(val message: String? = null) : MovieState()
}