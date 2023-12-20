package com.moviearchive.data.repository

import com.moviearchive.core.Result
import com.moviearchive.data.model.MovieDataModel
import javax.inject.Inject

class MovieRepository @Inject constructor() {
    fun getData(): Result<List<MovieDataModel>, String> {
        val list = listOf<MovieDataModel>()
        return Result.Success(list)
    }
}