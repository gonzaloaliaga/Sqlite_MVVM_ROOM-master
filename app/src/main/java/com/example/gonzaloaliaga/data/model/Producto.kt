package com.example.gonzaloaliaga.data.model

data class Producto (
    val id: String? = null,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val categoria: String,
    val img: String,
)

// SOPORTE HATEOAS
data class ProductoEmbedded(
    val productoList: List<Producto>
)

data class ProductoResponse(
    val _embedded: ProductoEmbedded?
)