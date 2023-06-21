package com.cibertec.burgerbross.categoria

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoriaProductoDao {
    @Insert
    fun insert(categoria: CategoriaProducto)

    @Update
    fun update(categoria: CategoriaProducto)

    @Delete
    fun delete(categoria: CategoriaProducto)

    @Query("Select * from " + CategoriaProducto.TABLE_NAME)
    fun list(): LiveData<List<CategoriaProducto>>
}
