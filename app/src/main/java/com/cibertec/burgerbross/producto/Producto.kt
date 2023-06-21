package com.cibertec.burgerbross.producto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Producto.TABLE_NAME)
data class Producto(
    @ColumnInfo(name = "nombre_producto")
    val nombreProducto: String,

    @ColumnInfo(name = "id_categoria_prod")
    val idCategoriaProducto: Int,

    @ColumnInfo(name = "desc_prod")
    val descripcion: String,

    val stock: Int,

    @ColumnInfo(name = "precio_producto")
    val precioProducto: Double
) {
    companion object {
        const val TABLE_NAME = "tb_productos"
    }

    @ColumnInfo(name = "id_producto")
    @PrimaryKey(autoGenerate = true)
    val idProducto: Int = 0
}
