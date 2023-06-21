package com.cibertec.burgerbross.producto

data class ProductoFirestore(
    val nombre_producto: String,
    val desc_producto: String,
    val categoria_producto: String,
    val precio_producto: Double,
    val stock: Int,
    val img: Int
)