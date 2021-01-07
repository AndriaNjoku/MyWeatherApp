package com.example.ForecastApp.DI.composer

import com.example.ForecastApp.onlyActivity.HomeActivity

import dagger.Component

@ActivityScope
@Component(modules = [ComposerModule::class])
interface ComposerComponent {

    fun inject(homeActivity: HomeActivity)
}
