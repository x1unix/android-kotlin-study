package com.techmagic.kotlinexample.data.net

import com.techmagic.kotlinexample.data.pojo.ForecastListEntity

import retrofit2.http.GET
import rx.Observable

/**
 * Created by Roman Ursu on 5/22/17
 */

interface WeatherAPI {

    @GET("http://api.openweathermap.org/data/2.5/forecast?q=Lviv&APPID=15646a06818f61f7b8d7823ca833e1ce&mode=json&units=metric&cnt=3")
    fun loadWeather(): Observable<ForecastListEntity>
}
