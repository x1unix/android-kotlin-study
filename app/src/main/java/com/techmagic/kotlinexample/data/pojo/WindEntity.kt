package com.techmagic.kotlinexample.data.pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by Roman Ursu on 5/23/17
 */
data class WindEntity(@SerializedName("speed") val speed: Float,
                      @SerializedName("deg") val deg: Float)