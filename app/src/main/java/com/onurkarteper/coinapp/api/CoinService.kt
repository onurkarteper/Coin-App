@file:Suppress("SpellCheckingInspection")

package com.onurkarteper.coinapp.api

import com.onurkarteper.coinapp.vo.Coin
import com.onurkarteper.coinapp.vo.response.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API access points
 */
interface CoinService {

    @GET("cryptocurrency/map")
    fun getCoinList(@Query("start") start: Int, @Query("limit") limit: String = "50"): Deferred<Response<BaseResponse<MutableList<Coin>>>>


    @GET("cryptocurrency/info")
    fun getCoinInfo(@Query("id") id: Int): Deferred<Response<BaseResponse<HashMap<Int, Coin>>>>
}