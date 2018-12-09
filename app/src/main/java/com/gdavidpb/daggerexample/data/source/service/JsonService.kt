package com.gdavidpb.daggerexample.data.source.service

import com.gdavidpb.daggerexample.domain.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface JsonService {
    @Headers("Content-Type: application/json")
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}