package com.techmagic.kotlinexample.data.pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by Roman Ursu on 5/22/17
 */

data class MainEntity(
        @SerializedName("temp") val temperature: Float,
        @SerializedName("temp_min") val temperatureMin: Float,
        @SerializedName("temp_max") val temperatureMax: Float,
        @SerializedName("pressure") val temperaturePressure: Float,
        @SerializedName("sea_level") val seaLevel: Float,
        @SerializedName("grnd_level") val groundLevel: Float,
        @SerializedName("humidity") val humidity: Int,
        @SerializedName("temp_kf") val tempKf: Float)