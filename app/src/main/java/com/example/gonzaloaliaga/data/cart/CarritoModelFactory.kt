package com.example.gonzaloaliaga.data.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gonzaloaliaga.data.repository.CarritoRepository
import com.example.gonzaloaliaga.viewmodel.CarritoViewModel
import com.example.gonzaloaliaga.viewmodel.UsuarioViewModel

class CarritoViewModelFactory(
    private val repository: CarritoRepository,
    private val uservm: UsuarioViewModel
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarritoViewModel::class.java)) {
            return CarritoViewModel(repository, uservm) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}