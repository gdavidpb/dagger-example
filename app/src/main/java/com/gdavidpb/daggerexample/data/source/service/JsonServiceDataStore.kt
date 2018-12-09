package com.gdavidpb.daggerexample.data.source.service

import com.gdavidpb.daggerexample.data.datastore.JsonDataStore
import com.gdavidpb.daggerexample.domain.model.Post
import retrofit2.Call
import javax.inject.Inject

open class JsonServiceDataStore @Inject constructor(
    private val service: JsonService
) : JsonDataStore {
    override fun getPosts(): List<Post> = service.getPosts().resolve()

    private fun <T> Call<T>.resolve(): T {
        return execute().run {
            if (isSuccessful)
                body() ?: throw NullPointerException("body")
            else
                throw RuntimeException("Response code: ${code()}, ${message()}")
        }
    }
}