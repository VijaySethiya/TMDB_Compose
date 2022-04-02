package com.example.tmdb_compose.uiCompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.tmdb_compose.data.model.Results
import com.example.tmdb_compose.viewadapter.MovieDatabaseViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieContent(
    result: List<Results>,
    navController: NavHostController
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3)
    ) {
        items(result.size) {
            MovieListItem(
                movie = result[it],
                navController

            )
        }
    }
}
