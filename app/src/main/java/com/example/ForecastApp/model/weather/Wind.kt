package com.example.ForecastApp.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind {
    @SerializedName("speed")
    @Expose
    var speed: Double = 0.toDouble()

    @SerializedName("deg")
    @Expose
    var deg: Double = 0.toDouble()
}
