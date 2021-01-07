package com.example.ForecastApp.weatherSearchScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import butterknife.BindView
import com.example.ForecastApp.DI.composer.WeatherFeatureModule
import com.example.ForecastApp.R
import com.example.ForecastApp.adapter.RecentSearchesAdapter
import com.example.ForecastApp.adapter.SearchAutoCompleteAdapter
import com.example.ForecastApp.App
import com.example.ForecastApp.model.weather.Forecast
import com.example.ForecastApp.model.predicitions.Prediction
import com.example.ForecastApp.onlyActivity.ui.MainActivityPresenter
import com.example.ForecastApp.weatherSearchScreen.ui.WeatherSearchPresenter
import com.example.ForecastApp.widget.DelayAutoCompleteTextView
import kotlinx.android.synthetic.main.weather_mainpage_frame.*
import java.util.*
import javax.inject.Inject


class WeatherSearchFragment @Inject constructor(): Fragment(), WeatherSearchPresenter.View {

    companion object{
        const val THRESHOLD = 3
    }

    @Inject
    lateinit var presenter: WeatherSearchPresenter

    var recentSavedSearches: List<Forecast> = ArrayList()

    lateinit var savedSearchesAdapter: RecentSearchesAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        injectDependencies()
        val view = inflater.inflate(R.layout.weather_mainpage_frame, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedSearchesInit()
        autoCompleteSearchInit()

        btn_clear.setOnClickListener {
            txt_search.setText("")
        }
    }

    override fun injectDependencies() {
        App.instance.component.plus(WeatherFeatureModule(activity)).inject(this)
    }

    override fun savedSearchesInit() {
        context?.let {
            savedSearchesAdapter = RecentSearchesAdapter(it)
        }
        mainpage_results.adapter = savedSearchesAdapter
        mainpage_results.onItemClickListener = AdapterView.OnItemClickListener {
            parent, view, position, id ->
            val forecast = parent.getItemAtPosition(position) as Forecast
            val location = forecast.city?.name.toString()

            val activityView = context as MainActivityPresenter.View
            activityView.showSearchResultsFragment(location)
        }
    }

    override fun autoCompleteSearchInit() {
        txt_search.threshold = THRESHOLD //min chars before search
        context?.let {
            txt_search.setAdapter(SearchAutoCompleteAdapter(it))
        }
        txt_search.setLoadingIndicator(mainpage_loading_indicator)
        txt_search.onItemClickListener = AdapterView.OnItemClickListener {
            parent, view, position, id ->

            val predicition = parent.getItemAtPosition(position) as Prediction
            presenter.setSelectedLocation(predicition.toString())
        }
    }

    override fun showRecentSavedSearches(cities: List<Forecast>) {
        savedSearchesAdapter.setRecentForecasts(cities)
    }

    override fun showError(error: Throwable){
        Log.e(" Weather Search", error.message.toString())
    }

    override fun showProgress(b: Boolean) = when (b) {
            true -> mainpage_loading_indicator.visibility = View.VISIBLE
            false -> mainpage_loading_indicator.visibility = View.INVISIBLE
        }

    override fun onDetach() {
        super.onDetach()
        presenter.dispose()
    }
}

interface OnLocationSelectedListener {
    fun onLocationSelected(location: String)
    fun showDetailsFragment(location: String)
}
