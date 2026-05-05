package com.example.fitlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
            TopAppBar(
                title = { Text("FitLog") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("crear") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->

        if (sesiones.isEmpty()) {
            // 🔥 MENSAJE BONITO SI NO HAY DATOS
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No hay sesiones aún 💪",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(sesiones) { sesion ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(6.dp),
                        onClick = {
                            navController.navigate("detalle/${sesion.id}")
                        }
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            // 🔹 TITULO
                            Text(
                                sesion.nombreRutina,
                                style = MaterialTheme.typography.titleLarge
                            )

                            // 🔹 FECHA
                            Text(
                                "📅 ${sesion.fecha}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            // 🔹 ESTADO
                            AssistChip(
                                onClick = {},
                                label = {
                                    Text(
                                        if (sesion.completada)
                                            "Completada ✅"
                                        else
                                            "Pendiente ⏳"
                                    )
                                }
                            )

                            // 🔹 BOTÓN ELIMINAR
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(
                                    onClick = {
                                        viewModel.eliminarSesion(sesion)
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "Eliminar"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}