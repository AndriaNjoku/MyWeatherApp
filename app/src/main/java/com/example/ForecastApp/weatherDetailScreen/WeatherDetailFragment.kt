package com.example.ForecastApp.weatherDetailScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.ForecastApp.App
import com.example.ForecastApp.DI.composer.WeatherFeatureModule
import com.example.ForecastApp.R
import com.example.ForecastApp.adapter.WeatherDetailAdapter
import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.onlyActivity.ui.MainActivityPresenter
import com.example.ForecastApp.weatherDetailScreen.ui.WeatherDetailPresenter
import kotlinx.android.synthetic.main.forecast_detail_f.*
import javax.inject.Inject


class WeatherDetailFragment : Fragment(), WeatherDetailPresenter.View {

    @Inject
    lateinit var presenter: WeatherDetailPresenter

    private val forecastAdapter = WeatherDetailAdapter()

    lateinit var location: String

    var day: Int = 0

    var binder: Unbinder? = null

    companion object {

        fun newInstance(location: String): WeatherDetailFragment {
            val detailFragment = WeatherDetailFragment()
            val bundle = Bundle().apply {
                putString("Location", location)
            }
            detailFragment.arguments = bundle

            return detailFragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        injectDependencies()
        val view = inflater.inflate(R.layout.forecast_detail_f, container, false)
        location = arguments?.getString("Location").toString()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detail_results.layoutManager = LinearLayoutManager(activity)
        detail_results.adapter = forecastAdapter
        presenter.getDayDetails(location)
        val activityView = context as MainActivityPresenter.View
        search_button.setOnClickListener {
            activityView.showSearchResultsFragment(location)
        }

        detail_try_again.setOnClickListener {
            presenter.getDayDetails(location)
        }
    }

    override fun showForecast(days: List<Day>) {
        forecastAdapter.setData(days)
    }

    override fun injectDependencies() {
        App.instance.component.plus(WeatherFeatureModule(activity)).inject(this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.dispose()
    }

    override fun showError(throwable: Throwable) {
        throwable.printStackTrace()
        val myActivityView = context as MainActivityPresenter.View

        myActivityView.showError(throwable)
    }

    override fun showProgress(shouldShow: Boolean) {
        detail_progress.visibility = if (shouldShow) View.VISIBLE else View.GONE
    }

    override fun setActivityTitle(name: String?) {

    }

    override fun showTryAgain(shouldShow: Boolean) {
        if (shouldShow) {
            detail_results.visibility = View.GONE
            detail_try_again.visibility = View.VISIBLE
        } else {
            detail_try_again.visibility = View.GONE
            detail_results.visibility = View.VISIBLE
        }
    }
}





