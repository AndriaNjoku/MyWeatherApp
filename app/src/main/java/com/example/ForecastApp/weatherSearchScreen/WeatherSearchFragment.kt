package com.example.ForecastApp.weatherSearchScreen

import android.content.Context
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
import java.util.*
import javax.inject.Inject


class WeatherSearchFragment: Fragment(), WeatherSearchPresenter.View {

    companion object{
        const val THRESHOLD = 3
    }

    @Inject
    lateinit var presenter: WeatherSearchPresenter

    var recentSavedSearches: List<Forecast> = ArrayList()

    lateinit var savedSearchesAdapter: RecentSearchesAdapter

    @BindView(R.id.txt_search)
    lateinit var mSearchView: DelayAutoCompleteTextView

    @BindView(R.id.mainpage_loading_indicator)
    lateinit var mProgress: ProgressBar

    @BindView(R.id.mainpage_results)
    lateinit var mSavedResults: ListView

    @BindView(R.id.btn_clear)
    lateinit var mCancel: View


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        injectDependencies()
        val view = inflater.inflate(R.layout.weather_mainpage_frame, container, false)
        //unbinder = ButterKnife.bind(this, view)
        //initialise the adapter logic for the autoCOmplete text search and the recent searches list
        savedSearchesInit()
        autoCompleteSearchInit()

        // clear search text
        mCancel.setOnClickListener {
            mSearchView.setText("")
        }
        return view
    }

    override fun injectDependencies() {
        App.instance.component.plus(WeatherFeatureModule()).inject(this)
    }

    override fun savedSearchesInit() {
        context?.let {
            savedSearchesAdapter = RecentSearchesAdapter(it)
        }
        mSavedResults.adapter = savedSearchesAdapter
        mSavedResults.onItemClickListener = AdapterView.OnItemClickListener {
            parent, view, position, id ->
            val forecast = parent.getItemAtPosition(position) as Forecast
            val location = forecast.city?.name.toString()

            val activityView = context as MainActivityPresenter.View
            activityView.showSearchResultsFragment(location)
        }
    }

    override fun autoCompleteSearchInit() {
        mSearchView.threshold = THRESHOLD //min chars before search
        context?.let {
            mSearchView.setAdapter(SearchAutoCompleteAdapter(it))
        }
        mSearchView.setLoadingIndicator(mProgress)
        mSearchView.onItemClickListener = AdapterView.OnItemClickListener {
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
            true -> mProgress.visibility = View.VISIBLE
            false -> mProgress.visibility = View.INVISIBLE
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
