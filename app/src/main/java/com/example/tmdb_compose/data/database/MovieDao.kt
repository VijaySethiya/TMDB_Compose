package com.example.tmdb_compose.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Moviedata)

//    @Update
//    suspend fun updateContact(contact: )
//
//    @Delete
//    suspend fun deleteContact(contact: )
//
//    @Query("SELECT * FROM Movie")
//    suspend fun getMovie(): LiveData<List<Moviedata>>

    @Query("DELETE FROM Movie where id=:id")
    suspend fun deleteSingleMovie(id: Int?)

    @Query("SELECT EXISTS(SELECT * from Movie where id=:id)")
    fun isLiked(id: Int?): Flow<Boolean>
}
