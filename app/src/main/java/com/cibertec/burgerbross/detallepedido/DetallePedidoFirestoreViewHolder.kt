package com.cibertec.burgerbross.detallepedido

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.producto.Producto

class DetallePedidoFirestoreViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_detalle_pedido, parent, false)){

    private var det_ped_producto : TextView? = null
    private var det_ped_precio : TextView? = null
    private var det_ped_subtotal : TextView? = null
    private var det_ped_cantidad : TextView? = null

    init {
        det_ped_producto = itemView.findViewById(R.id.det_ped_producto)
        det_ped_precio = itemView.findViewById(R.id.det_ped_precio)
        det_ped_subtotal = itemView.findViewById(R.id.det_ped_subtotal)
        det_ped_cantidad = itemView.findViewById(R.id.det_ped_cantidad)
    }
    fun bind(prod: DetallePedidoFirestore) {
        var subTotal = prod.cantidad * prod.precio_producto

        det_ped_producto?.text = prod.producto
        det_ped_precio?.text = prod.precio_producto.toString()
        det_ped_cantidad?.text = prod.cantidad.toString()
        det_ped_subtotal?.text = subTotal.toString()
    }


}