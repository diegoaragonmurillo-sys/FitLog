package com.example.fitlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    val detalle by viewModel.obtenerDetalle(id).collectAsState(initial = null)

    Scaffold(
        containerColor = Color(0xFF121212),
        topBar = {
            TopAppBar(
                title = { Text("Detalle", color = Color.White) },
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

        detalle?.let { data ->

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // 🔥 CARD PRINCIPAL
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E1E)
                        ),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                data.sesion.nombreRutina,
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color.White
                            )

                            Text(
                                "📅 ${data.sesion.fecha}",
                                color = Color.Gray
                            )

                            AssistChip(
                                onClick = {
                                    viewModel.cambiarEstado(
                                        data.sesion.id,
                                        !data.sesion.completada
                                    )
                                },
                                label = {
                                    Text(
                                        if (data.sesion.completada)
                                            "Completada ✅"
                                        else
                                            "Pendiente ⏳"
                                    )
                                },
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = if (data.sesion.completada)
                                        Color(0xFF00C853)
                                    else
                                        Color(0xFFFFA000)
                                )
                            )
                        }
                    }
                }

                // 🔥 TITULO
                item {
                    Text(
                        "Ejercicios",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }

                // 🔥 LISTA
                items(data.ejercicios) { ejercicio ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E1E)
                        ),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                ejercicio.nombre,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )

                            Text(
                                "${ejercicio.series} series x ${ejercicio.repeticiones} reps",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF00C853))
            }
        }
    }
}