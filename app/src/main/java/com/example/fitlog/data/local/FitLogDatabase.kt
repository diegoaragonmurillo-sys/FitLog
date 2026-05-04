package com.example.fitlog.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitlog.data.model.*

@Database(
    entities = [Sesion::class, Ejercicio::class],
    version = 1
)
abstract class FitLogDatabase : RoomDatabase() {

    abstract fun dao(): FitLogDao

    companion object {

        @Volatile
        private var INSTANCE: FitLogDatabase? = null

        fun getDatabase(context: Context): FitLogDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitLogDatabase::class.java,
                    "fitlog_db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}