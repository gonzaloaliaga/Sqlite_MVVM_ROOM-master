package com.example.gonzaloaliaga.data.repository

import com.example.gonzaloaliaga.data.api.UserApi
import com.example.gonzaloaliaga.model.Usuario

class UsuarioRepository(private val api: UserApi) {
    suspend fun login(correo: String, password: String): Usuario {
        require(correo.isNotBlank()) { "El correo no puede estar vacío" }
        require(password.isNotBlank()) { "La contraseña no puede estar vacía" }

        return api.login(correo, password)
    }

    suspend fun registrar(usuario: Usuario): Usuario {
        return api.createUser(usuario)
    }

    suspend fun obtenerTodos(): List<Usuario> {
        return api.getAllUsers()._embedded?.usuarioList ?: emptyList()
    }

    suspend fun obtenerPorId(id: String): Usuario {
        return api.getUserById(id)
    }

    suspend fun actualizar(id: String, usuario: Usuario): Usuario {
        return api.updateUser(id, usuario)
    }

    suspend fun eliminar(id: String) {
        api.deleteUser(id)
    }

}