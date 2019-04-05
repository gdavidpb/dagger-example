package com.gdavidpb.daggerexample.data.source.service

import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.domain.repository.JsonRepository
import retrofit2.Call

open class JsonServiceDataStore(
    private val service: JsonService
) : JsonRepository {
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