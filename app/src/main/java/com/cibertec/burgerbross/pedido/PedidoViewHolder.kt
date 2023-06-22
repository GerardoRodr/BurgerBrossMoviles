package com.cibertec.burgerbross.pedido

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R

class PedidoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pedido, parent, false)) {

    private var itemPedidoNombre : TextView? = null
    private var itemPedidoTotal : TextView? = null
    private var itemPedidoFecha : TextView? = null
    private var itemPedidoEstado : TextView? = null

    init {
        itemPedidoNombre = itemView.findViewById(R.id.itemPedidoNombre)
        itemPedidoTotal = itemView.findViewById(R.id.itemPedidoTotal)
        itemPedidoFecha = itemView.findViewById(R.id.itemPedidoFecha)
        itemPedidoEstado = itemView.findViewById(R.id.itemPedidoEstado)
    }

    fun bind(pedidoFirestore: PedidoFirestore) {
        //VALIDACION PARA DARLE UN STRING PERSONALIZADO AL ESTADO DEL PEDIDO
        var estadoPedido : String =
            if(pedidoFirestore.estadoPedido) {
                "Entregado"
            } else {
                "Pendiente"
            }

        itemPedidoNombre?.text = pedidoFirestore.nombreCliente
        itemPedidoTotal?.text = pedidoFirestore.totalPedido.toString()
        itemPedidoFecha?.text = pedidoFirestore.fechaPedido.toDate().toString()
        itemPedidoEstado?.text = estadoPedido
    }
}