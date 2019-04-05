package com.gdavidpb.daggerexample.domain.repository

import com.gdavidpb.daggerexample.domain.model.Post

interface JsonRepository {
    suspend fun getPosts(): List<Post>?
}