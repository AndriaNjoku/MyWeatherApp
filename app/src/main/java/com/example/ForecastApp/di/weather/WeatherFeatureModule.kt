package com.example.ForecastApp.di.composer


import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.example.ForecastApp.repository.NetWeatherForecast
import com.example.ForecastApp.repository.RoomWeatherForecast
import com.example.ForecastApp.useCase.*

import com.example.ForecastApp.weatherDetailScreen.ui.PojoWeatherDetailPresenter
import com.example.ForecastApp.weatherDetailScreen.ui.WeatherDetailPresenter
import com.example.ForecastApp.weatherResultsScreen.ui.PojoSearchResultsPresenter

import com.example.ForecastApp.weatherResultsScreen.ui.SearchResultsPresenter
import com.example.ForecastApp.weatherSearchScreen.ui.PojoWeatherSearchPresenter
import com.example.ForecastApp.weatherSearchScreen.ui.WeatherSearchPresenter

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers

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
                    getWeatherForecast,
                    AndroidSchedulers.mainThread()
            )

    @Provides
    @FragmentScope
    internal fun provideSearchResultsPresenter(
            view: SearchResultsPresenter.View,
            context: Context,
            networkChecker: NetworkChecker,
            getWeatherForecast: GetWeatherForecast): SearchResultsPresenter = PojoSearchResultsPresenter(view, context, networkChecker, getWeatherForecast, AndroidSchedulers.mainThread())


    @Provides
    @FragmentScope
    internal fun provideWeatherDetailsPresenter(
            view: WeatherDetailPresenter.View,
            context: Context,
            getWeatherForecast: GetWeatherForecast): WeatherDetailPresenter = PojoWeatherDetailPresenter(view, context, getWeatherForecast,AndroidSchedulers.mainThread())


    @Provides
    @FragmentScope
    internal fun provideWeatherForecastGetter(networkChecker: NetworkChecker, local: RoomWeatherForecast, net: NetWeatherForecast): GetWeatherForecast = PojoGetWeatherForecast(networkChecker,local, net)


    @Provides
    @FragmentScope
    internal fun provideConverter(): Converter = PojoConverter()




}
