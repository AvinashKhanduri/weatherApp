package com.example.weatherapp.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.icons.rememberHumidityMid
import com.example.weatherapp.model.Weather
import com.example.weatherapp.util.formateDate
import com.example.weatherapp.util.formateDateTime
import com.example.weatherapp.util.formateDesimals

@Composable
fun weatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl), contentDescription = "icon image",
        modifier = Modifier.size(80.dp)
    )
}

@Composable
fun sunSetsunRise(data: Weather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise icon",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = formateDateTime(data.list[0].sunrise),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 5.dp)
            )


        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset icon",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = formateDateTime(data.list[0].sunset),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 5.dp)
            )


        }
    }
}

@Composable
fun humidityWindRow(data: Weather) {
    Row(
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = rememberHumidityMid(), contentDescription = "humidity",
                modifier = Modifier
                    .padding(10.dp)
                    .size(20.dp)
            )
            Text(text = "${data.list[0].humidity}%", style = MaterialTheme.typography.titleSmall)

        }

        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier
                    .padding(10.dp)
                    .size(20.dp)
            )
            Text(text = "${data.list[0].pressure} psi", style = MaterialTheme.typography.titleSmall)

        }

        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wind), contentDescription = "wind icon",
                modifier = Modifier
                    .padding(10.dp)
                    .size(20.dp)
            )
            Text(text = "${data.list[0].speed} mph", style = MaterialTheme.typography.titleSmall)

        }
    }
}


@Composable
fun weatherDetailRow(data: Weather, dayCount: Int) {
    var imageUrl = "https://openweathermap.org/img/wn/${data.list[dayCount].weather[0].icon}.png"
    var dayCount = dayCount
    var day: String = ""
    for (i in 0..2) {
        day += formateDate(data.list[dayCount].dt)[i]
    }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable { Log.d("dc", "$dayCount") }
            .background(
                color = Color(0xFF089C6B),
                shape = RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = day,
            modifier = Modifier.padding(5.dp),
            fontSize = 20.sp,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.SemiBold
        )

        weatherStateImage(imageUrl)

        Surface(
            modifier = Modifier.wrapContentSize(),
            color = Color(0xFFFF9100),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = data.list[dayCount].weather[0].main,
                modifier = Modifier.padding(5.dp),
                fontSize = 20.sp,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${formateDesimals(data.list[dayCount].feels_like.day)}°",
                modifier = Modifier.padding(5.dp),
                fontSize = 23.sp,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Blue
            )

            Text(
                text = "${formateDesimals(data.list[dayCount].temp.day)}°",
                modifier = Modifier.padding(2.dp),
                fontSize = 23.sp,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.ExtraLight,

                )

        }
    }
}


