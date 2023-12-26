package com.moviearchive.feature.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.feature.model.toUi
import com.moviearchive.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<Result<MovieUiModel, Error>>(Result.Loading)
    val uiState = _uiState.asStateFlow()

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            getMovieUseCase(movieId)
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    updateError(throwable)
                }
                .collect { result ->
                    _uiState.value =
                        result.map { it.toUi() }
                }
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        _uiState.update { Result.Loading }
    }

    private fun updateError(throwable: Throwable) {
        _uiState.update { Result.Failure(Error(throwable = throwable)) }
    }
}