package com.ztktsn.moviego.model

data class Movie (
    val id: Int,
    val title: String,
    val shortDescription: String,
    val rating : Double,
    val imageUrl: String,
    val duration: Int,
)
