package com.gdavidpb.daggerexample.data.di.modules

import com.gdavidpb.daggerexample.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ContributorModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}