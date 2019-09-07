package com.isseiaoki.coroutinesinpractice.sample2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.isseiaoki.coroutinesinpractice.*
import com.isseiaoki.coroutinesinpractice.databinding.ActivitySample2Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class Sample2Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySample2Binding
    private lateinit var viewModel: SampleViewModel
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample2)
        title = "Sample2"
        viewModel =
            ViewModelProvider(this, SampleViewModelFactory(SampleDataRepository())).get(SampleViewModel::class.java)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.run {
            postData.observe(this@Sample2Activity, Observer {
                it.fold(
                    { data ->
                        updateView(data)
                    },
                    {
                        Toast.makeText(this@Sample2Activity, "error", Toast.LENGTH_SHORT).show()
                    }
                )
                binding.progress.visibility = View.INVISIBLE
            })
            loadPostData()
        }
    }

    private fun updateView(postData: List<Post>) {
        adapter.clear()
        adapter.addAll(postData.map { PostItem(it) })
    }
}
