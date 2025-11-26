package com.example.gonzaloaliaga.data.repository

import com.example.gonzaloaliaga.data.api.ProductApi
import com.example.gonzaloaliaga.model.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(private val api: ProductApi) {

    // Obtener TODOS los productos (manejo de HATEOAS)
    val productos: Flow<List<Producto>> = flow {
        val response = api.getAll()
        emit(response._embedded?.productoList ?: emptyList())
    }

    suspend fun agregar(
        nombre: String,
        precio: Double,
        descripcion: String,
        categoria: String,
        img: String
    ): Producto {

        require(nombre.isNotBlank()) { "El nombre no puede estar vacío" }
        require(precio > 0) { "El precio debe ser mayor a 0" }
        require(descripcion.isNotBlank()) { "La descripción no puede estar vacía" }
        require(categoria.isNotBlank()) { "La categoría no puede estar vacía" }
        require(img.isNotBlank()) { "La imagen no puede estar vacía" }

        val nuevo = Producto(
            nombre = nombre.trim(),
            precio = precio,
            descripcion = descripcion,
            categoria = categoria,
            img = img
        )

        return api.create(nuevo)
    }

    suspend fun actualizar(
        id: String,
        nombre: String,
        precio: Double,
        descripcion: String,
        categoria: String,
        img: String
    ): Producto {

        require(id.isNotBlank()) { "ID inválido" }
        require(nombre.isNotBlank()) { "El nombre no puede estar vacío" }
        require(precio > 0) { "El precio debe ser mayor a 0" }
        require(descripcion.isNotBlank()) { "La descripción no puede estar vacía" }
        require(categoria.isNotBlank()) { "La categoría no puede estar vacía" }
        require(img.isNotBlank()) { "La imagen no puede estar vacía" }

        val actualizado = Producto(
            id = id,
            nombre = nombre.trim(),
            precio = precio,
            descripcion = descripcion,
            categoria = categoria,
            img = img
        )

        return api.update(id, actualizado)
    }

    suspend fun eliminar(id: String) {
        require(id.isNotBlank()) { "ID inválido" }
        api.delete(id)
    }

    suspend fun obtener(id: String): Producto {
        require(id.isNotBlank()) { "ID inválido" }
        return api.getById(id)
    }

    suspend fun obtenerPorCategoria(categoria: String): List<Producto> {
        require(categoria.isNotBlank()) { "La categoría no puede estar vacía" }

        val result = api.getAll()
        val list = result._embedded?.productoList ?: emptyList()

        return list.filter { it.categoria.equals(categoria, ignoreCase = true) }
    }
}