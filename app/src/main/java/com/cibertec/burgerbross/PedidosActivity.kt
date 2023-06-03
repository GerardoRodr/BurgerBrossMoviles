package com.cibertec.burgerbross

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PedidosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        val actionBar = supportActionBar
        actionBar?.hide()
    }
}