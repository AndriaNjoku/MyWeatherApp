package com.example.ForecastApp.repository

import com.example.ForecastApp.model.weather.Forecast
import io.reactivex.Observable

interface WeatherForecast {

    fun weatherForecast(location: String): Observable<Forecast>

}