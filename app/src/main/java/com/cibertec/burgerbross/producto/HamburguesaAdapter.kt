package com.cibertec.burgerbross.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HamburguesaAdapter(val list: List<ProductoFirestore>):
    RecyclerView.Adapter<ProductoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto: ProductoFirestore = list[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int = list.size


}