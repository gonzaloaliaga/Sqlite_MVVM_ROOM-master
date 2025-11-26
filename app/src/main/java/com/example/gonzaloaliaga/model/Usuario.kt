package com.example.gonzaloaliaga.model

data class Usuario(
    val id: String? = null,
    val correo: String,
    val pass: String,
    val rol: String,
    val telefono: String? = null,
    val region: String? = null,
    val comuna: String? = null
)

// SOPORTE HATEOAS
data class UsuarioEmbedded(
    val usuarioList: List<Usuario>
)

data class UsuarioResponse(
    val _embedded: UsuarioEmbedded?
)