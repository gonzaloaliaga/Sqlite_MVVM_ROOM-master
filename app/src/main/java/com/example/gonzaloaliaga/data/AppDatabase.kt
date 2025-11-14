package com.example.gonzaloaliaga.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gonzaloaliaga.data.dao.CartDao
import com.example.gonzaloaliaga.data.cart.CartItemEntity
import com.example.gonzaloaliaga.data.dao.ProductoDao
import com.example.gonzaloaliaga.data.dao.UsuarioDao
import com.example.gonzaloaliaga.data.model.Producto
import com.example.gonzaloaliaga.data.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Producto::class, Usuario::class, CartItemEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductoDao
    abstract fun usuarioDao(): UsuarioDao
    abstract fun carritoDao(): CartDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database.db"
                )
                    .fallbackToDestructiveMigration(true)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = get(context).productDao()
                                dao.insertarProductosIniciales(
                                    listOf(
                                        Producto(nombre = "BMX Pro Street", precio = 250000.0, descripcion = "Bicicleta BMX ideal para trucos y uso urbano. Marco de acero reforzado.", categoria = "BMX"),
                                        Producto(nombre = "BMX Freestyle Air", precio = 310000.0, descripcion = "Modelo ligero para competiciones de freestyle, con llantas reforzadas.", categoria = "BMX"),

                                        Producto(nombre = "Skate Classic Maple", precio = 120000.0, descripcion = "Skateboard clásico de madera de arce con ruedas de alta resistencia.", categoria = "Skate"),
                                        Producto(nombre = "Skate Street Pro", precio = 150000.0, descripcion = "Patineta profesional para trucos técnicos y rampas.", categoria = "Skate"),

                                        Producto(nombre = "Casco Deportivo", precio = 45000.0, descripcion = "Casco ajustable con ventilación y certificación de seguridad.", categoria = "Accesorios"),
                                        Producto(nombre = "Rodilleras y Coderas Set", precio = 30000.0, descripcion = "Set completo de protecciones para deportes extremos.", categoria = "Accesorios")
                                    )
                                )
                            }
                        }
                    })
                    .build()
                    .also { INSTANCE = it }
            }
    }
}