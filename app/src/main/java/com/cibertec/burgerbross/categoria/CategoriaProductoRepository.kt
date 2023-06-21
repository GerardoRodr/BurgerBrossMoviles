package com.cibertec.burgerbross.categoria

import android.app.Application
import androidx.lifecycle.LiveData
import com.cibertec.burgerbross.BurgerBrossDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriaProductoRepository (application: Application) {
    private val categoriaDao: CategoriaProductoDao? =
        BurgerBrossDatabase.getInstance(application)?.categoriaProdDao()

    suspend fun insertCategoriaWithCoroutines(categoria: CategoriaProducto) {
        processInsertCategoria(categoria)
    }

    private suspend fun processInsertCategoria(categoria: CategoriaProducto) {
        withContext(Dispatchers.Default) {
            categoriaDao?.insert(categoria)
        }
    }

    fun getCategorias(): LiveData<List<CategoriaProducto>>? {
        return categoriaDao?.list()
    }
}
