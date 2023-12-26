package com.moviearchive.usecase

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.model.MovieDomainModel
import com.moviearchive.model.toDomain
import com.moviearchive.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieUseCase @Inject internal constructor(
    private val repository: MovieRepository
) : UseCase<Int, Flow<Result<MovieDomainModel, Error>>> {
    override suspend fun invoke(input: Int): Flow<Result<MovieDomainModel, Error>> =
        repository.get(input).map {
            it.map { it.toDomain() }
        }
}