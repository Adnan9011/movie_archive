package com.moviearchive.feature.presentation.home

import androidx.lifecycle.ViewModel
import com.moviearchive.domain.MovieRepository
import com.moviearchive.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MovieUiState(
    val movies: ImmutableList<Movie> = emptyList<Movie>() as ImmutableList<Movie>,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    private fun storeMovies() {
//        movieRepository.updateSaved(isSaved = true)
    }

    private fun updateLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun updateSaved(isSaved: Boolean) {
        _uiState.update { it.copy(isSaved = isSaved) }
    }

}