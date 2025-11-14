package com.example.gonzaloaliaga.data.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gonzaloaliaga.data.repository.ProductRepository
import com.example.gonzaloaliaga.viewmodel.ProductViewModel


class ProductViewModelFactory(private val repo: ProductRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}