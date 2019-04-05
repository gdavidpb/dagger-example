package com.gdavidpb.daggerexample

import android.app.Application
import com.gdavidpb.daggerexample.data.di.modules.appModule
import org.koin.android.ext.android.startKoin

open class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}