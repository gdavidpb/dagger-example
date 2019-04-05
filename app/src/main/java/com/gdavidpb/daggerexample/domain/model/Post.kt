package com.gdavidpb.daggerexample.domain.model

data class Post(
        val id: Int = 0,
        val userId: Int = 0,
        val title: String = "",
        val body: String = ""
)