package com.cibertec.burgerbross.categoria

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CategoriaProducto.TABLE_NAME)
data class CategoriaProducto(
    @ColumnInfo(name = "nombre_categoria")
    val nombreCategoria: String) {

    companion object {
        const val TABLE_NAME = "tb_categoria_productos"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_categoria_prod")
    var idCategoriaProducto: Int = 0

    var iconoCategoria: Int = 0 // Variable para almacenar la ruta o identificador de la imagen
}
