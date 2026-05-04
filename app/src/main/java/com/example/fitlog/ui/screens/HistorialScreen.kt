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
import com.example.fitlog.ui.viewmodel.FitLogViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HistorialScreen(
    navController: NavController,
    viewModel: FitLogViewModel
) {
    val sesiones by viewModel.sesiones.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("FitLog") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("crear") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            items(sesiones) { sesion ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    onClick = {
                        navController.navigate("detalle/${sesion.id}")
                    }
                ) {

                    Column(Modifier.padding(16.dp)) {

                        Text(
                            sesion.nombreRutina,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text("Fecha: ${sesion.fecha}")

                        Spacer(modifier = Modifier.height(8.dp))

                        AssistChip(
                            onClick = {},
                            label = {
                                Text(
                                    if (sesion.completada)
                                        "Completada"
                                    else "Pendiente"
                                )
                            }
                        )

                        IconButton(
                            onClick = {
                                viewModel.eliminarSesion(sesion)
                            }
                        ) {
                            Icon(Icons.Default.Delete, "")
                        }
                    }
                }
            }
        }
    }
}