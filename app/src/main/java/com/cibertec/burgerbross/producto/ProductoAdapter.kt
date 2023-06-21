package com.cibertec.burgerbross.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(val mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<ProductoViewHolder>(){

    //Cambiado de emptyList<Producto>() a mutableListOf<Producto>()
    private var productoList = mutableListOf<Producto>()

    fun setProductos(producto: List<Producto>) {
        productoList.clear()
        productoList.addAll(producto)
        notifyDataSetChanged()
        /*this.productoList = producto
        this.notifyDataSetChanged()*/
    }

    fun updateCantidad(producto: Producto, nuevaCantidad: Int) {
        val index = productoList.indexOf(producto)
        if (index != -1) {
            val productoActualizado = productoList[index]
            productoActualizado.cantProd = nuevaCantidad
            productoList[index] = productoActualizado
            notifyItemChanged(index)
        }
    }

    override fun getItemCount(): Int = productoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto: Producto = productoList[position]
        holder.bind(producto)

        holder.btnDecrease.setOnClickListener {
            mItemClickListener.onBtnDecreaseClick(producto)
        }

        holder.btnIncrease.setOnClickListener {
            mItemClickListener.onBtnIncreaseClick(producto)
        }
    }

    interface ItemClickListener{
        fun onBtnIncreaseClick(prodItem: Producto)
        fun onBtnDecreaseClick(prodItem: Producto)
    }
}