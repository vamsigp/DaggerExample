package com.techyourchance.dagger2course.screens.common.composition

import android.app.Activity
import com.techyourchance.dagger2course.screens.common.ScreenNavigator

class ActivityCompositionRoot(private val activity: Activity) {

    val screenNavigator by lazy {
        ScreenNavigator(activity)
    }
}