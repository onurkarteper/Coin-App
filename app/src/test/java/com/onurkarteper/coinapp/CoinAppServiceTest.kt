package com.onurkarteper.coinapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.onurkarteper.coinapp.api.CoinService
import com.onurkarteper.coinapp.vo.Coin
import com.onurkarteper.coinapp.vo.response.BaseResponse
import org.junit.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.junit.Assert.assertThat
import retrofit2.Response

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@RunWith(JUnit4::class)
class CoinAppServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: CoinService

    private lateinit var mockWebServer: MockWebServer


    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(CoinService::class.java)
    }


    @Test
    fun getCoinsList() {
        enqueueResponse("coins.json")

        val res = (service.getCoinList(1))
        runBlocking {
            val body = res.await()
            mockWebServer.takeRequest()
            assertTrue(body.isSuccessful)
            assertThat<Response<BaseResponse<MutableList<Coin>>>>(body, notNullValue())
            assertTrue(body.body()?.data?.size == 100)
            assertTrue(body.body()?.status?.errorCode == 0)
        }
    }


    @Test
    fun getCoinInfo() {
        enqueueResponse("coin_info.json")

        val res = (service.getCoinInfo(1))
        runBlocking {
            val body = res.await()
            mockWebServer.takeRequest()
            assertTrue(body.isSuccessful)
            assertThat<Response<BaseResponse<HashMap<Int,Coin>>>>(body, notNullValue())
            assertTrue(body.body()?.data?.containsKey(1)!!)
            assertTrue(body.body()?.status?.errorCode == 0)
            assertThat<Coin>(body.body()?.data?.get(1), notNullValue())

        }
    }


    @After
    fun stopService() {
        mockWebServer.shutdown()
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
