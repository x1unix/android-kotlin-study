package com.techmagic.kotlinexample.data.net;

import com.google.gson.Gson;
import com.techmagic.kotlinexample.data.DataMapper;
import com.techmagic.kotlinexample.data.pojo.ForecastListEntity;
import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Roman Ursu on 5/22/17
 */

public class WeatherAPIManager {
    private WeatherAPI weatherAPI;
    private DataMapper dataMapper = new DataMapper();

    public WeatherAPIManager() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);


        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClientBuilder.build());

        this.weatherAPI = retrofitBuilder.build().create(WeatherAPI.class);
    }

    public Observable<List<WeatherDataDto>> getWeather() {
        return weatherAPI.loadWeather().map(new Func1<ForecastListEntity, List<WeatherDataDto>>() {

            @Override
            public List<WeatherDataDto> call(ForecastListEntity forecastListEntity) {
                return dataMapper.map(forecastListEntity);
            }
        });
    }
}
