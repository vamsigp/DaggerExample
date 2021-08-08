package com.techyourchance.dagger2course

import android.app.Application
import com.techyourchance.dagger2course.screens.common.dependencyinjection.AppCompositionRoot

class MyApplication : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot(this)
        super.onCreate()
    }

}