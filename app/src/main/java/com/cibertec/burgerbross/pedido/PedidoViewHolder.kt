package com.cibertec.burgerbross.pedido

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import java.text.SimpleDateFormat

class PedidoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pedido, parent, false)) {

    private var itemPedidoNombre : TextView? = null
    private var itemPedidoTotal : TextView? = null
    private var itemPedidoFecha : TextView? = null
    private var itemPedidoEstado : TextView? = null
    private var itemPedidoHora : TextView? = null
    var switchEstadoPedido : Switch? = null
    var btnEliminar : ImageButton? = null

    init {
        itemPedidoNombre = itemView.findViewById(R.id.itemPedidoNombre)
        itemPedidoTotal = itemView.findViewById(R.id.itemPedidoTotal)
        itemPedidoFecha = itemView.findViewById(R.id.itemPedidoFecha)
        itemPedidoEstado = itemView.findViewById(R.id.itemPedidoEstado)
        itemPedidoHora = itemView.findViewById(R.id.itemPedidoHora)
        switchEstadoPedido = itemView.findViewById(R.id.switchEstadoPedido)
        btnEliminar = itemView.findViewById(R.id.btnEliminarPedidoFirestore)
    }

    fun bind(pedidoFirestore: PedidoFirestore) {
        //VALIDACION PARA DARLE UN STRING PERSONALIZADO AL ESTADO DEL PEDIDO
        var estadoPedido : String =
            if(pedidoFirestore.estadoPedido) {
                "Entregado"
            } else {
                "Pendiente"
            }


        // Cambiar el color del TextView según la condición
        itemPedidoEstado?.let {
            val color = if (pedidoFirestore.estadoPedido) {
                // Estado del pedido es verdadero (true)
                Color.parseColor("#4BB543")
            } else {
                // Estado del pedido es falso (false)
                Color.parseColor("#FFD700")
            }

            it.setTextColor(color)
        }

        val dateFormat = SimpleDateFormat("dd/MM/yy")
        val timeFormat = SimpleDateFormat("hh:mm a")

        itemPedidoNombre?.text = pedidoFirestore.nombreCliente
        itemPedidoTotal?.text = pedidoFirestore.totalPedido.toString()
        //FECHA Y HORA CON FORMATO PARA SOLO MOSTRAR LA FECHA O LA HORA
        itemPedidoFecha?.text = dateFormat.format(pedidoFirestore.fechaPedido.toDate())
        itemPedidoHora?.text = timeFormat.format(pedidoFirestore.fechaPedido.toDate())
        //-------------------------------------------------------------------------------
        itemPedidoEstado?.text = estadoPedido
        switchEstadoPedido?.isChecked = pedidoFirestore.estadoPedido
    }
}