package com.example.gonzaloaliaga.data.formstate

data class UserFormState(
    val id: String? = null,
    val correo: String = "",
    val pass: String = "",
    val rol: String = "",
    var error: String? = null
)