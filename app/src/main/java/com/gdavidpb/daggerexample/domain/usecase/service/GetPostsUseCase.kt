package com.gdavidpb.daggerexample.domain.usecase.service

import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.domain.repository.JsonRepository
import com.gdavidpb.daggerexample.domain.usecase.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

open class GetPostsUseCase(
        private val jsonRepository: JsonRepository
) : ResultUseCase<Unit, List<Post>>(
        backgroundContext = Dispatchers.IO,
        foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: Unit) = jsonRepository.getPosts()
}