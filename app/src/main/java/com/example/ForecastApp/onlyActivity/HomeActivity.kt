package com.example.ForecastApp.onlyActivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ForecastApp.DI.composer.ComposerComponent
import com.example.ForecastApp.DI.composer.ComposerModule
import com.example.ForecastApp.DI.composer.DaggerComposerComponent
import com.example.ForecastApp.R
import com.example.ForecastApp.onlyActivity.ui.MainActivityPresenter
import com.example.ForecastApp.weatherDetailScreen.WeatherDetailFragment
import com.example.ForecastApp.weatherResultsScreen.SearchResultsFragment
import com.example.ForecastApp.weatherSearchScreen.OnLocationSelectedListener
import com.example.ForecastApp.weatherSearchScreen.WeatherSearchFragment
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), OnLocationSelectedListener, MainActivityPresenter.View {


    lateinit var activityComponent: ComposerComponent

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onLocationSelected(location: String) {
        showSearchResultsFragment(location)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        setContentView(R.layout.activity_home)
        presenter.initiateNetworkFragment()
        showWeatherSearchFragment()

    }

    override fun onBackPressed() {
        showWeatherSearchFragment()
    }

    override fun injectDependencies() {
        activityComponent = DaggerComposerComponent.builder()
                .composerModule(ComposerModule(this))
                .build()
        activityComponent.inject(this)
    }


    override fun showError(throwable: Throwable) {
          Log.e(" Home Activity ", throwable.message.toString())
    }

    override fun showWeatherSearchFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame, WeatherSearchFragment(), "main")
                .commit()
    }

    override fun showSearchResultsFragment(location: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame, SearchResultsFragment.newInstance(location), "results")
                .commit()
    }

    override fun showDetailsFragment(location: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame, WeatherDetailFragment.newInstance(location), "detail")
                .commit()


    }

}


