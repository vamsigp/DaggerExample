package com.techyourchance.dagger2course.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.questions.Question
import com.techyourchance.dagger2course.screens.common.ScreenNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.common.fragments.BaseFragment
import kotlinx.coroutines.*

class QuestionsListFragment : BaseFragment(), QuestionsListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase
    private lateinit var dialogNavigator: DialogNavigator
    private lateinit var screenNavigator: ScreenNavigator

    private lateinit var viewMvc: QuestionsListViewMvc

    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
        dialogNavigator = compositionRoot.dialogNavigator
        screenNavigator = compositionRoot.screenNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewMvc = compositionRoot.viewMvcFactory.createNewQuestionListMvc(container)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        if (!isDataLoaded) {
            fetchQuestions()
        }
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when (val result = fetchQuestionsUseCase.fetchLatestQuestions()) {
                    is FetchQuestionsUseCase.Result.SUCCESS -> {
                        viewMvc.bindQuestions(result.questions)
                        isDataLoaded = true
                    }
                    is FetchQuestionsUseCase.Result.FAIL -> {
                        onFetchFailed()
                    }
                }
            } finally {
                viewMvc.hideProgressIndication()
            }

        }
    }

    private fun onFetchFailed() {
        dialogNavigator.showServerErrorDialog()
    }

    override fun onRefreshClicked() {
        fetchQuestions()
    }

    override fun onQuestionClick(clickedQuestion: Question) {
        screenNavigator.toQuestionDetails(clickedQuestion.id)
    }
}