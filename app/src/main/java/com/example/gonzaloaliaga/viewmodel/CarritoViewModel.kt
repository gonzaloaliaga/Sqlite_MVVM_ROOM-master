package com.example.gonzaloaliaga.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gonzaloaliaga.data.repository.CarritoRepository
import com.example.gonzaloaliaga.model.Carrito
import com.example.gonzaloaliaga.model.CarritoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CarritoViewModel(
    private val repo: CarritoRepository,
    private val uservm: UsuarioViewModel
) : ViewModel() {

    // -----------------------------
    // CARRITO COMPLETO
    // -----------------------------
    private val _carrito = MutableStateFlow<Carrito?>(null)
    val carrito: StateFlow<Carrito?> = _carrito.asStateFlow()

    // -----------------------------
    // CARGAR CARRITO DEL USUARIO
    // -----------------------------
    init {
        viewModelScope.launch {
            uservm.currentUser.collect { user ->
                if (user == null) {
                    _carrito.value = null
                    return@collect
                }

                try {
                    val result = repo.obtenerCarrito(user.id!!)
                    _carrito.value = result
                } catch (e: Exception) {
                    _carrito.value = null
                }
            }
        }
    }

    // -----------------------------
    // AGREGAR ITEM
    // -----------------------------
    fun agregar(productoId: String) = viewModelScope.launch {
        val user = uservm.currentUser.value ?: return@launch

        val item = CarritoItem(
            productoId = productoId,
            cantidad = 1
        )

        try {
            val updated = repo.agregarItem(user.id!!, item)
            _carrito.value = updated
        } catch (_: Exception) { }
    }

    // -----------------------------
    // DISMINUIR ITEM
    // -----------------------------
    fun disminuir(productoId: String) = viewModelScope.launch {
        val user = uservm.currentUser.value ?: return@launch

        try {
            val updated = repo.disminuirItem(user.id!!, productoId)
            _carrito.value = updated
        } catch (_: Exception) { }
    }

    // -----------------------------
    // VACIAR COMPLETAMENTE
    // -----------------------------
    fun vaciar() = viewModelScope.launch {
        val user = uservm.currentUser.value ?: return@launch

        try {
            repo.vaciarCarrito(user.id!!)
            _carrito.value = Carrito(usuarioId = user.id!!, items = emptyList())
        } catch (_: Exception) { }
    }
}