package com.example.weatherapp.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.screens.main.MainViewModel
import com.example.weatherapp.widgets.appBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            appBar(
                navController = navController,
                titleText = "Search",
                isMainScreen = false,
                elevation = 30.dp,
                onButtonClicked = { navController.navigate(WeatherScreens.MainScreen.name+"/Delhi"){popUpTo(WeatherScreens.SearchScreen.name){inclusive = true} } })
        },

        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {

        Surface(
            modifier = Modifier.padding(it), color = MaterialTheme.colorScheme.primaryContainer

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                SearchBar(modifier = Modifier.padding(16.dp)) {
                    Log.d("S", " Searchd $it")
                    navController.navigate(WeatherScreens.MainScreen.name + "/$it"){popUpTo(WeatherScreens.SearchScreen.name){inclusive = true} }
                }
            }


        }
    }

}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }
    val keybordController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column(modifier = modifier) {
        commonTextField(
            valueState = searchQueryState,
            placeholder = "Seattle",
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keybordController?.hide()
            }

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun commonTextField(
    valueState: MutableState<String>,
    placeholder: String,
    onAction: KeyboardActions = KeyboardActions.Default,
    keybordTyle: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = valueState.value, onValueChange = { valueState.value = it },
        label = {
            Text(
                text = placeholder,
                color = Color(0xFF11C9E6),
                style = MaterialTheme.typography.labelLarge
            )
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keybordTyle, imeAction = imeAction),
        keyboardActions = onAction,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "search icon"
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF11C9E6),
            cursorColor = Color.Black,
            unfocusedBorderColor = Color(0xFF11C9E6)

        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )
}
