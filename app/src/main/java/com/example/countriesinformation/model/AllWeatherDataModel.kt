package com.example.countriesinformation.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class AllWeatherDataModel {
    @SerializedName("cod")
    @Expose
    private var cod: String? = null

    @SerializedName("message")
    @Expose
    private var message: Int? = null

    @SerializedName("cnt")
    @Expose
    private var cnt: Int? = null

    @SerializedName("list")
    @Expose
    private var list: List<ListWeatherModel?>? = null

    @SerializedName("city")
    @Expose
    private var city: CityWeatherModel? = null

    fun getCod(): String? {
        return cod
    }

    fun setCod(cod: String?) {
        this.cod = cod
    }

    fun getMessage(): Int? {
        return message
    }

    fun setMessage(message: Int?) {
        this.message = message
    }

    fun getCnt(): Int? {
        return cnt
    }

    fun setCnt(cnt: Int?) {
        this.cnt = cnt
    }

    fun getList(): List<ListWeatherModel?>? {
        return list
    }

    fun setList(list: List<ListWeatherModel?>?) {
        this.list = list
    }

    fun getCity(): CityWeatherModel? {
        return city
    }

    fun setCity(city: CityWeatherModel?) {
        this.city = city
    }
}