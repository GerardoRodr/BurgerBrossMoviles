package com.cibertec.burgerbross.producto

import android.app.Application
import androidx.lifecycle.LiveData
import com.cibertec.burgerbross.BurgerBrossDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductoRepository (application: Application) {
    private val productoDao: ProductoDao? =
        BurgerBrossDatabase.getInstance(application)?.productoDao()

    suspend fun insertProductoWithCoroutines(producto: Producto) {
        processInsertProducto(producto)
    }

    private suspend fun processInsertProducto(producto: Producto) {
        withContext(Dispatchers.Default) {
            productoDao?.insert(producto)
        }
    }

    fun getProductos(): LiveData<List<Producto>>? {
        return productoDao?.list()
    }

    fun getProductosByCategoria(idCat: Int): LiveData<List<Producto>>? {
        return productoDao?.listByIdCategoria(idCat)
    }

    fun getProductoById(id: Int): Producto? {
        return productoDao?.prodById(id)
    }
}