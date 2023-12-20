package com.moviearchive.data.source.api.model

internal data class MovieApiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val numComments: Int,
    val numLikes: Int,
    val isLiked: Boolean
)
