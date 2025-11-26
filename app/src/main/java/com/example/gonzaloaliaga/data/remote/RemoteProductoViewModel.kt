package com.example.gonzaloaliaga.data.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RemoteProductoViewModel : ViewModel() {

    private val repository = RemoteProductoRepository()

    private val _productList = MutableStateFlow<List<Producto>>(emptyList())

    val productList: StateFlow<List<Producto>> = _productList

    init {
        fetchProducts()
    }

    private fun fetchProducts(){
        viewModelScope.launch {
            try {
                _productList.value = repository.getPosts()
            } catch (e: Exception) {
                println("Error al obtener datos: ${e.localizedMessage}")
            }
        }
    }
}