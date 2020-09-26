package com.example.countriesinformation.model

class DisplayWeatherDetailsModel {

    private var weatherDate: String? = null

    private var weatherTemp: Double? = null

    private var weatherHumidity: Int? = null

    private var weatherMain: String? = null

    private var weatherDescription: String? = null

    private var weatherSpeed: Double? = null

    private var weatherDeg: Int? = null


    fun getWeatherDate(): String? {
        return weatherDate
    }

    fun setWeatherDate(weatherDate: String?) {
        this.weatherDate = weatherDate
    }


    fun getWeatherTemp(): Double? {
        return weatherTemp
    }

    fun setWeatherTemp(weatherTemp: Double?) {
        this.weatherTemp = weatherTemp
    }


    fun getWeatherHumidity(): Int? {
        return weatherHumidity
    }

    fun setWeatherHumidity(weatherHumidity: Int?) {
        this.weatherHumidity = weatherHumidity
    }

    fun getweatherMain(): String? {
        return weatherMain
    }

    fun setweatherMain(weatherMain: String?) {
        this.weatherMain = weatherMain
    }

    fun getWeatherDescription(): String? {
        return weatherDescription
    }

    fun setWeatherDescription(weatherDescription: String?) {
        this.weatherDescription = weatherDescription
    }


    fun getWeatherSpeed(): Double? {
        return weatherSpeed
    }

    fun setWeatherSpeed(weatherSpeed: Double?) {
        this.weatherSpeed = weatherSpeed
    }

    fun getWeatherDeg(): Int? {
        return weatherDeg
    }

    fun setWeatherDeg(weatherDeg: Int?) {
        this.weatherDeg = weatherDeg
    }


}