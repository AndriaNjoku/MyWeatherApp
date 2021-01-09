package com.example.ForecastApp.weatherDetailScreen.ui

import android.content.Context
import com.example.ForecastApp.useCase.GetWeatherForecast
import com.example.ForecastApp.useCase.NetworkChecker
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PojoWeatherDetailPresenter(
        val view: WeatherDetailPresenter.View,
        val context: Context,
        private val weatherForecast: GetWeatherForecast,
        private val foreground: Scheduler) : WeatherDetailPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getDayDetails(location: String) {
                compositeDisposable.add(
                        weatherForecast.getDetailed(location)
                                .subscribeOn(Schedulers.io())
                                .observeOn(foreground)
                                .doOnSubscribe { view.showProgress(true) }
                                .doOnComplete { view.showProgress(false) }
                                .doOnError { view.showError(it) }
                                .subscribe {
                                    view.showForecast(it.days ?: emptyList()) }

                )
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}
