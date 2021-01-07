package com.example.ForecastApp.weatherDetailScreen.ui

import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.model.weather.Forecast
import com.example.ForecastApp.model.weather.Weather
import com.example.ForecastApp.useCase.GetWeatherForecast
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.Test

class PojoWeatherDetailPresenterTest() {

    @Test
    fun testPresenterCallsUseCase() {

        val mockWeatherGetter = mock<GetWeatherForecast> {
            on { getDetailed(any()) } doReturn Observable.never()
        }

        // Given
        val sut = PojoWeatherDetailPresenter(
                mock(),
                mock(),
                mockWeatherGetter
        )

        //When
        sut.getDayDetails("place")

        //Then
        verify(mockWeatherGetter, times(1)).getDetailed("place")
    }

    @Test
    fun testWeatherPassedToView() {

        val oneDayMock = Day().apply {
            dateAndTime = 1000L
            weather = listOf(Weather().apply {
                description = "Rainy"
            })
        }

        val mockWeatherGetter = mock<GetWeatherForecast> {
            on { getDetailed(any()) } doReturn Observable.just(Forecast().apply {
                days = listOf(oneDayMock )})
        }

        val mockView = mock<WeatherDetailPresenter.View>()

        // Given
        val sut = PojoWeatherDetailPresenter(
                mockView,
                mock(),
                mockWeatherGetter
        )

        //When
        sut.getDayDetails("place")

        //Then
        verify(mockView, times(1)).showForecast(listOf(oneDayMock))

    }


}