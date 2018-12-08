package com.gdavidpb.daggerexample.domain.model

import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class WrappedLiveData<T> : MutableLiveData<Data<T>>() {
    private var lastOneJob: Job? = null

    fun postException(exception: Throwable) {
        postValue(
            Data(
                dataState = DataState.ERROR,
                exception = exception
            )
        )
    }

    fun postSuccess(value: T) {
        postValue(
            Data(
                dataState = DataState.SUCCESS,
                data = value
            )
        )
    }

    fun postLoading() {
        postValue(Data(dataState = DataState.LOADING))
    }

    fun assume(task: suspend () -> Unit) {
        lastOneJob?.cancel()

        lastOneJob = GlobalScope.launch {
            task()
        }
    }
}