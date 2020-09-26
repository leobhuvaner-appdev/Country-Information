package com.example.countriesinformation.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class   CityWeatherModel {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("coord")
    @Expose
    private var coord: CoordWeatherModel? = null

    @SerializedName("country")
    @Expose
    private var country: String? = null

    @SerializedName("population")
    @Expose
    private var population: Int? = null

    @SerializedName("timezone")
    @Expose
    private var timezone: Int? = null

    @SerializedName("sunrise")
    @Expose
    private var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    private var sunset: Int? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCoord(): CoordWeatherModel? {
        return coord
    }

    fun setCoord(coord: CoordWeatherModel?) {
        this.coord = coord
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getPopulation(): Int? {
        return population
    }

    fun setPopulation(population: Int?) {
        this.population = population
    }

    fun getTimezone(): Int? {
        return timezone
    }

    fun setTimezone(timezone: Int?) {
        this.timezone = timezone
    }

    fun getSunrise(): Int? {
        return sunrise
    }

    fun setSunrise(sunrise: Int?) {
        this.sunrise = sunrise
    }

    fun getSunset(): Int? {
        return sunset
    }

    fun setSunset(sunset: Int?) {
        this.sunset = sunset
    }
}