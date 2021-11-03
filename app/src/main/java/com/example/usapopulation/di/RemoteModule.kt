package com.example.usapopulation.di

import com.example.usapopulation.data.remote.api.PopulationAPI
import com.example.usapopulation.utils.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    fun provideOkHTTPClientInstance(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLoggingInterceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .apply {
                baseUrl(BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
            }
            .build()

    @Provides
    fun providePopulationAPI(retrofit: Retrofit): PopulationAPI = retrofit.create(PopulationAPI::class.java)
}