package com.example.ForecastApp.DI.composer


import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.example.ForecastApp.ForecastService
import com.example.ForecastApp.Utils
import com.example.ForecastApp.database.ForecastDatabase

import com.example.ForecastApp.useCase.GetWeatherForecast
import com.example.ForecastApp.useCase.PojoGetWeatherForecast
import com.example.ForecastApp.weatherDetailScreen.ui.PojoWeatherDetailPresenter
import com.example.ForecastApp.weatherDetailScreen.ui.WeatherDetailPresenter
import com.example.ForecastApp.weatherResultsScreen.ui.PojoSearchResultsPresenter

import com.example.ForecastApp.weatherResultsScreen.ui.SearchResultsPresenter
import com.example.ForecastApp.weatherSearchScreen.ui.PojoWeatherSearchPresenter
import com.example.ForecastApp.weatherSearchScreen.ui.WeatherSearchPresenter

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers

@Module
class WeatherFeatureModule(val activity: FragmentActivity?) {


    @Provides
    @FragmentScope
    internal fun provideMainFragmentView(): WeatherSearchPresenter.View {
        val t = activity as FragmentActivity
        val result = t.supportFragmentManager.findFragmentByTag("main")
        return result as WeatherSearchPresenter.View
    }

    @Provides
    @FragmentScope
    internal fun provideSearchResultsFragmentView(): SearchResultsPresenter.View {
        val t = activity as FragmentActivity
        val result = t.supportFragmentManager.findFragmentByTag("results")
        return result as SearchResultsPresenter.View
    }

    @Provides
    @FragmentScope
    internal fun provideWeatherDetailsFragmentView(): WeatherDetailPresenter.View {
        val t = activity as FragmentActivity
        val result = t.supportFragmentManager.findFragmentByTag("detail")
        return result as WeatherDetailPresenter.View
    }

    @Provides
    @FragmentScope
    internal fun provideMainFragmentPresenter(
            view: WeatherSearchPresenter.View,
            getWeatherForecast: GetWeatherForecast): WeatherSearchPresenter =
            PojoWeatherSearchPresenter(
                    view,
                    activity as Activity,
                    getWeatherForecast

            )

    @Provides
    @FragmentScope
    internal fun provideSearchResultsPresenter(
            view: SearchResultsPresenter.View,
            context: Context,
            getWeatherForecast: GetWeatherForecast): SearchResultsPresenter = PojoSearchResultsPresenter(view, context, getWeatherForecast)


    @Provides
    @FragmentScope
    internal fun provideWeatherDetailsPresenter(
            view: WeatherDetailPresenter.View,
            context: Context,
            getWeatherForecast: GetWeatherForecast): WeatherDetailPresenter = PojoWeatherDetailPresenter(view, context, getWeatherForecast)

    @Provides
    @FragmentScope
    internal fun provideWeatherForecastGetter(service: ForecastService, database: ForecastDatabase) : GetWeatherForecast = PojoGetWeatherForecast(service, database)

}
