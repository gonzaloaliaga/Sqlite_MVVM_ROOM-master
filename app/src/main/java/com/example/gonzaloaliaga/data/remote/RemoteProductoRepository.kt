package com.example.gonzaloaliaga.data.remote

class RemoteProductoRepository {

    suspend fun getPosts(): List<Producto> {
        return RetrofitInstance.api.getProducts()
    }
}