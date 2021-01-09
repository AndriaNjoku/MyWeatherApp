package com.example.ForecastApp.weatherResultsScreen.ui

import android.content.Context
import com.example.ForecastApp.useCase.GetWeatherForecast
import com.example.ForecastApp.useCase.NetworkChecker
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PojoSearchResultsPresenter
(val view: SearchResultsPresenter.View,
 val context: Context,
 private val networkChecker: NetworkChecker,
 private val getWeatherForecast: GetWeatherForecast,
 val foreground: Scheduler) : SearchResultsPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun showSearchResults(location: String) {

                compositeDisposable.add(
                        getWeatherForecast.getBasic(location)
                                .subscribeOn(Schedulers.io())
                                .observeOn(foreground)
                                .doOnError { view.showError(it) }
                                .subscribe {
                                    it.days?.let { days ->
                                        view.showSearchResults(days)
                                    }
                                }
                )
        }

    override fun dispose() {
        compositeDisposable.dispose()
    }


}
