package com.cibertec.burgerbross.pedido

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.producto.Producto
import com.cibertec.burgerbross.producto.ProductoAdapter
import com.cibertec.burgerbross.producto.ProductoViewModel
import com.cibertec.burgerbross.producto.ProductosManager
import com.cibertec.burgerbross.producto.ProductosPredefinidos

class IngresarDetallePedidoActivity: AppCompatActivity(), ProductoAdapter.ItemClickListener {

    private lateinit var prodViewModel: ProductoViewModel
    lateinit var listaProductos: List<Producto>
    private lateinit var productoAdapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_pedido_detalle)

        val actionBar = supportActionBar
        actionBar?.hide()

        val btnContinuar = findViewById<ImageButton>(R.id.btn_adelante_ingresar_pedido)
        btnContinuar.setOnClickListener {
            val intent = Intent(this, FinalizarPedidoActivity::class.java)
            startActivity(intent)
        }

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

        //CARGA DE DATOS DE PRODUCTOS("BEBIDAS")
        ProductosPredefinidos.initializeBebidas(this)

        //CARGA DE DATOS DE PRODUCTOS("COMPLEMENTOS")
        ProductosPredefinidos.initializeComplementos(this)

        //CARGA DE DATOS DE PRODUCTOS("ADICIONALES")
        ProductosPredefinidos.initializeAdicionales(this)

        val recyclerProds = findViewById<RecyclerView>(R.id.recycler_IngresarPedido_Producto)

        val adapter = ProductoAdapter(this)

        recyclerProds.adapter = adapter
        recyclerProds.layoutManager = LinearLayoutManager(applicationContext)

        productoAdapter = adapter

        if (receivedCategoryId != null) {
            prodViewModel.prodByIdCat(receivedCategoryId)?.observe(this) {prods ->
                if (prods.isNotEmpty()) {
                    recyclerProds.visibility = View.VISIBLE

                    // Recorre la lista de productos de la base de datos
                    for (p in prods) {
                        // Busca el producto en la lista de ProductosManager por su id
                        val productoEnManager = ProductosManager.getProductosList().find { it.idProducto == p.idProducto }

                        //SOLO SE MODIFICA SI EL productoEnManager no es nulo (Es decir si encuentra un producto en la lista con esa id)
                        if(productoEnManager != null) {
                            p.cantProd = productoEnManager.cantProd
                        }
                    }

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
        if (prodItem.cantProd == 0) {
            //Añade +1 a la cantidad del producto ("De base tiene 0")
            prodItem.cantProd += 1
            //Se agrega el producto a la lista de Producto
            ProductosManager.addProducto(prodItem)
            //Se actualiza la cantidad en el RecyclerView
            productoAdapter.updateCantidad(prodItem, prodItem.cantProd)
        } else {
            //Añade +1 a la cantidad del producto ("De base tiene más de 0")
            prodItem.cantProd += 1
            //Se ACTUALIZA el producto en la lista de Producto
            ProductosManager.updateProducto(prodItem)
            //Se actualiza la cantidad en el RecyclerView
            productoAdapter.updateCantidad(prodItem, prodItem.cantProd)
        }
    }

    override fun onBtnDecreaseClick(prodItem: Producto) {
        if (prodItem.cantProd != 0) {
            //Resta -1 a la cantidad del producto ("De base tiene más de 0")
            prodItem.cantProd -= 1

            if (prodItem.cantProd == 0) {
                ProductosManager.delProducto(prodItem)
                //Se actualiza la cantidad en el RecyclerView
                productoAdapter.updateCantidad(prodItem, prodItem.cantProd)
            } else {
                //Se actualiza el producto en la lista de Productos
                ProductosManager.updateProducto(prodItem)
                //Se actualiza la cantidad en el RecyclerView
                productoAdapter.updateCantidad(prodItem, prodItem.cantProd)
            }
        }
    }
}