package com.example.fitlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitlog.ui.viewmodel.FitLogViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetalleSesionScreen(
    navController: NavController,
    viewModel: FitLogViewModel,
    id: Int
) {

    val data by viewModel.obtenerDetalle(id)
        .collectAsState(initial = null)

    data?.let {

        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {

            item {
                Text(it.sesion.nombreRutina)
                Text("Fecha: ${it.sesion.fecha}")

                Button(
                    onClick = {
                        viewModel.cambiarEstado(
                            it.sesion.id,
                            !it.sesion.completada
                        )
                    }
                ) {
                    Text("Cambiar estado")
                }
            }

            items(it.ejercicios) {
                Text("${it.nombre} - ${it.series}x${it.repeticiones}")
            }
        }
    }
}