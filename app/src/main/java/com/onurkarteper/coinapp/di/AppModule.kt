package com.onurkarteper.coinapp.di

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.onurkarteper.coinapp.BuildConfig
import com.onurkarteper.coinapp.api.CoinService
import com.onurkarteper.coinapp.base.CoinApp
import com.onurkarteper.coinapp.repository.CoinRepository
import com.onurkarteper.coinapp.util.ResourceHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.HttpUrl


@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideMovieService(client: OkHttpClient): CoinService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(CoinService::class.java)
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        val httpClient = OkHttpClient.Builder()
        httpClient.addNetworkInterceptor() { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter("CMC_PRO_API_KEY", BuildConfig.API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.addNetworkInterceptor(logging)
        return httpClient.build()
    }




    @Singleton
    @Provides
    fun provideResourceHelper(application: Application): ResourceHelper {
        return ResourceHelper(application)
    }

    @Singleton
    @Provides
    fun provideCoinRepository(service: CoinService,resourceHelper: ResourceHelper): CoinRepository {
        return CoinRepository(service,resourceHelper)
    }

}
