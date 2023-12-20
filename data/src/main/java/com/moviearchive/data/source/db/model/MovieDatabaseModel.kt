package com.moviearchive.data.source.db.model

internal data class MovieDatabaseModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val numComments: Int,
    val numLikes: Int,
    val isLiked: Boolean
)