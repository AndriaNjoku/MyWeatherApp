package com.example.ForecastApp.useCase

import android.util.Log
import com.example.ForecastApp.di.composer.ActivityScope
import com.example.ForecastApp.model.weather.Forecast
import com.example.ForecastApp.repository.NetWeatherForecast
import com.example.ForecastApp.repository.RoomWeatherForecast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject



@ActivityScope
class PojoGetWeatherForecast @Inject constructor(
        val networkChecker: NetworkChecker,
        private val localWeatherForecast: RoomWeatherForecast,
        private val netWeatherForecast: NetWeatherForecast) : GetWeatherForecast {

    private fun forecastFromLocal(location: String): Observable<Forecast> =
            localWeatherForecast.weatherForecast(location)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .toObservable()

    private fun forecastFromNetwork(location: String): Observable<Forecast> =
            netWeatherForecast.weatherForecast(location).cache()
                    .doOnNext {
                        localWeatherForecast.saveForecast(it)
                    }

    override fun getBasic(location: String): Observable<Forecast> = when (networkChecker.isOnline()) {
        true -> forecastFromNetwork(location)
        false -> forecastFromLocal(location)
    }

    override fun getDetailed(location: String): Observable<Forecast> = when (networkChecker.isOnline()) {
        true -> forecastFromNetwork(location)
        false -> forecastFromLocal(location)
    }

    override fun getRecentForecasts(): Observable<List<Forecast>> =
            localWeatherForecast
                    .allForecasts()
                    .observeOn(AndroidSchedulers.mainThread())
                    .toObservable()

}