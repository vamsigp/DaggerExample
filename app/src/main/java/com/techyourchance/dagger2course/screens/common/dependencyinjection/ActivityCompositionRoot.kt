package com.techyourchance.dagger2course.screens.common.dependencyinjection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.common.ScreenNavigator

class ActivityCompositionRoot(
    val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {
    val screenNavigator by lazy {
        ScreenNavigator(activity)
    }

    val application get() = appCompositionRoot.application

    val fragmentManager get() = activity.supportFragmentManager

    val stackoverflowApi get() = appCompositionRoot.stackoverflowApi

    val layoutInflater: LayoutInflater get() = LayoutInflater.from(activity)
}