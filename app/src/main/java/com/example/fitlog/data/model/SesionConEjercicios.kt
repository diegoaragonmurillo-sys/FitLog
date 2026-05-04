package com.example.fitlog.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class SesionConEjercicios(

    @Embedded
    val sesion: Sesion,

    @Relation(
        parentColumn = "id",
        entityColumn = "sesionId"
    )
    val ejercicios: List<Ejercicio>
)