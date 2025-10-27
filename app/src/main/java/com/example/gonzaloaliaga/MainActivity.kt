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
import com.example.gonzaloaliaga.ui.ProductViewModel
import com.example.gonzaloaliaga.ui.ProductViewModelFactory
import com.example.gonzaloaliaga.ui.UsuarioViewModel
import com.example.gonzaloaliaga.ui.UsuarioViewModelFactory
import com.example.gonzaloaliaga.ui.screen.*
import com.example.gonzaloaliaga.ui.screen.admin.AdminScreen
import com.example.gonzaloaliaga.ui.screen.admin.ProductManagerScreen
import com.example.gonzaloaliaga.ui.screen.products.CatalogScreen
import com.example.gonzaloaliaga.ui.screen.products.ProductDetailScreen

class MainActivity : ComponentActivity() {

    private val uservm: UsuarioViewModel by viewModels { UsuarioViewModelFactory(application) }
    private val prodvm: ProductViewModel by viewModels { ProductViewModelFactory(application) }

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
                    CatalogScreen(uservm, prodvm, navController)
                }

                composable(
                    "productDetail/{productId}",
                    listOf(navArgument("productId") {type = NavType.LongType })
                ) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getLong("productId") ?: 0L
                    ProductDetailScreen(uservm, prodvm, navController, productId)
                }

                composable("cart") {
                    ShoppingCartScreen(uservm, prodvm, navController)
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