package com.example.ForecastApp.model.weather

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

import com.example.ForecastApp.database.ConvertDay
import com.example.ForecastApp.model.City
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//forecast is the main element and contains a list of days object which has all the wether detaisl for each day (5 days)
//of a particular city in the city field

@Entity
class Forecast {
    //@PrimaryKey(autoGenerate = true)
    //var id: Int = 0
    @SerializedName("cod")
    @Expose
    var cod: String? = null

    @SerializedName("message")
    @Expose
    var message: Double = 0.toDouble()

    @SerializedName("cnt")
    @Expose
    var cnt: Int = 0

    @TypeConverters(ConvertDay::class)
    @SerializedName("list")
    @Expose
    var days: List<Day>? = null

    @PrimaryKey
    @Embedded
    @SerializedName("city")
    @Expose
    lateinit var city: City
}
