package com.example.countriesinformation.apiinterface

import com.example.countriesinformation.model.AllWeatherDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
     @GET("data/2.5/forecast")
     fun getAllWeatherData(@Query("q") qValue : String, @Query("appid") appidValue : String ): Call<AllWeatherDataModel>
}