package com.example.gonzaloaliaga.data.model

data class ProductoEmbedded(
    val productoList: List<Producto>
)

data class ProductoResponse(
    val _embedded: ProductoEmbedded?
)