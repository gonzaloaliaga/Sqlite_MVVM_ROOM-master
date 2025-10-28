package com.example.gonzaloaliaga.data.cart

import androidx.room.*
import com.example.gonzaloaliaga.model.Producto
import com.example.gonzaloaliaga.model.Usuario

@Entity(
    tableName = "carrito",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id"],
            childColumns = ["usuarioId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Producto::class,
            parentColumns = ["id"],
            childColumns = ["productoId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("usuarioId"), Index("productoId")]
)
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val usuarioId: Long,
    val productoId: Long,
    val cantidad: Int
)