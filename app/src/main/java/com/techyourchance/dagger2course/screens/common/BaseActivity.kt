package com.techyourchance.dagger2course.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.screens.common.composition.AppCompositionRoot

open class BaseActivity : AppCompatActivity() {

    val compositionRoot get() = (application as MyApplication).appCompositionRoot

}