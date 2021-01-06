package com.example.ForecastApp.DI.composer

import androidx.fragment.app.Fragment
import com.example.ForecastApp.searchScreen.MainScreenFragment
import com.example.ForecastApp.weatherResultsScreen.SearchResultsFragment
import com.example.ForecastApp.weatherDetailScreen.WeatherDetailFragment
import com.example.minimoneybox.model.ApplicationModelContract

import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScope
@Subcomponent(modules = [WeatherFeatureModule::class])

interface WeatherFeatureComponent: AndroidInjector<Fragment> {

    val myModelInteractor: ApplicationModelContract

    fun inject(fragment: MainScreenFragment)

    fun inject(fragment: SearchResultsFragment)

    fun inject(fragment : WeatherDetailFragment)
}

