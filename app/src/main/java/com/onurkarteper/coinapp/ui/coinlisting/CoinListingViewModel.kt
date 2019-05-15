package com.onurkarteper.coinapp.ui.coinlisting

import androidx.lifecycle.MutableLiveData
import com.onurkarteper.coinapp.api.Result
import com.onurkarteper.coinapp.repository.CoinRepository
import com.onurkarteper.coinapp.viewmodel.BaseViewModel
import com.onurkarteper.coinapp.vo.Coin
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinListingViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : BaseViewModel() {

    var isLastPage: Boolean = false
    private var page: Int = 0
    private var start: Int = 1
    val data = MutableLiveData<MutableList<Coin>>()


    fun fetchCoinList() {
        if (data.value == null) {
            data.value = ArrayList()
        }
        scope.launch {
            loading.postValue(true)
            when (val response = coinRepository.getCoinList(start)) {
                is Result.Success -> {
                    val body = response.data
                    if (body.status.isSuccess()) {
                        body.data?.let {
                            isLastPage = it.size < 50
                            data.value?.addAll(it)
                            data.postValue(data.value)
                        }
                    } else {
                        body.status.errorMessage?.let {
                            errorMessage.postValue(it)
                        }
                    }
                }
                is Result.Error -> {
                    errorMessage.postValue(response.exception.message)
                }
            }
            loading.postValue(false)
        }
    }

    fun fetchNextPage() {
        page++
        start = page * 50 + 1
        fetchCoinList()
    }

}
