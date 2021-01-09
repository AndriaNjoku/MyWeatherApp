package com.example.ForecastApp.di.composer

import androidx.fragment.app.Fragment
import com.example.ForecastApp.weatherSearchScreen.WeatherSearchFragment
import com.example.ForecastApp.weatherResultsScreen.SearchResultsFragment
import com.example.ForecastApp.weatherDetailScreen.WeatherDetailFragment

import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScope
@Subcomponent(modules = [WeatherFeatureModule::class])

interface WeatherFeatureComponent: AndroidInjector<Fragment> {

    fun inject(fragment: WeatherSearchFragment)

    fun inject(fragment: SearchResultsFragment)

    fun inject(fragment : WeatherDetailFragment)
}

