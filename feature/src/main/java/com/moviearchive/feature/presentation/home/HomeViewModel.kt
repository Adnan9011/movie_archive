package com.moviearchive.feature.presentation.home

import android.util.Log
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
class HomeViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    init {
        Log.e("Adnan", "ViewModel - init")
        getMovies()
    }

    private val _uiState = MutableStateFlow<Result<List<MovieUiModel>, Error>>(Result.Loading)
    val uiState = _uiState.asStateFlow()

    private fun getMovies() {
        Log.e("Adnan", "ViewModel - getMovies")

        viewModelScope.launch {
            getMovieUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    updateError(throwable)
                }
                .collect { result ->
                    Log.e("Adnan", "ViewModel - result")
                    Log.e("Adnan", "ViewModel - $result")
                    _uiState.value = result.map { list -> list.map { it.toUi() } }
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