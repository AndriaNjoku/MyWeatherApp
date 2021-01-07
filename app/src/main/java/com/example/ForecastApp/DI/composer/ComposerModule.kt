package com.example.ForecastApp.DI.composer

import android.app.Activity
import android.content.Context
import com.example.ForecastApp.onlyActivity.HomeActivity
import com.example.ForecastApp.onlyActivity.ui.MainActivityPresenter
import com.example.ForecastApp.onlyActivity.ui.PojoMainActivityPresenter


import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class ComposerModule(private val activityContext: Activity) {


    @Provides
    @ActivityScope
    internal fun provideContext():Context{
        return activityContext
    }

    @Provides
    internal fun provideActivity(): Activity {
        return activityContext as HomeActivity
    }

    @Provides
    @ActivityScope
    internal fun provideActivityPresenter(myActivityContext: Context): MainActivityPresenter = PojoMainActivityPresenter(myActivityContext as MainActivityPresenter.View, myActivityContext)

}
