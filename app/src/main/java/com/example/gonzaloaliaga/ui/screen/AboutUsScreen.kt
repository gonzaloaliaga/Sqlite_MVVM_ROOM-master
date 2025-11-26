package com.example.gonzaloaliaga.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gonzaloaliaga.R
import com.example.gonzaloaliaga.viewmodel.ProductViewModel
import com.example.gonzaloaliaga.viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(uservm: UsuarioViewModel, prodvm: ProductViewModel, navController: NavController) {
    val user by uservm.currentUser.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sobre nosotros") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver al menú"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Somos una empresa dedicada a ofrecer productos de calidad, con una amplia variedad de artículos que abarcan todas tus necesidades. Nos enfocamos en brindar una experiencia de compra confiable, ágil y accesible para todos nuestros clientes, procurando siempre la satisfacción y la confianza en cada compra. Nuestro compromiso es ofrecer productos útiles y de calidad a precios competitivos, adaptándonos a las necesidades de un mercado diverso y en constante cambio.")
            Image(
                painter = painterResource(id = R.drawable.skate),
                contentDescription = "Imagen decorativa",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}