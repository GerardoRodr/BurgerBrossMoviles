package com.cibertec.burgerbross.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(val mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<ProductoViewHolder>(){

    private var productoList = emptyList<Producto>()

    fun setProductos(producto: List<Producto>) {
        this.productoList = producto
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = productoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto: Producto = productoList[position]
        holder.bind(producto)

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(producto)
        }
    }

    interface ItemClickListener{
        fun onItemClick(prodItem: Producto)
    }
}