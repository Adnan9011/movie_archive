package com.moviearchive.data.source.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moviearchive.data.source.db.util.COMMENT_TABLE

@Entity(tableName = COMMENT_TABLE)
data class CommentDatabaseModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val movieId: Int
)