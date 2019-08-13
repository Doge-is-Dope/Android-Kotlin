package com.chunchiehliang.test

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.test.domain.Post
import com.chunchiehliang.test.ui.home.PostListAdapter

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as PostListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

