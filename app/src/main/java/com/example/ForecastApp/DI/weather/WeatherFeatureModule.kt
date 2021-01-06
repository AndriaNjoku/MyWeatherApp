package com.example.ForecastApp.DI.composer


import com.example.ForecastApp.database.ForecastDatabase
import com.example.ForecastApp.ForecastService
import com.example.ForecastApp.mvp.MainScreenFragment.*
import com.example.ForecastApp.searchScreen.ui.MainScreenFragmentContract
import com.example.ForecastApp.searchScreen.ui.PojoWeatherSearchFragment
import com.example.ForecastApp.weatherResultsScreen.ui.SearchResultsFragmentContract
import com.example.ForecastApp.weatherResultsScreen.ui.SearchResultsFragmentPresenter
import com.example.minimoneybox.model.ApplicationModel
import com.example.minimoneybox.model.ApplicationModelContract

import dagger.Module
import dagger.Provides

@Module
class WeatherFeatureModule(){


    @Provides
    fun provideModelInteractor(service: ForecastService, data:ForecastDatabase): ApplicationModelContract{
        return ApplicationModel(service,data)
    }


    @Provides
    @FragmentScope
    internal fun provideMainFragmentPresenter(mymodelinteractor: ApplicationModelContract): MainScreenFragmentContract.Presenter {

        return PojoWeatherSearchFragment(mymodelinteractor)
    }

    @Provides
    @FragmentScope
    internal fun provideSearchResultsPresenter(myModelInteractor: ApplicationModelContract): SearchResultsFragmentContract.Presenter {

        return SearchResultsFragmentPresenter(myModelInteractor)
    }

    @Provides
    @FragmentScope
    internal fun provideWeatherDetailsPresenter(myModelInteractor: ApplicationModelContract): DetailFragmentContract.Presenter {

        return DetailFragmentPresenter(myModelInteractor)
    }


}


