package com.example.gonzaloaliaga.data.remote

data class Post(
    val userId: Int, // ID del usuario que creó el post
    val id: Int, // ID del post
    val title: String,
    val body: String
)