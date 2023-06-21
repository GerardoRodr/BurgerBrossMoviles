package com.cibertec.burgerbross.categoria

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoriaProductoViewModel (application: Application): AndroidViewModel(application) {
    private val repository = CategoriaProductoRepository(application)

    val cat = repository.getCategorias()

    fun saveCategoriaProdsWithCoroutines(cat: CategoriaProducto) {
        viewModelScope.launch {
            repository.insertCategoriaWithCoroutines(cat)
        }
    }
}