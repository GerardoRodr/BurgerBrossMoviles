package com.cibertec.burgerbross.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StockAdapter(val mItemClickListener: StockAdapter.ItemClickListener) : RecyclerView.Adapter<StockViewHolder>() {

    private var productoList = mutableListOf<Producto>()

    fun setProductos(producto: List<Producto>) {
        productoList.clear()
        productoList.addAll(producto)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StockViewHolder(inflater, parent)
    }

    override fun getItemCount() = productoList.size

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val producto: Producto = productoList[position]
        holder.bind(producto)

        holder.btnModificarStock.setOnClickListener {
            mItemClickListener.onBtnModificarStockClick(producto)
        }
    }

    interface ItemClickListener {
        fun onBtnModificarStockClick(producto: Producto)
    }

}