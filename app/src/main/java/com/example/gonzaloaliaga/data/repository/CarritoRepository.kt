package com.example.gonzaloaliaga.data.repository

import com.example.gonzaloaliaga.data.api.CarritoApi
import com.example.gonzaloaliaga.model.CarritoItem
import com.example.gonzaloaliaga.model.Carrito

class CarritoRepository(
    private val api: CarritoApi
) {

    suspend fun obtenerCarrito(usuarioId: String): Carrito {
        return api.getCarrito(usuarioId)
    }

    suspend fun agregarItem(usuarioId: String, item: CarritoItem): Carrito {
        return api.addItem(usuarioId, item)
    }

    suspend fun disminuirItem(usuarioId: String, productoId: String): Carrito {
        return api.removeItem(usuarioId, productoId)
    }

    suspend fun vaciarCarrito(usuarioId: String) {
        api.vaciarCarrito(usuarioId)
    }

    suspend fun eliminarItemCompletamente(usuarioId: String, productoId: String, cantidad: Int) {
        repeat(cantidad) {
            api.removeItem(usuarioId, productoId) // RESTA 1 cada vez. El backend elimina el item si cantidad llega a 0
        }
    }
}