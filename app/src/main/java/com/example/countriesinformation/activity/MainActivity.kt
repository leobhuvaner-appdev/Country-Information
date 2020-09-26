package com.example.countriesinformation.activity

import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import butterknife.BindView
import butterknife.ButterKnife
import com.example.countriesinformation.R
import com.example.countriesinformation.apiclient.ApiClient
import com.example.countriesinformation.apiclient.WeatherApiClient
import com.example.countriesinformation.apiinterface.ApiInterface
import com.example.countriesinformation.apiinterface.WeatherApiInterface
import com.example.countriesinformation.fragment.ListCountriesFragmentFragment
import com.example.countriesinformation.fragment.SingleCountryInformationFragment
import com.example.countriesinformation.fragment.WeatherDisplayFragment
import com.example.countriesinformation.listener.RefreshCallBack
import com.example.countriesinformation.model.*
import com.example.countriesinformation.utils.ConnectionDetector
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    ListCountriesFragmentFragment.OnListFragmentInteractionListener, RefreshCallBack,
    ConnectionDetector.ConnectivityReceiverListener {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    val PERMISSION_ID = 1010
    var cityNameValue: String = ""
    var refreshCallBack: RefreshCallBack? = null
    var watherApiInterface: WeatherApiInterface? = null
    private var apiInterface: ApiInterface? = null
    var allWeatherDetailsListModelList: AllWeatherDataModel? = null
    var displayWeatherDetailsModelList: ArrayList<DisplayWeatherDetailsModel> = ArrayList()

    var countryNameFlagModelList: ArrayList<CountryNameFlagModel> = ArrayList()
    var countryDetailsHashMap: HashMap<String, CountryDetailsModel>? = HashMap()
    var countryNameFlagModelListFull: ArrayList<CountryNameFlagModel>? = ArrayList()
    var snackBar: Snackbar? = null
    var activity: AppCompatActivity? = null

    @JvmField
    @BindView(R.id.main_activity)
    var mContstrainLayout: ConstraintLayout? = null

    @JvmField
    @BindView(R.id.navigation)
    var mBottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            watherApiInterface = WeatherApiClient.weatherApiInterface
            apiInterface = ApiClient.apiInterface
            ButterKnife.bind(this)
            this.activity = this;
            mBottomNavigationView!!.setOnNavigationItemSelectedListener(this)
            this.refreshCallBack = this
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

            loadFragment(
                WeatherDisplayFragment(
                    cityNameValue,
                    displayWeatherDetailsModelList,
                    refreshCallBack!!
                )
            )

            registerReceiver(
                ConnectionDetector(),
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_weather -> fragment = WeatherDisplayFragment(
                cityNameValue,
                displayWeatherDetailsModelList,
                refreshCallBack!!
            )
            R.id.navigation_country_list -> fragment =
                ListCountriesFragmentFragment(
                    countryNameFlagModelList,
                    countryDetailsHashMap,
                    countryNameFlagModelListFull!!
                )
            R.id.navigation_country_info -> fragment()
        }
        return loadFragment(fragment)
    }

    fun fragment() {
        Toast.makeText(this, "Please select any one of country", Toast.LENGTH_SHORT).show()
    }

    /*load first fragment*/
    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }

    /*implement interface method */
    override fun onListFragmentInteraction(_countryDetailsModel: CountryDetailsModel) {
        var countryDetailsModel = _countryDetailsModel
        val bundle = Bundle()
        bundle.putSerializable("MY_OBJEDT", countryDetailsModel)

        val transaction = this.supportFragmentManager.beginTransaction()
        val frag2 = SingleCountryInformationFragment()
        frag2.arguments = bundle

        transaction.replace(R.id.fragment_container, frag2)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    fun getLastLocation() {
        if (CheckPermission()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        NewLocationData()
                    } else {
                        Log.d("Debug:", "Your Location:" + location.longitude)
                        getCityName(location.latitude, location.longitude)
                    }
                }
            } else {
                Toast.makeText(this, "Please Turn on Your device Location", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            RequestPermission()
        }
    }

    fun NewLocationData() {
        var locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:", "your last last location: " + lastLocation.longitude.toString())
            getCityName(lastLocation.latitude, lastLocation.longitude)
        }
    }

    private fun CheckPermission(): Boolean {
        //this function will return a boolean
        //true: if we have permission
        //false if not
        if (
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    fun RequestPermission() {
        //this function will allows us to tell the user to requesut the necessary permsiion if they are not garented
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    fun isLocationEnabled(): Boolean {
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug:", "You have the Permission")
            }
        }
    }

    /*get current address location*/
    private fun getCityName(lat: Double, long: Double): String {
        var geoCoder = Geocoder(this, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat, long, 3)

        cityNameValue = Adress.get(0).subAdminArea
        fetchAllWeatherDetails(cityNameValue)
        return cityNameValue
    }


    override fun RefreshCallBack() {
        RequestPermission()
        getLastLocation()
    }

    /*create REST API call for get current weather details*/
    fun fetchAllWeatherDetails(cityName: String) {
        val allWindDetailsListCall: Call<AllWeatherDataModel> =
            watherApiInterface!!.getAllWeatherData(cityName, "c5c630a04ee4f1aae71728d960e807c3")
        allWindDetailsListCall!!.enqueue(object : Callback<AllWeatherDataModel> {
            override fun onFailure(call: Call<AllWeatherDataModel>?, t: Throwable?) {
                Log.d("WEATHERDETAILFAILURE", "Failure Response Code${t}")
            }

            override fun onResponse(
                call: Call<AllWeatherDataModel>?,
                response: Response<AllWeatherDataModel>?
            ) {
                if (!response!!.isSuccessful) {
                    var code = response!!.code()
                    Log.d("WEATHERDETAILRESPONSE", "Response Code${code}")
                }
                allWeatherDetailsListModelList = response.body()
                if (allWeatherDetailsListModelList != null) {
                    var listWeatherModel: List<ListWeatherModel> =
                        allWeatherDetailsListModelList!!.getList() as List<ListWeatherModel>
                    for (i in 0 until listWeatherModel.size) {
                        var displayWeatherDetailsModel: DisplayWeatherDetailsModel =
                            DisplayWeatherDetailsModel()

                        var weatherDate = listWeatherModel.get(i).getDtTxt()
                        displayWeatherDetailsModel.setWeatherDate(weatherDate)

                        var weatherTemp = listWeatherModel.get(i).getMain()!!.getTemp()
                        displayWeatherDetailsModel.setWeatherTemp(weatherTemp)

                        var weatherHumidity = listWeatherModel.get(i).getMain()!!.getHumidity()
                        displayWeatherDetailsModel.setWeatherHumidity(weatherHumidity)

                        var weatherMainCloud: String =
                            listWeatherModel!!.get(i).getWeather()!!.get(0)!!.getMain()!!
                        displayWeatherDetailsModel.setweatherMain(weatherMainCloud)

                        var weatherDescription: String =
                            listWeatherModel!!.get(i)!!.getWeather()!!
                                .get(0)!!.getDescription()!!
                        displayWeatherDetailsModel.setWeatherDescription(weatherDescription)


                        var weatherSpeed: Double =
                            listWeatherModel!!.get(0)!!.getWind()!!
                                .getSpeed()!!
                        displayWeatherDetailsModel.setWeatherSpeed(weatherSpeed)

                        var weatherDegree: Int =
                            listWeatherModel!!.get(0)!!.getWind()!!
                                .getDeg()!!
                        displayWeatherDetailsModel.setWeatherDeg(weatherDegree)

                        displayWeatherDetailsModelList.add(displayWeatherDetailsModel)
                    }

                    var city: CityWeatherModel =
                        allWeatherDetailsListModelList!!.getCity() as CityWeatherModel
                    cityNameValue = city.getName()!!
                    loadFragment(
                        WeatherDisplayFragment(
                            cityNameValue,
                            displayWeatherDetailsModelList,
                            refreshCallBack!!
                        )
                    )
                }
            }
        })
    }

    /*create REST API call for get all country details*/
    private fun fetchAllCountiesDetails() {
        val allCountryDetailsListCall: Call<List<AllCountryDetailsListModel>> =
            apiInterface!!.getAllCountryDetails()
        allCountryDetailsListCall!!.enqueue(object : Callback<List<AllCountryDetailsListModel>> {
            override fun onFailure(call: Call<List<AllCountryDetailsListModel>>?, t: Throwable?) {
                Log.d("COUNTRYFAILUERESPONS", "Failure Response Code${t}")
            }

            override fun onResponse(
                call: Call<List<AllCountryDetailsListModel>>?,
                response: Response<List<AllCountryDetailsListModel>>?
            ) {
                if (!response!!.isSuccessful) {
                    var code = response!!.code()
                    Log.d("FAILUERESPONSCODE", "Response Code${code}")
                }

                var allCountryDetailsListModelList: List<AllCountryDetailsListModel> =
                    response.body()
                if (allCountryDetailsListModelList != null) {
                    for (i in 0 until allCountryDetailsListModelList.size) {
                        val countryNameFlagModel = CountryNameFlagModel()
                        countryNameFlagModel.setCountryName(
                            allCountryDetailsListModelList.get(i).getName()
                        )
                        countryNameFlagModel.setCountryFlag(
                            allCountryDetailsListModelList.get(i).getFlag()
                        )
                        countryNameFlagModelList.add(countryNameFlagModel)
                        countryNameFlagModelListFull!!.add(countryNameFlagModel)
                        val countryDetailsModel = CountryDetailsModel()
                        countryDetailsModel.setCountryName(
                            allCountryDetailsListModelList.get(i).getName()
                        )
                        countryDetailsModel.setFlagImage(
                            allCountryDetailsListModelList.get(i).getFlag()
                        )
                        countryDetailsModel.setCapital(
                            allCountryDetailsListModelList.get(i).getCapital()
                        )
                        countryDetailsModel.setRegion(
                            allCountryDetailsListModelList.get(i).getRegion()
                        )
                        countryDetailsModel.setSubRegion(
                            allCountryDetailsListModelList.get(i).getSubregion()
                        )
                        countryDetailsModel.setArea(allCountryDetailsListModelList.get(i).getArea())
                        countryDetailsModel.setPopulation(
                            allCountryDetailsListModelList.get(i).getPopulation()
                        )
                        //countryDetailsHashMap!![allCountryDetailsListModelList.get(i).getName().toString()] = countryDetailsModel
                        countryDetailsHashMap!!.put(
                            allCountryDetailsListModelList.get(i).getName().toString(),
                            countryDetailsModel
                        )
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        ConnectionDetector.connectivityReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetWorkState(isConnected)
    }

    fun showNetWorkState(isConnected: Boolean) {
        if (!isConnected) {
            snackBar = Snackbar.make(
                mContstrainLayout!!,
                "You are offline",
                Snackbar.LENGTH_LONG
            ) //Assume "rootLayout" as the root layout of every activity.
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.show()
        } else {
            snackBar?.dismiss()
            RequestPermission()
            getLastLocation()
            fetchAllCountiesDetails()
        }
    }
}


