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
import kotlinx.android.synthetic.main.search_results_frame.*
import javax.inject.Inject



class SearchResultsFragment : Fragment(), SearchResultsPresenter.View{


    lateinit var forecastAdapter: SearchResultsAdapter

    @Inject
    lateinit var presenter: SearchResultsPresenter

    var binder: Unbinder? = null

    lateinit var location : String

    companion object{

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectDependencies()
        val view =inflater.inflate(R.layout.search_results_frame,container,false)
        location = arguments?.getString("Location").toString()

        val a = context as HomeActivity
        val bar = a.supportActionBar
        bar?.title =location
        forecastAdapter= SearchResultsAdapter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_results.layoutManager = LinearLayoutManager(context)
        search_results.adapter = forecastAdapter
        //retrieve the location of the city thats passed in the bundle wehen we created this fragment

        presenter.showSearchResults(location)
        val b = context as MainActivityPresenter.View
        detail_button.setOnClickListener {
            Log.e("sclick", "button clicked ")
            b.showDetailsFragment(location)

            search_try_again.setOnClickListener {
                presenter.showSearchResults(location)
            }
        }
    }

    override fun injectDependencies() {
       App.instance.component.plus(WeatherFeatureModule(activity)).inject(this)
    }

    override fun showSearchResults(days: List<Day>) {
        forecastAdapter.setData(days)
    }

    override fun showError(throwable: Throwable?) {
        throwable?.printStackTrace()
        val myActivityView = context as MainActivityPresenter.View

        myActivityView.showError(throwable!!)
    }

    override fun showProgress(shouldShow: Boolean) {
        search_progress.visibility = if (shouldShow) View.VISIBLE else View.GONE
    }

    override fun setActivityTitle(name: String?){

    }

    override fun showTryAgain(shouldShow: Boolean) {
        if (shouldShow) {
            search_progress.visibility = View.GONE
            search_try_again.visibility = View.VISIBLE
        } else {
            search_try_again.visibility = View.GONE
            search_progress.visibility = View.VISIBLE
        }
    }



}





