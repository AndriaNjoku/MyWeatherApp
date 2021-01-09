package com.example.ForecastApp

import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.model.weather.Forecast
import com.example.ForecastApp.model.weather.Weather
import com.example.ForecastApp.useCase.GetWeatherForecast
import com.example.ForecastApp.weatherResultsScreen.ui.PojoSearchResultsPresenter
import com.example.ForecastApp.weatherResultsScreen.ui.SearchResultsPresenter
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Test

class PojoSearchResultsPresenterTest() {


    @Test
    fun testPresenterCallsUseCase() {

        val mockWeatherGetter = mock<GetWeatherForecast> {
            on { getBasic( any()) } doReturn Observable.never()
        }

        // Given
        val sut = PojoSearchResultsPresenter(
                mock(),
                mock(),
                mock{on{isOnline()} doReturn true},
                mockWeatherGetter,
                Schedulers.trampoline()
        )


        sut.showSearchResults("location")
        //Then
        verify(mockWeatherGetter, times(1)).getBasic(any())
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
            on { getBasic(any()) } doReturn Observable.just(mockedWeather)
        }

        val mockedView = mock<SearchResultsPresenter.View>()
        // Given
        val sut = PojoSearchResultsPresenter(
                mockedView,
                mock(),
                mock{on{isOnline()} doReturn true},
                mockWeatherGetter,
                Schedulers.trampoline()
        )

        //When
        // No need for when we initiate inside of init

        sut.showSearchResults("location")
        //Then
        verify(mockedView, times(1)).showSearchResults(mockedWeather.days!!)
    }

}

