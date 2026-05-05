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
fun HistorialScreen(
    navController: NavController,
    viewModel: FitLogViewModel
) {
    val sesiones by viewModel.sesiones.collectAsState()

    Scaffold(
        containerColor = Color(0xFF121212), // 🔥 fondo oscuro
        topBar = {
            TopAppBar(
                title = {
                    Text("FitLog 💪", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E1E)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("crear") },
                containerColor = Color(0xFF00C853) // verde fitness
            ) {
                Icon(Icons.Default.Add, contentDescription = "", tint = Color.White)
            }
        }
    ) { padding ->

        if (sesiones.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Empieza tu primera rutina 💪", color = Color.White)
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(sesiones) { sesion ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E1E)
                        ),
                        elevation = CardDefaults.cardElevation(8.dp),
                        onClick = {
                            navController.navigate("detalle/${sesion.id}")
                        }
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                sesion.nombreRutina,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )

                            Text(
                                "📅 ${sesion.fecha}",
                                color = Color.Gray
                            )

                            AssistChip(
                                onClick = {},
                                label = {
                                    Text(
                                        if (sesion.completada)
                                            "Completada ✅"
                                        else
                                            "Pendiente ⏳"
                                    )
                                },
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = if (sesion.completada)
                                        Color(0xFF00C853)
                                    else
                                        Color(0xFFFFA000)
                                )
                            )

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
                                        contentDescription = "",
                                        tint = Color.Red
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