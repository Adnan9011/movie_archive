package com.moviearchive.data.source.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moviearchive.data.source.db.model.MovieDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM MOVIE_TABLE")
    fun getAllMovie(): Flow<List<MovieDatabaseModel>>

    @Query("SELECT * FROM MOVIE_TABLE WHERE isLiked = true")
    fun getMoviesLiked(): Flow<List<MovieDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDatabaseModel>)

    @Query("DELETE FROM MOVIE_TABLE")
    suspend fun deleteAll()
}