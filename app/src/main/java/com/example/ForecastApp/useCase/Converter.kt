package com.example.ForecastApp.useCase

interface Converter {

    fun getCelsiusFromKelvinC(value: Double?): String

    fun getDateForLocaleFromUtc(value: Long): String

    fun roundDoubleToTwoDecimalPoints(value: Double?) : String
    fun getCelsiusFromKelvin(value: Double?): String
}