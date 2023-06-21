package com.cibertec.burgerbross

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.categoria.Categoria
import com.cibertec.burgerbross.categoria.CategoriaAdapter

class IngresarPedidoActivity : AppCompatActivity() {


    val listaCategoria = listOf(
        Categoria("Hamburguesas", R.drawable.hamburguesas_icon),
        Categoria("Bebidas", R.drawable.bebidas_icon),
        Categoria("Complementos", R.drawable.complementos_icon),
        Categoria("Adicionales", R.drawable.adicionales_icon)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_pedido)

        val actionBar = supportActionBar
        actionBar?.hide()

        val recyclerCategoria = findViewById<RecyclerView>(R.id.recyclerCategoriasIngresarPedido)

        recyclerCategoria.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = CategoriaAdapter(listaCategoria)
        }
    }
}