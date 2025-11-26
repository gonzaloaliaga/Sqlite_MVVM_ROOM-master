package com.example.gonzaloaliaga

import android.app.Application
import com.example.gonzaloaliaga.data.api.ApiClient
import com.example.gonzaloaliaga.data.api.UserApi
import com.example.gonzaloaliaga.data.api.ProductApi
import com.example.gonzaloaliaga.data.api.CarritoApi
import com.example.gonzaloaliaga.data.repository.CarritoRepository
import com.example.gonzaloaliaga.data.repository.ProductRepository
import com.example.gonzaloaliaga.data.repository.UsuarioRepository
import retrofit2.Retrofit

class MiApp : Application() {
    val usuarioApi by lazy { ApiClient.buildService(UserApi::class.java) }
    val productoApi by lazy { ApiClient.buildService(ProductApi::class.java) }
    val carritoApi by lazy { ApiClient.buildService(CarritoApi::class.java) }

    val usuarioRepository by lazy { UsuarioRepository(usuarioApi)}
    val productRepository by lazy { ProductRepository( productoApi)}
    val carritoRepository by lazy { CarritoRepository(carritoApi)}

    override fun onCreate() {
        super.onCreate()
    }
}