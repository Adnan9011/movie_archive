package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.model.CommentDataModel
import com.moviearchive.data.model.MovieDataModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<Result<List<MovieDataModel>, Error>>
    fun getComments(): Flow<Result<List<CommentDataModel>, Error>>
}