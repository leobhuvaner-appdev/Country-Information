package com.example.countriesinformation.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CoordWeatherModel {
    @SerializedName("lat")
    @Expose
    private var lat: Double? = null

    @SerializedName("lon")
    @Expose
    private var lon: Double? = null

    fun getLat(): Double? {
        return lat
    }

    fun setLat(lat: Double?) {
        this.lat = lat
    }

    fun getLon(): Double? {
        return lon
    }

    fun setLon(lon: Double?) {
        this.lon = lon
    }
}