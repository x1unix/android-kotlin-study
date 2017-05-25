package com.techmagic.kotlinexample.data.pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by Roman Ursu on 5/22/17
 */

data class ForecastEntity(
        @SerializedName("dt") var dt: Long,
        @SerializedName("dt_txt") var date: String,
        @SerializedName("main") var main: MainEntity,
        @SerializedName("weather") var weather: List<WeatherEntity>,
        @SerializedName("wind") var wind: WindEntity)