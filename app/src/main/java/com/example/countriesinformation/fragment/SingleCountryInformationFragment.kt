package com.example.countriesinformation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import com.ahmadrosid.svgloader.SvgLoader
import com.example.countriesinformation.R
import com.example.countriesinformation.model.CountryDetailsModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SingleCountryInformationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingleCountryInformationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var countryDetailsModel: CountryDetailsModel? = null

    @JvmField
    @BindView(R.id.separate_country_name)
    var separateCountryNameTV: TextView? = null

    @JvmField
    @BindView(R.id.capital_value)
    var capitalTV: TextView? = null

    @JvmField
    @BindView(R.id.region_value)
    var regionTV: TextView? = null

    @JvmField
    @BindView(R.id.sub_region_value)
    var subRegionTV: TextView? = null

    @JvmField
    @BindView(R.id.area_value)
    var areaTV: TextView? = null

    @JvmField
    @BindView(R.id.population_value)
    var populationTV: TextView? = null

    @JvmField
    @BindView(R.id.separate_country_flag)
    var flagImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        countryDetailsModel = arguments?.getSerializable("MY_OBJEDT") as CountryDetailsModel
        val view = inflater.inflate(R.layout.fragment_single_country_information, container, false)
        ButterKnife.bind(this,view)

        if (countryDetailsModel != null) {
            val flagImageUrl = countryDetailsModel!!.getFlagImage()
            val countryName = countryDetailsModel!!.getCountryName()
            val capitalValue = countryDetailsModel!!.getCapital()
            val regionValue = countryDetailsModel!!.getRegion()
            val subRegionValue = countryDetailsModel!!.getSubRegion()
            val areaValue = countryDetailsModel!!.getArea()
            val populationValue = countryDetailsModel!!.getPopulation()!!
            if (flagImageUrl != null) {
                SvgLoader.pluck()
                    .with(activity)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(flagImageUrl, flagImage)
            }
            if (!countryName.equals("")) {
                separateCountryNameTV!!.text = countryName
            } else {
                separateCountryNameTV!!.text = "---"
            }
            if (!capitalValue.equals("")) {
                capitalTV!!.text = capitalValue
            } else {
                capitalTV!!.text = "---"
            }
            if (!regionValue.equals("")) {
                regionTV!!.text = regionValue
            } else {
                regionTV!!.text = "---"
            }
            if (!subRegionValue.equals("")) {
                subRegionTV!!.text = subRegionValue
            } else {
                subRegionTV!!.text = "---"
            }
            if (!areaValue!!.equals("")) {
                areaTV!!.text = areaValue.toString()
            } else {
                areaTV!!.text = "---"
            }
            if (!populationValue!!.equals("")) {
                populationTV!!.text = populationValue.toString()
            } else {
                populationTV!!.text = "---"
            }
        }
        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SingleCountryInformationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SingleCountryInformationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
