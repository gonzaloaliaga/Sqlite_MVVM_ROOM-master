package com.example.gonzaloaliaga.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gonzaloaliaga.viewmodel.ProductViewModel
import com.example.gonzaloaliaga.viewmodel.UsuarioViewModel

@Composable
fun HomeScreen(uservm: UsuarioViewModel, prodvm: ProductViewModel, navController: NavController) {
    val user by uservm.currentUser.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "¡Bienvenido, ${user?.nombre ?: "Usuario"}!",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { navController.navigate("catalog") },
                        modifier = Modifier.fillMaxWidth(),
                    ) { Text("Ver Productos") }

                    Button(
                        onClick = { navController.navigate("cart") },
                        modifier = Modifier.fillMaxWidth(),
                    ) { Text("Ver Carrito") }

                    Button(
                        onClick = { navController.navigate("about") },
                        modifier = Modifier.fillMaxWidth(),
                    ) { Text("Sobre nosotros") }

                    if (user?.rol == "admin") {
                        Button(
                            onClick = { navController.navigate("adminScreen") },
                            modifier = Modifier.fillMaxWidth(),
                        ) { Text("Panel de Admin") }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    uservm.logout()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) { Text("Cerrar sesión") }
        }
    }
}