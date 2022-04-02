package com.example.tmdb_compose.viewadapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_compose.data.model.MovieList
import com.example.tmdb_compose.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies: MutableStateFlow<MovieList?> = MutableStateFlow(null)
    val movies = _movies.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _movies.value = repository.getPopularMovies("en-US", 1)
        }
    }
}
