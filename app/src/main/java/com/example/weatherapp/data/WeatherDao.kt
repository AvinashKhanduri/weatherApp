package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.model.Favorite
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    @Query(value = "SELECT * FROM FAV_TBL")
    fun getFavorites(): Flow<List<Favorite>>


    @Query(value = "select * from fav_tbl where city=:city")
    suspend fun getFavById(city: String): Favorite


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)


    @Query(value = "delete from fav_tbl")
    suspend fun deleteAllFavorite()


    @Delete
    suspend fun deleteFavorite(favorite: Favorite)


}