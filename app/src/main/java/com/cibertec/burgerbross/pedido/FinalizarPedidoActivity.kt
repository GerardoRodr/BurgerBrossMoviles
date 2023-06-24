package com.cibertec.burgerbross.pedido

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.InicioActivity
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.producto.Producto
import com.cibertec.burgerbross.producto.ProductosManager
import com.google.firebase.Timestamp

class FinalizarPedidoActivity: AppCompatActivity() {

    var listaDetallePedido = ProductosManager.getProductosList()

    private lateinit var pedidoViewModel: PedidoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalizar_pedido)

        val actionBar = supportActionBar
        actionBar?.hide()

        val loadingAnimation = findViewById<RelativeLayout>(R.id.loadingAnimation)

        pedidoViewModel = ViewModelProvider(this)[PedidoViewModel::class.java]

        var adapter = DetallePedidoAdapter()
        adapter.setDetallePedidos(listaDetallePedido)

        //DEFINIENDO EL TOTAL Y AÑADIENDOLO A LA VISTA
        var total = 0.0
        for (prod in listaDetallePedido) {
            total += prod.cantProd * prod.precioProducto
        }
        val txtTotal = findViewById<TextView>(R.id.totalFinalizarPed)
        txtTotal.text = total.toString()
        //---------------------------------------------------------------

        val recyclerDetallePedido = findViewById<RecyclerView>(R.id.recyclerDetallePedidoFinalizar)
        recyclerDetallePedido.adapter = adapter
        recyclerDetallePedido.layoutManager = LinearLayoutManager(applicationContext)

        val txtEditNombreCliente = findViewById<EditText>(R.id.txtEditNombreCliente)

        val btnContinuar = findViewById<ImageButton>(R.id.btn_adelante_finalizar_pedido)
        btnContinuar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmar pedido")
            builder.setMessage("¿Estás seguro de que deseas concluir el pedido?")
            builder.setPositiveButton("Sí") { dialog, which ->

                //VALIDACION DEL NOMBRE DEL CLIENTE
                if (txtEditNombreCliente.text.isEmpty()) {
                    Toast.makeText(this, "Debes ingresar el nombre del cliente.", Toast.LENGTH_SHORT).show()
                } else {

                    loadingAnimation.visibility = View.VISIBLE

                    pedidoViewModel.registrarPedidoFirestore(crearPedido(listaDetallePedido,
                        txtEditNombreCliente.text.toString()), listaDetallePedido) {
                        //CUANDO SE TERMINE DE EJECUTAR EL REGISTRAR PEDIDO SE EJECUTA ESTO:
                        ProductosManager.eliminarPrePedido()
                        loadingAnimation.visibility = View.GONE
                        startActivity(Intent(this, InicioActivity::class.java))
                    }
                }
            }
            builder.setNegativeButton("No") { dialog, which ->
                println("NO SE CONFIRMO EL PEDIDO")
            }
            builder.show()
        }
    }

    private fun crearPedido(detPed: List<Producto>, nCliente: String): PedidoFirestore {
        var totalPedido = 0.0

        for (producto in detPed) {
            totalPedido += producto.cantProd * producto.precioProducto
        }
        return PedidoFirestore(false, Timestamp.now(), nCliente, totalPedido)
    }
}