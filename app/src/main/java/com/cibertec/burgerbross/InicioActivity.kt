package com.cibertec.burgerbross

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val actionBar = supportActionBar
        actionBar?.hide()

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
    }
}