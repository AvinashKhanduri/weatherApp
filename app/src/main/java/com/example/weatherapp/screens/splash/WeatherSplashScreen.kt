package com.example.weatherapp.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun weatherSplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 1.0f, animationSpec = tween(
            durationMillis = 1000,
            easing = {
                OvershootInterpolator(3f).getInterpolation(it)
            }
        ))
        delay(2000L)
        navController.navigate(WeatherScreens.MainScreen.name + "/Delhi"){
            popUpTo(WeatherScreens.SplashScreen.name){inclusive = true}

        }


    })


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .size(300.dp)
                .scale(scale.value),
            shape = CircleShape, color = Color.Transparent,
            border = BorderStroke(5.dp, color = MaterialTheme.colorScheme.surfaceTint)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sun),
                    contentDescription = "sunny icon",
                    modifier = Modifier.size(95.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Hello weather",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
            }

        }
    }

}