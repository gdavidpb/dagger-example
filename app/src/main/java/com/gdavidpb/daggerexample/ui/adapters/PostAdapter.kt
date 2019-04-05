package com.gdavidpb.daggerexample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gdavidpb.daggerexample.R
import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.ui.viewholders.PostViewHolder

open class PostAdapter : BaseAdapter<Post>() {
    override fun provideComparator() = compareBy(Post::id)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

        return PostViewHolder(itemView)
    }
}