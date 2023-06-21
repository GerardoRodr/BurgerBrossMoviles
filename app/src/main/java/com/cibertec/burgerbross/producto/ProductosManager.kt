package com.cibertec.burgerbross.producto

//ESTE OBJETO PERSISTIRA EN LAS ACTIVITYS DONDE LO LLAMEMOS
/*Por eso es importantisimo tenerlo, ya que en este tendremos el "carrito de compras"
  donde se almacenaran todos los productos que se ingrese*/
object ProductosManager {
    private val productosList = mutableListOf<Producto>()

    fun getProductosList(): List<Producto> {
        return productosList
    }

    fun addProducto(producto: Producto) {
        productosList.add(producto)
    }

    fun eliminarPrePedido() {
        productosList.clear()
    }

    fun delProducto(producto: Producto) {
        productosList.remove(producto)
    }

    fun updateProducto(producto: Producto) {
        /*Busca el índice del primer elemento que cumpla con una condición específica.
        En este caso, la condición es que el idProducto del elemento coincida con el idProducto
        del producto que se desea actualizar.*/
        val index = productosList.indexOfFirst { it.idProducto == producto.idProducto }
        if (index != -1) {
            productosList[index] = producto
        }
    }
}