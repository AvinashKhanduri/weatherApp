package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.navigation.weatherNavigation
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            weatherApp {
                weatherNavigation()
            }

        }
    }
}

@Composable
fun weatherApp(con: @Composable () -> Unit) {
    WeatherAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {
            con()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {

    }
}