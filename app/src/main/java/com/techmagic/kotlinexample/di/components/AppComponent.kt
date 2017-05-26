package com.techmagic.kotlinexample.di.components

import com.techmagic.kotlinexample.di.modules.AppModule
import com.techmagic.kotlinexample.di.modules.NetModule
import com.techmagic.kotlinexample.di.modules.WeatherModule
import com.techmagic.kotlinexample.presentation.MainActivity

import javax.inject.Singleton

import dagger.Component

/**
 * Created by Roman Ursu on 5/25/17
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class, WeatherModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
