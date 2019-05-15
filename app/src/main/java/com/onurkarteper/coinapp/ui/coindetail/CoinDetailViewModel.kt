package com.onurkarteper.coinapp.ui.coindetail

import androidx.lifecycle.MutableLiveData
import com.onurkarteper.coinapp.api.Result
import com.onurkarteper.coinapp.repository.CoinRepository
import com.onurkarteper.coinapp.viewmodel.BaseViewModel
import com.onurkarteper.coinapp.vo.Coin
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : BaseViewModel() {

    val data = MutableLiveData<Coin>()

    fun fetchCoinData(coin: Coin) {
        scope.launch {
            coin.id?.let { id ->
                loading.postValue(true)
                when (val response = coinRepository.getCoinDetail(id)) {
                    is Result.Error -> {
                        errorMessage.postValue(response.exception.message)
                    }
                    is Result.Success -> {
                        if (response.data.status.isSuccess()) {
                            data.postValue(response.data.data?.get(id))
                        } else {
                            errorMessage.postValue(response.data.status.errorMessage)
                        }

                    }
                }
                loading.postValue(false)
            }
        }
    }

}
