package com.cibertec.burgerbross

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.detallepedido.DetallePedidoActivity
import com.cibertec.burgerbross.pedido.PedidoAdapter
import com.cibertec.burgerbross.pedido.PedidoFirestore
import com.cibertec.burgerbross.pedido.PedidoViewModel

class PedidosActivity: AppCompatActivity(), PedidoAdapter.ItemClickListener {

    private lateinit var pedViewModel: PedidoViewModel
    val adapter = PedidoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        val actionBar = supportActionBar
        actionBar?.hide()

        pedViewModel = run {
            ViewModelProvider(this)[PedidoViewModel::class.java]
        }

        val recyclerPedidos = findViewById<RecyclerView>(R.id.recyclerPedidos)

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
        val intent = Intent(this, DetallePedidoActivity::class.java)
        val bundle = Bundle()

        bundle.putString("documentId", pedido.documentId)
        bundle.putString("nombreCliente", pedido.nombreCliente)
        bundle.putDouble("total", pedido.totalPedido)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun setOnCheckedChangeListener(pedido: PedidoFirestore, estado: Boolean) {

        pedViewModel.actualizarEstadoPedidoFirestore(pedido.documentId!!, estado)

        println(pedido.nombreCliente)
        println(estado)
    }

    override fun setOnBtnEliminarClick(pedido: PedidoFirestore) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar pedido")
        builder.setMessage("¿Estás seguro de que deseas eliminar el pedido?")
        builder.setPositiveButton("Sí") { dialog, which ->
            pedViewModel.borrarDocumentoFirestore(pedido.documentId!!) {
                pedViewModel.getPedidosFirestore()
                Toast.makeText(this, "Se ha eliminado el pedido de ${pedido.nombreCliente}", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("No") { dialog, which ->
            println("NO SE ELIMINO EL PEDIDO")
        }
        builder.show()
    }
}