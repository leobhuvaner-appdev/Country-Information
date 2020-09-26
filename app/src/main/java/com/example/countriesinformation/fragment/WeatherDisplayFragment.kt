package com.example.countriesinformation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.countriesinformation.R
import com.example.countriesinformation.adapter.MyWeatherListRecyclerViewAdapter
import com.example.countriesinformation.listener.RefreshCallBack
import com.example.countriesinformation.model.DisplayWeatherDetailsModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherDisplayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherDisplayFragment(
    var _cityName: String,
    var displayWeatherDetailsModelList: ArrayList<DisplayWeatherDetailsModel>,
    var _refreshCallBack: RefreshCallBack
) : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var myWeatherListRecyclerViewAdapter: MyWeatherListRecyclerViewAdapter? = null

    @JvmField
    @BindView(R.id.cityNameDisplay)
    var mCityTextView: TextView? = null

    @JvmField
    @BindView(R.id.refresh_icon)
    var mRefreshImage: ImageView? = null

    @JvmField
    @BindView(R.id.weather_details_display_recyclerview)
    var mWeatherDisplayRecyclerView: RecyclerView? = null

    private var refreshCallBack: RefreshCallBack? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_weather_display, container, false)
        ButterKnife.bind(this,view)
        if (!_cityName.isEmpty()) {
            mRefreshImage!!.visibility = View.INVISIBLE
            mCityTextView!!.visibility = View.VISIBLE
            mCityTextView!!.text = _cityName
        }

        this.refreshCallBack = _refreshCallBack;
        mRefreshImage!!.setOnClickListener {
            refreshCallBack!!.RefreshCallBack()
        }
        setUpRecyclerView(context!!, displayWeatherDetailsModelList)
        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(
            _cityName: String,
            displayWeatherDetailsModelList: ArrayList<DisplayWeatherDetailsModel>,
            _refreshCallBack: RefreshCallBack
        ) =
            WeatherDisplayFragment(
                _cityName,
                displayWeatherDetailsModelList,
                _refreshCallBack
            ).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    /*load data inside recyclerview*/
    private fun setUpRecyclerView(
        context: Context,
        weatherDetailList: ArrayList<DisplayWeatherDetailsModel>
    ) {
        lateinit var layoutManager: RecyclerView.LayoutManager
        layoutManager = LinearLayoutManager(context)
        mWeatherDisplayRecyclerView!!.layoutManager = layoutManager
        myWeatherListRecyclerViewAdapter = MyWeatherListRecyclerViewAdapter(
            context,
            weatherDetailList
        )
        mWeatherDisplayRecyclerView!!.adapter = myWeatherListRecyclerViewAdapter
    }
}
