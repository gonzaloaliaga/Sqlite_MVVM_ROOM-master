package com.example.gonzaloaliaga.data.model

data class Usuario (
    val id: String? = null,
    val correo: String,
    val pass: String,
    val rol: String = "cliente",
    val telefono: String? = null,
    val region: String? = null,
    val comuna: String? = null
)