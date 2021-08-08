package com.techyourchance.dagger2course.screens.common.composition

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreenNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory

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