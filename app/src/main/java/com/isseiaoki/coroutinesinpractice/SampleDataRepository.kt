package com.isseiaoki.coroutinesinpractice

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SampleDataRepository {

    suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {
        delay(200)
        createDummyPosts()
    }

    suspend fun getAds(): List<Ad> = withContext(Dispatchers.IO) {
        delay(300)
        createDummyAds()
    }

    private fun createDummyPosts(): List<Post> {
        return (1..20)
            .map {
                Post(
                    it.toLong(),
                    "hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello ",
                    "User$it"
                )
            }
    }

    private fun createDummyAds(): List<Ad> {
        return (1..2)
            .map {
                Ad(
                    it.toLong(),
                    "Ad"
                )
            }
    }
}

data class Post(
    var id: Long,
    var content: String,
    var userName: String
)

data class Ad(
    var id: Long,
    var name: String
)