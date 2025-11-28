package com.example.gonzaloaliaga.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gonzaloaliaga.data.repository.CarritoRepository
import com.example.gonzaloaliaga.viewmodel.CarritoViewModel
import com.example.gonzaloaliaga.viewmodel.UsuarioViewModel

class CarritoModelFactory (
    private val repo: CarritoRepository,
    private val uservm: UsuarioViewModel
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarritoViewModel(repo, uservm) as T
    }
}