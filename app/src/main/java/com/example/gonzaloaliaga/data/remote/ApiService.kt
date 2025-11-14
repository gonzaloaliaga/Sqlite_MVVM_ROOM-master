package com.example.gonzaloaliaga.data.remote

import com.example.gonzaloaliaga.data.remote.Post
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}