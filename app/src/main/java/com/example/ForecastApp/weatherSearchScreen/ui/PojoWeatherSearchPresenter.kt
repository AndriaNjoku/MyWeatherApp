package com.example.ForecastApp.weatherSearchScreen.ui

import android.app.Activity
import android.content.Context
import com.example.ForecastApp.onlyActivity.HomeActivity
import com.example.ForecastApp.weatherSearchScreen.OnLocationSelectedListener
import com.example.ForecastApp.useCase.GetWeatherForecast
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PojoWeatherSearchPresenter(
        val view: WeatherSearchPresenter.View,
        private val mainActivityContext: Activity,
        private val getWeather: GetWeatherForecast,
        private val foreground: Scheduler
) : WeatherSearchPresenter {

    private val disposable = CompositeDisposable()
    private var listener: OnLocationSelectedListener? = null

    override fun showSavedSearches(){
        disposable.add(getWeather.getRecentForecasts()
                .subscribeOn(Schedulers.io())
                .observeOn(foreground)
                .doOnError { view.showError(it) }
                .subscribe { view.showRecentSavedSearches(it) })
    }

    override fun setSelectedLocation(location: String) {
        checkIfListenerAttached()
        listener?.onLocationSelected(location)
    }

    override fun dispose() = disposable.dispose()

    private fun checkIfListenerAttached() {
        if (listener == null) {
            try {
                listener = mainActivityContext as OnLocationSelectedListener
            } catch (e: ClassCastException) {
                throw ClassCastException("${mainActivityContext}must implement OnLocationSelectedListener")
            }
        }
    }
}
