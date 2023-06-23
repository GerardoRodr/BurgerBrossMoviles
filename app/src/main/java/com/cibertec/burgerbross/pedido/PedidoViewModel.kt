package com.cibertec.burgerbross.pedido

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cibertec.burgerbross.detallepedido.DetallePedidoFirestore
import com.cibertec.burgerbross.producto.Producto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException

class PedidoViewModel: ViewModel() {

    private lateinit var firestore: FirebaseFirestore
    val listPedidoMutable = MutableLiveData<List<PedidoFirestore>>()
    val listDetallePedidoMutable = MutableLiveData<List<DetallePedidoFirestore>>()

    //FUNCION PARA OBTENER LOS PEDIDOS DESDE FIRESTORE
    fun getPedidosFirestore() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("pedidos").get().addOnSuccessListener { documentList ->
            val listPedidos = arrayListOf<PedidoFirestore>()

            for (document in documentList) {
                val estadoPedido = document.getBoolean("estado_pedido")
                val fechaPedido = document.getTimestamp("fecha_pedido")
                val nombreCliente = document.getString("nombre_cliente")
                val totalPedido = document.getDouble("total_pedido")

                if (estadoPedido != null && fechaPedido != null && nombreCliente != null && totalPedido != null) {
                    var item = PedidoFirestore(estadoPedido, fechaPedido, nombreCliente, totalPedido)
                    item.documentId = document.id
                    listPedidos.add(item)
                }
            }
            listPedidoMutable.value = listPedidos
        }
    }

    fun registrarPedidoFirestore(pedidoFirestore: PedidoFirestore, detPed: List<Producto>,
        callback: () -> Unit){
        firestore = FirebaseFirestore.getInstance()
        val pedidosCollection = firestore.collection("pedidos")

        // Crear un nuevo documento en la colección "pedidos"
        val nuevoPedido = pedidosCollection.document()

        // Crear un mapa con los datos del pedido
        val datosPedido = hashMapOf(
            "estado_pedido" to pedidoFirestore.estadoPedido,
            "fecha_pedido" to pedidoFirestore.fechaPedido,
            "nombre_cliente" to pedidoFirestore.nombreCliente,
            "total_pedido" to pedidoFirestore.totalPedido
        )

        nuevoPedido.set(datosPedido)
            .addOnSuccessListener {
                println("Pedido registrado correctamente en Firestore")

                val detallePedidoCollection = nuevoPedido.collection("detalle_pedido")

                for(producto in detPed) {
                    val nuevoDetalle = detallePedidoCollection.document()
                    val datosDetalle = hashMapOf(
                        "idProducto" to producto.idProducto,
                        "cantidad" to producto.cantProd,
                        "precio_producto" to producto.precioProducto,
                        "producto" to producto.nombreProducto,
                        "subtotal" to producto.cantProd * producto.precioProducto
                    )

                    nuevoDetalle.set(datosDetalle).addOnSuccessListener {
                        println(producto.nombreProducto + " CANTIDAD: " + producto.cantProd)
                        //CUANDO SE LLEGUE A GUARDAR LOS DATOS DE detallePedido EN FIRESTORE RECIEN TERMINA LA FUNCION
                        callback()
                    }.addOnFailureListener { e->
                        println("Error al registrar el detallePedido en Firestore: $e")
                    }
                }
            }
            .addOnFailureListener { e ->
                // Ocurrió un error al intentar registrar el pedido en Firestore
                println("Error al registrar el pedido en Firestore: $e")
            }
    }

    fun getDetallePedidoById(documentId: String, nombreCliente: String) {
        firestore = FirebaseFirestore.getInstance()
        val pedidosCollectionRef = firestore.collection("pedidos")

        val detallePedidoCollectionRef = pedidosCollectionRef.document(documentId).collection("detalle_pedido")

        detallePedidoCollectionRef.get().addOnSuccessListener { documentList ->
            val listProductos = arrayListOf<DetallePedidoFirestore>()

            for (document in documentList) {
                val cantidad = document.getLong("cantidad")?.toInt()
                val idProducto = document.getLong("idProducto")?.toInt()
                val precioProducto = document.getDouble("precio_producto")
                val producto = document.getString("producto")
                val subtotal = document.getDouble("subtotal")

                if (idProducto != null && cantidad != null && precioProducto != null && producto != null && subtotal != null) {
                    // Crea un objeto DetallePedido con los datos obtenidos
                    var detallePedido = DetallePedidoFirestore(cantidad, idProducto, precioProducto, producto, subtotal)
                    detallePedido.nombreCliente = nombreCliente
                    listProductos.add(detallePedido)
                }
            }
            listDetallePedidoMutable.value = listProductos
        }
    }
}