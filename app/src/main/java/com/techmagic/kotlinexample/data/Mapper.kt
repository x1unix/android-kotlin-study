package com.techmagic.kotlinexample.data

import com.techmagic.kotlinexample.data.net.NetConstants
import com.techmagic.kotlinexample.data.pojo.ForecastEntity
import com.techmagic.kotlinexample.data.pojo.ForecastListEntity
import com.techmagic.kotlinexample.data.pojo.WeatherEntity
import com.techmagic.kotlinexample.domain.Units
import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto

/**
 * Created by Roman Ursu on 5/26/17
 */

class Mapper {
    companion object {

        fun map(units: Units): String =
                when (units) {
                    Units.METRIC -> "metric"
                    Units.IMPERIAL -> "imperial"
                }

        fun map(forecastEntity: ForecastEntity): WeatherDataDto {
            val weatherEntity: WeatherEntity = forecastEntity.weather[0]

            return WeatherDataDto(forecastEntity.main.temperature,
                    forecastEntity.main.humidity,
                    weatherEntity.description,
                    forecastEntity.wind.speed,
                    NetConstants.BASE_IMAGE_URL + "${weatherEntity.icon}.png")
        }

        fun map(forecastListEntity: ForecastListEntity): List<WeatherDataDto> {
            val weatherDataList: MutableList<WeatherDataDto> = mutableListOf()
            forecastListEntity.items.mapTo(weatherDataList) { map(it) }

            return weatherDataList.toList()
        }

    }
}