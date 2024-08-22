package com.example.weatherapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun appBar(titleText:String = "title",
           icon:ImageVector? = Icons.Default.Search,
           isMainScreen:Boolean = true,
           isFavorite:Boolean = false,
           elevation: Dp = 0.dp,
           navController: NavController,
           onFavoriteClicked:()->Unit = {},
           onAddActionClicked:()->Unit = {},
           onButtonClicked:()->Unit = {}
){
    val showDialog = remember {
        mutableStateOf(false)
    }
    if (showDialog.value){
        ShowSettingDropDownMenu(showDialog = showDialog,navController)
    }



    CenterAlignedTopAppBar(
        title = { Text(text = titleText,
        color = MaterialTheme.colorScheme.surfaceTint,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        )},
        navigationIcon = {
            if (!isMainScreen){
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription ="Arrow Back",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable(onClick = { onButtonClicked.invoke() }))
            }
            else {
                if (isFavorite){
                    Icon(imageVector = Icons.Default.Favorite, contentDescription ="Favorite icon",
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { onFavoriteClicked() },
                        tint = Color.Red
                    )
                }
                else{
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription ="Favorite icon",
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { onFavoriteClicked() },
                        tint = Color.Red
                    )
                }


            }


        },
        actions = {
                  if(isMainScreen){
                      IconButton(onClick = {onAddActionClicked() }) {
                        Icon(imageVector = icon!!, contentDescription = "Search icon")
                      }

                      IconButton(onClick = { showDialog.value = true }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Icon ")
                      }
                  }



        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.shadow(elevation = elevation, shape = RoundedCornerShape(20.dp)),

    )

}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
                .absolutePadding(top = 45.dp, right = 20.dp)
    ){
        DropdownMenu(expanded = showDialog.value, onDismissRequest = { showDialog.value = false },
            modifier = Modifier
                .width(140.dp)

        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .clickable { navController.navigate(WeatherScreens.FavoriteScreen.name) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "favorite icon", modifier = Modifier.padding(5.dp)
                )
                Text(text = "Favorite", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            }

            //About

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .clickable { navController.navigate(WeatherScreens.AboutScreen.name) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Icon(imageVector = Icons.Default.Info, contentDescription = "About icon", modifier = Modifier.padding(5.dp),
                )
                Text(text = "About", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            }


            //Setting

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .clickable { navController.navigate(WeatherScreens.SettingScreen.name) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Icon(imageVector = Icons.Default.Settings, contentDescription = "setting icon", modifier = Modifier.padding(5.dp))
                Text(text = "Setting", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            }


        }
    }

}
