package com.example.gonzaloaliaga

import android.app.Application
import com.example.gonzaloaliaga.data.AppDatabase
import com.example.gonzaloaliaga.data.repository.CarritoRepository
import com.example.gonzaloaliaga.data.repository.ProductRepository
import com.example.gonzaloaliaga.data.repository.UsuarioRepository

class MiApp : Application() {
    val database by lazy { AppDatabase.get(this) }

    val usuarioRepository by lazy { UsuarioRepository(database.usuarioDao()) }
    val productRepository by lazy { ProductRepository(database.productDao()) }
    val carritoRepository by lazy { CarritoRepository(database.carritoDao()) }

    override fun onCreate() {
        super.onCreate()
        // inicializar cosas globales si se requiere
    }
}