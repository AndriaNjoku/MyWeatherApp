package com.example.ForecastApp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.ForecastApp.database.ForecastDatabase
import com.example.ForecastApp.model.weather.Forecast
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class RoomWeatherForecast @Inject constructor(private val forecastDatabase: ForecastDatabase) {

    fun allForecasts(): Maybe<List<Forecast>> = forecastDatabase.forecastDao().forecastAll.cache()

    fun weatherForecast(location: String): Single<Forecast> = forecastDatabase.forecastDao().forecast(location)

    fun saveForecast(forecast: Forecast)= forecastDatabase.forecastDao().insertForecasts(forecast)

}