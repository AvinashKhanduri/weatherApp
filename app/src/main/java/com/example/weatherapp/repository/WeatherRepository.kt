package com.example.weatherapp.repository

import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.data.WeatherDatabase
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.model.Weather
import com.example.weatherapp.network.WeatherApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherApi,private val weatherDao: WeatherDao) {
     suspend fun getWeather(city:String):DataOrException<Weather,Boolean,Exception>{
        val data  = try {
            api.getWeather(city, units = "metric")

        }catch (e:Exception){
                return DataOrException(loading = true, e =e)
        }
         return DataOrException(data)
    }

    suspend fun getLocalDataBase():Flow<List<Favorite>>{
        val localData = weatherDao.getFavorites()
        return localData
    }

    suspend fun insertLoacalDataBase(favorite: Favorite){
        weatherDao.insertFavorite(favorite)
    }

    suspend fun updateFavorite(favorite: Favorite){
        weatherDao.updateFavorite(favorite)
    }

    suspend fun deleteAllFavorite(){
        weatherDao.deleteAllFavorite()
    }

    suspend fun deleteFavorite(favorite: Favorite){
        weatherDao.deleteFavorite(favorite)
    }

    suspend fun getFavById(city:String){
        weatherDao.getFavById(city)
    }






}