package com.gdavidpb.daggerexample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdavidpb.daggerexample.R
import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.ui.viewholders.PostViewHolder

open class PostAdapter(
    private val source: List<Post>
) : RecyclerView.Adapter<PostViewHolder>() {

    override fun getItemCount(): Int = source.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = source[position]

        holder.bindView(item)
    }
}