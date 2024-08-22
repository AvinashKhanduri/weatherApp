package com.example.weatherapp.screens.favorite

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.widgets.appBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController, favoriteViewModel: FavoriteViewModel) {
    val dataList  =  favoriteViewModel.favList.collectAsState().value
    Scaffold(
        topBar = {
            appBar(navController = navController,
                titleText = "Favorite Cities",
                isMainScreen = false,
                elevation = 20.dp,
                onButtonClicked = { navController.popBackStack() }
            )
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {


        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(5.dp)) {
                items(dataList) { it ->
                    favCityRow(favorite = it, favoriteViewModel){
                        navController.navigate(WeatherScreens.MainScreen.name+"/${it.city}"){popUpTo(WeatherScreens.FavoriteScreen.name){inclusive = true} }
                    }

                }
            }
        }

    }
}


@Composable
fun favCityRow(
    favorite: Favorite,
    favoriteViewModel: FavoriteViewModel,
    onIconClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(
                shape = RoundedCornerShape(20.dp), color = Color(0xF35B9457)
            ).clickable { onIconClick() },

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = favorite.city,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(10.dp),
            fontSize = 20.sp
        )
        Surface(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp), shape = CircleShape, color = MaterialTheme.colorScheme.primary
        ) {

            Text(text = favorite.country, modifier = Modifier.padding(5.dp))
        }
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete icon",
            tint = Color.Red,
            modifier = Modifier.clickable { favoriteViewModel.deleteFavorite(favorite) })

    }

}


