package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.MovieApiModel
import com.moviearchive.data.source.db.model.MovieDatabaseModel

data class MovieDataModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val numComments: Int,
    val numLikes: Int,
    val isLiked: Boolean
)

internal fun MovieApiModel.toData() = MovieDataModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    numComments = numComments,
    numLikes = numLikes,
    isLiked = isLiked
)

internal fun MovieDatabaseModel.toData() = MovieDataModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    numComments = numComments,
    numLikes = numLikes,
    isLiked = isLiked
)