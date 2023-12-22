package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.model.CommentDataModel
import com.moviearchive.data.model.MovieDataModel
import com.moviearchive.data.model.toData
import com.moviearchive.data.source.api.api.ApiServiceImpl
import com.moviearchive.data.source.datastore.DataStoreSource
import com.moviearchive.data.source.db.dao.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRespositoryImpl @Inject constructor(
    val api: ApiServiceImpl,
    val dao: MovieDao,
    val dataStore: DataStoreSource
) : MovieRepository {
    override fun getMovies(): Flow<Result<List<MovieDataModel>, Error>> =
        api.getMovies().map { result ->
            result.map { list ->
                list.map { it.toData() }
            }
        }

    override fun getComments(): Flow<Result<List<CommentDataModel>, Error>> =
        api.getComments().map { result ->
            result.map { list ->
                list.map { it.toData() }
            }
        }
}