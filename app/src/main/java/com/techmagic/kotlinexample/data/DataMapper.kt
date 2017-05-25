package com.techmagic.kotlinexample.data

import com.techmagic.kotlinexample.data.pojo.ForecastEntity
import com.techmagic.kotlinexample.data.pojo.ForecastListEntity
import com.techmagic.kotlinexample.data.pojo.WeatherEntity
import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto

/**
 * Created by Roman Ursu on 5/22/17
 */

class DataMapper {

    fun map(forecastEntity: ForecastEntity): WeatherDataDto {
        val weatherEntity: WeatherEntity = forecastEntity.weather[0]

        return WeatherDataDto(forecastEntity.main.temperature,
                forecastEntity.main.humidity,
                weatherEntity.description,
                forecastEntity.wind.speed,
                "http://openweathermap.org/img/w/${weatherEntity.icon}.png")
    }

    fun map(forecastListEntity: ForecastListEntity): List<WeatherDataDto> {
        val weatherDataList: MutableList<WeatherDataDto> = mutableListOf()
        forecastListEntity.items.mapTo(weatherDataList) { map(it) }

        return weatherDataList.toList()
    }
}
