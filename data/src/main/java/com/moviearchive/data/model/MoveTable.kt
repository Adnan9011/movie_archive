package com.moviearchive.data.model

data class MoveTable(val id: Int,
                     val title: String,
                     val imageUrl: String,
                     val numComments: Int,
                     val numLikes: Int,
                     val isLiked: Boolean)