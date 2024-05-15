package com.ztktsn.moviego.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ztktsn.moviego.model.MovieApi
import com.ztktsn.moviego.model.MovieApiResponse
import com.ztktsn.moviego.model.MovieState
import com.ztktsn.moviego.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

    class MovieViewModel(
        private val service: MovieService
    ) : ViewModel() {

        class Provider(
            val service: MovieService
        ) : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MovieViewModel(service) as T
            }
        }

        private val _movieListState = MutableLiveData<MovieState>()
        val movielistState: LiveData<MovieState> get() = _movieListState

        fun fetchOfferList() {
            _movieListState.value = MovieState.Loading(true)

            service.fetchMovieList().enqueue(object : Callback<MovieApiResponse> {
                override fun onResponse(
                    call: Call<MovieApiResponse>,
                    response: Response<MovieApiResponse>
                ) {
                    if (!response.isSuccessful) {
                        _movieListState.value = MovieState.Error()
                    }

                    _movieListState.value = response.body()?.let {
                        MovieState.Success(
                            it.results.map {
                                MovieApi.toMovie(it)
                            }
                        )
                    } ?: MovieState.Error()

                    _movieListState.value = MovieState.Loading(false)
                }

                override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {
                    _movieListState.value = MovieState.Error(t.message)
                }

            })
        }
    }
