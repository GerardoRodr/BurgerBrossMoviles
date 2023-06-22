package com.cibertec.burgerbross.pedido

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.producto.ProductosManager

class FinalizarPedidoActivity: AppCompatActivity() {

    var listaDetallePedido = ProductosManager.getProductosList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalizar_pedido)

        val actionBar = supportActionBar
        actionBar?.hide()

        var adapter = DetallePedidoAdapter()
        adapter.setDetallePedidos(listaDetallePedido)

        val recyclerDetallePedido = findViewById<RecyclerView>(R.id.recyclerDetallePedidoFinalizar)
        recyclerDetallePedido.adapter = adapter
        recyclerDetallePedido.layoutManager = LinearLayoutManager(applicationContext)
    }
}