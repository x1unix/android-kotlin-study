package com.techmagic.kotlinexample.data.net

import com.techmagic.kotlinexample.data.pojo.ForecastListEntity

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Roman Ursu on 5/22/17
 */

interface WeatherAPI {

    @GET("data/2.5/forecast?q=Lviv&APPID=15646a06818f61f7b8d7823ca833e1ce&mode=json")
    fun loadWeather(@Query("cnt") count: Int, @Query("units") units: String): Observable<ForecastListEntity>
}
