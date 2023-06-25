package com.cibertec.burgerbross.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R

class StockViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_detalle_producto, parent, false)) {

    private var imgProducto : ImageView? = null
    private var detProdNombreProducto: TextView? = null
    private var detProdDescProducto: TextView? = null
    private var detProdStock: TextView? = null
    var btnModificarStock: Button = itemView.findViewById(R.id.btnModificarStock)

    init {
        imgProducto = itemView.findViewById(R.id.detProdImgProducto)
        detProdNombreProducto = itemView.findViewById(R.id.detProdNombreProducto)
        detProdDescProducto = itemView.findViewById(R.id.detProdDescProducto)
        detProdStock = itemView.findViewById(R.id.detProdStock)
    }

    fun bind(producto: Producto) {
        imgProducto?.setImageResource(producto.img)
        detProdNombreProducto?.text = producto.nombreProducto
        detProdDescProducto?.text = producto.descripcion
        detProdStock?.text = producto.stock.toString()
    }
}