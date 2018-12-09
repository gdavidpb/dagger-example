package com.gdavidpb.daggerexample.domain.repository

import com.gdavidpb.daggerexample.domain.model.Post

interface JsonRepository {
    fun getPosts(): List<Post>
}