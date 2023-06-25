package com.cibertec.burgerbross

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.cibertec.burgerbross.categoria.CategoriaProducto
import com.cibertec.burgerbross.categoria.CategoriaProductoViewModel
import com.cibertec.burgerbross.producto.ProductoViewModel
import com.cibertec.burgerbross.producto.ProductosPredefinidos
import com.cibertec.burgerbross.usuario.UsuarioManager

class InicioActivity : AppCompatActivity() {
    private lateinit var catViewModel: CategoriaProductoViewModel
    private lateinit var prodViewModel: ProductoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val actionBar = supportActionBar
        actionBar?.hide()

        catViewModel = run {
            ViewModelProvider(this)[CategoriaProductoViewModel::class.java]
        }

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

        //INSERSION DE DATOS PREDEFINIDOS DENTRO DE LA BD
        catViewModel.cat?.observe(this) { cats ->
            if (cats.isEmpty()) {
                val cat1 = CategoriaProducto("Hamburguesas")
                cat1.iconoCategoria = R.drawable.hamburguesas_icon

                val cat2 = CategoriaProducto("Bebidas")
                cat2.iconoCategoria = R.drawable.bebidas_icon

                val cat3 = CategoriaProducto("Complementos")
                cat3.iconoCategoria = R.drawable.complementos_icon

                val cat4 = CategoriaProducto("Adicionales")
                cat4.iconoCategoria = R.drawable.adicionales_icon

                catViewModel.saveCategoriaProdsWithCoroutines(cat1)
                catViewModel.saveCategoriaProdsWithCoroutines(cat2)
                catViewModel.saveCategoriaProdsWithCoroutines(cat3)
                catViewModel.saveCategoriaProdsWithCoroutines(cat4)
            }
        }
        //------------------------------------------------------------------------------------------------

        val txtEmail = findViewById<TextView>(R.id.emailUsuarioInicio)
        txtEmail.text = UsuarioManager.obtenerEmail()

        //Cuando se presione el icono de perfil:
        val btnPerfil = findViewById<ImageButton>(R.id.btn_perfil)
        btnPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        //Cuando se presione el cardView de IngresarPedido
        val cvIngresarPedido = findViewById<CardView>(R.id.card_ingresar_pedido)
        cvIngresarPedido.setOnClickListener {
            startActivity(Intent(this, IngresarPedidoActivity::class.java))
        }

        //Cuando se presione el cardView de VerPedidos
        val cvVerPedidos = findViewById<CardView>(R.id.card_ver_pedidos)
        cvVerPedidos.setOnClickListener {
            startActivity(Intent(this, PedidosActivity::class.java))
        }

        //Cuando se presione el cardView de verStock
        val cvVerStock = findViewById<CardView>(R.id.card_ver_stock)
        cvVerStock.setOnClickListener {
            startActivity(Intent(this, StockActivity::class.java))
        }
    }
}