package com.onurkarteper.coinapp.repository

import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException
import com.onurkarteper.coinapp.api.Result
import com.onurkarteper.coinapp.vo.response.BaseResponse
import com.onurkarteper.coinapp.vo.response.StatusResponse
import timber.log.Timber
import java.lang.Exception

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): Result<T> {
        return safeApiResult(call, errorMessage)
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return Result.Success(response.body()!!)
            }
            val errorJson = response.errorBody()?.string()
            errorJson?.let {
                val baseResponse = Gson().fromJson(it, BaseResponse::class.java)
                return Result.Error(
                    IOException(
                        baseResponse.status.errorMessage ?: errorMessage
                    )
                )
            }

        } catch (ex: Exception) {
            Timber.d(ex)
        }

        return Result.Error(
            IOException(
                errorMessage
            )
        )

    }
}