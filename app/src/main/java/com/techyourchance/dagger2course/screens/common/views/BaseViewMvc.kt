package com.techyourchance.dagger2course.screens.common.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

open class BaseViewMvc<LISTENER_TYPE>(
        layoutInflater: LayoutInflater,
        parent: ViewGroup?,
        @LayoutRes private val layoutId: Int
) {

    protected val listeners = mutableSetOf<LISTENER_TYPE>()
    val rootView: View = layoutInflater.inflate(layoutId, parent, false)
    protected val context: Context get() = rootView.context

    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }

    fun <T : View?> findViewById(@IdRes id: Int): T {
        return rootView.findViewById<T>(id)
    }

}

