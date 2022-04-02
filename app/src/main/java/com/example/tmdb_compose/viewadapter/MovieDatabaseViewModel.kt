package com.example.tmdb_compose.viewadapter

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_compose.data.database.MovieDatabase
import com.example.tmdb_compose.data.database.Moviedata
import com.example.tmdb_compose.data.repository.MovieDataBaseRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDatabaseViewModel(application: Context) : ViewModel() {
    private val repository: MovieDataBaseRepo

    private val _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean> = _isLiked

    init {
        val movieDb = MovieDatabase.getDatabase(application)
        val movieDao = movieDb.movieDao()
        repository = MovieDataBaseRepo(movieDao)
    }

    fun insertMovie(id: Int?) {
        repository.addMovie(Moviedata(id))
    }

    fun isLikedView(id: Int?) {
        viewModelScope.launch {
            repository.checkIsLiked(id).collect {
                _isLiked.emit(it)
            }
        }
    }

    fun deleteMovieClicked(id: Int?) {
        repository.deleteMovie(id)
    }
}
