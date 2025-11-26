package com.example.gonzaloaliaga.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gonzaloaliaga.data.formstate.UserFormState
import com.example.gonzaloaliaga.data.repository.UsuarioRepository
import com.example.gonzaloaliaga.data.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repo: UsuarioRepository) : ViewModel() {

    // Estado del formulario
    private val _form = MutableStateFlow(UserFormState())
    val form: StateFlow<UserFormState> = _form.asStateFlow()

    // Usuario actualmente logueado
    private val _currentUser = MutableStateFlow<Usuario?>(null)
    val currentUser: StateFlow<Usuario?> = _currentUser.asStateFlow()

    // ---------------------
    // FORMULARIO
    // ---------------------

    fun onCorreoChange(v: String)   { _form.update { it.copy(correo = v) } }
    fun onPassChange(v: String)     { _form.update { it.copy(pass = v) } }
    fun onRolChange(v: String)      { _form.update { it.copy(rol = v) } }
    fun limpiarError()              { _form.update { it.copy(error = null) } }

    // ---------------------
    // LOGIN
    // ---------------------

    fun login(onSuccess: () -> Unit = {}) = viewModelScope.launch {
        try {
            val f = _form.value

            val usuario = repo.login(
                correo = f.correo.trim(),
                password = f.pass.trim()
            )

            _currentUser.value = usuario
            _form.value = UserFormState()

            onSuccess()

        } catch (e: Exception) {
            _form.update { it.copy(error = e.message ?: "Error desconocido") }
        }
    }

    fun logout() {
        _currentUser.value = null
    }

    // ---------------------
    // REGISTRO
    // ---------------------

    fun registrar(onSuccess: () -> Unit = {}) = viewModelScope.launch {
        try {
            val f = _form.value

            val nuevo = Usuario(
                correo = f.correo.trim(),
                pass = f.pass.trim(),
                rol = f.rol.trim()
            )

            repo.registrar(nuevo)

            _form.value = UserFormState()
            onSuccess()

        } catch (e: Exception) {
            _form.update { it.copy(error = e.message ?: "Error desconocido") }
        }
    }
}
