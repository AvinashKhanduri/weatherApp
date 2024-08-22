package com.example.weatherapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.screens.about.AboutScreen
import com.example.weatherapp.screens.favorite.FavoriteScreen
import com.example.weatherapp.screens.favorite.FavoriteViewModel
import com.example.weatherapp.screens.main.MainViewModel
import com.example.weatherapp.screens.main.mainScreen
import com.example.weatherapp.screens.search.searchScreen
import com.example.weatherapp.screens.setting.SettingScreen
import com.example.weatherapp.screens.splash.weatherSplashScreen

@Composable
fun weatherNavigation(){
    val navController = rememberNavController()
    val viewModel:MainViewModel = viewModel()
    val favoriteViewModel:FavoriteViewModel = viewModel()
    NavHost(navController = navController,
            startDestination = WeatherScreens.SplashScreen.name){
            composable(WeatherScreens.SplashScreen.name){
                weatherSplashScreen(navController = navController)
            }

            composable(WeatherScreens.MainScreen.name+"/{city}",
                arguments = listOf(navArgument(name = "city"){ type = NavType.StringType })
            ){
                mainScreen(navController = navController,viewModel,favoriteViewModel,it.arguments?.getString("city").toString())
            }

            composable(WeatherScreens.SearchScreen.name){
                searchScreen(navController = navController,viewModel)
            }

            composable(WeatherScreens.SettingScreen.name){
                SettingScreen(navController = navController)

            }

            composable(WeatherScreens.AboutScreen.name){
                AboutScreen(navController = navController)
            }

            composable(WeatherScreens.FavoriteScreen.name){
                    FavoriteScreen(navController = navController,favoriteViewModel)
            }


    }
    
}