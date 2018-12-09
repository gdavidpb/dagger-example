package com.gdavidpb.daggerexample.data.di.modules

import android.content.Context
import android.net.ConnectivityManager
import com.gdavidpb.daggerexample.DaggerApp
import com.gdavidpb.daggerexample.R
import com.gdavidpb.daggerexample.data.source.service.JsonService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(app: DaggerApp): ConnectivityManager {
        return app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(app: DaggerApp, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(app.getString(R.string.baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): JsonService {
        return retrofit.create(JsonService::class.java)
    }
}