package com.example.weatherapp.screens.favorite

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class FavoriteViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
//    ViewModel() {
//    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
//    val favList = _favList.asStateFlow()
//
//    fun insertFavorite(favorite: Favorite) {
//        viewModelScope.launch {
//            weatherRepository.insertLoacalDataBase(favorite)
//        }
//    }
//
//    fun deleteFavorite(favorite: Favorite) {
//        viewModelScope.launch {
//            weatherRepository.deleteFavorite(favorite)
//        }
//    }
//
//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            weatherRepository.getLocalDataBase().distinctUntilChanged()
//                .collect { listOfFav ->
//                    if (listOfFav.isEmpty()) {
//                        Log.d("FL", ":list is empty ")
//                    } else {
//                        _favList.value = listOfFav
//                        Log.d("FLF", "${favList.value}: ")
//                    }
//                }
//
//        }
//
//    }
//
//
//}

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getLocalDataBase().distinctUntilChanged()
                .collect { listOfFav ->
                    if (listOfFav.isEmpty()) {
                        Log.d("FL", "list is empty")
                    } else {
                        _favList.value = listOfFav
                        Log.d("FLF", "Collected favorites: ${favList.value}")
                    }
                }
        }
    }

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.insertLoacalDataBase(favorite)
            // Ensure that the database is updated and will trigger a new flow emission
            _favList.value = weatherRepository.getLocalDataBase().first()
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.deleteFavorite(favorite)
            // Ensure that the database is updated and will trigger a new flow emission
            _favList.value = weatherRepository.getLocalDataBase().first()
        }
    }
}
