package com.example.ForecastApp.DI.composer

import android.content.Context
import com.example.ForecastApp.DI.composer.ActivityScope
import com.example.ForecastApp.mvp.MainScreenFragment.MainActivityContract
import com.example.ForecastApp.mvp.MainScreenFragment.MainActivityPresenter


import dagger.Module
import dagger.Provides

@Module
class ComposerModule(private val activityContext: Context) {


    @Provides
    @ActivityScope
    internal fun provideContext():Context{
        return activityContext
    }

    @Provides
    @ActivityScope
    internal fun provideActivityPresenter(myActivityContext: Context): MainActivityContract.Presenter {

        return MainActivityPresenter(myActivityContext)
    }
}
