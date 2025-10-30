package com.example.gonzaloaliaga.data.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gonzaloaliaga.data.repository.UsuarioRepository
import com.example.gonzaloaliaga.ui.viewmodel.UsuarioViewModel

class UsuarioViewModelFactory(private val repo: UsuarioRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            return UsuarioViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}