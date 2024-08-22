package com.example.weatherapp.util

import java.text.SimpleDateFormat
import java.util.Date

fun formateDate(timeStamp:Int):String{
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = Date(timeStamp.toLong()*1000)
    return  sdf.format(date)
}

fun formateDateTime(timeStamp:Int):String{
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = Date(timeStamp.toLong()*1000)
    return sdf.format(date)
}

fun formateDesimals(item:Double):String{
    return "%.0f".format(item)
}


