package com.example.ForecastApp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.example.ForecastApp.Constants.AUTOCOMPLETE_API_URL
import com.example.ForecastApp.R
import com.example.ForecastApp.App
import com.example.ForecastApp.model.predicitions.Prediction
import com.example.ForecastApp.model.predicitions.Predictions
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion
import java.net.URLEncoder
import java.util.*

class SearchAutoCompleteAdapter(private val mContext: Context) : BaseAdapter(), Filterable {

    companion object {
        private var URL = AUTOCOMPLETE_API_URL + App.instance.getString(R.string.google_api_key)
        fun getLocationSearchUrl(queryText: String): String? = String.format(URL, URLEncoder.encode(queryText, "UTF-8"))
    }

    private var resultList: List<Prediction> = ArrayList()

    override fun getCount(): Int {
        return resultList.size
    }

    override fun getItem(index: Int): Prediction {
        return resultList[index]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.simple_dropdown_item, parent, false)
        }
        (convertView!!.findViewById<View>(R.id.text1) as TextView).text = getItem(position).toString()
        return convertView
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                val filterResults = FilterResults()
                if (constraint != null) {
                    Log.e("Filter", "constraint text is not null")
                    val predictions = findLocation(mContext, constraint.toString())
                    filterResults.values = predictions.predictions
                    filterResults.count = predictions.count
                }

            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.let {
                if (it.count > 0) {
                    try {
                        resultList = results.values as List<Prediction>
                    } catch (exception: ClassCastException) {
                        Log.e("AutoCompleteAdapater", exception.message.toString())
                    }
                    notifyDataSetChanged()
                }
            }
            notifyDataSetInvalidated()
        }
    }
}


private fun findLocation(context: Context, queryText: String): Predictions {
    val url = getLocationSearchUrl(queryText) ?: return Predictions.ERROR
    return Ion.with(context).load(url).`as`(object : TypeToken<Predictions>() {}).get()
}
}
