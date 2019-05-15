
package com.onurkarteper.coinapp.di
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onurkarteper.coinapp.ui.coindetail.CoinDetailViewModel
import com.onurkarteper.coinapp.ui.coinlisting.CoinListingViewModel
import com.onurkarteper.coinapp.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CoinListingViewModel::class)
    abstract fun bindCoinListingViewModel(coinListingViewModel: CoinListingViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailViewModel::class)
    abstract fun bindCoinDetailViewModel(coinDetailViewModel: CoinDetailViewModel): ViewModel



    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
