package com.example.ForecastApp.database

import androidx.room.TypeConverter

import com.example.ForecastApp.model.weather.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ConvertWeatherType {

    @TypeConverter
    @JvmStatic
    fun stringToListOfWeather(data: String?): List<Weather> {
        val gson = Gson()
        if (data == null) {
            return emptyList()
        }
        val type = object : TypeToken<List<Weather>>() {

        }.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    @JvmStatic
    fun listOfWeatherToString(weatherList: List<Weather>): String {
        return Gson().toJson(weatherList)
    }
}
