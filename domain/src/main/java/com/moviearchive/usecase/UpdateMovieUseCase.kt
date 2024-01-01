package com.moviearchive.usecase

import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.model.MovieDomainModel
import com.moviearchive.model.toData
import com.moviearchive.util.UseCase
import javax.inject.Inject

class UpdateMovieUseCase @Inject internal constructor(
    private val repository: MovieRepository
) : UseCase<MovieDomainModel, Unit> {
    override suspend fun invoke(movie: MovieDomainModel) =
        repository.update(movie.toData())
}