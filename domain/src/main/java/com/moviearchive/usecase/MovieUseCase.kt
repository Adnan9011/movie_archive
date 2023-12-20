package com.moviearchive.usecase

import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.model.MovieDomainModel
import com.moviearchive.model.toDomain
import com.moviearchive.util.UseCaseNoInput
import javax.inject.Inject

class MovieUseCase @Inject internal constructor(
    private val repository: MovieRepository
) : UseCaseNoInput<Result<List<MovieDomainModel>, String>> {
    override suspend fun invoke(): Result<List<MovieDomainModel>, String> {
        val result: Result<List<MovieDomainModel>, String>
        return repository.getData().map { list -> list.map { it.toDomain() } }
    }
}
