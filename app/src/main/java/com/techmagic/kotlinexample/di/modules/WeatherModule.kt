package com.techmagic.kotlinexample.di.modules

import com.techmagic.kotlinexample.data.DataMapper
import com.techmagic.kotlinexample.data.net.WeatherAPI
import com.techmagic.kotlinexample.data.net.WeatherAPIManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Roman Ursu on 5/26/17
 */

@Module
class WeatherModule() {

    @Singleton
    @Provides
    fun provideWeatherManager(weatherAPI: WeatherAPI, dataMapper: DataMapper): WeatherAPIManager = WeatherAPIManager(weatherAPI, dataMapper)

    @Singleton
    @Provides
    fun provideDataMapper(): DataMapper = DataMapper()
}