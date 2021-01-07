package com.example.ForecastApp.useCase

import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.model.weather.Forecast
import io.reactivex.Observable

interface GetWeatherForecast {

    fun getBasic(isOnline: Boolean, location:String): Observable<Forecast>

    fun getDetailed(location:String): Observable<Forecast>

    fun getRecentForecasts(): Observable<List<Forecast>>


}