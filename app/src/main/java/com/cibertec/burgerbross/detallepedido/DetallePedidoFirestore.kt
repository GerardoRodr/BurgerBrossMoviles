package com.cibertec.burgerbross.detallepedido

data class DetallePedidoFirestore(
    val cantidad: Int,
    val idProducto: Int,
    val precio_producto: Double,
    val producto: String,
    val subtotal: Double
) {
    var nombreCliente : String = ""
}