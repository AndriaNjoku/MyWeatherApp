package com.example.ForecastApp.weatherResultsScreen

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.example.ForecastApp.onlyActivity.HomeActivity
import com.example.ForecastApp.DI.composer.WeatherFeatureModule
import com.example.ForecastApp.R
import com.example.ForecastApp.adapter.SearchResultsAdapter
import com.example.ForecastApp.App
import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.onlyActivity.ui.MainActivityPresenter
import com.example.ForecastApp.weatherResultsScreen.ui.SearchResultsPresenter
import javax.inject.Inject



class SearchResultsFragment : Fragment(), SearchResultsPresenter.View{


    lateinit var forecastAdapter: SearchResultsAdapter

    @Inject
    lateinit var presenter: SearchResultsPresenter.Presenter


    private lateinit var activityContext: Context

    var binder: Unbinder? = null

    lateinit var location : String

    @BindView(R.id.detail_button)
    lateinit var detailb: ImageView
    @BindView(R.id.search_results)
    lateinit var searchresults: RecyclerView
    @BindView(R.id.search_progress)
    lateinit var progressBar: ProgressBar
    @BindView(R.id.search_try_again)
   lateinit var tryAgain: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectDependencies()
        val view =inflater.inflate(R.layout.search_results_frame,container,false)
        binder = ButterKnife.bind(this,view)

        location = arguments?.getString("Location").toString()
        val a =activityContext as HomeActivity
        val bar = a.supportActionBar
        bar?.title =location
        forecastAdapter= SearchResultsAdapter()
            searchresults.layoutManager = LinearLayoutManager(activityContext)
            searchresults.adapter = forecastAdapter
        presenter.attach(activityContext,this)
        //retrieve the location of the city thats passed in the bundle wehen we created this fragment
        presenter.showSearchResults(location)
         val b = activityContext as MainActivityPresenter.View
        detailb.setOnClickListener {
            Log.e("sclick","button clicked ")
            b.showDetailsFragment(location)

        }

        return view

    }
    override fun injectDependencies() {
       App.instance.component.plus(WeatherFeatureModule()).inject(this)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activityContext=context

    }


    override fun showSearchResults(days: List<Day>) {
        forecastAdapter.setData(days)
    }

    override fun showError(throwable: Throwable?) {
        throwable?.printStackTrace()
        val myActivityView = activityContext as MainActivityPresenter.View

        myActivityView.showError(throwable!!)
    }

    override fun showProgress(shouldShow: Boolean) {
        progressBar.visibility = if (shouldShow) View.VISIBLE else View.GONE
    }


    //show the title of the city we are displaying weather results for
    override fun setActivityTitle(name: String?){

    }

    override fun showTryAgain(shouldShow: Boolean) {
        if (shouldShow) {
            searchresults.visibility = View.GONE
            tryAgain.visibility = View.VISIBLE
        } else {
            tryAgain.visibility = View.GONE
            searchresults.visibility = View.VISIBLE
        }
    }

    @OnClick(R.id.search_try_again)
    fun onTryAgainClicked() {
        presenter.showSearchResults(location)
    }

    //here we create a new searchFragment and we are passing the location via a bundle
    //this will allow us to initiate the search based on the location and display in the fragments recyclerview

    fun newInstance(location:String): SearchResultsFragment {

        Log.e("SearchResults","creating fragment")

        val searchResultsFragment = SearchResultsFragment()
        val bundle = Bundle().apply {
            putString("Location", location)
        }
        searchResultsFragment.arguments = bundle

        return searchResultsFragment
    }
}





