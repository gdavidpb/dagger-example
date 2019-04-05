package com.gdavidpb.daggerexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.domain.usecase.service.GetPostsUseCase
import com.gdavidpb.daggerexample.utils.LiveResult

open class MainActivityViewModel constructor(
        private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    val posts = LiveResult<List<Post>>()

    fun getPosts() {
        getPostsUseCase.execute(params = Unit, liveData = posts)
    }
}