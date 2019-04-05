package com.gdavidpb.daggerexample.ui.viewholders

import android.view.View
import com.gdavidpb.daggerexample.domain.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

open class PostViewHolder(itemView: View) : BaseViewHolder<Post>(itemView) {
    override fun bindView(item: Post) {
        with(itemView) {
            tViewPostTitle.text = item.title
            tViewPostBody.text = item.body
        }
    }
}