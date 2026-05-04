package com.example.fitlog.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sesiones")
data class Sesion(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val fecha: String,
    val nombreRutina: String,
    val completada: Boolean = false
)