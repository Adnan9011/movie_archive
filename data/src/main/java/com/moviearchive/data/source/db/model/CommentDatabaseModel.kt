package com.moviearchive.data.source.db.model

internal data class CommentDatabaseModel(
    val id: Int,
    val title: String,
    val movieId: Int
)