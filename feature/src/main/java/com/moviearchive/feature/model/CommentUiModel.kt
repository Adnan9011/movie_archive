package com.moviearchive.feature.model

import com.moviearchive.model.CommentDomainModel

data class CommentUiModel(
    val id: Int,
    val title: String
)

internal fun CommentDomainModel.toUi() = CommentUiModel(
    id = id,
    title = title
)