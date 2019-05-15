package com.onurkarteper.coinapp.ui.coinlisting

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.onurkarteper.coinapp.R
import com.onurkarteper.coinapp.base.BaseFragment
import com.onurkarteper.coinapp.databinding.FragmentCoinListingBinding
import com.onurkarteper.coinapp.util.PaginationScrollListener
import com.onurkarteper.coinapp.vo.Coin
import timber.log.Timber
import javax.inject.Inject

public const val KEY_COIN = "coin"

class CoinListingFragment : BaseFragment<FragmentCoinListingBinding>(R.layout.fragment_coin_listing), CoinItemCallback {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var adapter: CoinAdapter

    private lateinit var viewModel: CoinListingViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CoinListingViewModel::class.java)

        if (viewModel.data.value == null)
            viewModel.fetchCoinList()
        initRecycler()
        observe()
    }

    private fun initRecycler() {
        binding?.let { fragmentCoinListingBinding ->
            val layoutManager = fragmentCoinListingBinding.recyclerCoin.layoutManager as LinearLayoutManager
            fragmentCoinListingBinding.recyclerCoin.addOnScrollListener(object :
                PaginationScrollListener(layoutManager) {
                override fun loadMoreItems() {
                    viewModel.fetchNextPage()
                }

                override fun isLoading() = viewModel.loading.value!!

                override fun isLastPage() = viewModel.isLastPage
            })
            adapter = CoinAdapter(viewModel.data.value!!, this)
            fragmentCoinListingBinding.recyclerCoin.adapter = adapter
        }
    }

    private fun observe() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            Timber.d("Coin data changed")
            adapter.notifyDataSetChanged()
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding?.loading = it
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            showErrorDialog(it)
        })
    }

    override fun onCoinClick(coin: Coin) {
        view?.let {
            val bundle = Bundle()
            bundle.putParcelable(KEY_COIN, coin)
            Navigation.findNavController(it).navigate(R.id.action_coinListingFragment_to_coinDetailFragment, bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.onDestroy()
        viewModel.data.removeObservers(this)
        viewModel.loading.removeObservers(this)
        viewModel.errorMessage.removeObservers(this)
    }

}