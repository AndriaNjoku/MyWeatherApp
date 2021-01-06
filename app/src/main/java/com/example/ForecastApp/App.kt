package com.example.ForecastApp

import android.app.Application

import com.example.ForecastApp.DI.app.AppComponent
import com.example.ForecastApp.DI.app.modules.AppModule
import com.example.ForecastApp.DI.app.DaggerAppComponent
import com.example.ForecastApp.DI.app.modules.NetworkModule

import net.danlew.android.joda.JodaTimeAndroid

class App : Application() {

    companion object {
        lateinit var instance: App private set
    }

    lateinit var component: AppComponent

        private set

    override fun onCreate() {
        super.onCreate()
        instance =this
        setup()

    }

    private fun setup() {

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()

                component.inject(this)


        JodaTimeAndroid.init(this)

    }



}


