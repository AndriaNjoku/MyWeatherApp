package com.example.ForecastApp.useCase

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject


class PojoConverter @Inject constructor() : Converter {

     companion object {
         private const val KELVIN_CONSTANT = 273.15
         const val CELSIUS_EXTENSION = "°C"

     }

    override fun getCelsiusFromKelvinC(value: Double?): String{
        val tempInCelsius = value?.minus(KELVIN_CONSTANT)
        return roundDoubleToTwoDecimalPoints(tempInCelsius) + CELSIUS_EXTENSION
    }

    override fun getCelsiusFromKelvin(value: Double?): String{
        val tempInCelsius = value?.minus(KELVIN_CONSTANT)
        return roundDoubleToTwoDecimalPoints(tempInCelsius)
    }

    override fun getDateForLocaleFromUtc(value: Long): String{
        val dateTime = DateTime(value * 1000L, DateTimeZone.getDefault()) //Converting to milliseconds
        val dateTimeFormatter = DateTimeFormat.forPattern("d MMM E h:m a")
        return dateTimeFormatter.print(dateTime)
    }

    override fun roundDoubleToTwoDecimalPoints(value: Double?): String{
        val decimalFormat = DecimalFormat("#.#")
        decimalFormat.roundingMode = RoundingMode.CEILING
        return decimalFormat.format(value)
    }
}