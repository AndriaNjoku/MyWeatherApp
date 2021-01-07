package com.example.ForecastApp.database

import androidx.room.TypeConverter

import com.example.ForecastApp.model.weather.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ConvertWeatherType {

    @TypeConverter
    @JvmStatic
    fun stringToListOfWeather(data: String?): List<Weather>  = when(data){
        null -> emptyList()
        else -> {
            val type = object : TypeToken<List<Weather>>(){ }.type
            Gson().fromJson(data, type)
        }
    }

    @TypeConverter
    @JvmStatic
    fun listOfWeatherToJson(weatherList: List<Weather>): String = Gson().toJson(weatherList)

}
