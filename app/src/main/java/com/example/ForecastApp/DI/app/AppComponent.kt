package com.example.ForecastApp.DI.app


import com.example.ForecastApp.DI.composer.WeatherFeatureComponent
import com.example.ForecastApp.DI.composer.WeatherFeatureModule
import com.example.ForecastApp.DI.app.modules.AppModule
import com.example.ForecastApp.DI.app.modules.NetworkModule
import com.example.ForecastApp.database.ForecastDatabase
import com.example.ForecastApp.ForecastService
import com.example.ForecastApp.App

import javax.inject.Singleton

import dagger.Component
import dagger.android.AndroidInjector

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent: AndroidInjector<App>{

    // App component provides
    val userRepository: ForecastDatabase
    val apiService: ForecastService

    // inject at scope
    fun plus(mySubmodule: WeatherFeatureModule): WeatherFeatureComponent



}
