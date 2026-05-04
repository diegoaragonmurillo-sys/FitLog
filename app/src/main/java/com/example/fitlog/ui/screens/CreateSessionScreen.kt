package com.example.fitlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitlog.data.model.Ejercicio
import com.example.fitlog.ui.viewmodel.FitLogViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CrearSesionScreen(
    navController: NavController,
    viewModel: FitLogViewModel
) {

    var fecha by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }

    var nombreEj by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("") }
    var rep by remember { mutableStateOf("") }

    var lista by remember { mutableStateOf(listOf<Ejercicio>()) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Nueva sesión") })
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            item {

                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha") }
                )

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Rutina") }
                )

                Spacer(Modifier.height(12.dp))

                Text("Ejercicios")

                OutlinedTextField(
                    value = nombreEj,
                    onValueChange = { nombreEj = it },
                    label = { Text("Nombre") }
                )

                OutlinedTextField(
                    value = series,
                    onValueChange = { series = it },
                    label = { Text("Series") }
                )

                OutlinedTextField(
                    value = rep,
                    onValueChange = { rep = it },
                    label = { Text("Reps") }
                )

                Button(
                    onClick = {
                        lista = lista + Ejercicio(
                            sesionId = 0,
                            nombre = nombreEj,
                            series = series.toIntOrNull() ?: 0,
                            repeticiones = rep.toIntOrNull() ?: 0
                        )

                        nombreEj = ""
                        series = ""
                        rep = ""
                    }
                ) {
                    Text("Agregar ejercicio")
                }
            }

            items(lista) {
                Text("${it.nombre} - ${it.series}x${it.repeticiones}")
            }

            item {
                Button(
                    onClick = {
                        viewModel.guardarSesion(fecha, nombre, lista)
                        navController.popBackStack()
                    }
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}