package com.cibertec.burgerbross.pedido

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.producto.Producto
import com.cibertec.burgerbross.producto.ProductoAdapter
import com.cibertec.burgerbross.producto.ProductoViewHolder
import com.cibertec.burgerbross.producto.ProductoViewModel
import com.cibertec.burgerbross.producto.ProductosPredefinidos

class IngresarDetallePedidoActivity: AppCompatActivity(), ProductoAdapter.ItemClickListener {

    private lateinit var prodViewModel: ProductoViewModel
    lateinit var listaProductos: List<Producto>
    private lateinit var prodVH: ProductoViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_pedido_detalle)

        val actionBar = supportActionBar
        actionBar?.hide()

        val receivedBundle = intent.extras
        val receivedCategoryId = receivedBundle?.getInt("categoryId")
        val receivedTitulo = receivedBundle?.getString("titulo")

        val tituloActivity = findViewById<TextView>(R.id.activity_ingresar_detalle_titulo)
        tituloActivity.text = receivedTitulo

        prodViewModel = run {
            ViewModelProvider(this)[ProductoViewModel::class.java]
        }

        //CARGA DE DATOS DE PRODUCTOS
        ProductosPredefinidos.initializeHamburguesas(this)

        val recyclerProds = findViewById<RecyclerView>(R.id.recycler_IngresarPedido_Producto)

        val adapter = ProductoAdapter(this)

        recyclerProds.adapter = adapter
        recyclerProds.layoutManager = LinearLayoutManager(applicationContext)

        if (receivedCategoryId != null) {
            prodViewModel.prodByIdCat(receivedCategoryId)?.observe(this) {prods ->
                if (prods.isNotEmpty()) {
                    recyclerProds.visibility = View.VISIBLE

                    listaProductos = prods

                    prods?.let {
                        adapter.setProductos(it)
                    }
                } else {
                    recyclerProds.visibility = View.GONE
                }
            }
        }

    }

    override fun onBtnIncreaseClick(prodItem: Producto) {
        println("INCREMENTADO")
    }

    override fun onBtnDecreaseClick(prodItem: Producto) {
        println("DECREMENTADO")
    }

    override fun onItemClick(prodItem: Producto) {
        TODO("Posible Funcion para mostrar detalle del producto")
    }
}