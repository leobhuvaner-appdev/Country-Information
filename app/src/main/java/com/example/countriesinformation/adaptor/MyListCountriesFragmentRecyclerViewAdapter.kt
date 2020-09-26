package com.example.countriesinformation.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.ahmadrosid.svgloader.SvgLoader
import com.example.countriesinformation.R
import com.example.countriesinformation.listener.CountryCallBack
import com.example.countriesinformation.model.CountryNameFlagModel


class MyListCountriesFragmentRecyclerViewAdapter(
    var context: Context,
    var _countryNameFlagModelList: ArrayList<CountryNameFlagModel>,
    var activity: Activity,var _countryNameFlagModelListFull: ArrayList<CountryNameFlagModel>,var _countryCallBack : CountryCallBack
) : RecyclerView.Adapter<MyListCountriesFragmentRecyclerViewAdapter.ViewHolder>(), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItem: View = inflator.inflate(R.layout.fragment_list_countries_items, parent, false)
        return ViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return _countryNameFlagModelList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val countryNameFlagModel: CountryNameFlagModel = _countryNameFlagModelList.get(position)
        val countryName = countryNameFlagModel.getCountryName()
        val countryFlagUrl = countryNameFlagModel.getCountryFlag()
        viewHolder.countryNameTV!!.setText(countryName)

        SvgLoader.pluck()
            .with(activity)
            .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
            .load(countryFlagUrl, viewHolder.countryFlagImage)

        viewHolder.itemView.setOnClickListener(View.OnClickListener {
            val countryName: String =
                _countryNameFlagModelList.get(position).getCountryName().toString()
               _countryCallBack.countryName(countryName)
        })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @JvmField
        @BindView(R.id.country_name_tv)
        var countryNameTV: TextView? = null

        @JvmField
        @BindView(R.id.country_flage_img)
        var countryFlagImage: ImageView? = null


        init {
            ButterKnife.bind(this, itemView)
           /* countryNameTV = itemView.findViewById(R.id.country_name_tv)
            countryFlagImage =
                itemView.findViewById(R.id.country_flage_img)*/
        }
    }

    override fun getFilter(): Filter? {
        return exampleFilter
    }

    //filter functionality
    private var exampleFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val filteredList: ArrayList<CountryNameFlagModel> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(_countryNameFlagModelListFull)
            } else {
                val filterPattern =
                    constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in _countryNameFlagModelListFull) {
                    if (item.getCountryName()!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(
            constraint: CharSequence?,
            results: FilterResults
        ) {
            _countryNameFlagModelList.clear()
            _countryNameFlagModelList.addAll(results.values as List<CountryNameFlagModel>)
            notifyDataSetChanged()
        }
    }
}

