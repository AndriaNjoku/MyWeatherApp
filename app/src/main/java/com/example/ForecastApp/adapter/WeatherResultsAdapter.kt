package com.example.ForecastApp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ForecastApp.R
import com.example.ForecastApp.model.WEATHERVERSION
import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.useCase.Converter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.forecast_detail_item.view.*
import kotlinx.android.synthetic.main.forecast_search_item.view.*
import java.util.*

class WeatherResultsAdapter(val converter: Converter, val version: WEATHERVERSION) : RecyclerView.Adapter<WeatherResultsAdapter.ForecastViewHolder>() {

    private val daysinfo: MutableList<Day>

    init {
        daysinfo = ArrayList()
    }

    fun setData(days: List<Day>) {
        Objects.requireNonNull(days)
        daysinfo.clear()
        daysinfo.addAll(days)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val rootView = when (version) {
            WEATHERVERSION.BASIC -> LayoutInflater.from(parent.context).inflate(R.layout.forecast_search_item, parent, false)
            WEATHERVERSION.DETAILED -> LayoutInflater.from(parent.context).inflate(R.layout.forecast_detail_item, parent, false)
        }

        return ForecastViewHolder(rootView, converter, version)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(daysinfo[position])
    }

    override fun getItemCount(): Int {
        return daysinfo.size
    }

    class ForecastViewHolder(itemView: View, val converter: Converter, val version: WEATHERVERSION) : RecyclerView.ViewHolder(itemView) {

        companion object {
            const val ICON_BASE_URL = "https://openweathermap.org/img/w/"
            const val ICON_EXTENSION = ".png"
        }

        fun bind(day: Day) {
            with(itemView) {
                when (version) {
                    WEATHERVERSION.BASIC -> {
                        Picasso.get().load(ICON_BASE_URL + (day.weather?.get(0)?.icon) + ICON_EXTENSION).into(basic_weather_icon)
                        s_day.text = converter.getDateForLocaleFromUtc(day.dateAndTime)
                        r_condition.text = day.weather?.get(0)?.description?.toUpperCase(Locale.getDefault())
                        val tempMax = converter.getCelsiusFromKelvinC(day.main?.tempMax)
                        s_maxmintemp.text = "$tempMax" //TODO("Add formatter, use here")

                    }
                    WEATHERVERSION.DETAILED -> {
                        Picasso.get().load(ICON_BASE_URL + (day.weather?.get(0)?.icon) + ICON_EXTENSION).into(detail_weather_icon)
                        d_day.text = converter.getDateForLocaleFromUtc(day.dateAndTime)
                        d_condition.text = day.weather?.get(0)?.description?.toUpperCase(Locale.getDefault())
                        d_clouds.text = resources.getString(R.string.cloud_percentage, day.clouds?.all)
                        d_temp.text = converter.getCelsiusFromKelvinC(day.main?.temp)
                        d_wind.text = resources.getString(R.string.wind_speed, converter.roundDoubleToTwoDecimalPoints(day.wind?.speed))
                    }
                }

            }
        }


    }
}
