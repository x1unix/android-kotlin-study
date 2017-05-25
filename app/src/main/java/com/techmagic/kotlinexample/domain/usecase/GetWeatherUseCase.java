package com.techmagic.kotlinexample.domain.usecase;

import com.techmagic.kotlinexample.data.net.WeatherAPIManager;
import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto;

import java.util.List;

import rx.Observable;

/**
 * Created by Roman Ursu on 5/22/17
 */

public class GetWeatherUseCase {
    private WeatherAPIManager weatherAPIManager = new WeatherAPIManager();

    public Observable<List<WeatherDataDto>> getWeather() {
        return weatherAPIManager.getWeather();
    }
}
