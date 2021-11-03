package com.example.usapopulation.di

import android.content.Context
import com.example.usapopulation.MainActivity
import com.example.usapopulation.ui.PopulationFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RemoteModule::class, DataModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: PopulationFragment)
}