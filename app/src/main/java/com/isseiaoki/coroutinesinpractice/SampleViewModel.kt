package com.isseiaoki.coroutinesinpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SampleViewModel(val repository: SampleDataRepository) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main

    val postData = MutableLiveData<Result<List<Post>>>()
    val postAndAdData = MutableLiveData<Result<Pair<List<Post>, List<Ad>>>>()

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    fun loadPostData() {
        launch {
            try {
                postData.value = Result.success(repository.getPosts())
            } catch (e: CancellationException) {
                postData.value = Result.failure(e)
            } catch (e: Throwable) {
                postData.value = Result.failure(e)
            }
        }
    }

    fun loadPostAndAdData() {
        launch {
            try {
                val posts = async { repository.getPosts() }
                val ads = async { repository.getAds() }
                postAndAdData.value = Result.success(Pair(posts.await(), ads.await()))
            } catch (e: CancellationException) {
                postData.value = Result.failure(e)
            } catch (e: Throwable) {
                postData.value = Result.failure(e)
            }
        }
    }
}