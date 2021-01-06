package com.example.ForecastApp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.ForecastApp.model.weather.Forecast

@Database(entities = [Forecast::class], version = 1)
abstract class ForecastDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    companion object {
        var INSTANCE: ForecastDatabase? = null

        fun getAppDataBase(context: Context): ForecastDatabase? {
            if (INSTANCE == null){
                synchronized(ForecastDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, ForecastDatabase::class.java, "forecast").build()
                }
            }
            return INSTANCE
        }

        fun isEmpty(): Boolean{

           val disposable= INSTANCE?.forecastDao()?.forecastAll?.isEmpty

            var boolean= false

            disposable?.subscribe{ result -> boolean=result}


            return false

        }


        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}
