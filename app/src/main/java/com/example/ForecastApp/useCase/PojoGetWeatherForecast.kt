package com.example.ForecastApp.useCase

import com.example.ForecastApp.Constants
import com.example.ForecastApp.ForecastService
import com.example.ForecastApp.database.ForecastDatabase
import com.example.ForecastApp.model.weather.Forecast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PojoGetWeatherForecast @Inject constructor(val service: ForecastService, val database: ForecastDatabase) : GetWeatherForecast {

    private fun forecastFromLocal(location: String): Observable<Forecast> =
            database.forecastDao()
                    .forecast(location)
                    .observeOn(AndroidSchedulers.mainThread())
                    .toObservable()

    private fun forecastFromNetwork(location: String): Observable<Forecast> {
        return service.getFiveDayForecast(location, Constants.API_KEY)
                .doOnNext {
                    database.forecastDao()
                            .insertForecasts(it)
                }
    }

    override fun getBasic(isOnline: Boolean, location: String): Observable<Forecast> = when (isOnline) {
        true -> forecastFromNetwork(location)
        false -> forecastFromLocal(location)
    }

    override fun getDetailed(location: String): Observable<Forecast> = forecastFromLocal(location)

    override fun getRecentForecasts(): Observable<List<Forecast>>  =
            database
            .forecastDao()
            .forecastAll
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()

}