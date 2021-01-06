package com.example.ForecastApp.database

import androidx.room.TypeConverter

import com.example.ForecastApp.model.weather.Day
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object ConvertDay {

    @TypeConverter
    @JvmStatic
    fun ltString(days: List<Day>): String {
        return Gson().toJson(days)
    }


    @TypeConverter
    @JvmStatic
    fun stringtl(data: String?): List<Day> {
        val gson = Gson()
        if (data == null) {
            return emptyList()
        }
        val type = object : TypeToken<List<Day>>() {

        }.type
        return gson.fromJson(data, type)
    }


}
