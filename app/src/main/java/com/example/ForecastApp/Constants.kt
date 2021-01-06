package com.example.ForecastApp

object Constants {

    val OPEN_WEATHER_API = "https://api.openweathermap.org/data/2.5/"
    val OPEN_WEATHER_API_MODE = "&mode=json"
    val OPEN_WEATHER_COMMON_PARAMS = OPEN_WEATHER_API_MODE
    val CONNECTION_TIMEOUT: Long = 60
    val READ_TIMEOUT: Long = 60
    val BASE_URL = "https://api.openweathermap.org/"
    val ICON_BASE_URL = "https://openweathermap.org/img/w/"
    val AUTOCOMPLETE_API_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=%s&types=(regions)&sensor=false&key="
    val API_KEY = "e87978f0ef99fd866adffe6a767099d3"


    val ICON_EXTENSION = ".png"

    val CELSIUS_EXTENSION = "°C"
}
