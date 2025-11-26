package com.example.gonzaloaliaga

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gonzaloaliaga.ui.viewmodel.CarritoViewModel
import com.example.gonzaloaliaga.data.cart.CarritoViewModelFactory
import com.example.gonzaloaliaga.ui.viewmodel.ProductViewModel
import com.example.gonzaloaliaga.data.factory.ProductViewModelFactory
import com.example.gonzaloaliaga.ui.viewmodel.UsuarioViewModel
import com.example.gonzaloaliaga.data.factory.UsuarioViewModelFactory
import com.example.gonzaloaliaga.ui.screen.profile.LoginScreen
import com.example.gonzaloaliaga.ui.screen.profile.RegisterScreen
import com.example.gonzaloaliaga.ui.screen.*
import com.example.gonzaloaliaga.ui.screen.admin.AdminScreen
import com.example.gonzaloaliaga.ui.screen.admin.ProductManagerScreen
import com.example.gonzaloaliaga.ui.screen.products.CatalogScreen
import com.example.gonzaloaliaga.ui.screen.products.ProductDetailScreen

class MainActivity : ComponentActivity() {
    private val app by lazy { application as MiApp }


    // PENDIENTE ----------------------------------------
    private val uservm: UsuarioViewModel by viewModels {
        UsuarioViewModelFactory(app.usuarioRepository)
    }

    private val prodvm: ProductViewModel by viewModels {
        ProductViewModelFactory(app.productRepository)
    }

    private val cartvm: CarritoViewModel by viewModels {
        CarritoViewModelFactory(app.carritoRepository, uservm)
    }
    // ----------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController, startDestination = "login") {

                composable("login") {
                    LoginScreen(
                        uservm = uservm,
                        navController = navController,
                        onLoginSuccess = {
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    )
                }

                composable("register") {
                    RegisterScreen(uservm, navController)
                }

                composable("home") {
                    HomeScreen(uservm, prodvm, navController)
                }

                composable("catalog") {
                    CatalogScreen(uservm, prodvm, cartvm, navController)
                }

                composable("productDetail/{id}") {
                    val id = it.arguments?.getString("id")!!
                    ProductDetailScreen(uservm, prodvm, cartvm, navController, id)
                }

                composable("cart") {
                    ShoppingCartScreen(cartvm, navController)
                }

                composable("about") {
                    AboutUsScreen(uservm, prodvm, navController)
                }

                composable("adminScreen") {
                    AdminScreen(uservm, prodvm, navController)
                }

                composable("productManager") {
                    ProductManagerScreen(uservm, prodvm, navController)
                }
            }
        }
    }
}