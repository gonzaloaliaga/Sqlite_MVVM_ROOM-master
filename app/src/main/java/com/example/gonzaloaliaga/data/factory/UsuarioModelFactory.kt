package com.example.gonzaloaliaga.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gonzaloaliaga.data.api.ApiClient
import com.example.gonzaloaliaga.data.api.UserApi
import com.example.gonzaloaliaga.data.repository.UsuarioRepository
import com.example.gonzaloaliaga.ui.viewmodel.UsuarioViewModel

class UsuarioViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val api = ApiClient.buildService(UserApi::class.java)
        val repo = UsuarioRepository(api)
        return UsuarioViewModel(repo) as T
    }
}