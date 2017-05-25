package com.techmagic.kotlinexample.presentation

import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto

/**
 * Created by Roman Ursu on 5/22/17
 */

interface MainView {
    fun showProgress()
    fun hideProgress()
    fun showData(weatherData: List<WeatherDataDto>?)
    fun showError(message: String?)
}
