package com.techyourchance.dagger2course.screens.common.composition

import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    private val fragmentManager get() = activityCompositionRoot.fragmentManager
    private val stackoverflowApi get() = activityCompositionRoot.stackoverflowApi

    val screenNavigator get() = activityCompositionRoot.screenNavigator
    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)
    val dialogNavigator get() = DialogNavigator(fragmentManager)
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
}