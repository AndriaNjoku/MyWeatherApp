package com.example.ForecastApp.di.app.modules

import android.app.Application
import android.content.Context

import com.example.ForecastApp.database.ForecastDatabase
import com.example.ForecastApp.useCase.NetworkChecker
import com.example.ForecastApp.useCase.PojoNetworkChecker

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {


    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideNetworkChecker(context: Context): NetworkChecker{
        //lazy instantiation  of database ensures we only create and access the one instance
        return PojoNetworkChecker(context)

    }

    @Provides
    @Singleton
    fun provideForecastDatabase(context: Context): ForecastDatabase {
        //lazy instantiation  of database ensures we only create and access the one instance
        return ForecastDatabase.getAppDataBase(context)!!

    }

}
