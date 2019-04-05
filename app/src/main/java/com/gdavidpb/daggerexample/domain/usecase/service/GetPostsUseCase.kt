package com.gdavidpb.daggerexample.domain.usecase.service

import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.domain.repository.JsonRepository
import com.gdavidpb.daggerexample.domain.usecase.LiveDataUseCase

open class GetPostsUseCase constructor(
    private val jsonRepository: JsonRepository
) : LiveDataUseCase<Void?, List<Post>>() {
    override fun task(param: Void?): List<Post> = jsonRepository.getPosts()
}