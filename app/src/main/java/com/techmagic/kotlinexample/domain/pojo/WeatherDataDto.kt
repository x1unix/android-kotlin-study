package com.techmagic.kotlinexample.domain.pojo

/**
 * Created by Roman Ursu on 5/22/17
 */

data class WeatherDataDto(val temperature: Float,
                          val humidity: Int,
                          val description: String,
                          val windSpeed: Float,
                          val iconUrl: String)

