package com.cibertec.burgerbross.pedido

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.producto.HamburguesaAdapter
import com.cibertec.burgerbross.producto.ProductoFirestore
import com.cibertec.burgerbross.producto.ProductoViewModel

class IngPedHambActivity: AppCompatActivity() {

    val listaFinalProductos = mutableListOf<ProductoFirestore>()

    private lateinit var viewModel: ProductoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_pedido_hamburguesas)

        val actionBar = supportActionBar
        actionBar?.hide()

        viewModel = ViewModelProvider(this)[ProductoViewModel::class.java]
        viewModel.getProductosFirestore()

        val RecyclerProductos = findViewById<RecyclerView>(R.id.recycler_IngresarPedido_Hamburguesas)

        viewModel.listProductoMutable.observe(this) {listProductos ->
            if(listProductos.isNotEmpty()) {
                for (p in listProductos){
                    listaFinalProductos.add(p)
                }
            }
        }

        RecyclerProductos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = HamburguesaAdapter(listaFinalProductos)
        }
    }
}