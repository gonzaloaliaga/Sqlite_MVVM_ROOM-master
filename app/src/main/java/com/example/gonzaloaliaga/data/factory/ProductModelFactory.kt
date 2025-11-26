package com.example.gonzaloaliaga.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gonzaloaliaga.data.api.ApiClient
import com.example.gonzaloaliaga.data.api.ProductApi
import com.example.gonzaloaliaga.data.repository.ProductRepository
import com.example.gonzaloaliaga.ui.viewmodel.ProductViewModel


class ProductViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val api = ApiClient.buildService(ProductApi::class.java)
        val repo = ProductRepository(api)
        return ProductViewModel(repo) as T
    }
}