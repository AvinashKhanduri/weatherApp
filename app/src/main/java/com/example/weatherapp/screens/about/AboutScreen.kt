package com.example.weatherapp.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.widgets.appBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            appBar(navController = navController,
                titleText = "About",
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
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Made by Avinash Khanduri",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}