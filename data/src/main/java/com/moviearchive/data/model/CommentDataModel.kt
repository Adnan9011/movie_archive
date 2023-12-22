package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.CommentApiModel
import com.moviearchive.data.source.db.model.CommentDatabaseModel

data class CommentDataModel(
    val id: Int,
    val title: String,
    val movieId: Int
)

internal fun CommentApiModel.toData() = CommentDataModel(
    id = id,
    title = title,
    movieId = movieId
)

internal fun CommentDatabaseModel.toData() = CommentDataModel(
    id = id,
    title = title,
    movieId = movieId
)