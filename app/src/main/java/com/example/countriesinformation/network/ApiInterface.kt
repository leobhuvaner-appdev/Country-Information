package com.example.countriesinformation.apiinterface

import com.example.countriesinformation.model.AllCountryDetailsListModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
     @GET("rest/v2/all")
     fun getAllCountryDetails(): Call<List<AllCountryDetailsListModel>>
}