package com.techmagic.kotlinexample.data.pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by Roman Ursu on 5/23/17
 */

class WeatherEntity(@SerializedName("id") var id: Int,
                    @SerializedName("main") var main: String,
                    @SerializedName("description") var description: String,
                    @SerializedName("icon") var icon: String)