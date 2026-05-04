package com.example.fitlog.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.fitlog.ui.screens.*
import com.example.fitlog.ui.viewmodel.FitLogViewModel

@Composable
fun AppNavigation(viewModel: FitLogViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "historial"
    ) {

        composable("historial") {
            HistorialScreen(navController, viewModel)
        }

        composable("crear") {
            CrearSesionScreen(navController, viewModel)
        }

        composable("detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments
                ?.getString("id")
                ?.toIntOrNull() ?: 0

            DetalleSesionScreen(navController, viewModel, id)
        }
    }
}