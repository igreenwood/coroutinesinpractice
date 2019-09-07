package com.isseiaoki.coroutinesinpractice.sample3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.isseiaoki.coroutinesinpractice.*
import com.isseiaoki.coroutinesinpractice.databinding.ActivitySample2Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import java.util.*

class Sample3Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySample2Binding
    private lateinit var viewModel: SampleViewModel
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample2)
        title = "Sample3"
        viewModel = SampleViewModel(SampleDataRepository())

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.run {
            postAndAdData.observe(this@Sample3Activity, Observer {
                it.fold(
                    { data ->
                        updateView(data)
                    },
                    {
                        Toast.makeText(this@Sample3Activity, "error", Toast.LENGTH_SHORT).show()
                    }
                )
                binding.progress.visibility = View.INVISIBLE
            })
            loadPostAndAdData()
        }
    }

    private fun updateView(data: Pair<List<Post>, List<Ad>>) {
        adapter.clear()
        val items = data.first.map { PostItem(it) } + data.second.map { AdItem(it) }
        adapter.addAll(items.shuffled(Random(0)))
    }
}
