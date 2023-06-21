package com.cibertec.burgerbross.pedido

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.producto.Producto
import com.cibertec.burgerbross.producto.ProductoAdapter
import com.cibertec.burgerbross.producto.ProductoViewHolder
import com.cibertec.burgerbross.producto.ProductoViewModel
import com.cibertec.burgerbross.producto.ProductosManager
import com.cibertec.burgerbross.producto.ProductosPredefinidos

class IngresarDetallePedidoActivity: AppCompatActivity(), ProductoAdapter.ItemClickListener {

    private var lastSelectedItem: Producto? = null
    private lateinit var prodViewModel: ProductoViewModel
    lateinit var listaProductos: List<Producto>
    private lateinit var productoAdapter: ProductoAdapter

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

        //CARGA DE DATOS DE PRODUCTOS("HAMBURGUESAS")
        ProductosPredefinidos.initializeHamburguesas(this)

        val recyclerProds = findViewById<RecyclerView>(R.id.recycler_IngresarPedido_Producto)

        val adapter = ProductoAdapter(this)

        recyclerProds.adapter = adapter
        recyclerProds.layoutManager = LinearLayoutManager(applicationContext)

        productoAdapter = adapter

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
        if (lastSelectedItem != null && lastSelectedItem == prodItem) {
            //Le añade +1 a la cantidad del ultimo item seleccionado
            lastSelectedItem!!.cantProd += 1
            //Lo actualiza en la lista de Productos que persiste en varios activity's
            ProductosManager.updateProducto(lastSelectedItem!!)
            //Actualiza el RecyclerView
            productoAdapter.updateCantidad(lastSelectedItem!!, lastSelectedItem!!.cantProd)
            //TEST
            println(prodItem.nombreProducto + " CANTIDAD: " + prodItem.cantProd)
        } else {
            //Añade +1 a la cantidad del producto ("De base tiene 0")
            prodItem.cantProd += 1
            //Se agrega el producto a la lista de Producto
            ProductosManager.addProducto(prodItem)
            //Se le da el valor de ese producto a la variable lastSelectedItem ("Ultimo producto seleccionado")
            lastSelectedItem = prodItem
            //Se actualiza la cantidad en el RecyclerView
            productoAdapter.updateCantidad(prodItem, prodItem.cantProd)
            //TEST TODO:ELIMINAR EL PRINTLN
            println(prodItem.nombreProducto + " CANTIDAD: " + prodItem.cantProd)
        }
    }

    override fun onBtnDecreaseClick(prodItem: Producto) {
        if (lastSelectedItem != null && lastSelectedItem == prodItem) {
            //Le añade +1 a la cantidad del ultimo item seleccionado
            lastSelectedItem!!.cantProd -= 1
            //Lo actualiza en la lista de Productos que persiste en varios activity's
            ProductosManager.updateProducto(lastSelectedItem!!)
            //Actualiza el RecyclerView
            productoAdapter.updateCantidad(lastSelectedItem!!, lastSelectedItem!!.cantProd)
            //TEST
            println(prodItem.nombreProducto + " CANTIDAD: " + prodItem.cantProd)
        } else {
            Toast.makeText(this, "No existe ningun producto para eliminar", Toast.LENGTH_SHORT).show()
        }
    }
}