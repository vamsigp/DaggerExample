package com.techyourchance.dagger2course.screens.questionslist

import android.os.Bundle
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.screens.common.BaseActivity

class QuestionsListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_list_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_content, QuestionsListFragment()).commit()
        }
    }
}