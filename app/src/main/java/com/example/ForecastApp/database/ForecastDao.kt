package com.example.ForecastApp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.ForecastApp.model.weather.Forecast
import io.reactivex.Completable

import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface ForecastDao {

    @get:Query("SELECT * FROM forecast")
    val forecastAll: Maybe<List<Forecast>>

    @Query("SELECT * FROM forecast WHERE name LIKE :cityname ")
    fun forecast(cityname: String): Single<Forecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecasts(vararg forecasts: Forecast)
}
