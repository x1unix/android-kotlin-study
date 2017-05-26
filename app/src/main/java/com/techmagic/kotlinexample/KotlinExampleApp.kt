package com.techmagic.kotlinexample

import android.app.Application
import com.techmagic.kotlinexample.di.components.AppComponent
import com.techmagic.kotlinexample.di.components.DaggerAppComponent
import com.techmagic.kotlinexample.di.modules.AppModule
import com.techmagic.kotlinexample.di.modules.NetModule
import com.techmagic.kotlinexample.di.modules.WeatherModule

/**
 * Created by Roman Ursu on 5/25/17
 */

class KotlinExampleApp : Application() {

    private var appComponent: AppComponent? = null

    companion object {
        var app: KotlinExampleApp? = null
    }

    override fun onCreate() {
        super.onCreate()

        app = this@KotlinExampleApp

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .weatherModule(WeatherModule())
                .build()
    }

    fun getAppComponent(): AppComponent = appComponent!!
}
