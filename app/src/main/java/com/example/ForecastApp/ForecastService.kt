package com.example.ForecastApp


import com.example.ForecastApp.model.weather.Forecast


import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("forecast")
    fun getFiveDayForecast(@Query("q") location: String, @Query("APPID") appId: String): Observable<Forecast>

}
