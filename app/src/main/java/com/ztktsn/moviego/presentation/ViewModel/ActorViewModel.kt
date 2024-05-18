package com.ztktsn.moviego.presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ztktsn.moviego.data.model.ActorApi
import com.ztktsn.moviego.data.model.ActorResponse
import com.ztktsn.moviego.data.model.ActorState
import com.ztktsn.moviego.data.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActorViewModel (
    private val service: MovieService
    ) : ViewModel() {

        class Provider(
            val service: MovieService
        ) : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ActorViewModel(service) as T
            }
        }

        private val _actorListState = MutableLiveData<ActorState>()
        val actorListState: LiveData<ActorState> get() = _actorListState

        fun fetchActorList() {
            _actorListState.value = ActorState.Loading(true)

            service.fetchActorList().enqueue(object : Callback<ActorResponse> {
                override fun onResponse(
                    call: Call<ActorResponse>,
                    response: Response<ActorResponse>
                ) {
                    if (!response.isSuccessful) {
                        _actorListState.value = ActorState.Error()
                    }

                    _actorListState.value = response.body()?.let {
                        ActorState.Success(
                            it.results.map {
                                ActorApi.toActor(it)
                            }
                        )
                    } ?: ActorState.Error()

                    _actorListState.value = ActorState.Loading(false)
                }

                override fun onFailure(call: Call<ActorResponse>, t: Throwable) {
                    _actorListState.value = ActorState.Error(t.message)
                }

            })
        }

        }
