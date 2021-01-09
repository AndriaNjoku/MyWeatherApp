package com.example.ForecastApp.repository

import android.content.Context
import com.example.ForecastApp.ForecastService
import com.example.ForecastApp.R
import com.example.ForecastApp.model.weather.Forecast
import io.reactivex.Observable
import javax.inject.Inject

class NetWeatherForecast @Inject constructor(val context: Context, private val serverInterface: ForecastService): WeatherForecast {

    override fun weatherForecast(location: String): Observable<Forecast> =
            serverInterface.getFiveDayForecast(location, context.getString(R.string.openweather_api_key))

}