package com.example.ForecastApp.DI.composer

import com.example.ForecastApp.HomeActivity

import dagger.Component

@ActivityScope
@Component(modules = [ComposerModule::class])
interface ComposerComponent {

    fun inject(homeActivity: HomeActivity)
}
