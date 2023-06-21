package com.cibertec.burgerbross.categoria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoriaProductoAdapter(val mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<CategoriaProductoViewHolder>(){

    private var categoriaList = emptyList<CategoriaProducto>()

    fun setCategorias(categoria: List<CategoriaProducto>) {
        this.categoriaList = categoria
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = categoriaList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriaProductoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CategoriaProductoViewHolder, position: Int) {
        val categoria: CategoriaProducto = categoriaList[position]
        holder.bind(categoria)

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(categoria)
        }
    }

    interface ItemClickListener{
        fun onItemClick(categItem: CategoriaProducto)
    }
}
