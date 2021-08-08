package com.techyourchance.dagger2course.screens.common.dependencyinjection

import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreenNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import java.lang.reflect.Field

class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return clientClass.declaredFields
    }


    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotations = field.annotations
        for (annotation in fieldAnnotations) {
            if (annotation is Service) {
                return true
            }
        }
        return false
    }

    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClass(field.type))
        field.isAccessible = isAccessibleInitially
    }

    private fun getServiceForClass(type: Class<*>): Any {
        when (type) {
            DialogNavigator::class.java -> {
                return compositionRoot.dialogNavigator
            }
            ScreenNavigator::class.java -> {
                return compositionRoot.screenNavigator
            }
            FetchQuestionsUseCase::class.java -> {
                return compositionRoot.fetchQuestionsUseCase
            }
            ViewMvcFactory::class.java -> {
                return compositionRoot.viewMvcFactory
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }
}