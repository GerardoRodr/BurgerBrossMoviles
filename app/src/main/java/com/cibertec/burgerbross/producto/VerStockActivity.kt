package com.cibertec.burgerbross.producto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.burgerbross.R
import org.w3c.dom.Text

class VerStockActivity: AppCompatActivity(), StockAdapter.ItemClickListener {
    private lateinit var prodViewModel: ProductoViewModel
    private lateinit var stockAdapter: StockAdapter
    lateinit var listaProductos: List<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_stock)

        val actionBar = supportActionBar
        actionBar?.hide()

        val receivedBundle = intent.extras
        val receivedCategoryId = receivedBundle?.getInt("categoryId")
        val receivedTitulo = receivedBundle?.getString("titulo")

        val tituloActivity = findViewById<TextView>(R.id.txtVerStockCategoria)
        tituloActivity.text = receivedTitulo

        prodViewModel = run {
            ViewModelProvider(this)[ProductoViewModel::class.java]
        }

        val recyclerProds = findViewById<RecyclerView>(R.id.recyclerProdsStock)

        val adapter = StockAdapter(this)

        recyclerProds.adapter = adapter
        recyclerProds.layoutManager = LinearLayoutManager(applicationContext)

        stockAdapter = adapter

        if (receivedCategoryId != null) {
            prodViewModel.prodByIdCat(receivedCategoryId)?.observe(this) {prods ->
                if (prods.isNotEmpty()) {
                    recyclerProds.visibility = View.VISIBLE

                    listaProductos = prods

                    prods?.let {
                        adapter.setProductos(it)
                    }
                } else {
                    recyclerProds.visibility = View.GONE
                }
            }
        }
    }

    override fun onBtnModificarStockClick(p: Producto) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_modificar_stock, null)

        val mBuilder = AlertDialog.Builder(this).setView(mDialogView)

        val mAlertDialog = mBuilder.show()

        val edtStock = mDialogView.findViewById<EditText>(R.id.edtStock)
        val btnEditar = mDialogView.findViewById<Button>(R.id.btnEditarStock)
        val nombreProd = mDialogView.findViewById<TextView>(R.id.nombreProdDialogStock)

        //LE DAMOS AL EDITTEXT EL VALOR ACTUAL DEL STOCK
        edtStock.setText(p.stock.toString())
        nombreProd.text = p.nombreProducto

        btnEditar.setOnClickListener {
            mAlertDialog.dismiss()

            val stock = Integer.parseInt(edtStock.text.toString())

            var prodVm = Producto(p.nombreProducto, p.idCategoriaProducto, p.descripcion, stock, p.precioProducto)
            prodVm.idProducto = p.idProducto
            prodVm.img = p.img

            prodViewModel.updateProdWithCoroutines(prodVm)
        }
    }
}