package com.example.usapopulation.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.usapopulation.PopulationApp
import com.example.usapopulation.di.AppComponent

fun Fragment.toast(message: Any, length: Int = Toast.LENGTH_SHORT) = context?.let { context ->
    Toast.makeText(context, message.toString(), length).show()
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is PopulationApp -> appComponent
        else -> applicationContext.appComponent
    }