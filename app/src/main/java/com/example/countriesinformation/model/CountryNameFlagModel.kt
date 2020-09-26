package com.example.countriesinformation.model

class CountryNameFlagModel {
    private var countryName: String? = null
    private var countryFlag: String? = null

    fun getCountryName(): String? {
        return countryName
    }

    fun setCountryName(countryName: String?) {
        this.countryName = countryName
    }

    fun getCountryFlag(): String? {
        return countryFlag
    }

    fun setCountryFlag(countryFlag: String?) {
        this.countryFlag = countryFlag
    }
}