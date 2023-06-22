package com.cibertec.burgerbross.pedido

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.producto.Producto

class DetallePedidoAdapter : RecyclerView.Adapter<DetallePedidoViewHolder>() {

    private var detallePedidoList = emptyList<Producto>()

    fun setDetallePedidos(prod: List<Producto>) {
        this.detallePedidoList = prod
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetallePedidoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetallePedidoViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = detallePedidoList.size

    override fun onBindViewHolder(holder: DetallePedidoViewHolder, position: Int) {
        val prod: Producto = detallePedidoList[position]
        holder.bind(prod)
    }
}