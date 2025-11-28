package com.example.gonzaloaliaga.data.model

data class UsuarioEmbedded(
    val usuarioList: List<Usuario>
)

data class UsuarioResponse(
    val _embedded: UsuarioEmbedded?
)