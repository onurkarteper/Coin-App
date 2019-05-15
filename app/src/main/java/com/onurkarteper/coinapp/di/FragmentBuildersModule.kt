package com.onurkarteper.coinapp.di

import com.onurkarteper.coinapp.ui.coindetail.CoinDetailFragment
import com.onurkarteper.coinapp.ui.coinlisting.CoinListingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeCoinListingFragment(): CoinListingFragment

    @ContributesAndroidInjector
    abstract fun contributeCoinDetailFragment(): CoinDetailFragment



}
