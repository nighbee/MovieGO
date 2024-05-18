package com.ztktsn.moviego.data.model

import java.util.UUID

data class Actor
    (val id: String = UUID.randomUUID().toString(),
     val name: String,
     val imageUrl: String,
            )