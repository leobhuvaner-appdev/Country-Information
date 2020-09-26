package com.example.countriesinformation.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ListWeatherModel {
    @SerializedName("dt")
    @Expose
    private var dt: Int? = null

    @SerializedName("main")
    @Expose
    private var main: MainWeatherModel? = null

    @SerializedName("weather")
    @Expose
    private var weather: List<WeatherModel?>? = null

    @SerializedName("clouds")
    @Expose
    private var clouds: CloudsWeatherModel? = null

    @SerializedName("wind")
    @Expose
    private var wind: WindWeatherModel? = null

    @SerializedName("visibility")
    @Expose
    private var visibility: Int? = null

    @SerializedName("pop")
    @Expose
    private var pop: Double? = null

    @SerializedName("sys")
    @Expose
    private var sys: SysWeatherModel? = null

    @SerializedName("dt_txt")
    @Expose
    private var dtTxt: String? = null

    @SerializedName("rain")
    @Expose
    private var rain: RainWeatherModel? = null

    fun getDt(): Int? {
        return dt
    }

    fun setDt(dt: Int?) {
        this.dt = dt
    }

    fun getMain(): MainWeatherModel? {
        return main
    }

    fun setMain(main: MainWeatherModel?) {
        this.main = main
    }

    fun getWeather(): List<WeatherModel?>? {
        return weather
    }

    fun setWeather(weather: List<WeatherModel?>?) {
        this.weather = weather
    }

    fun getClouds(): CloudsWeatherModel? {
        return clouds
    }

    fun setClouds(clouds: CloudsWeatherModel?) {
        this.clouds = clouds
    }

    fun getWind(): WindWeatherModel? {
        return wind
    }

    fun setWind(wind: WindWeatherModel?) {
        this.wind = wind
    }

    fun getVisibility(): Int? {
        return visibility
    }

    fun setVisibility(visibility: Int?) {
        this.visibility = visibility
    }

    fun getPop(): Double? {
        return pop
    }

    fun setPop(pop: Double?) {
        this.pop = pop
    }

    fun getSys(): SysWeatherModel? {
        return sys
    }

    fun setSys(sys: SysWeatherModel?) {
        this.sys = sys
    }

    fun getDtTxt(): String? {
        return dtTxt
    }

    fun setDtTxt(dtTxt: String?) {
        this.dtTxt = dtTxt
    }

    fun getRain(): RainWeatherModel? {
        return rain
    }

    fun setRain(rain: RainWeatherModel?) {
        this.rain = rain
    }

}