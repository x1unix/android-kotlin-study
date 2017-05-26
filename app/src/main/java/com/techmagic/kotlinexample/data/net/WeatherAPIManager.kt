package com.techmagic.kotlinexample.data.net

import com.techmagic.kotlinexample.data.DataMapper
import com.techmagic.kotlinexample.data.manager.Manager
import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto
import rx.Observable

/**
 * Created by Roman Ursu on 5/22/17
 */

class WeatherAPIManager : Manager {
    val weatherAPI: WeatherAPI
    val dataMapper: DataMapper

    constructor(weatherAPI: WeatherAPI, dataMapper: DataMapper) {
        this.weatherAPI = weatherAPI
        this.dataMapper = dataMapper
    }

    fun loadWeather(): Observable<List<WeatherDataDto>> {
        return weatherAPI.loadWeather().map { forecastListEntity -> dataMapper.map(forecastListEntity) }
    }
}
