package com.example.gonzaloaliaga.data.cart

data class CarritoConProducto(
    val id: Long,
    val usuarioId: Long,
    val productoId: Long,
    val cantidad: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val categoria: String
)