package com.example.usapopulation

import android.app.Application
import com.example.usapopulation.di.AppComponent
import com.example.usapopulation.di.DaggerAppComponent

class PopulationApp: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create(applicationContext)
    }
}