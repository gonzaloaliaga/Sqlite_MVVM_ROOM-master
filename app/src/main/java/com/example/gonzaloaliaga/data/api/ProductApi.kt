package com.example.gonzaloaliaga.data.api

import com.example.gonzaloaliaga.model.Producto
import com.example.gonzaloaliaga.model.ProductoResponse
import retrofit2.http.*

interface ProductApi {

    @GET("api/products")
    suspend fun getAll(): ProductoResponse

    @GET("api/products/{id}")
    suspend fun getById(@Path("id") id: String): Producto

    @POST("api/products")
    suspend fun create(@Body p: Producto): Producto

    @PUT("api/products/{id}")
    suspend fun update(@Path("id") id: String, @Body p: Producto): Producto

    @DELETE("api/products/{id}")
    suspend fun delete(@Path("id") id: String)
}