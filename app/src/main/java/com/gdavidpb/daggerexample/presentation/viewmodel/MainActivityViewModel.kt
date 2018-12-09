package com.gdavidpb.daggerexample.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.domain.model.WrappedLiveData
import com.gdavidpb.daggerexample.domain.usecase.service.GetPostsUseCase
import javax.inject.Inject

open class MainActivityViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    val posts = WrappedLiveData<List<Post>>()

    fun getPosts() {
        getPostsUseCase.execute(liveData = posts)
    }
}