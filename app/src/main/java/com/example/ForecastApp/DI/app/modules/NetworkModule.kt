package com.example.ForecastApp.DI.app.modules

import android.content.Context
import com.example.ForecastApp.Constants.CONNECTION_TIMEOUT
import com.example.ForecastApp.Constants.OPEN_WEATHER_API
import com.example.ForecastApp.Constants.READ_TIMEOUT

import com.example.ForecastApp.ForecastService

import java.util.concurrent.TimeUnit

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("OkHttpCacheSize")
    fun provideOkHttpCacheSize(): Int {
        return 5 * 1024 * 1024 //5 MB
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(context: Context, @Named("OkHttpCacheSize") cacheSize: Int): Cache {
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(OPEN_WEATHER_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideForecastService(retrofit: Retrofit): ForecastService {
        return retrofit.create(ForecastService::class.java)
    }
}
