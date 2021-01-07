package com.example.ForecastApp

import android.content.Context


interface BasePresenter{

    interface View {
       fun injectDependencies()

    }

}