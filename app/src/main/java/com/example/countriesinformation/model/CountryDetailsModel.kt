package com.example.countriesinformation.model

import java.io.Serializable

class CountryDetailsModel : Serializable {
    private var flagImage: String? = null
    private var countryName: String? = null
    private var capital: String? = null
    private var region: String? = null
    private var subRegion: String? = null
    private var area: Double? = null
    private var population: Int? = null

    fun getFlagImage(): String? {
        return flagImage
    }

    fun setFlagImage(image: String?) {
        flagImage = image
    }

    fun getCountryName(): String? {
        return countryName
    }

    fun setCountryName(countryName: String?) {
        this.countryName = countryName
    }

    fun getCapital(): String? {
        return capital
    }

    fun setCapital(capital: String?) {
        this.capital = capital
    }

    fun getRegion(): String? {
        return region
    }

    fun setRegion(region: String?) {
        this.region = region
    }

    fun getSubRegion(): String? {
        return subRegion
    }

    fun setSubRegion(subRegion: String?) {
        this.subRegion = subRegion
    }

    fun getArea(): Double? {
        return area
    }

    fun setArea(area: Double?) {
        this.area = area
    }

    fun getPopulation(): Int? {
        return population
    }

    fun setPopulation(population: Int?) {
        this.population = population
    }
}