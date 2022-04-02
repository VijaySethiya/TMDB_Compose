package com.example.tmdb_compose.viewadapter

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MovieDVMFactory(private val application: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDatabaseViewModel(application) as T
    }
}