package com.isseiaoki.coroutinesinpractice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.isseiaoki.coroutinesinpractice.databinding.ActivityMainBinding
import com.isseiaoki.coroutinesinpractice.sample1.Sample1Activity
import com.isseiaoki.coroutinesinpractice.sample2.Sample2Activity
import com.isseiaoki.coroutinesinpractice.sample3.Sample3Activity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            sample1Button.setOnClickListener {
                startActivity(Intent(this@MainActivity, Sample1Activity::class.java))
            }
            sample2Button.setOnClickListener {
                startActivity(Intent(this@MainActivity, Sample2Activity::class.java))
            }
            sample3Button.setOnClickListener {
                startActivity(Intent(this@MainActivity, Sample3Activity::class.java))
            }
        }
    }
}
