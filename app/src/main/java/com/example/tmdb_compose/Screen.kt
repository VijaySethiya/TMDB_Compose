package com.example.tmdb_compose

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Next : Screen("next")
}
