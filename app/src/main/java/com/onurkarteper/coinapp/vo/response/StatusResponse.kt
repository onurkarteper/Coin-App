package com.onurkarteper.coinapp.vo.response

import com.google.gson.annotations.SerializedName


data class StatusResponse(
    @SerializedName("credit_count")
    val creditCount: Int?,
    @SerializedName("elapsed")
    val elapsed: Int?,
    @SerializedName("error_code")
    val errorCode: Int?,
    @SerializedName("error_message")
    val errorMessage: String?,
    @SerializedName("timestamp")
    val timestamp: String?
) {
    fun isSuccess(): Boolean {
        errorCode?.let {
            return it == 0
        }
        return false
    }
}