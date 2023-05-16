package com.cibertec.burguerbross.categorias


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R

class CatogoriaActivity : AppCompatActivity() {

    val listaCategoria = listOf(
        Categoria("Hamburguesas", R.drawable.hamburguesa),
        Categoria("Bebidas",R.drawable.soda),
        Categoria("Complementos",R.drawable.complemento),
        Categoria("Adicionales",R.drawable.adicionales)
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_pedido)


        val recyclerCategoria = findViewById<RecyclerView>(R.id.recyclerCategorias)
        recyclerCategoria.apply {

            layoutManager = GridLayoutManager(context,2)

            adapter = CategoriaAdapter(listaCategoria)
            adapter = CategoriaAdapter(listaCategoria)

        }



    }


}