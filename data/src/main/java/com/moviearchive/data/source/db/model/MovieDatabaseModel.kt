package com.moviearchive.data.source.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moviearchive.data.source.db.util.MOVIE_TABLE

@Entity(tableName = MOVIE_TABLE)
data class MovieDatabaseModel(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val numComments: Int,
    val numLikes: Int,
    val isLiked: Boolean
)