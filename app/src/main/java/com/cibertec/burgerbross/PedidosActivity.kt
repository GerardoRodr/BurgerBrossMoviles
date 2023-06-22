package com.cibertec.burgerbross

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.pedido.PedidoAdapter
import com.cibertec.burgerbross.pedido.PedidoFirestore
import com.cibertec.burgerbross.pedido.PedidoViewModel

class PedidosActivity: AppCompatActivity(), PedidoAdapter.ItemClickListener {

    private lateinit var pedViewModel: PedidoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        val actionBar = supportActionBar
        actionBar?.hide()

        pedViewModel = run {
            ViewModelProvider(this)[PedidoViewModel::class.java]
        }

        val recyclerPedidos = findViewById<RecyclerView>(R.id.recyclerPedidos)

        val adapter = PedidoAdapter(this)

        recyclerPedidos.adapter = adapter
        recyclerPedidos.layoutManager = LinearLayoutManager(applicationContext)

        pedViewModel.getPedidosFirestore()

        pedViewModel.listPedidoMutable.observe(this) {listPedidoMutable ->
            if(listPedidoMutable.isNotEmpty()) {
                adapter.setPedidos(listPedidoMutable)
            }
        }

    }

    override fun onItemClick(pedido: PedidoFirestore) {
        TODO("Not yet implemented")
    }

    override fun setOnCheckedChangeListener(pedido: PedidoFirestore, estado: Boolean) {
        println(pedido.nombreCliente)
        println(estado)
    }
}