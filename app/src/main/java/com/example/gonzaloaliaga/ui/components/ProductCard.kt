package com.example.gonzaloaliaga.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.gonzaloaliaga.viewmodel.CarritoViewModel
import com.example.gonzaloaliaga.data.model.Producto

@Composable
fun ProductCard(
    producto: Producto,
    cartvm: CarritoViewModel,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("productDetail/${producto.id}") },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 👉 Imagen con Coil moderno
            AsyncImage(
                model = producto.img,
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(90.dp)          // Tamaño fijo, no deformable
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                Text("$${producto.precio}", style = MaterialTheme.typography.bodyMedium)

                Text(
                    producto.descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 2
                )
            }

            val context = LocalContext.current
            Button(
                onClick = {
                    cartvm.agregar(producto.id!!) // 👈 tu método agrega por id
                    Toast.makeText(
                        context,
                        "${producto.nombre} agregado al carrito",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("Agregar")
            }
        }
    }
}