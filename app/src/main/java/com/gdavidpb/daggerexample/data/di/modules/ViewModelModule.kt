package com.gdavidpb.daggerexample.data.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gdavidpb.daggerexample.data.di.ViewModelFactory
import com.gdavidpb.daggerexample.data.di.ViewModelKey
import com.gdavidpb.daggerexample.presentation.viewmodel.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun mainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}