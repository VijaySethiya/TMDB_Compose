package com.example.tmdb_compose.data.model

data class MovieList(
    val page: Int,
    val results: List<Results>,
    val total_pages: Int,
    val total_results: Int
)
