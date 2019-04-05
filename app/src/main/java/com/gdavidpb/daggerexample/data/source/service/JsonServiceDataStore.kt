package com.gdavidpb.daggerexample.data.source.service

import com.gdavidpb.daggerexample.domain.repository.JsonRepository
import com.gdavidpb.daggerexample.utils.await

open class JsonServiceDataStore(
        private val service: JsonService
) : JsonRepository {
    override suspend fun getPosts() = service.getPosts().await()
}