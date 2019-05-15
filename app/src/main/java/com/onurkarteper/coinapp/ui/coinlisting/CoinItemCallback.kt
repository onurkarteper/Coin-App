package com.onurkarteper.coinapp.ui.coinlisting

import com.onurkarteper.coinapp.vo.Coin

interface CoinItemCallback{

    fun  onCoinClick(coin : Coin)
}