package com.example.gonzaloaliaga.data.remote

import com.example.gonzaloaliaga.data.remote.Post

class PostRepository {

    suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }
}