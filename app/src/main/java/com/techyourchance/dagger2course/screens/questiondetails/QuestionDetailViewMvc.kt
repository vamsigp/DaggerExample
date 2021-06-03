package com.techyourchance.dagger2course.screens.questiondetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import com.techyourchance.dagger2course.screens.common.views.BaseViewMvc

class QuestionDetailViewMvc(
        layoutInflater: LayoutInflater, parent: ViewGroup?
) : BaseViewMvc<QuestionDetailViewMvc.Listener>(
        layoutInflater,
        parent,
        R.layout.layout_question_details) {

    interface Listener {
        fun onBackKeyPressed()
    }

    private val toolbar: MyToolbar = findViewById(R.id.toolbar)
    private val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swipeRefresh)
    private val txtQuestionBody: TextView = findViewById(R.id.txt_question_body)

    init {

        // init toolbar
        toolbar.setNavigateUpListener {
            for (listener in listeners) {
                listener.onBackKeyPressed()
            }
        }
        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh.isEnabled = false
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }

    fun bindQuestionBody(fromHtml: String?) {
        txtQuestionBody.text = fromHtml
    }
}