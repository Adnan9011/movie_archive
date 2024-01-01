package com.moviearchive.data.source.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.moviearchive.data.source.db.model.MovieDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM MOVIE_TABLE")
    fun getAll(): Flow<List<MovieDatabaseModel>>

    @Query("SELECT * FROM MOVIE_TABLE WHERE id=:movieId")
    fun getMovie(movieId: Int): Flow<MovieDatabaseModel>

    @Query("SELECT * FROM MOVIE_TABLE WHERE isLiked = true")
    fun getAllLiked(): Flow<List<MovieDatabaseModel>>

    @Update
    suspend fun update(movie: MovieDatabaseModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movies: List<MovieDatabaseModel>)

    @Query("DELETE FROM MOVIE_TABLE")
    suspend fun deleteAll()
}