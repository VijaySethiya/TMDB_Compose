package com.example.tmdb_compose.uiCompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tmdb_compose.R
import com.example.tmdb_compose.data.model.MovieList
import com.example.tmdb_compose.viewadapter.MovieDatabaseViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AdoptionScreen(
    Id: String?,
    movies: StateFlow<MovieList?>,
    movieDatabaseViewModel: MovieDatabaseViewModel
) {
    var index1: Int = 0
    if (Id != null) {
        index1 = Id.toInt()
    }
    val result = movies.collectAsState()
    val result1 = result.value?.results?.filter {
        it.id == index1
    }
    movieDatabaseViewModel.isLikedView(result1?.get(0)?.id)
    val isLiked by movieDatabaseViewModel.isLiked.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box() {
            MovieImage(imgPath = "${result1?.get(0)?.backdrop_path}", "desc")
            IconButton(
                onClick = {
                    if (isLiked) movieDatabaseViewModel.deleteMovieClicked(
                        result1?.get(0)?.id
                    ) else movieDatabaseViewModel.insertMovie(result1?.get(0)?.id)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .scale(1.5f)
                    .padding(top = 15.dp, end = 15.dp)
            ) {
                if (!isLiked)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favourite),
                        contentDescription = "liked icon"
                    )
                else
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favourite_filled),
                        contentDescription = "liked icon",
                        tint = Color.Red
                    )
            }
        }
        Text(
            text = "${result1?.get(0)?.overview}",
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.body1
        )
    }
}
