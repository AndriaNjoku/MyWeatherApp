package com.example.ForecastApp.weatherDetailScreen.ui

import android.content.Context
import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.mvp.BaseContract


interface DetailFragmentContract {

    interface View : BaseContract.View {

        fun showForecast(days: List<Day>)
        fun showError(throwable: Throwable)
        fun showProgress(shouldShow: Boolean)
        fun showTryAgain(shouldShow: Boolean)
        fun setActivityTitle(name: String?) {

        }


    }

    interface Presenter : BaseContract.Presenter<View> {


        fun attach(context: Context, fragView: View)
        fun getDayDetails(location: String)
    }
}