package com.example.gonzaloaliaga.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gonzaloaliaga.data.repository.ProductRepository
import com.example.gonzaloaliaga.viewmodel.ProductViewModel


class ProductViewModelFactory(
    private val repo: ProductRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repo) as T
    }
}