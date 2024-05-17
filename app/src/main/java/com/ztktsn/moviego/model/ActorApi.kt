package com.ztktsn.moviego.model

import com.google.gson.annotations.SerializedName

data class ActorApi(
    val id: String,
    val name: String,
    @SerializedName("profile_path") val profile_path: String
    ) {

        companion object {
            fun toActor(ActorApi: ActorApi) = Actor(
                id = ActorApi.id,
                name = ActorApi.name,
                imageUrl = ActorApi.profile_path
            )
        }
    }
