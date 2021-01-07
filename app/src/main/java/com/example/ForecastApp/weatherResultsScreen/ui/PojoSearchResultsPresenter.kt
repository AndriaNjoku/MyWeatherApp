package com.example.ForecastApp.weatherResultsScreen.ui

import android.content.Context
import com.example.ForecastApp.Utils
import com.example.ForecastApp.useCase.GetWeatherForecast
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable


class PojoSearchResultsPresenter(val view: SearchResultsPresenter.View, val context: Context, private val getWeatherForecast: GetWeatherForecast, private val foreground: Scheduler) : SearchResultsPresenter {

    lateinit var compositeDisposable: CompositeDisposable

    override fun showSearchResults(location: String) {
        compositeDisposable.add(
                getWeatherForecast.getBasic(Utils.isOnline(context), location)
                        .subscribeOn(foreground)
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
