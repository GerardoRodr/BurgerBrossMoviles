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

        fun initializeBebidas(activity: AppCompatActivity) {
            val prodViewModel = ViewModelProvider(activity)[ProductoViewModel::class.java]

            //INSERSION DE DATOS PREDEFINIDOS DENTRO DE LA BD
            prodViewModel.prod?.observe(activity) { prods ->
                if (prods.isEmpty()) {
                    val p1 = Producto("Inka-Kola", 2, "600ml - Personal", 12, 4.00)
                    p1.img = R.drawable.inca600ml

                    val p2 = Producto("Coca-Cola", 2, "600ml - Personal", 12, 4.00)
                    p2.img = R.drawable.coca600ml

                    val p3 = Producto("Fanta", 2, "500ml - Personal", 12, 3.50)
                    p3.img = R.drawable.fanta500

                    val p4 = Producto("Inka-Cola", 2, "1L", 10, 7.00)
                    p4.img = R.drawable.inka1l

                    prodViewModel.saveProductoWithCoroutines(p1)
                    prodViewModel.saveProductoWithCoroutines(p2)
                    prodViewModel.saveProductoWithCoroutines(p3)
                    prodViewModel.saveProductoWithCoroutines(p4)
                }
            }
            //------------------------------------------------------------------------------------------------
        }

        fun initializeComplementos(activity: AppCompatActivity) {
            val prodViewModel = ViewModelProvider(activity)[ProductoViewModel::class.java]

            //INSERSION DE DATOS PREDEFINIDOS DENTRO DE LA BD
            prodViewModel.prod?.observe(activity) { prods ->
                if (prods.isEmpty()) {
                    val p1 = Producto("Huevo frito", 3, "Huevo frito como complemento", 12, 1.50)
                    p1.img = R.drawable.huevo_frito

                    val p2 = Producto("Papas fritas", 3, "Papas fritas como complemento", 12, 3.50)
                    p2.img = R.drawable.papas_fritas

                    val p3 = Producto("Hot dog", 3, "Hot dog como complemento", 12, 1.50)
                    p3.img = R.drawable.hot_dog

                    val p4 = Producto("Tocino", 3, "Tocino como complemento", 12, 4.00)
                    p4.img = R.drawable.tocino

                    prodViewModel.saveProductoWithCoroutines(p1)
                    prodViewModel.saveProductoWithCoroutines(p2)
                    prodViewModel.saveProductoWithCoroutines(p3)
                    prodViewModel.saveProductoWithCoroutines(p4)
                }
            }
            //------------------------------------------------------------------------------------------------
        }

        fun initializeAdicionales(activity: AppCompatActivity) {
            val prodViewModel = ViewModelProvider(activity)[ProductoViewModel::class.java]

            //INSERSION DE DATOS PREDEFINIDOS DENTRO DE LA BD
            prodViewModel.prod?.observe(activity) { prods ->
                if (prods.isEmpty()) {
                    val p1 = Producto("SalchiPapa", 4, "Plato de papas y salchichas fritas", 12, 6.00)
                    p1.img = R.drawable.salchipapa

                    val p2 = Producto("SalchiPollo", 4, "Plato de papas y pollo frito", 12, 6.50)
                    p2.img = R.drawable.salchipollo

                    val p3 = Producto("Choripan", 4, "Pan con chorizo", 12, 5.00)
                    p3.img = R.drawable.choripan

                    val p4 = Producto("Empanada(Pollo)", 4, "Empanada con relleno de pollo", 12, 2.00)
                    p4.img = R.drawable.empanada

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