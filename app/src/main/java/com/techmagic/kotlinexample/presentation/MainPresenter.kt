package com.techmagic.kotlinexample.presentation

import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto
import com.techmagic.kotlinexample.domain.usecase.GetWeatherUseCase
import rx.Subscriber
import javax.inject.Inject

/**
 * Created by Roman Ursu on 5/22/17
 */

class MainPresenter @Inject constructor(getWeatherUseCase: GetWeatherUseCase) {

    private var view: MainView? = null
    private var getWeatherUseCase: GetWeatherUseCase = getWeatherUseCase

    fun loadData() {
        getWeatherUseCase.execute(null, object : Subscriber<List<WeatherDataDto>>() {
            override fun onError(e: Throwable?) {
                view?.showError(e?.message)
            }

            override fun onCompleted() {}

            override fun onNext(weatherData: List<WeatherDataDto>?) {
                if (weatherData != null) {
                    view?.hideProgress()
                    view?.showData(weatherData)
                }
            }
        })
    }

    fun setView(view: MainView?) {
        this.view = view
    }
}
