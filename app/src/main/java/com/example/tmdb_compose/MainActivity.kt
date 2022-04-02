package com.example.tmdb_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tmdb_compose.data.network.MovieService
import com.example.tmdb_compose.data.repository.MovieRepository
import com.example.tmdb_compose.ui.theme.TMDB_COMPOSETheme
import com.example.tmdb_compose.uiCompose.AdoptionScreen
import com.example.tmdb_compose.uiCompose.ShowMovie
import com.example.tmdb_compose.viewadapter.MainViewModel
import com.example.tmdb_compose.viewadapter.MainViewModelFactory
import com.example.tmdb_compose.viewadapter.MovieDVMFactory
import com.example.tmdb_compose.viewadapter.MovieDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var mainViewModel: MainViewModel
        lateinit var movieDatabaseViewModel: MovieDatabaseViewModel
        super.onCreate(savedInstanceState)
        val repository = MovieRepository(MovieService.movieInstance)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        movieDatabaseViewModel =
            ViewModelProvider(
                this,
                MovieDVMFactory(applicationContext)
            ).get(MovieDatabaseViewModel::class.java)
        setContent {
            TMDB_COMPOSETheme { // }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieNavigation(mainViewModel, movieDatabaseViewModel)
                }
            }
        }
    }
}

@Composable
fun MovieNavigation(mainViewModel: MainViewModel, movieDatabaseViewModel: MovieDatabaseViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            ShowMovie(mainViewModel, navController)
        }
        composable(route = Screen.Next.route + "/{id}") { navBackStack ->
            val Id = navBackStack.arguments?.getString("id")
            AdoptionScreen(Id, mainViewModel.movies, movieDatabaseViewModel)
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun DefaultPreview() {
//    TMDB_COMPOSETheme {
//        ShowMovie()
//    }
// }


