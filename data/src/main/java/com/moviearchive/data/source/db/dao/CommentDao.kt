package com.moviearchive.data.source.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moviearchive.data.source.db.model.CommentDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Query("SELECT * FROM COMMENT_TABLE WHERE movieId = :movieId")
    fun getAll(movieId: Int): Flow<List<CommentDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comments: List<CommentDatabaseModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: CommentDatabaseModel)

    @Query("DELETE FROM COMMENT_TABLE")
    suspend fun deleteAll()
}