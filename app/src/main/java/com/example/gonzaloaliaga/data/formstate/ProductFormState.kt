package com.example.gonzaloaliaga.data.formstate

data class ProductFormState(
    val id: String? = null,
    val nombre: String = "",
    val precio: Double = 0.0,
    val descripcion: String = "",
    val categoria: String = "",
    val img: String = "",
    val error: String? = null
)