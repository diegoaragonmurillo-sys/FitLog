package com.example.fitlog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlog.data.model.*
import com.example.fitlog.data.repository.FitLogRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FitLogViewModel(
    private val repository: FitLogRepository
) : ViewModel() {

    val sesiones = repository.obtenerSesiones()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun obtenerDetalle(id: Int) = repository.obtenerDetalle(id)

    fun guardarSesion(
        fecha: String,
        nombre: String,
        ejercicios: List<Ejercicio>
    ) {
        viewModelScope.launch {
            val sesion = Sesion(
                fecha = fecha,
                nombreRutina = nombre
            )

            repository.guardarSesion(sesion, ejercicios)
        }
    }

    fun cambiarEstado(id: Int, estado: Boolean) {
        viewModelScope.launch {
            repository.cambiarEstado(id, estado)
        }
    }

    fun eliminarSesion(sesion: Sesion) {
        viewModelScope.launch {
            repository.eliminarSesion(sesion)
        }
    }
}