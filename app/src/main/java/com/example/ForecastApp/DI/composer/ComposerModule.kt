package com.example.ForecastApp.DI.composer

import android.content.Context
import com.example.ForecastApp.onlyActivity.ui.MainActivityPresenter
import com.example.ForecastApp.onlyActivity.ui.PojoMainActivityPresenter


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
    internal fun provideActivityPresenter(myActivityContext: Context): MainActivityPresenter.Presenter {

        return PojoMainActivityPresenter(myActivityContext)
    }
}
