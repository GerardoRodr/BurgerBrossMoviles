package com.cibertec.burguerbross.categorias

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R

class CategoriaViewHolder(inflater: LayoutInflater, parent : ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_categoria,parent,false)) {

    private var imgIcono : ImageView?  =    null
    private var txtTitulo : TextView?   =    null


    init {
        imgIcono = itemView.findViewById(R.id.iconoCategoria)
        txtTitulo = itemView.findViewById(R.id.nombreCategoria)
    }

    fun bind(categoria: Categoria) {
        txtTitulo?.text         = categoria.titulo
        imgIcono?.setImageResource(categoria.icono)
    }

}