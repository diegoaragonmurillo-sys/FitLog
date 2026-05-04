package com.example.fitlog.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "ejercicios",
    foreignKeys = [
        ForeignKey(
            entity = Sesion::class,
            parentColumns = ["id"],
            childColumns = ["sesionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Ejercicio(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val sesionId: Int,
    val nombre: String,
    val series: Int,
    val repeticiones: Int
)