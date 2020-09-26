package com.example.countriesinformation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.countriesinformation.R
import com.example.countriesinformation.model.DisplayWeatherDetailsModel


class MyWeatherListRecyclerViewAdapter(
    var context: Context,
    var _weatherDisplayDetailsList: ArrayList<DisplayWeatherDetailsModel>
) : RecyclerView.Adapter<MyWeatherListRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItem: View = inflator.inflate(R.layout.weather_list_items, parent, false)
        return ViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return _weatherDisplayDetailsList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val displayWeatherDetailsModel: DisplayWeatherDetailsModel = _weatherDisplayDetailsList.get(position)
        viewHolder.dateValueTV!!.text = displayWeatherDetailsModel.getWeatherDate()
        viewHolder.mainCloudTV!!.text  = displayWeatherDetailsModel.getweatherMain()+" :"
        viewHolder.weatherDescriptionTV!!.text = displayWeatherDetailsModel.getWeatherDescription()
        viewHolder.tempHumidityTV!!.text = displayWeatherDetailsModel.getWeatherTemp().toString()+"/"+displayWeatherDetailsModel.getWeatherHumidity().toString()
        viewHolder.weatherSpeedTV!!.text = displayWeatherDetailsModel.getWeatherSpeed().toString()
        viewHolder.weatherDegreeTV!!.text = displayWeatherDetailsModel.getWeatherDeg().toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @JvmField
        @BindView(R.id.weather_date_value)
        var dateValueTV : TextView? = null

        @JvmField
        @BindView(R.id.main_cloud)
        var mainCloudTV : TextView? = null

        @JvmField
        @BindView(R.id.weather_description)
        var weatherDescriptionTV : TextView? = null

        @JvmField
        @BindView(R.id.temp_humidity_value)
        var tempHumidityTV : TextView? = null

        @JvmField
        @BindView(R.id.weather_speed_value)
        var weatherSpeedTV : TextView? = null

        @JvmField
        @BindView(R.id.weather_degree_value)
        var weatherDegreeTV : TextView? = null


        init {
            ButterKnife.bind(this, itemView)
           /* dateValueTV = itemView.findViewById(R.id.weather_date_value)
            mainCloudTV = itemView.findViewById(R.id.main_cloud)
            weatherDescriptionTV = itemView.findViewById(R.id.weather_description)
            tempHumidityTV = itemView.findViewById(R.id.temp_humidity_value)
            weatherSpeedTV = itemView.findViewById(R.id.weather_speed_value)
            weatherDegreeTV = itemView.findViewById(R.id.weather_degree_value)*/

        }
    }
}

