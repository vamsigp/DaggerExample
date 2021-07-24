package com.techyourchance.dagger2course.screens.common.viewmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailViewMvc
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListViewMvc

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {

    fun createNewQuestionListMvc(parent: ViewGroup?): QuestionsListViewMvc {
        return QuestionsListViewMvc(layoutInflater, parent)
    }

    fun createNewQuestionDetailsMvc(parent: ViewGroup?): QuestionDetailViewMvc {
        return QuestionDetailViewMvc(layoutInflater, parent)
    }
}