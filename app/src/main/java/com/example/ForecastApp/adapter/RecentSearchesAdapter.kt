package com.example.ForecastApp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ForecastApp.R
import java.util.ArrayList
import java.util.Objects
import com.example.ForecastApp.model.weather.Forecast

class RecentSearchesAdapter(private val mContext: Context) : BaseAdapter(){

    private val forecastInfo: MutableList<Forecast>

    init {
        forecastInfo = ArrayList()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1
        if (convertView == null) {
            val inflater = mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.forecast_recent_item, p2, false)
        }
        val get= getItem(p0)
        (convertView!!.findViewById<View>(R.id.r_location) as TextView).text = get.city.name + " " +get.city.country

        return convertView
    }

    override fun getItem(posit: Int): Forecast {
        return forecastInfo[posit]

    }

    override fun getItemId(posit: Int): Long {
        return posit.toLong()

    }

    override fun getCount(): Int {
        return forecastInfo.size

    }

    fun setRecentForecasts(forecasts: List<Forecast>) {
        Objects.requireNonNull(forecasts)
        forecastInfo.clear()
        forecastInfo.addAll(forecasts)
        notifyDataSetChanged()
    }


}

