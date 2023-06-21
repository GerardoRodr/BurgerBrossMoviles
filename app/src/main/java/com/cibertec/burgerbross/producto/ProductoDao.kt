package com.cibertec.burgerbross.producto

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductoDao {
    @Insert
    fun insert(producto: Producto)

    @Update
    fun update(producto: Producto)

    @Delete
    fun delete(producto: Producto)

    @Query("Select * from " + Producto.TABLE_NAME)
    fun list(): LiveData<List<Producto>>

    @Query("Select * from " + Producto.TABLE_NAME + " WHERE id_categoria_prod = :categoryId")
    fun listByIdCategoria(categoryId: Int): LiveData<List<Producto>>
}