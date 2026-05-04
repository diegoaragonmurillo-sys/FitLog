package com.example.fitlog.data.repository

import com.example.fitlog.data.local.FitLogDao
import com.example.fitlog.data.model.*

class FitLogRepository(
    private val dao: FitLogDao
) {

    fun obtenerSesiones() = dao.obtenerSesiones()

    fun obtenerDetalle(id: Int) = dao.obtenerDetalle(id)

    suspend fun guardarSesion(
        sesion: Sesion,
        ejercicios: List<Ejercicio>
    ) {
        val idSesion = dao.insertarSesion(sesion).toInt()

        val lista = ejercicios.map {
            it.copy(sesionId = idSesion)
        }

        dao.insertarEjercicios(lista)
    }

    suspend fun cambiarEstado(id: Int, estado: Boolean) {
        dao.cambiarEstado(id, estado)
    }

    suspend fun eliminarSesion(sesion: Sesion) {
        dao.eliminarSesion(sesion)
    }
}