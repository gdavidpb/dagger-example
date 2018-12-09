package com.gdavidpb.daggerexample.data.repository

import com.gdavidpb.daggerexample.data.source.service.ServiceDataStoreFactory
import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.domain.repository.JsonRepository
import javax.inject.Inject

open class JsonDataRepository @Inject constructor(
    private val factory: ServiceDataStoreFactory
) : JsonRepository {
    override fun getPosts(): List<Post> {
        val dataStore = factory.retrieveDataStore()

        return dataStore.getPosts()
    }
}