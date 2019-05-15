package com.onurkarteper.coinapp.ui.coindetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.onurkarteper.coinapp.R
import com.onurkarteper.coinapp.base.BaseFragment
import com.onurkarteper.coinapp.databinding.FragmentCoinDetailBinding
import com.onurkarteper.coinapp.ui.coinlisting.KEY_COIN
import com.onurkarteper.coinapp.vo.Coin
import javax.inject.Inject

class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>(R.layout.fragment_coin_detail) {


    var coin: Coin? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CoinDetailViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CoinDetailViewModel::class.java)
        readArguments()
        coin?.let { mCoin ->
            viewModel.fetchCoinData(mCoin)
        }
        observe()
    }

    private fun readArguments() {
        arguments?.let {
            coin = it.getParcelable(KEY_COIN)
        }
    }

    private fun observe() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            it?.let { coinDetail ->
                binding?.data = coinDetail

            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding?.loading = it
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            showErrorDialog(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.data.removeObservers(this)
        viewModel.loading.removeObservers(this)
        viewModel.errorMessage.removeObservers(this)
    }
}
