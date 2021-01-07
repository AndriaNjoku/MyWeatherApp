package com.example.ForecastApp.weatherDetailScreen.ui

import android.content.Context
import com.example.ForecastApp.Utils
import com.example.ForecastApp.useCase.GetWeatherForecast
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.Util

class PojoWeatherDetailPresenter(
        val view: WeatherDetailPresenter.View,
        val context: Context,
        private val weattherForecast: GetWeatherForecast) : WeatherDetailPresenter {

    private val compositeDisposable = CompositeDisposable()


    override fun getDayDetails(location: String) {
        // removed network check as cant mock final Utils object
        // TODO(" Break utils into use cases which are mockable')
       // if (Utils.isOnline(context)) {
            compositeDisposable.add(
                    weattherForecast.getDetailed(location)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe { view.showProgress(true) }
                            .doOnComplete { view.showProgress(false) }
                            .doOnError { view.showError(it) }
                            .subscribe { view.showForecast(it.days ?: emptyList()) }
            )

        }
     //   else {
       //     view.showTryAgain(true)
     //   }

    override fun dispose(){
        compositeDisposable.dispose()
    }
}
