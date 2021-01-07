package com.example.ForecastApp.weatherDetailScreen.ui

import android.content.Context
import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.BasePresenter


interface DetailFragmentContract {

    interface View : BasePresenter.View {

        fun showForecast(days: List<Day>)
        fun showError(throwable: Throwable)
        fun showProgress(shouldShow: Boolean)
        fun showTryAgain(shouldShow: Boolean)
        fun setActivityTitle(name: String?) {

        }


    }

    interface Presenter : BasePresenter.Presenter<View> {


        fun attach(context: Context, fragView: View)
        fun getDayDetails(location: String)
    }
}