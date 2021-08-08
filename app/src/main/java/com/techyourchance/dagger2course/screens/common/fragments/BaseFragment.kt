package com.techyourchance.dagger2course.screens.common.fragments

import androidx.fragment.app.Fragment
import com.techyourchance.dagger2course.screens.common.BaseActivity
import com.techyourchance.dagger2course.screens.common.dependencyinjection.Injector
import com.techyourchance.dagger2course.screens.common.dependencyinjection.PresentationCompositionRoot

open class BaseFragment : Fragment() {

    private val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }

    protected val injector = Injector(compositionRoot)
}