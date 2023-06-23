package com.cibertec.burgerbross.detallepedido

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.producto.Producto

class DetallePedidoFirestoreAdapter : RecyclerView.Adapter<DetallePedidoFirestoreViewHolder>() {

    private var detallePedidoList = emptyList<DetallePedidoFirestore>()

    fun setDetallePedidos(prod: List<DetallePedidoFirestore>) {
        this.detallePedidoList = prod
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetallePedidoFirestoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetallePedidoFirestoreViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = detallePedidoList.size

    override fun onBindViewHolder(holder: DetallePedidoFirestoreViewHolder, position: Int) {
        val prod: DetallePedidoFirestore = detallePedidoList[position]
        holder.bind(prod)
    }
}