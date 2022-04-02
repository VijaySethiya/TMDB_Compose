package com.example.tmdb_compose.uiCompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.tmdb_compose.data.model.Results

@Composable
fun MovieListItem(movie: Results, navController: NavHostController) {
    Column(modifier = Modifier.clickable { navController.navigate("next/${movie.id}") }) {
        MovieImage(movie.backdrop_path, file_name = "main")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
//                .align(Alignment.CenterVertically)

        ) {
            Text(text = movie.title, style = MaterialTheme.typography.h6)
        }
    }
}

@Composable
fun MovieImage(imgPath: String, file_name: String) {
    val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original"
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("$BASE_URL_IMAGE/$imgPath")
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )
    var size = 200
    if (file_name == "desc") {
        size = 330
    }
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .size(size.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))

    )
}
