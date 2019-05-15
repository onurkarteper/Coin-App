package com.onurkarteper.coinapp.vo.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @SerializedName("status")
    val status: StatusResponse,
    @SerializedName("data")
    val data: T?

)