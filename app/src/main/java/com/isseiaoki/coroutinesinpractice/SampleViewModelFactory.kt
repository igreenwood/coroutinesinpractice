package com.isseiaoki.coroutinesinpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SampleViewModelFactory(var repository: SampleDataRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass == SampleViewModel::class.java) {
            return SampleViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class : ${modelClass.name}")
        }
    }
}