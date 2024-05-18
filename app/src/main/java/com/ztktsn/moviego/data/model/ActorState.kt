package com.ztktsn.moviego.data.model
sealed class ActorState {

    data class Loading(val isLoading: Boolean) : ActorState()
    data class Success(val items: List<Actor>) : ActorState()
    data class Error(val message: String? = null) : ActorState()
}