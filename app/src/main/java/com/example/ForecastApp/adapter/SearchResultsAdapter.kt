package com.example.ForecastApp.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.example.ForecastApp.Constants
import com.example.ForecastApp.R
import com.example.ForecastApp.Utils
import com.example.ForecastApp.model.weather.Day
import com.squareup.picasso.Picasso
import java.util.ArrayList
import java.util.Objects
import butterknife.BindView
import butterknife.ButterKnife


class SearchResultsAdapter() : RecyclerView.Adapter<SearchResultsAdapter.ForecastViewHolder>() {


    private val daysinfo: MutableList<Day>


    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

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
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.forecast_search_item,
                parent, false)
        val viewholder = ForecastViewHolder(rootView)

        return viewholder
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(daysinfo[position])
    }

    override fun getItemCount(): Int {
        return daysinfo.size
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @Nullable
        @BindView(R.id.s_day)
        lateinit var dayAndTime: TextView
        @Nullable
        @BindView(R.id.r_condition)
        lateinit var condition: TextView
        @BindView(R.id.s_maxmintemp)
        lateinit var temparature: TextView

        @BindView(R.id.iv_weather_icon)
        lateinit var weatherIcon: ImageView

        private val resources: Resources

        init {
            ButterKnife.bind(this, itemView)
            resources = itemView.context.resources
        }

        fun bind(day: Day) {
            Picasso.get().load(Constants.ICON_BASE_URL + (day.weather?.get(0)?.icon) + Constants.ICON_EXTENSION)
                    .into(weatherIcon)
            dayAndTime.text = Utils.getDateForLocaleFromUtc(day.dateAndTime)
            condition.text = day.weather?.get(0)?.description?.toUpperCase()
            val tempMin = Utils.getCelsiusFromKelvin(day.main?.tempMin)
            val tempMax = Utils.getCelsiusFromKelvin(day.main?.tempMax)
            temparature.text = "$tempMin - $tempMax"

        }


    }
}
