package com.cibertec.burgerbross.producto

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.burgerbross.R
import com.cibertec.burgerbross.categoria.CategoriaProducto
import com.cibertec.burgerbross.categoria.CategoriaProductoViewModel

class ProductosPredefinidos {


    companion object {
        fun initializeHamburguesas(activity: AppCompatActivity) {
            val prodViewModel = ViewModelProvider(activity)[ProductoViewModel::class.java]

            //INSERSION DE DATOS PREDEFINIDOS DENTRO DE LA BD
            prodViewModel.prod?.observe(activity) { prods ->
                if (prods.isEmpty()) {
                    val p1 = Producto("Hamburguesa Royal", 1, "Hamburguesa con huevo", 12, 6.00)
                    p1.img = R.drawable.hambroyal

                    val p2 = Producto("Hamburguesa Cheese", 1, "Hamburguesa con queso", 12, 6.50)
                    p2.img = R.drawable.hambcheese

                    val p3 = Producto("Hamburguesa Tocino", 1, "Hamburguesa con tocino", 12, 7.00)
                    p3.img = R.drawable.hambtocino

                    val p4 = Producto("Hamburguesa Pollo", 1, "Hamburguesa de pollo", 12, 7.00)
                    p4.img = R.drawable.hambpollo

                    prodViewModel.saveProductoWithCoroutines(p1)
                    prodViewModel.saveProductoWithCoroutines(p2)
                    prodViewModel.saveProductoWithCoroutines(p3)
                    prodViewModel.saveProductoWithCoroutines(p4)
                }
            }
            //------------------------------------------------------------------------------------------------
        }
    }

}