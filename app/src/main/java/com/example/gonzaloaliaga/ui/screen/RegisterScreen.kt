package com.example.gonzaloaliaga.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gonzaloaliaga.ui.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    uservm: UsuarioViewModel,
    navController: NavController
) {
    val form by uservm.form.collectAsState()

    // Si hay un mensaje de error en el formulario, lo mostramos
    val error = form.error

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Registrar Usuario") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextField(
                value = form.nombre,
                onValueChange = { uservm.onNombreChange(it) },
                label = { Text("Nombre de usuario") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = form.password,
                onValueChange = { uservm.onPasswordChange(it) },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = form.rol,
                onValueChange = { uservm.onRolChange(it) },
                label = { Text("Rol (por ejemplo: cliente, admin)") },
                modifier = Modifier.fillMaxWidth()
            )

            // Mostrar error si existe
            error?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    uservm.guardar {
                        // Si el registro fue exitoso, vuelve al login
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = { navController.popBackStack() }) {
                Text("Volver al inicio de sesión")
            }
        }
    }
}