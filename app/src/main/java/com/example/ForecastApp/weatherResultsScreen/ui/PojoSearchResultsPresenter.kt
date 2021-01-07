package com.example.ForecastApp.weatherResultsScreen.ui

import android.content.Context
import com.example.ForecastApp.Utils
import com.example.ForecastApp.useCase.GetWeatherForecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PojoSearchResultsPresenter
(val view: SearchResultsPresenter.View,
 val context: Context,
 private val getWeatherForecast: GetWeatherForecast) : SearchResultsPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun showSearchResults(location: String) {

//        val connected = Utils.isOnline(context)

        // Have removed the network check since Util is final
        //TODO(" break up Utils into use cases which are mockable)

        compositeDisposable.add(
                getWeatherForecast.getBasic(true, location)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
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
