package com.cibertec.burgerbross.categoria

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R

class CategoriaProductoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_categoria, parent, false)){

    private var nombreCategoria : TextView? = null
    private var imgIcono : ImageView? = null

    init {
        nombreCategoria = itemView.findViewById(R.id.nombreCategoria)
        imgIcono = itemView.findViewById(R.id.iconoCategoria)
    }

    fun bind(categoria: CategoriaProducto) {
        nombreCategoria?.text = categoria.nombreCategoria
        imgIcono?.setImageResource(categoria.iconoCategoria)
    }
}