package com.example.countriesinformation.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CloudsWeatherModel {
    @SerializedName("all")
    @Expose
    private var all: Int? = null

    fun getAll(): Int? {
        return all
    }

    fun setAll(all: Int?) {
        this.all = all
    }
}