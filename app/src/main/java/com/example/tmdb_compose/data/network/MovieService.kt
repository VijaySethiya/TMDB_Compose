package com.example.tmdb_compose.data.network

import com.example.tmdb_compose.data.model.MovieList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/"
const val API_KEY = "c70c85f93863758f60741347aee79697"
const val type = "popular"

interface MovieInterface {
    @GET("3/movie/$type?api_key=$API_KEY")
    suspend fun getPopularMovie(@Query("language") language: String, @Query("page") page: Int): Response<MovieList>
}

object MovieService {
    val movieInstance: MovieInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieInstance = retrofit.create(MovieInterface::class.java)
    }
}
