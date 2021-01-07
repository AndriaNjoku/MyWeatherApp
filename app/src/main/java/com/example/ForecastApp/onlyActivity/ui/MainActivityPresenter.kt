package com.example.ForecastApp.onlyActivity.ui

import android.content.Context
import com.example.ForecastApp.BasePresenter


interface MainActivityPresenter : BasePresenter {

    interface View : BasePresenter.View{
        fun showError(throwable: Throwable)
        fun showWeatherSearchFragment()
        fun showSearchResultsFragment(location: String)
        fun showDetailsFragment(location: String)
    }

        fun initiateNetworkFragment()
    }

