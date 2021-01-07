package com.example.ForecastApp.weatherResultsScreen.ui

import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.model.weather.Forecast
import com.example.ForecastApp.model.weather.Weather
import com.example.ForecastApp.useCase.GetWeatherForecast
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.Test

internal class PojoSearchResultsPresenterTest() {


    @Test
    fun testPresenterCallsUseCase() {

        val mockWeatherGetter = mock<GetWeatherForecast> {
            on { getBasic(any(), any()) } doReturn Observable.never()
        }

        // Given
        val sut = PojoSearchResultsPresenter(
                mock(),
                mock(),
                mockWeatherGetter
        )

        //When
        // No need for when we initiate inside of init


        sut.showSearchResults("location")
        //Then
        verify(mockWeatherGetter, times(1)).getBasic(any(), any())
    }

    @Test
    fun testThatTheWeatherReturnedIsPassedToView() {


        val mockedWeather: Forecast = Forecast().apply {
            days = listOf(Day().apply {
                dateAndTime = 1000L
                weather = listOf(Weather().apply {
                    description = "Rainy"
                })
            })
        }

        val mockWeatherGetter = mock<GetWeatherForecast> {
            on { getBasic(any(), any()) } doReturn Observable.just(mockedWeather)
        }

        val mockedView = mock<SearchResultsPresenter.View>()
        // Given
        val sut = PojoSearchResultsPresenter(
                mockedView,
                mock(),
                mockWeatherGetter
        )

        //When
        // No need for when we initiate inside of init


        sut.showSearchResults("location")
        //Then
        verify(mockedView, times(1)).showSearchResults(mockedWeather.days!!)
    }

}

