package com.gdavidpb.daggerexample

import com.gdavidpb.daggerexample.data.di.modules.ContributorModule
import com.gdavidpb.daggerexample.data.di.modules.NetworkModule
import com.gdavidpb.daggerexample.data.di.modules.ViewModelModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

open class DaggerApp : DaggerApplication() {
    @Singleton
    @dagger.Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ViewModelModule::class,
            ContributorModule::class,
            NetworkModule::class
        ]
    )
    interface Component : AndroidInjector<DaggerApp> {
        @dagger.Component.Builder
        abstract class Builder : AndroidInjector.Builder<DaggerApp>()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerDaggerApp_Component.builder().create(this)
    }
}