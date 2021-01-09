package com.example.ForecastApp.di.app


import com.example.ForecastApp.di.composer.WeatherFeatureComponent
import com.example.ForecastApp.di.composer.WeatherFeatureModule
import com.example.ForecastApp.di.app.modules.AppModule
import com.example.ForecastApp.di.app.modules.NetworkModule
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
