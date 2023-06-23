package com.cibertec.burgerbross.pedido

import com.google.firebase.Timestamp
import java.util.Date

data class PedidoFirestore(
    val estadoPedido: Boolean,
    val fechaPedido: Timestamp,
    val nombreCliente: String,
    val totalPedido: Double
) {
    var documentId : String? = null
}