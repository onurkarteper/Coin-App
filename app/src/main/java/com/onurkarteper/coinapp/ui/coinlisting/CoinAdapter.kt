package com.onurkarteper.coinapp.ui.coinlisting

import com.onurkarteper.coinapp.R
import com.onurkarteper.coinapp.binding.DataBoundAdapter
import com.onurkarteper.coinapp.binding.DataBoundViewHolder
import com.onurkarteper.coinapp.vo.Coin

class CoinAdapter(private val items: MutableList<Coin>, private var callback: CoinItemCallback?) :
    DataBoundAdapter<com.onurkarteper.coinapp.databinding.ItemCoinBinding>(R.layout.item_coin) {
    override fun bindItem(
        holder: DataBoundViewHolder<com.onurkarteper.coinapp.databinding.ItemCoinBinding>,
        position: Int,
        payloads: List<Any>
    ) {
        holder.binding.data = items[position]
        holder.binding.callback = callback
    }

    override fun getItemCount() = items.size


    fun onDestroy() {
        callback = null
    }

}