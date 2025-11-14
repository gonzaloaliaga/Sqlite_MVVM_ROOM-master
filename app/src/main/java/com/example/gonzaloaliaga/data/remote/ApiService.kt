package com.example.gonzaloaliaga.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("api/products")
    suspend fun getProducts(): List<Producto>
}