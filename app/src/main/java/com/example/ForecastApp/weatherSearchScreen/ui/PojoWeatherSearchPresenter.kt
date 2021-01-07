package com.example.ForecastApp.weatherSearchScreen.ui

import android.content.Context
import com.example.ForecastApp.onlyActivity.HomeActivity
import com.example.ForecastApp.weatherSearchScreen.OnLocationSelectedListener
import com.example.ForecastApp.useCase.GetWeatherForecast
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable


class PojoWeatherSearchPresenter(
        val view: WeatherSearchPresenter.View,
        private val mainActivityContext: Context,
        getWeather: GetWeatherForecast,
        foreground: Scheduler
) : WeatherSearchPresenter {

    lateinit var disposable: CompositeDisposable
    private var listener: OnLocationSelectedListener? = null

    init {
        disposable.add(getWeather.getRecentForecasts()
                .subscribeOn(foreground)
                .doOnError { view.showError(it) }
                .subscribe { view.showRecentSavedSearches(it) })
    }

    override fun getWeatherDetails() {
        TODO("Not yet implemented")
    }

    override fun setSelectedLocation(location: String) {
        checkIfListenerAttached()
        listener?.onLocationSelected(location)
    }

    override fun dispose() = disposable.dispose()

    private fun checkIfListenerAttached() {
        if (listener == null) {
            try {
                listener = mainActivityContext as HomeActivity
            } catch (e: ClassCastException) {
                throw ClassCastException("${mainActivityContext}must implement OnLocationSelectedListener")
            }
        }
    }
}
