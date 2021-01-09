package com.example.ForecastApp.useCase

import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.model.weather.Forecast
import io.reactivex.Observable
import io.reactivex.Single

interface GetWeatherForecast {


    fun getDetailed(location:String): Observable<Forecast>

    fun getRecentForecasts(): Observable<List<Forecast>>

    fun getBasic(location: String): Observable<Forecast>
}