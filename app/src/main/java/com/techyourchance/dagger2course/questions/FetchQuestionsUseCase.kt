package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchQuestionsUseCase {

    private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val stackoverflowApi = retrofit.create(StackoverflowApi::class.java)

    sealed class Result {
        class SUCCESS(val questions: List<Question>) : Result()
        object FAIL : Result()
    }

    suspend fun fetchLatestQuestions(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.lastActiveQuestions(20)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.SUCCESS(response.body()!!.questions)
                } else {
                    return@withContext Result.FAIL
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.FAIL
                } else {
                    throw t
                }
            }
        }
    }
}