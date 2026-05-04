package com.example.fitlog.data.local

import androidx.room.*
import com.example.fitlog.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FitLogDao {

    // 🔹 Ver historial (ordenado por fecha)
    @Query("SELECT * FROM sesiones ORDER BY fecha DESC")
    fun obtenerSesiones(): Flow<List<Sesion>>

    // 🔹 Ver detalle con ejercicios
    @Transaction
    @Query("SELECT * FROM sesiones WHERE id = :id")
    fun obtenerDetalle(id: Int): Flow<SesionConEjercicios?>

    // 🔹 Insertar sesión
    @Insert
    suspend fun insertarSesion(sesion: Sesion): Long

    // 🔹 Insertar ejercicios
    @Insert
    suspend fun insertarEjercicios(ejercicios: List<Ejercicio>)

    // 🔹 Cambiar estado
    @Query("UPDATE sesiones SET completada = :estado WHERE id = :id")
    suspend fun cambiarEstado(id: Int, estado: Boolean)

    // 🔹 Eliminar sesión
    @Delete
    suspend fun eliminarSesion(sesion: Sesion)
}