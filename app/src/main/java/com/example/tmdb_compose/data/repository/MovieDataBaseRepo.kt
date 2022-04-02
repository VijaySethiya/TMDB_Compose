package com.example.tmdb_compose.data.repository

import android.util.Log
import com.example.tmdb_compose.data.database.MovieDao
import com.example.tmdb_compose.data.database.Moviedata
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class MovieDataBaseRepo(private val movieDao: MovieDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

//    val readAllData = movieDao.getMovie()

    fun addMovie(movieData: Moviedata) {
        coroutineScope.launch {
            movieDao.insertMovie(movieData)
        }
    }

    fun checkIsLiked(id: Int?): Flow<Boolean> = movieDao.isLiked(id)

    fun deleteMovie(id: Int?) {
        coroutineScope.launch {
            movieDao.deleteSingleMovie(id)
        }
    }
}
