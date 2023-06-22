package com.cibertec.burgerbross.pedido

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.producto.Producto

class DetallePedidoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
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
    fun bind(prod: Producto) {
        var subTotal = prod.cantProd * prod.precioProducto

        det_ped_producto?.text = prod.nombreProducto
        det_ped_precio?.text = prod.precioProducto.toString()
        det_ped_cantidad?.text = prod.cantProd.toString()
        det_ped_subtotal?.text = subTotal.toString()
    }


}