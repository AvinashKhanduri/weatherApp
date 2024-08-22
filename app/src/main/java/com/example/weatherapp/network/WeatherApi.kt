package com.example.weatherapp.network

import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton
//https://api.openweathermap.org/data/2.5/forecast/daily?q=afzalgarh&appid=ed60fcfbd110ee65c7150605ea8aceea&units=metric
@Singleton
interface WeatherApi{

    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query:String,
        @Query("units") units:String = "imperial",
        @Query("appid") appid:String = Constants.API_KEY
    ):Weather

}