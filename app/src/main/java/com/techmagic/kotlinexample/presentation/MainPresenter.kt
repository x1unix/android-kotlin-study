package com.techmagic.kotlinexample.presentation

import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto
import com.techmagic.kotlinexample.domain.usecase.GetWeatherUseCase
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Roman Ursu on 5/22/17
 */

class MainPresenter {

    private val getWeatherUsecase: GetWeatherUseCase = GetWeatherUseCase()
    private var view: MainView? = null

    fun loadData() {
        getWeatherUsecase
                .weather
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<List<WeatherDataDto>>() {
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
