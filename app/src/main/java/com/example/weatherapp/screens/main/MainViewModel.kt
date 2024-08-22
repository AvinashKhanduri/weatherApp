package com.example.weatherapp.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.City
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

//     val weatherData:MutableState<DataOrException<Weather,Boolean,Exception>> = mutableStateOf(
//          DataOrException(null,true,Exception(""))
//     )
//
//     init {
//         getData("dhaka")
//     }
//
//     private fun getData(city:String){
//          try {
//               viewModelScope.launch {
//                    weatherData.value = repository.getWeather(city)
//                    weatherData.value.loading = weatherData.value.data == null
//               }
//
//
//          }catch (e:Exception){
//               weatherData.value.e = e
//          }
//     }


    suspend fun getWeatherData(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(city)
    }
}
     
