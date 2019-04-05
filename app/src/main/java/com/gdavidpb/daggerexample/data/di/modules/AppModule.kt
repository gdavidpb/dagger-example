package com.gdavidpb.daggerexample.data.di.modules

import android.content.Context
import android.net.ConnectivityManager
import com.gdavidpb.daggerexample.data.source.service.JsonService
import com.gdavidpb.daggerexample.data.source.service.JsonServiceDataStore
import com.gdavidpb.daggerexample.domain.repository.JsonRepository
import com.gdavidpb.daggerexample.domain.usecase.service.GetPostsUseCase
import com.gdavidpb.daggerexample.presentation.viewmodel.MainActivityViewModel
import com.gdavidpb.daggerexample.utils.URL_BASE_API
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.factoryBy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* gets are automatically resolved by Koin */
val appModule = module {

    /* Network dependencies */

    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    single {
        OkHttpClient.Builder()
                .build()
    }

    single {
        Retrofit.Builder()
                .client(get())
                .baseUrl(URL_BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    single {
        get<Retrofit>().create(JsonService::class.java)
    }

    /* Factories */

    factoryBy<JsonRepository, JsonServiceDataStore>()

    /* Use cases */

    factory<GetPostsUseCase>()

    /* View models */

    viewModel<MainActivityViewModel>()
}
