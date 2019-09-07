package com.isseiaoki.coroutinesinpractice

import com.isseiaoki.coroutinesinpractice.databinding.ItemAdBinding
import com.xwray.groupie.databinding.BindableItem

class AdItem(var ad: Ad) : BindableItem<ItemAdBinding>() {

    override fun getLayout(): Int = R.layout.item_ad

    override fun bind(binding: ItemAdBinding, position: Int) {
        binding.contentTextView.text = ad.name
    }
}