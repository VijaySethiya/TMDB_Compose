package com.example.tmdb_compose.uiCompose

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.tmdb_compose.viewadapter.MainViewModel
import com.example.tmdb_compose.viewadapter.MovieDatabaseViewModel

@Composable
fun ShowMovie(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val movies = mainViewModel.movies.collectAsState()
//    val result = remember { mutableListOf(movie) }
    Scaffold(
        content = {
            MovieContent(movies.value?.results ?: emptyList(), navController)
        }
    )
}
