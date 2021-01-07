package com.example.ForecastApp.weatherSearchScreen

import com.example.ForecastApp.model.City
import com.example.ForecastApp.model.weather.Forecast
import com.example.ForecastApp.useCase.GetWeatherForecast
import com.example.ForecastApp.weatherSearchScreen.ui.PojoWeatherSearchPresenter
import com.example.ForecastApp.weatherSearchScreen.ui.WeatherSearchPresenter
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

internal class WeatherSearchFragmentTest{


    @Test
    fun testPresenterCallsUseCase() {

        val mockWeatherGetter = mock<GetWeatherForecast> {
            on { getRecentForecasts() } doReturn Observable.never()
        }

        // Given
        PojoWeatherSearchPresenter(
                mock(),
                mock(),
                mockWeatherGetter
        )

        //When
        // No need for when we initiate inside of init

        //Then
        verify(mockWeatherGetter, times(1)).getRecentForecasts()
    }


//
//    @Test
//    fun testCallToGetRecentForecastsReturnsTheMockedResponse() {
//
//        val mockRecentForecasts = listOf(Forecast().apply { city = City().apply{ name = " London"  }}, Forecast().apply { city = City().apply{ name = " Paris"  }})
//
//        val mockWeatherGetter = mock<GetWeatherForecast> {
//            on { getRecentForecasts() } doReturn Observable.just(mockRecentForecasts)
//        }
//
//        val mockView = mock<WeatherSearchPresenter.View>()
//        // Given
//        PojoWeatherSearchPresenter(
//                mockView,
//                mock(),
//                mockWeatherGetter
//        )
//
//        //When
//        // No need for when we initiate inside of init
//
//        //Then
//        verify(mockView, times(1)).showRecentSavedSearches(mockRecentForecasts)
//    }



}