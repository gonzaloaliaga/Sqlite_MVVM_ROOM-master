package com.example.gonzaloaliaga.data.api

import com.example.gonzaloaliaga.model.CarritoItem
import com.example.gonzaloaliaga.model.Carrito
import retrofit2.http.*

interface CarritoApi {

    @GET("/api/carrito/{usuarioId}")
    suspend fun getCarrito(@Path("usuarioId") usuarioId: String): Carrito

    @POST("/api/carrito/{usuarioId}/add")
    suspend fun addItem(
        @Path("usuarioId") usuarioId: String,
        @Body item: CarritoItem
    ): Carrito

    @PUT("/api/carrito/{usuarioId}/remove/{productoId}")
    suspend fun removeItem(
        @Path("usuarioId") usuarioId: String,
        @Path("productoId") productoId: String
    ): Carrito

    @DELETE("/api/carrito/{usuarioId}")
    suspend fun vaciarCarrito(@Path("usuarioId") usuarioId: String)
}
