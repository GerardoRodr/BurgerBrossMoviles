package com.cibertec.burgerbross.producto

data class ProductoDetallePedido(
    val cantidad: Int,
    val precioProducto: Double,
    val producto: String,
    val subtotal: Double
)
