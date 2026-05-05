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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 🔹 CARD DATOS SESIÓN
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            "Datos de la sesión",
                            style = MaterialTheme.typography.titleMedium
                        )

                        OutlinedTextField(
                            value = fecha,
                            onValueChange = { fecha = it },
                            label = { Text("Fecha") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Rutina") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                }
            }

            // 🔹 CARD EJERCICIOS
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            "Ejercicios",
                            style = MaterialTheme.typography.titleMedium
                        )

                        OutlinedTextField(
                            value = nombreEj,
                            onValueChange = { nombreEj = it },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = series,
                            onValueChange = { series = it },
                            label = { Text("Series") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = rep,
                            onValueChange = { rep = it },
                            label = { Text("Reps") },
                            modifier = Modifier.fillMaxWidth()
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
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.large
                        ) {
                            Text("Agregar ejercicio")
                        }
                    }
                }
            }

            // 🔹 LISTA DE EJERCICIOS
            items(lista) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        text = "${it.nombre} - ${it.series}x${it.repeticiones}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // 🔹 BOTÓN GUARDAR
            item {
                Button(
                    onClick = {
                        viewModel.guardarSesion(fecha, nombre, lista)
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text("Guardar sesión")
                }
            }
        }
    }
}