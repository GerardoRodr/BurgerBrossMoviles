package com.cibertec.burgerbross

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cibertec.burgerbross.categoria.CategoriaProducto
import com.cibertec.burgerbross.categoria.CategoriaProductoDao
import com.cibertec.burgerbross.producto.Producto
import com.cibertec.burgerbross.producto.ProductoDao

@Database(entities = [Producto::class, CategoriaProducto::class], version = 1)
abstract class BurgerBrossDatabase: RoomDatabase() {

    abstract fun productoDao(): ProductoDao

    abstract fun categoriaProdDao(): CategoriaProductoDao

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