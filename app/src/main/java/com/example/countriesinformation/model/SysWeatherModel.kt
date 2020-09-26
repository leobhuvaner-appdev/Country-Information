package com.example.countriesinformation.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class SysWeatherModel {
    @SerializedName("pod")
    @Expose
    private var pod: String? = null

    fun getPod(): String? {
        return pod
    }

    fun setPod(pod: String?) {
        this.pod = pod
    }
}