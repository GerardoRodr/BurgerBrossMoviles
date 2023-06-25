package com.cibertec.burgerbross

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.categoria.CategoriaProducto
import com.cibertec.burgerbross.categoria.CategoriaProductoAdapter
import com.cibertec.burgerbross.categoria.CategoriaProductoViewModel
import com.cibertec.burgerbross.pedido.IngresarDetallePedidoActivity
import com.cibertec.burgerbross.producto.VerStockActivity

class StockActivity: AppCompatActivity(), CategoriaProductoAdapter.ItemClickListener {
    private lateinit var catViewModel: CategoriaProductoViewModel
    lateinit var listaCategorias: List<CategoriaProducto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        val actionBar = supportActionBar
        actionBar?.hide()

        catViewModel = run {
            ViewModelProvider(this)[CategoriaProductoViewModel::class.java]
        }
        val recyclerCategoria = findViewById<RecyclerView>(R.id.recyclerCategoriasStock)

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
        val intent = Intent(this, VerStockActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("categoryId", categItem.idCategoriaProducto)
        bundle.putString("titulo", categItem.nombreCategoria)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}