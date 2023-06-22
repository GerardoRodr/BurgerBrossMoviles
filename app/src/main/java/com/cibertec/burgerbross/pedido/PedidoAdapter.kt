package com.cibertec.burgerbross.pedido

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PedidoAdapter(val mItemClickListener: ItemClickListener) : RecyclerView.Adapter<PedidoViewHolder>() {

    private var pedidosList = emptyList<PedidoFirestore>()

    fun setPedidos(pedido: List<PedidoFirestore>) {
        this.pedidosList = pedido
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PedidoViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = pedidosList.size

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido: PedidoFirestore = pedidosList[position]
        holder.bind(pedido)

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(pedido)
        }

        holder.switchEstadoPedido?.setOnCheckedChangeListener { buttonView, isChecked ->

            var estado: Boolean = isChecked

            mItemClickListener.setOnCheckedChangeListener(pedido, estado)
        }
    }

    interface ItemClickListener {
        fun onItemClick(pedido: PedidoFirestore)

        fun setOnCheckedChangeListener(pedido: PedidoFirestore, estado: Boolean)
    }
}