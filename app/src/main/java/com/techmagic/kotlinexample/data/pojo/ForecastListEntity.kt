package com.techmagic.kotlinexample.data.pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by Roman Ursu on 5/23/17
 */

data class ForecastListEntity(
        @SerializedName("cod") var cod: String,
        @SerializedName("cnt") var count: Int,
        @SerializedName("list") var items: List<ForecastEntity>)
