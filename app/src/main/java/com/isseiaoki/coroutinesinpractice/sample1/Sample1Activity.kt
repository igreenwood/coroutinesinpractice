package com.isseiaoki.coroutinesinpractice.sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.isseiaoki.coroutinesinpractice.R
import com.isseiaoki.coroutinesinpractice.databinding.ActivitySample1Binding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Sample1Activity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main

    private lateinit var binding: ActivitySample1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample1)
        title = "Sample1"
        
        launch {
            delay(1000)
            binding.textView.text = "Hello, World"
        }
    }

    override fun onPause() {
        super.onPause()
        coroutineContext.cancelChildren()
    }
}
