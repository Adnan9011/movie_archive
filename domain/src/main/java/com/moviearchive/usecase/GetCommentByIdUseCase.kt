package com.moviearchive.usecase

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.CommentRepository
import com.moviearchive.model.CommentDomainModel
import com.moviearchive.model.toDomain
import com.moviearchive.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCommentByIdUseCase @Inject internal constructor(
    private val repository: CommentRepository
) : UseCase<Int, Flow<Result<List<CommentDomainModel>, Error>>> {
    override suspend fun invoke(movieId: Int): Flow<Result<List<CommentDomainModel>, Error>> =
        repository.getAll(movieId).map { result ->
            result.map { list ->
                list.filter { it.movieId == movieId }
                    .map { it.toDomain() }
            }
        }
}