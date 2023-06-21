package com.cibertec.burgerbross

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cibertec.burgerbross.producto.Producto

@Database(entities = [Producto::class], version = 1)
abstract class BurgerBrossDatabase: RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "burgerbross_database"

        @Volatile
        private var INSTANCE: BurgerBrossDatabase? = null

        fun getInstance(context: Context): BurgerBrossDatabase? {
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BurgerBrossDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            return INSTANCE
        }
    }
}