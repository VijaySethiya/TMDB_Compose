package com.example.tmdb_compose.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class Moviedata(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = 0,
)
