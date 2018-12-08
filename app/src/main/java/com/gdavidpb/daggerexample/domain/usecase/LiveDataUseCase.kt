package com.gdavidpb.daggerexample.domain.usecase

import com.gdavidpb.daggerexample.domain.model.WrappedLiveData
import kotlinx.coroutines.delay

abstract class LiveDataUseCase<P, T> {
    protected abstract fun task(param: P? = null): T

    open fun execute(
        liveData: WrappedLiveData<T>,
        param: P? = null,
        postLoading: Boolean = true,
        postDelay: Long = 0
    ) {
        if (postLoading)
            liveData.postLoading()

        liveData.assume {
            runCatching { task(param) }.apply {
                if (postDelay > 0)
                    delay(postDelay)

                onSuccess(liveData::postSuccess)
                onFailure(liveData::postException)
            }
        }
    }
}