package com.example.gonzaloaliaga.model

data class Carrito (
    val id: String? = null,
    val usuarioId: String,
    val items: List<CarritoItem>
)
