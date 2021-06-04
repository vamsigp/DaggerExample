package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class FetchQuestionsUseCase(private val retrofit: Retrofit) {

    private val stackoverflowApi = retrofit.create(StackoverflowApi::class.java)

    sealed class Result {
        class SUCCESS(val questions: List<Question>) : Result()
        object FAIL : Result()
    }

    sealed class ResultDetail {
        class SUCCESS(val questionBody: String) : ResultDetail()
        object FAIL : ResultDetail()
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

    suspend fun fetchQuestionDetails(questionId: String?): ResultDetail {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    val questionBody = response.body()!!.question.body
                    return@withContext ResultDetail.SUCCESS(questionBody)
                } else {
                    return@withContext ResultDetail.FAIL
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext ResultDetail.FAIL
                } else {
                    throw t
                }
            }
        }
    }
}