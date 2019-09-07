package com.isseiaoki.coroutinesinpractice

import com.isseiaoki.coroutinesinpractice.databinding.ItemPostBinding
import com.xwray.groupie.databinding.BindableItem

class PostItem(var post: Post): BindableItem<ItemPostBinding>() {

    override fun getLayout(): Int = R.layout.item_post

    override fun bind(binding: ItemPostBinding, position: Int) {
        binding.contentTextView.text = post.content
        binding.nameTextView.text = post.userName
    }
}