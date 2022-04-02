package com.example.tmdb_compose.data.repository

import com.example.tmdb_compose.data.model.MovieList
import com.example.tmdb_compose.data.network.MovieInterface

class MovieRepository(private val movieInterface: MovieInterface) {
    //    private val movieLiveData = MutableLiveData<MovieList>()
//    val movies: LiveData<MovieList>
//        get() = movieLiveData
    suspend fun getPopularMovies(language: String, page: Int): MovieList? {
        val result = movieInterface.getPopularMovie(language, page)
        if (result.body() != null) {
            return result.body()
        }
        return null
    }
}
