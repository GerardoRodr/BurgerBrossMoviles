package com.cibertec.burgerbross.detallepedido

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.pedido.DetallePedidoAdapter
import com.cibertec.burgerbross.pedido.PedidoViewModel

class DetallePedidoActivity: AppCompatActivity() {

    private lateinit var pedidoViewModel: PedidoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido)

        val actionBar = supportActionBar
        actionBar?.hide()

        val txtNombreCliente = findViewById<TextView>(R.id.nombreClienteDetPed)
        val txtTotal = findViewById<TextView>(R.id.totalDetallePed)

        val receivedBundle = intent.extras
        var receivedDocumentId = ""
        var nombreCliente = ""
        var total = 0.0

        if (receivedBundle != null) {
            receivedDocumentId = receivedBundle.getString("documentId")!!

            nombreCliente = receivedBundle.getString("nombreCliente")!!
            txtNombreCliente.text = nombreCliente

            total = receivedBundle.getDouble("total");
            txtTotal.text = total.toString()
        }



        pedidoViewModel = run {
            ViewModelProvider(this)[PedidoViewModel::class.java]
        }

        val recyclerDetallePedido = findViewById<RecyclerView>(R.id.recyclerDetallePedido)

        val adapter = DetallePedidoFirestoreAdapter()

        pedidoViewModel.getDetallePedidoById(receivedDocumentId, nombreCliente)

        pedidoViewModel.listDetallePedidoMutable.observe(this) { list ->
            if(list.isNotEmpty()) {
                adapter.setDetallePedidos(list)
            }
        }

        recyclerDetallePedido.adapter = adapter
        recyclerDetallePedido.layoutManager = LinearLayoutManager(applicationContext)
    }
}