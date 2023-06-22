package com.cibertec.burgerbross.pedido

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        val txtEditNombreCliente = findViewById<EditText>(R.id.txtEditNombreCliente)

        val btnContinuar = findViewById<ImageButton>(R.id.btn_adelante_finalizar_pedido)
        btnContinuar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Estás seguro de que deseas continuar?")
            builder.setPositiveButton("Sí") { dialog, which ->

                //VALIDACION DEL NOMBRE DEL CLIENTE
                if (txtEditNombreCliente.text.isEmpty()) {
                    Toast.makeText(this, "Debes ingresar el nombre del cliente.", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("No") { dialog, which ->
                println("NO SE CONFIRMO EL PEDIDO")
            }
            builder.show()
        }
    }
}