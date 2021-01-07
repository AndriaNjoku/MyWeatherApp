package com.example.ForecastApp.DI.composer


import android.app.Activity
import com.example.ForecastApp.mvp.MainScreenFragment.*
import com.example.ForecastApp.onlyActivity.HomeActivity
import com.example.ForecastApp.useCase.GetWeatherForecast
import com.example.ForecastApp.weatherDetailScreen.WeatherDetailFragment
import com.example.ForecastApp.weatherResultsScreen.SearchResultsFragment
import com.example.ForecastApp.weatherResultsScreen.ui.PojoSearchResultsPresenter

import com.example.ForecastApp.weatherResultsScreen.ui.SearchResultsPresenter
import com.example.ForecastApp.weatherSearchScreen.WeatherSearchFragment
import com.example.ForecastApp.weatherSearchScreen.ui.PojoWeatherSearchPresenter
import com.example.ForecastApp.weatherSearchScreen.ui.WeatherSearchPresenter

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class WeatherFeatureModule(){

    companion object {
        const val FOREGROUND = "foreground"
    }

    @Provides
    @ActivityScope
    internal fun provideOnlyActivity(mainActivity: HomeActivity): Activity = mainActivity

    @Provides
    @FragmentScope
    internal fun provideMainFragmentView(mainFragment: WeatherSearchFragment): WeatherSearchPresenter.View = mainFragment

    @Provides
    @FragmentScope
    internal fun provideSearchResultsFragmentView(searchResultsFragment: SearchResultsFragment): SearchResultsPresenter.View = searchResultsFragment

    @Provides
    @FragmentScope
    internal fun provideWeatherDetailsFragmentView(weatherDetailFragment: WeatherDetailFragment): WeatherDetailPresenter.View = weatherDetailFragment

    @Provides
    @FragmentScope
    internal fun provideMainFragmentPresenter(
            view: WeatherSearchPresenter.View,
            context: Activity,
            getWeatherForecast: GetWeatherForecast,
            @Named(FOREGROUND) foreground: Scheduler): WeatherSearchPresenter =
            PojoWeatherSearchPresenter(
                    view,
                    context,
                    getWeatherForecast,
                    foreground)

    @Provides
    @FragmentScope
    internal fun provideSearchResultsPresenter(



    ): SearchResultsPresenter = PojoSearchResultsPresenter(myModelInteractor)
    }

    @Provides
    @FragmentScope
    internal fun provideWeatherDetailsPresenter(myModelInteractor: ApplicationModelContract): WeatherDetailPresenter.Presenter {

        return DetailFragmentPresenter(myModelInteractor)
    }


}


