package com.chunchiehliang.test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.test.databinding.ListItemPostBinding
import com.chunchiehliang.test.domain.Post

class PostListAdapter(val clickListener: PostClickListener) :
    ListAdapter<Post, PostListAdapter.PostListViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    class PostListViewHolder(private var binding: ListItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: PostClickListener, post: Post) {
            binding.post = post
            binding.imgPost.clipToOutline = true
            binding.clickListener = listener

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PostListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPostBinding.inflate(layoutInflater, parent, false)
                return PostListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        return PostListViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }
}

class PostClickListener(val clickListener: (post: Post) -> Unit) {
    fun onClick(post: Post) = clickListener(post)
}
