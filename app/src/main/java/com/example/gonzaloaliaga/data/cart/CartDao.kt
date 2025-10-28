package com.example.gonzaloaliaga.data.cart
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query(" SELECT carrito.id, carrito.usuarioId, carrito.productoId, carrito.cantidad, productos.nombre, productos.precio, productos.descripcion, productos.categoria FROM carrito INNER JOIN productos ON carrito.productoId = productos.id WHERE carrito.usuarioId = :usuarioId")
    fun getCarrito(usuarioId: Long): Flow<List<CarritoConProducto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItemEntity)

    @Update
    suspend fun update(cartItem: CartItemEntity)

    @Delete
    suspend fun delete(cartItem: CartItemEntity)

    @Query("DELETE FROM carrito WHERE usuarioId = :usuarioId")
    suspend fun limpiarCarrito(usuarioId: Long)

    @Query("SELECT * FROM carrito WHERE usuarioId = :usuarioId AND productoId = :productoId LIMIT 1")
    suspend fun findItem(usuarioId: Long, productoId: Long): CartItemEntity?
}