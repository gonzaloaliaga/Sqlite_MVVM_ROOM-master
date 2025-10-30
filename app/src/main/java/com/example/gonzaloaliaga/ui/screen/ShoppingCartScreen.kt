package com.example.gonzaloaliaga.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gonzaloaliaga.ui.viewmodel.CarritoViewModel
import com.example.gonzaloaliaga.model.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreen(cartvm: CarritoViewModel, navController: NavController) {
    val carrito by cartvm.carrito.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tu Carrito") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            if (carrito.isEmpty()) {
                Text("Tu carrito está vacío.")
            } else {
                LazyColumn {
                    items(carrito) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(item.nombre)
                                    Text("Cantidad: ${item.cantidad}")
                                    Text("Total: $${item.precio * item.cantidad}")
                                }
                                Row {
                                    IconButton(onClick = { cartvm.restar(Producto(item.productoId, item.nombre, item.precio, item.descripcion, item.categoria)) }) {
                                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Restar")
                                    }
                                    IconButton(onClick = { cartvm.agregar(Producto(item.productoId, item.nombre, item.precio, item.descripcion, item.categoria)) }) {
                                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Sumar")
                                    }
                                    IconButton(onClick = { cartvm.eliminar(Producto(item.productoId, item.nombre, item.precio, item.descripcion, item.categoria)) }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Total: $${cartvm.total()}", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { cartvm.vaciar() }) {
                    Text("Vaciar Carrito")
                }
            }
        }
    }
}