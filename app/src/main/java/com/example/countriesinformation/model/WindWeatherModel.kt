package com.example.countriesinformation.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class WindWeatherModel {
    @SerializedName("speed")
    @Expose
    private var speed: Double? = null

    @SerializedName("deg")
    @Expose
    private var deg: Int? = null

    fun getSpeed(): Double? {
        return speed
    }

    fun setSpeed(speed: Double?) {
        this.speed = speed
    }

    fun getDeg(): Int? {
        return deg
    }

    fun setDeg(deg: Int?) {
        this.deg = deg
    }
}