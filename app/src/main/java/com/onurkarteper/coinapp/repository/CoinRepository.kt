package com.onurkarteper.coinapp.repository

import com.onurkarteper.coinapp.R
import com.onurkarteper.coinapp.api.CoinService
import com.onurkarteper.coinapp.api.Result
import com.onurkarteper.coinapp.util.OpenForTesting
import com.onurkarteper.coinapp.util.ResourceHelper
import com.onurkarteper.coinapp.vo.Coin
import com.onurkarteper.coinapp.vo.response.BaseResponse
import javax.inject.Inject

@OpenForTesting
class CoinRepository @Inject constructor(
    val api: CoinService,
    private val resourceHelper: ResourceHelper
) : BaseRepository() {


    suspend fun getCoinList(start: Int): Result<BaseResponse<MutableList<Coin>>> {

        return safeApiCall(
            call = {
                api.getCoinList(start).await()
            },
            errorMessage = resourceHelper.getString(R.string.txt_default_error_message)
        )
    }

    suspend fun getCoinDetail(id: Int): Result<BaseResponse<HashMap<Int, Coin>>> {
        return safeApiCall(
            call = {
                api.getCoinInfo(id).await()
            },
            errorMessage = resourceHelper.getString(R.string.txt_default_error_message)
        )
    }
}