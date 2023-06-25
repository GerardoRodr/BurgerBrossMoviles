package com.cibertec.burgerbross.producto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductoViewModel (application: Application): AndroidViewModel(application) {
    private val repository = ProductoRepository(application)

    val prod = repository.getProductos()

    fun updateProdWithCoroutines(prod: Producto) {
        viewModelScope.launch {
            repository.updateProductoWithCoroutines(prod)
        }
    }

    fun prodByIdCat(idCat: Int): LiveData<List<Producto>>? {
        return repository.getProductosByCategoria(idCat);
    }

    fun prodById(id: Int): Producto? {
        return repository.getProductoById(id)
    }

    fun saveProductoWithCoroutines(prod: Producto) {
        viewModelScope.launch {
            repository.insertProductoWithCoroutines(prod)
        }
    }
}