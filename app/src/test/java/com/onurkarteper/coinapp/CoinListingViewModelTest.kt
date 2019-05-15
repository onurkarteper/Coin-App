package com.onurkarteper.coinapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.onurkarteper.coinapp.api.CoinService
import com.onurkarteper.coinapp.repository.CoinRepository
import com.onurkarteper.coinapp.ui.coinlisting.CoinListingViewModel
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class CoinListingViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var coinRepository = Mockito.mock(CoinRepository::class.java)
    private var coinListingViewModel = CoinListingViewModel(coinRepository)

    @Test
    fun testNull(){
        MatcherAssert.assertThat(coinListingViewModel.data,notNullValue())
        MatcherAssert.assertThat(coinListingViewModel.errorMessage,notNullValue())
        MatcherAssert.assertThat(coinListingViewModel.loading,notNullValue())
    }
}