package com.gdavidpb.daggerexample.data.datastore

import com.gdavidpb.daggerexample.domain.model.Post

interface JsonDataStore {
    fun getPosts(): List<Post>
}