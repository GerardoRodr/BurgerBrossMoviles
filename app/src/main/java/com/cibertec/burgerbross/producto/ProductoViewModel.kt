package com.cibertec.burgerbross.producto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ProductoViewModel: ViewModel() {

    private lateinit var firestore: FirebaseFirestore

    val listProductoMutable = MutableLiveData<List<ProductoFirestore>>()

    fun getProductosFirestore() {
        firestore = FirebaseFirestore.getInstance()

        firestore.collection("productos").get()
            .addOnSuccessListener { documenList ->
                val listProductos = arrayListOf<ProductoFirestore>()
                for (document in documenList) {
                    val nombre_producto = document.getString("nombre_producto")
                    val desc_producto = document.getString("desc_producto")
                    val categoria_producto = document.getString("categoria_producto")
                    val precio_producto = document.getDouble("precio_producto")
                    val stock = document.getLong("stock")?.toInt()

                    if (nombre_producto != null && desc_producto != null && categoria_producto != null && precio_producto != null && stock != null) {
                        val item = ProductoFirestore(
                            nombre_producto,
                            desc_producto,
                            categoria_producto,
                            precio_producto,
                            stock,
                            img = 1
                        )
                        listProductos.add(item)
                    }
                }
                listProductoMutable.value = listProductos
            }

    }


}