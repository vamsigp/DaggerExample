package com.techyourchance.dagger2course.screens.common.dependencyinjection

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListFragment

class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(fragment: QuestionsListFragment) {
        fragment.fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
        fragment.dialogNavigator = compositionRoot.dialogNavigator
        fragment.screenNavigator = compositionRoot.screenNavigator
        fragment.viewMvcFactory = compositionRoot.viewMvcFactory
    }

    fun inject(questionDetailsActivity: QuestionDetailsActivity) {
        questionDetailsActivity.fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
        questionDetailsActivity.dialogNavigator = compositionRoot.dialogNavigator
        questionDetailsActivity.screenNavigator = compositionRoot.screenNavigator
        questionDetailsActivity.viewMvcFactory = compositionRoot.viewMvcFactory
    }


}