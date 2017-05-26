package com.techmagic.kotlinexample.domain.usecase

import com.techmagic.kotlinexample.data.net.WeatherAPIManager
import com.techmagic.kotlinexample.domain.Units
import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto
import rx.Observable
import rx.Scheduler
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Roman Ursu on 5/22/17
 */

class GetWeatherUseCase @Inject constructor(val weatherAPIManager: WeatherAPIManager,
                                            @Named("Thread") threadScheduler: Scheduler,
                                            @Named("PostExecution") postExecutionScheduler: Scheduler)
    : BaseUseCase<Unit, List<WeatherDataDto>, WeatherAPIManager>(weatherAPIManager, threadScheduler, postExecutionScheduler) {

    private val units = Units.METRIC
    private val count = 10

    override fun buildObservable(requestData: Unit?): Observable<List<WeatherDataDto>> {
        return weatherAPIManager.loadWeather(units, count)
    }
}
