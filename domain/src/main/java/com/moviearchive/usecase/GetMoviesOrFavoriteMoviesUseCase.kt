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

class GetMoviesOrFavoriteMoviesUseCase @Inject internal constructor(
    private val repository: MovieRepository
) : UseCase<Boolean, Flow<Result<List<MovieDomainModel>, Error>>> {
    override suspend fun invoke(isFavorite: Boolean): Flow<Result<List<MovieDomainModel>, Error>> {
        return if (isFavorite) repository.getAllLiked().map { result ->
            result.map { list ->
                list.map { it.toDomain() }
            }
        } else repository.getAll().map { result ->
            result.map { list ->
                list.map { it.toDomain() }
            }
        }
    }
}
