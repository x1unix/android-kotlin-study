package com.techmagic.kotlinexample.di.modules

import android.content.Context

import com.techmagic.kotlinexample.KotlinExampleApp

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Roman Ursu on 5/25/17
 */

const val WORKER_THREAD: String = "Thread"
const val POST_EXECUTION_THREAD: String = "PostExecution"

@Module
class AppModule(private val app: KotlinExampleApp) {


    @Provides
    @Singleton
    fun provideContext(): Context {
        return app.applicationContext
    }

    @Provides
    @Named(WORKER_THREAD)
    @Singleton
    fun provideThreadScheduler(): Scheduler {
        return Schedulers.io()
    }


    @Provides
    @Named(POST_EXECUTION_THREAD)
    @Singleton
    fun providePostExecutionScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
