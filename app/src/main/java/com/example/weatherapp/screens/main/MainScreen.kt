package com.example.weatherapp.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.icons.rememberHumidityMid
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.model.Weather
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.screens.favorite.FavoriteViewModel
import com.example.weatherapp.util.formateDate
import com.example.weatherapp.util.formateDateTime
import com.example.weatherapp.util.formateDesimals
import com.example.weatherapp.widgets.appBar
import com.example.weatherapp.widgets.humidityWindRow
import com.example.weatherapp.widgets.sunSetsunRise
import com.example.weatherapp.widgets.weatherDetailRow
import com.example.weatherapp.widgets.weatherStateImage
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun mainScreen(
    navController: NavController,
    vm: MainViewModel,
    fvm: FavoriteViewModel,
    city: String? = "Delhi"
) {
    Log.d("GB", "mainScreen: city $city")

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(
            null,
            true,
            Exception("")
        ), producer = {
            value = vm.getWeatherData("$city")
            if (value.data != null) {
                value.loading = false
            }
        })

//    weatherData.value.data?.let { mainScaffold(weather = it) }
    if (weatherData.value.loading == true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.size(100.dp)
            )
        }

    } else if (weatherData.value.data != null) {
        mainScaffold(weather = weatherData.value.data!!, navController, fvm)


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainScaffold(weather: Weather, navController: NavController, fvm: FavoriteViewModel) {
    val isFavorite = rememberSaveable {
        mutableStateOf(false)
    }

    fvm.favList.collectAsState().value.forEach { it ->
        if (it.city == weather.city.name && it.country == weather.city.country) {
            isFavorite.value = true
        }
    }


    Scaffold(
        topBar = {
            appBar(
                elevation = 10.dp,
                titleText = "${weather.city.name}, ${weather.city.country}",
                onAddActionClicked = { navController.navigate(WeatherScreens.SearchScreen.name){popUpTo(WeatherScreens.MainScreen.name){inclusive = false} } },
                navController = navController,
                onFavoriteClicked = {
                    fvm.insertFavorite(Favorite(weather.city.name, weather.city.country))
                    Log.d("FL", "favorite ${fvm.favList.value}")
                    isFavorite.value = true
                },
                isFavorite = isFavorite.value
            )
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        mainContent(
            data = weather,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
    }
}


@Composable
fun mainContent(data: Weather, modifier: Modifier) {
    val image_url = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formateDate(data.list[0].dt), modifier = Modifier.padding(top = 10.dp),
            style = MaterialTheme.typography.titleMedium
        )

        Surface(
            modifier =
            Modifier
                .padding(10.dp)
                .size(150.dp),
            tonalElevation = 3.dp,
            shape = CircleShape
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(
                    color = Color(
                        0xFFFF9100
                    )
                )
            ) {
                weatherStateImage(imageUrl = image_url)
                Text(
                    text = "${formateDesimals(data.list[0].temp.day)}°",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = data.list[0].weather[0].main, fontStyle = FontStyle.Italic)

            }
        }
        humidityWindRow(data)
        Divider()
        sunSetsunRise(data = data)
        Text(text = "This Week", fontSize = 15.sp, style = MaterialTheme.typography.displayMedium)
        LazyColumn {
            items(data.list.size) {
                weatherDetailRow(data, it)
            }
        }


    }
}

