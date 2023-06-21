package com.cibertec.burgerbross.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R

class ProductoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_producto, parent, false)){

    private var imgProducto : ImageView? = null
    private var nombreProducto : TextView? = null
    private var descProducto : TextView? = null
    var btnDecrease : Button = itemView.findViewById(R.id.btnDecrease)
    var btnIncrease : Button = itemView.findViewById(R.id.btnIncrease)
    var cantProd : TextView = itemView.findViewById(R.id.cantidadProd)

    init {
        imgProducto = itemView.findViewById(R.id.imgProducto)
        nombreProducto = itemView.findViewById(R.id.nombreProducto)
        descProducto = itemView.findViewById(R.id.descProducto)
    }

    fun bind(producto: Producto) {
        nombreProducto?.text = producto.nombreProducto
        descProducto?.text = producto.descripcion
        imgProducto?.setImageResource(producto.img)
    }
}