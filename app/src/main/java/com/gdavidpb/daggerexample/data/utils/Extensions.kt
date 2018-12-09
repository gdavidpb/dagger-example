package com.gdavidpb.daggerexample.data.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.net.ConnectivityManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/* Context */

fun ConnectivityManager.isNetworkAvailable() = activeNetworkInfo?.isConnected == true

/* Live data */

fun <T, L : LiveData<T>> FragmentActivity.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

/* Utils */

inline fun <T> T?.notNull(exec: (T) -> Unit): T? = this?.also { exec(this) }