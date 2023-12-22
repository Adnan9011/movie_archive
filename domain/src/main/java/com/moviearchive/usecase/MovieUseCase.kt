package com.moviearchive.usecase

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.model.MovieDomainModel
import com.moviearchive.model.toDomain
import com.moviearchive.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject internal constructor(
    private val repository: MovieRepository
) : UseCaseNoInput<Flow<Result<List<MovieDomainModel>, Error>>> {
    override suspend fun invoke(): Flow<Result<List<MovieDomainModel>, Error>> {
        return repository.getMovies().map { result ->
            result.map { list ->
                list.map { it.toDomain() }
            }
        }
    }
}
