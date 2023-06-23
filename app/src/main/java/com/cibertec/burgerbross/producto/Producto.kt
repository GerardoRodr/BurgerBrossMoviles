package com.cibertec.burgerbross.producto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.cibertec.burgerbross.categoria.CategoriaProducto

@Entity(tableName = Producto.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = CategoriaProducto::class,
        parentColumns = ["id_categoria_prod"],
        childColumns = ["id_categoria_prod"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Producto(
    @ColumnInfo(name = "nombre_producto")
    val nombreProducto: String,

    @ColumnInfo(name = "id_categoria_prod")
    val idCategoriaProducto: Int,

    @ColumnInfo(name = "desc_prod")
    val descripcion: String,

    @ColumnInfo(name = "stock")
    val stock: Int,

    @ColumnInfo(name = "precio_producto")
    val precioProducto: Double) {

    companion object {
        const val TABLE_NAME = "tb_productos"
    }

    var cantProd: Int = 0

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_producto")
    var idProducto: Int = 0

    var img: Int = 0 // Variable para almacenar la ruta o identificador de la imagen
}