package com.example.countriesinformation.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.countriesinformation.R
import com.example.countriesinformation.adapter.MyListCountriesFragmentRecyclerViewAdapter
import com.example.countriesinformation.listener.CountryCallBack
import com.example.countriesinformation.model.CountryDetailsModel
import com.example.countriesinformation.model.CountryNameFlagModel
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ListCountriesFragmentFragment.OnListFragmentInteractionListener] interface.
 */
class ListCountriesFragmentFragment(var countryNameFlagModelList:ArrayList<CountryNameFlagModel>,var countryDetailsHashMap: java.util.HashMap<String, CountryDetailsModel>?,var countryNameFlagModelListFull: ArrayList<CountryNameFlagModel>) : Fragment(),SearchView.OnQueryTextListener,CountryCallBack{
    override fun countryName(mCountryName: String?) {
         var countryDetailsModel : CountryDetailsModel? = countryDetailsHashMap!!.get(mCountryName)
         listener!!.onListFragmentInteraction(countryDetailsModel!!)
    }

    // TODO: Customize parameters
    private var listener: OnListFragmentInteractionListener? = null
    private var myListCountriesFragmentRecyclerViewAdapter: MyListCountriesFragmentRecyclerViewAdapter? = null
    private var mActivity: Activity? = null
    private var mCountryCallBack : CountryCallBack? = null

    @JvmField
    @BindView(R.id.country_name_flag_recyclerview)
    var mCountryNameFlagrecyclerView : RecyclerView? = null

    @JvmField
    @BindView(R.id.searchView)
    var mSearchView : SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mActivity = getActivity()
        this.mCountryCallBack = this
        val view = inflater.inflate(R.layout.fragment_list_countries_fragment_list, container, false)
        ButterKnife.bind(this,view)
       /* mCountryNameFlagrecyclerView = view.findViewById(R.id.country_name_flag_recyclerview)
        mSearchView = view.findViewById(R.id.searchView)*/
        setUpRecyclerView(context!!,countryNameFlagModelList,activity!!)
        mSearchView!!.setOnQueryTextListener(this)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(countryDetailsModel : CountryDetailsModel)
    }

    companion object {
        @JvmStatic
        fun newInstance(countryNameFlagModelList:ArrayList<CountryNameFlagModel>, countryDetailsHashMap: HashMap<String, CountryDetailsModel>?, countryNameFlagModelListFull: ArrayList<CountryNameFlagModel>) =
            ListCountriesFragmentFragment(countryNameFlagModelList!!, countryDetailsHashMap, countryNameFlagModelListFull).apply {
                arguments = Bundle().apply {

                }
            }
    }

    /*load data inside recyclerview*/
    private fun setUpRecyclerView(context: Context, countryNameFlagModelList: ArrayList<CountryNameFlagModel>,activity: Activity) {
        lateinit var layoutManager: RecyclerView.LayoutManager
        layoutManager = LinearLayoutManager(context)
        mCountryNameFlagrecyclerView!!.layoutManager = layoutManager
        myListCountriesFragmentRecyclerViewAdapter = MyListCountriesFragmentRecyclerViewAdapter(
            context,
            countryNameFlagModelList,activity,countryNameFlagModelListFull,mCountryCallBack!!
        )
        mCountryNameFlagrecyclerView!!.adapter = myListCountriesFragmentRecyclerViewAdapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        myListCountriesFragmentRecyclerViewAdapter!!.getFilter()!!.filter(newText)
        return false
    }
}
