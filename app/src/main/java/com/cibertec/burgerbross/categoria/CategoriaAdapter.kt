package com.cibertec.burgerbross.categoria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoriaAdapter(val list: List<Categoria>):
    RecyclerView.Adapter<CategoriaViewHolder>()  {
    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriaViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria: Categoria = list[position]
        holder.bind(categoria)
    }
}