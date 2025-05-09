package com.example.isa

import android.app.Application
import android.content.Context
import com.bumptech.glide.BuildConfig
import com.example.isa.di.global.ComponentHolder
import timber.log.Timber


class App : Application() {

    private lateinit var componentHolder: ComponentHolder

    companion object {
        fun getApp(context: Context?): App {
            return context?.applicationContext as App
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        componentHolder = ComponentHolder(this)
        componentHolder.inject()
    }

    fun getComponentsHolder(): ComponentHolder = componentHolder

}