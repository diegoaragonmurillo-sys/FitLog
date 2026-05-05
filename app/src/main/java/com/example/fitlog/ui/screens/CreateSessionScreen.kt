package com.example.fitlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.input.KeyboardType
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

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedContainerColor = Color(0xFF1E1E1E),
        unfocusedContainerColor = Color(0xFF1E1E1E),
        focusedBorderColor = Color(0xFF00C853),
        unfocusedBorderColor = Color.Gray,
        focusedLabelColor = Color(0xFF00C853),
        unfocusedLabelColor = Color.Gray,
        cursorColor = Color(0xFF00C853)
    )

    Scaffold(
        containerColor = Color(0xFF121212),
        topBar = {
            TopAppBar(
                title = { Text("Nueva sesión", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, "", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E1E)
                )
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 🔥 DATOS SESIÓN
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E1E1E)
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text("Datos de la sesión", color = Color.White)

                        OutlinedTextField(
                            value = fecha,
                            onValueChange = { fecha = it },
                            label = { Text("Fecha") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = textFieldColors
                        )

                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Rutina") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = textFieldColors
                        )
                    }
                }
            }

            // 🔥 EJERCICIOS
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E1E1E)
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text("Ejercicios", color = Color.White)

                        OutlinedTextField(
                            value = nombreEj,
                            onValueChange = { nombreEj = it },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = textFieldColors
                        )

                        OutlinedTextField(
                            value = series,
                            onValueChange = { series = it },
                            label = { Text("Series") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = textFieldColors
                        )

                        OutlinedTextField(
                            value = rep,
                            onValueChange = { rep = it },
                            label = { Text("Reps") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = textFieldColors
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
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF00C853)
                            )
                        ) {
                            Text("Agregar ejercicio")
                        }
                    }
                }
            }

            // 🔥 LISTA
            items(lista) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E1E1E)
                    )
                ) {
                    Text(
                        "${it.nombre} - ${it.series}x${it.repeticiones}",
                        modifier = Modifier.padding(16.dp),
                        color = Color.White
                    )
                }
            }

            // 🔥 BOTÓN GUARDAR
            item {
                Button(
                    onClick = {
                        viewModel.guardarSesion(fecha, nombre, lista)
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00C853)
                    )
                ) {
                    Text("Guardar sesión")
                }
            }
        }
    }
}