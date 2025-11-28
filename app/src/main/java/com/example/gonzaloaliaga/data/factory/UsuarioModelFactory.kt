package com.example.gonzaloaliaga.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gonzaloaliaga.data.repository.UsuarioRepository
import com.example.gonzaloaliaga.viewmodel.UsuarioViewModel

class UsuarioViewModelFactory(
    private val repo: UsuarioRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsuarioViewModel(repo) as T
    }
}