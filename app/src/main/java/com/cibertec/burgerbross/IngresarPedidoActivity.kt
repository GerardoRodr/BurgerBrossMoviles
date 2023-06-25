package com.cibertec.burgerbross

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.cibertec.burgerbross.categoria.CategoriaProducto
import com.cibertec.burgerbross.categoria.CategoriaProductoAdapter
import com.cibertec.burgerbross.categoria.CategoriaProductoViewModel
import com.cibertec.burgerbross.pedido.FinalizarPedidoActivity
import com.cibertec.burgerbross.pedido.IngresarDetallePedidoActivity
import com.cibertec.burgerbross.producto.ProductosManager

class IngresarPedidoActivity: AppCompatActivity(), CategoriaProductoAdapter.ItemClickListener {

    private lateinit var catViewModel: CategoriaProductoViewModel
    lateinit var listaCategorias: List<CategoriaProducto>
    private val productoEnManager = ProductosManager.getProductosList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_pedido)

        val actionBar = supportActionBar
        actionBar?.hide()

        val btnEliminarPedido = findViewById<Button>(R.id.btnEliminarPedido)

        btnEliminarPedido.setOnClickListener {

            if (productoEnManager.isNotEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Eliminar pedido")
                builder.setMessage("¿Estás seguro de que deseas eliminar el pedido?")
                builder.setPositiveButton("Sí") { dialog, which ->
                    ProductosManager.eliminarPrePedido()
                    Toast.makeText(this, "Se elimino el pedido", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No") { dialog, which ->
                    println("NO SE ElIMINO EL PEDIDO")
                }
                builder.show()
            } else {
                Toast.makeText(this, "Aun no has agregado nada al pedido", Toast.LENGTH_SHORT).show()
            }
        }

        val btnAdelante = findViewById<ImageButton>(R.id.btn_adelante_ingresar_pedido)
        btnAdelante.setOnClickListener {
            if (productoEnManager.isNotEmpty()) {
                val intent = Intent(this, FinalizarPedidoActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Aun no has agregado nada al pedido", Toast.LENGTH_LONG).show()
            }
        }

        catViewModel = run {
            ViewModelProvider(this)[CategoriaProductoViewModel::class.java]
        }

        val recyclerCategoria = findViewById<RecyclerView>(R.id.recyclerCategoriasIngresarPedido)

        val adapter = CategoriaProductoAdapter(this)

        recyclerCategoria.adapter = adapter
        recyclerCategoria.layoutManager = GridLayoutManager(this,2)

        catViewModel.cat?.observe(this) {cats ->
            if(cats.isNotEmpty()) {
                recyclerCategoria.visibility = View.VISIBLE

                listaCategorias = cats

                cats?.let {
                    adapter.setCategorias(it)
                }
            } else {
                recyclerCategoria.visibility = View.GONE
            }
        }
    }

    override fun onItemClick(categItem: CategoriaProducto) {
        val intent = Intent(this, IngresarDetallePedidoActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("categoryId", categItem.idCategoriaProducto)
        bundle.putString("titulo", categItem.nombreCategoria)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}