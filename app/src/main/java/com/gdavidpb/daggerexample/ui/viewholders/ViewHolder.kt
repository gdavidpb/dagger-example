package com.gdavidpb.daggerexample.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindView(item: T)
}